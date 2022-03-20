
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

// On my honor:

//

// - I have not used source code obtained from another student,

// or any other unauthorized source, either modified or

// unmodified.

//

// - All source code and documentation used in my program is

// either my original work, or was derived by me from the

// source code published in the textbook for this course.

//

// - I have not discussed coding details about this project with

// anyone other than my partner (in the case of a joint

// submission), instructor, ACM/UPE tutors or the TAs assigned

// to this course. I understand that I may discuss the concepts

// of this program with other students, and that another student

// may help me debug my program so long as neither of us writes

// anything during the discussion or modifies any computer file

// during the discussion. I have violated neither the spirit nor

// letter of this restriction.

// Weiting Li
/**
 * This is the main class
 * 
 * @version 01/23/2022
 * @author Administrator
 */
public class Externalsort {
    /**
     * Main method
     * 
     * @param args
     *            string
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // GenFile file1 = new GenFile();

        // file1.reversed(args);
        // file1.sorted(args);
        // file1.random(args);
        
        if (args.length != 1) {
            System.out.println("wrong input");
            throw new IllegalArgumentException();
        }

        try {

            RandomAccessFile raf = new RandomAccessFile(args[0], "r");

            Process reader = new Process(raf);

        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * cleanser
     * 
     * @param str
     *            string
     * @throws IOException
     */
    public static void cleanFile(String str) throws IOException {
        FileWriter fw = new FileWriter(str, false);
        fw.close();
    }

}
