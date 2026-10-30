import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class DataInputOutputStreamPractice {

    void streams() {
        byte[] byteArray = null;
        double d = 123_123_123_123_123_123.123_123;
        long l = 321_321_321_321_321_321L;
        int i = 1_876_876_876;
       
       var bO = new ByteArrayOutputStream(20);
       var dO = new DataOutputStream(bO);
            
       try {
            dO.writeDouble(d);
            dO.writeLong(l);
            dO.writeInt(i);
            byteArray = bO.toByteArray();
       } catch (IOException ioe) {
           System.out.println(ioe.toString());
       } finally {
           try {
               dO.close();
           } catch (IOException ioe) {} 
       }
        
        var bI = new ByteArrayInputStream(byteArray);
        var dI = new DataInputStream(bI);
        try {
            System.out.println(dI.readDouble());
            System.out.println(dI.readLong());
            System.out.println(dI.readInt());
        } catch(IOException ioe) {
            System.out.println(ioe);
        } finally {
            try {
                dI.close();
            } catch(IOException ioe) {}
        }
    }

    public static void main(String...args) {
       var d = new DataInputOutputStreamPractice();
       d.streams();
    }
}
