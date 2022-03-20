
/**
 * The purpose of this class is to parse a text file into its appropriate, line
 * by line commands for the format specified in the project spec.
 * 
 * @author CS Staff
 * 
 * @version 2021-08-23
 */
public class CommandProcessor {

    // the database object to manipulate the
    // commands that the command processor
    // feeds to it
    private Database data;
    
    private  String key = "";
    private  int x = 0;
    private  int y = 0;
    private  int w = 0;
    private  int h = 0;
    /**
     * The constructor for the command processor requires a database instance to
     * exist, so the only constructor takes a database class object to feed
     * commands to.
     * 
     * 
     */
    public CommandProcessor() {
        data = new Database();
    }


    /**
     * This method identifies keywords in the line and calls methods in the
     * database as required. Each line command will be specified by one of the
     * keywords to perform the actions within the database required. These
     * actions are performed on specified objects and include insert, remove,
     * regionsearch, search, intersections, and dump. If the command in the file line is not
     * one of these, an appropriate message will be written in the console. This
     * processor method is called for each line in the file. Note that the
     * methods called will themselves write to the console, this method does
     * not, only calling methods that do.
     * 
     * @param line
     *            a single line from the text file
     */
    public void processor(String line) {
        //line = line.replaceAll("\\s+", " ");
        
        
        String[] l = line.split("\\s+");
 
        String function = l[0];

        if(function.equals("insert")) {
            //access data
            key = l[1];
            x = Integer.parseInt(l[2]);
            y = Integer.parseInt(l[3]);
            w = Integer.parseInt(l[4]);
            h = Integer.parseInt(l[5]);
            KVPair<String,NewRectangle> pair; //kvpair to store
            NewRectangle t1 = new NewRectangle(x,y,w,h); //rectangle to store
            pair = new KVPair<String,NewRectangle>(key,t1); //set up the kvpair
            //operation
            data.insert(pair);
            //System.out.println("Success");
        }else if(function.equals("dump")){
            data.dump();
        }else if(function.equals("regionsearch")) {
            x = Integer.parseInt(l[1]);
            y = Integer.parseInt(l[2]);
            w = Integer.parseInt(l[3]);
            h = Integer.parseInt(l[4]);
            data.regionsearch(x, y, w, h);
        }else if(function.equals("remove")){
            if(l.length == 2) {
                key = l[1];
                data.remove(key);
                }
            else {
                x = Integer.parseInt(l[1]);
                y = Integer.parseInt(l[2]);
                w = Integer.parseInt(l[3]);
                h = Integer.parseInt(l[4]);
                
                data.remove(x, y, w, h);
            }
        }else if(function.equals("search")) {
            key = l[1];
            //System.out.println(key);
            data.search(key);
        }else if(function.equals("intersections")) {
            data.intersections();
        }
    }

}
