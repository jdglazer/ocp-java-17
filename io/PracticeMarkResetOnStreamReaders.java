import java.io.FileReader;
import java.io.BufferedReader;


public class PracticeMarkResetOnStreamReaders {
    
    /**
      Let's create an exception from trying to reset stream after read limit passes
    */
    void createResetReadLimitException() {
        try (var fileReader = new FileReader("person.xml");
             var bufferedReader = new BufferedReader(fileReader, 2)) {
             int c,
                 length = 0;

             while ((c = bufferedReader.read()) != -1) {
                 char ch = (char) c;
                 if (ch == '<') {
                     bufferedReader.mark(5);
                     length = 0;
                 } else if (ch == '>') {
                     bufferedReader.reset();
                     // should get an exception from here
                     bufferedReader.skip(length + 1); // need this to not get in unterminated loop
                 } else {
                     length++;
                 }
             }
        } catch (java.io.IOException ioe) {
            System.out.println(ioe.toString());
        }
    }
    
    /**
        A clunky, but functional xml parsing algorithm showing open tag names using mark and reset on BufferedReader
    */
    void showOpenXMLTagNamesEncountered() {
        try(var fileReader = new FileReader("person.xml");
            var bufferedReader = new BufferedReader(fileReader)) {
            
            int c,
                length = 0;
            while((c = bufferedReader.read()) != -1) {
                char ch = (char) c;
                if (ch == '<') {
                    length = 0;
                    bufferedReader.mark(32);
                } else if (ch == '>') {
                    bufferedReader.reset();
                    char[] chArray = new char[length];
                    bufferedReader.read(chArray); // we could check to make sure it read expected length, but we'll skip it
                    if (chArray[0] != '/') {
                        System.out.println(new String(chArray));
                    }
                    bufferedReader.skip(1);
                } else {
                    length++;
                }
            }   
        } catch (java.io.IOException ioe) {
           System.out.println(ioe.toString());
        }
    }

    /*
       Let's practice parsing with markSupported, mark, reset functions
    */
    public static void main(String...a) {
        var p = new PracticeMarkResetOnStreamReaders();
        p.createResetReadLimitException();
        p.showOpenXMLTagNamesEncountered();
    }
}
