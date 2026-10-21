import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class GenerateDailyChapterToReview {
    
    private static final String QUIT_MSG = "Press any key and then return/enter to quit: ";
    private static final Path PATH = Path.of("/home/josh/Desktop/.ocp_chapters_remaining.csv");
    
    public static void main(String [] args) throws IOException {
        List<String> lines = Files.readAllLines(PATH);
        int chapterCount = lines.size();
        int index = (int) Math.floor(Math.random()*chapterCount);
        String value = lines.remove(index);
        Files.write(PATH, lines, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        System.out.printf("Next Chapter: %s%nChapters remaining: %d%n", value, (int)(chapterCount - 1));
        System.out.println(QUIT_MSG);
        
        try(var scanner = new Scanner(System.in)) {
            scanner.next();
        }
    }

}
