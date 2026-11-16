import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class DemonstrateSerialization {

    public static class EmptyPojo {
    }

    public static class IAmNotSerializable {
         
         int aPrimitive;
         
         // EmptyPojo obj; // triggers NotSerializableException, uncomment it to see

         {
             System.out.println("IAmNotSerializable initializer block");
         }

         public IAmNotSerializable() {
             System.out.println("IAmNotSerializable default constructor");
         }
    }

    public static class IAmSerializable extends IAmNotSerializable implements Serializable {

        {
            System.out.println("IAmSerializable initializer block");
        }

        public IAmSerializable() {
            System.out.println("IAmSerializable default constructor");
        }
    }

    void serialize(Object obj, Path dataFile) {
        try (OutputStream os = Files.newOutputStream(dataFile, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
             oos.writeObject(obj);
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }

    Stream<Object> deserialize(Path dataFile) {
 
        Supplier<Object> supplier = new Supplier<Object>() {
             
             boolean done;
             ObjectInputStream ois;

             @Override
             public Object get() {
                 if (this.done) {
                     return null;
                 }

                 try {
                     if (ois == null) {
                         ois = new ObjectInputStream(Files.newInputStream(dataFile));
                     }
                     
                     return ois.readObject();
                 } catch(Exception ioe) {
                     ioe.printStackTrace();
                     if (ois != null) {
                         try { ois.close(); } catch(IOException ioe1){}
                     }
                     this.done = true;
                     return null;
                 }
             }
        };

        return Stream.generate(supplier).takeWhile(obj -> obj != null);
    }

    void demonstrateConstructorRun() {
        Path file = Path.of("serialized.dat");
        serialize(new IAmSerializable(), file);

        System.out.println("Starting deserialization now");
        var des = deserialize(file).findFirst().orElse(null);
        System.out.println("Deserialized object: " + des);
    }

    public static void main(String a[]) {
        var d = new DemonstrateSerialization();
        d.demonstrateConstructorRun();
    }
}
