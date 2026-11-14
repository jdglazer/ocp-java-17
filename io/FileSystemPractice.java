import java.nio.file.FileSystems;
import java.nio.file.FileSystem;
import java.nio.file.Path;

public class FileSystemPractice {

    public static void main(String [] args) {
        FileSystem fileSystem = FileSystems.getDefault();
        System.out.print("Here's the system file separator: '");
        System.out.println(fileSystem.getSeparator() + "'");
        
        System.out.println("Now let's list root directories: ");
        for (Path root : fileSystem.getRootDirectories()) {
            System.out.println(root);
        }
    }

}
