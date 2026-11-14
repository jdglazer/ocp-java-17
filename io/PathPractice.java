import java.nio.file.Path;

public class PathPractice {
    /*
      Path implements Iterable<Path>, Comparable<Path>

      Instance Methods: 
        int getNameCount()
        Path getName(int index)
        Path getParent()
        Path getRoot()
        Path subpath(int beginIndex, int endIndex) 
        Path toAbsolutePath()
        Path toRealPath(LinkOption... options)
        java.io.File toFile()
        Path normalize()
        Path relativize(Path other)
        Path resolve(Path other)

      Static method:
        Path of(String first, String... more)
    */

    private static final Path myRelativePath = Path.of(".", "test", "relative.txt");
    private static final Path myFilePath = Path.of("C:/", "Users", "josh", "Documents", "text.txt");
    private static final Path linuxAbsolutePath = Path.of("/var/www/html/test/../test.php");

    void iterateOverPath() {
        System.out.println("Path implements Iterable<Path>\n-------------------");
        for (Path pathPart : myFilePath) {
            System.out.println(pathPart);
        }
    }

    void showBasicFunctionsOfPath(Path p) {
        System.out.println(p.toString() + "\n" + "---------------------");
        System.out.println("getNameCount(): " + p.getNameCount());
        System.out.println("getName(1): " + p.getName(1).toString());
        System.out.println("getParent(): " + p.getParent());
        System.out.println("getRoot(): " + p.getRoot());
        System.out.println("subpath(1,3): " + p.subpath(1,3));
        System.out.println("toAbsolutePath(): " + p.toAbsolutePath());
        System.out.println("normalize(): " + p.normalize());
    }

    void relativizeAndResolve() {
        /**
           path1.relativize(path2); // translation is relativize path2 using path1 as to root path
        */
        Path p1 = Path.of("/home/josh/Documents/test.txt");
        Path p2 = Path.of("/home/josh");
        Path p3 = Path.of("Documents/test.txt");
        
        System.out.print("Relativize\n---------\nfrom path 1 to path 2: ");
        System.out.println(p1.relativize(p2).toString());
        System.out.println("path 2 to path 1: " + p2.relativize(p1).toString());

        System.out.print("Resolve:\n--------\n");
        System.out.println("from path2 to path3: " + p2.resolve(p3)); // /home/josh/Documents/test.txt
        System.out.println("from path3 to path2: " + p3.resolve(p2)); // /home/josh it can't resolve the given path against current so it just returns it as is, it's already a root path
    }
    
    void 

    public static void main(String...args) {
       var pathPractice = new PathPractice();
       pathPractice.iterateOverPath();
       pathPractice.showBasicFunctionsOfPath(myRelativePath);
       pathPractice.showBasicFunctionsOfPath(myFilePath);
       pathPractice.showBasicFunctionsOfPath(linuxAbsolutePath);
       pathPractice.relativizeAndResolve();
    }
}
