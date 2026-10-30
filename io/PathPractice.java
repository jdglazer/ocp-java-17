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

    public static void main(String...args) {
       var pathPractice = new PathPractice();
       pathPractice.iterateOverPath();
       pathPractice.showBasicFunctionsOfPath(myRelativePath);
       pathPractice.showBasicFunctionsOfPath(myFilePath);
       pathPractice.showBasicFunctionsOfPath(linuxAbsolutePath);
    }
}
