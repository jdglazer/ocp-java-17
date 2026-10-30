import java.io.File;
import java.io.IOException;

public class TestFileFunctions {
    
    static void listDir() {
        File dir = new File("test_dir");
        System.out.println("List directory contents - " + dir);
        for (var var : dir.list()) {
            System.out.println(var);
        }
    }

    public static void main(String...args) {
        File dne = new File("dne.xml");
        System.out.println("DNE:\n----");
        System.out.println("Name: " + dne.getName());
        System.out.println("Exists: " + dne.exists());
        System.out.println("Is File: " + dne.isFile());
        System.out.println("Is Directory: " + dne.isDirectory());
        
        try {
            System.out.println("New file created: " + dne.createNewFile());
        } catch (IOException ioe){}
        
        try {
            System.out.println("Create new file again result: " + dne.createNewFile());
        } catch (IOException ioe){}
        
        System.out.println("Absolute Path: " + dne.getAbsolutePath());
        
        try {
            System.out.println("Canonical Path: " + dne.getCanonicalPath());
        } catch (IOException ioe){}
        
        System.out.println("Is file hidden: " + dne.isHidden());
        System.out.println("Last modified: " + dne.lastModified());
        
        File dne1 = new File("dne1.xml");
        System.out.println("Rename to operation: " + dne.renameTo(dne1));
        System.out.println("File deletion result: " + dne1.delete());
        
        System.out.println("\nSystem name separator: " + File.separator);
        System.out.println("System path separator: " + File.pathSeparator + "\n");
        
        listDir();
    }
}
