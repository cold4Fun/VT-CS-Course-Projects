
/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 *
 * @author Jiayue Zhou
 *
 * @version 2021-09-25
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private Database data;

    /**
     * This method trim the command string to eliminate invalid characters and
     * duplicate blanks
     * eliminate the tabs, null, Line Feed, Horizontal Tab, Vertical Tab etc.
     * eliminate all the control characters which ASCII code is less than 32.
     *
     * @param
     * @return a String trimmed
     */
    private String trimInside(String str) {
        String ans = "";
        String temp = "";
        // eliminate invalid characters
        for (int i = 0; i < str.length(); ++i) {
            // Only keep the valid characters, eliminate tabs
            if (str.charAt(i) < 32) {
                temp += ' ';
            }
            else {
                temp += str.charAt(i);
            }
        }
        // eliminate duplicate blanks
        for (int i = 0; i < temp.length() - 1; ++i) {
            while (temp.charAt(i) == temp.charAt(i + 1) && temp.charAt(
                i) == ' ') {
                ++i;
            }
            ans += temp.charAt(i);
        }
        if (str.length() >= 1) {
            ans += temp.charAt(temp.length() - 1);
        }
        return ans;
    }


    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     *
     * the database object to manipulate
     */
    public CommandProcessor() {
        // @param dataIn
        data = new Database();
    }


    /**
     * This method identifies keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions within the database required. These
     * actions are performed on specified objects and include insert, remove,
     * regionsearch, search, intersections, and dump.
     * If the command in the file line is not
     * one of these, an appropriate message will be written in the console. This
     * processor method is called for each line in the file. Note that the
     * methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     *
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
        line += " ";
        int index = line.indexOf(" ");
        // find the command key word
        String keyWord = line.substring(0, index);
        // get the other of command
        String paras = line.substring(index);
        paras = paras.trim();
        // trimInside eliminate invalid characters and duplicate blanks
        String temp = trimInside(paras);
        String[] arr = temp.split(" ");
        if (keyWord.equals("insert")) {
            String name = arr[0];
            int x;
            int y;
            x = Integer.parseInt(arr[1]);
            y = Integer.parseInt(arr[2]);
            Point point = new Point(x, y, name);
            /*
             * KVPair<String, Rectangle> kvp =
             * new KVPair<String, Rectangle>(name, rectangle);
             */
            data.insert(point);
        }
        else if (keyWord.equals("remove")) {
            if (arr.length > 1) {
                int x;
                int y;
                x = Integer.parseInt(arr[0]);
                y = Integer.parseInt(arr[1]);
                data.remove(x, y);
            }
            else {
                String name = arr[0];
                data.remove(name);
            }
        }
        else if (keyWord.equals("regionsearch")) {
            int x;
            int y;
            int w;
            int h;
            x = Integer.parseInt(arr[0]);
            y = Integer.parseInt(arr[1]);
            w = Integer.parseInt(arr[2]);
            h = Integer.parseInt(arr[3]);
            data.regionsearch(x, y, w, h);
        }
        else if (keyWord.equals("duplicates")) {
            data.duplicates();
        }
        else if (keyWord.equals("search")) {
            String name = arr[0];
            data.search(name);
        }
        else if (keyWord.equals("dump")) {
            data.dump();
        }
        else {
            System.out.println("No Such a command.");
        }
    }

}
