import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here.
 * Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author zjy
 * @version 2021-10-22
 */
public class Point2 {
    // ~ Fields ................................................................

    // ~ Constructors ..........................................................

    // ~Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param args
     */
    public static void main(String[] args) {
        // the file object
        File file = null;

        // Attempts to open the file and scan through it
        try {
            // takes the first command line argument and opens that file
            file = new File(args[0]);
            // System.out.println(args[0]);
            // creates a scanner object
            Scanner scanner = new Scanner(file);
            // System.out.println("file");
            // creates a command processor object
            CommandProcessor cmdProc = new CommandProcessor();
            // reads the entire file and processes the commands
            // line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // determines if the file has more lines to read
                if (!line.trim().isEmpty()) {
                    cmdProc.processor(line.trim());
                }
            }
            // closes the scanner
            scanner.close();
        }
        // catches the exception if the file cannot be found
        // and outputs the correct information to the console
        catch (FileNotFoundException e) {
            System.out.println("Invalid file");
            e.printStackTrace();
        }

    }
}
