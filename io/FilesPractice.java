import java.io.InputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardCopyOption;

public class FilesPractice {
    
    void getBasicStreams() {
        Path inFile = Path.of("person.xml");
        try (InputStream is = Files.newInputStream(inFile, StandardOpenOption.READ);
            Reader reader = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(reader)) {
            System.out.println(bufferedReader.readLine());
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
   void copyMoveDelete() {
       Path path = Path.of("data/notes");
       try {
           Files.createDirectories(path);
       } catch(IOException ioe) {
           ioe.printStackTrace();
       }
       
       Path path1 = path.resolve(Path.of("today.txt"));
       try {
           Files.createFile(path1);
       } catch(IOException ioe) {
           System.out.println("'today.txt' already exists");
       }
       
       try {
           Files.writeString(path1, "These are my tasks");
       } catch(IOException ioe) {
           System.out.println("Error writing file");
       }
       
       Path backup = path.resolve(Path.of( "backup.txt"));
       try {
           Files.copy(path1, backup, StandardCopyOption.REPLACE_EXISTING);
       } catch(IOException ioe) {
           System.out.println("Error writing file");
       }
       
       try {
           Files.delete(path1);
           Files.delete(backup);
           Files.delete(path);
           Files.delete(Path.of("data"));
       } catch(IOException ioe) {
           ioe.printStackTrace();
       }
   }
    
    public static void main(String...args) {
         FilesPractice f = new FilesPractice();
         f.getBasicStreams();
         f.copyMoveDelete();
    }
}
