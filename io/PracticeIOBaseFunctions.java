import java.util.Arrays;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.function.Supplier;

public class PracticeIOBaseFunctions {
    /*
        Practice these:
        ---------------
    	abstract class InputStream

	The root class for all byte based input streams.

	Important Methods:
	int available()
	int read()
	int read(byte[] b)
	int read(byte[] b, int off, int len)
	byte[] readAllBytes()
	void reset()
	long skip(long n)
	void mark(int readlimit)
	boolean markSupported()
	void close()
	
	---------------------------
	
	abstract class OutputStream

	The root class for all byte based output streams.

	Important Methods:
	void write(byte[] b)
	void write(byte[] b, int off, int len)
	void write(int b)
	void flush()
	void close()
    */
    
    private byte[] verifyArray(byte[] currentByteArray, int currentIndex) {
        return (currentIndex == currentByteArray.length) 
                ? Arrays.copyOf(currentByteArray, currentByteArray.length*2 + 1)
                : currentByteArray;
    }

    void readBytesFromFile(Supplier<byte[]> reader, String functionName) {
        System.out.print("----\n" + functionName + " results:\n------------------------------------\n");
        Long start = System.currentTimeMillis();
        byte[] bytesInFile = reader.get();
        System.out.println("Read took " + (System.currentTimeMillis() - start) + " millisecond(s)");
        System.out.println("Byte length read: " + bytesInFile.length);
    }
    
    byte[] readBytesFromFileOneByOne() {
        // no try-with-resources for the sake of manually exercising close functions
        int index = 0;
        byte[] bytesInFile = new byte[0];
        try {
            var fileInputStream = new FileInputStream("test.dat");
            int next = -1;
            while((next = fileInputStream.read()) != -1) {
                bytesInFile = verifyArray(bytesInFile, index);
                bytesInFile[index] = (byte) next;
                index++;
            }
            fileInputStream.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        
        return Arrays.copyOf(bytesInFile, index); // index is last successful index read + 1 which is length
    }
    
    byte[] readBytesFromFileInChunks() {
        int totalBytesRead = 0;
        byte[] bytesInFile = new byte[1024];
        
        try {
            var fileInputStream = new FileInputStream("test.dat");
            int bytesRead = 0;
            while ((bytesRead = fileInputStream.read(bytesInFile, totalBytesRead, 1024)) >= 0) {
                totalBytesRead += bytesRead; 
                bytesInFile = Arrays.copyOf(bytesInFile, totalBytesRead + 1024);
            }
            fileInputStream.close();
            
        } catch(IOException ioe) {
            System.out.println(ioe);
        }
        return Arrays.copyOf(bytesInFile, totalBytesRead);
    }
    
    byte[] readAllBytesFromFile() {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream("test.dat");
            return fileInputStream.readAllBytes();
        } catch(IOException ioe) {
            System.out.println(ioe);
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch(IOException ioe){}
            }
        }
        return new byte[0];
    }

    public static void main(String...args) {
        var practiceIOBaseFunctions = new PracticeIOBaseFunctions();
        practiceIOBaseFunctions.readBytesFromFile(practiceIOBaseFunctions::readBytesFromFileOneByOne, "readBytesFromFileOneByOne()");
        practiceIOBaseFunctions.readBytesFromFile(practiceIOBaseFunctions::readBytesFromFileInChunks, "readBytesFromFileInChunks()");
        practiceIOBaseFunctions.readBytesFromFile(practiceIOBaseFunctions::readAllBytesFromFile, "readAllBytesFromFile()");
    }
}
