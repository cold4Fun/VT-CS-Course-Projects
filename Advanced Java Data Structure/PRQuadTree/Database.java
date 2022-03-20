import java.util.ArrayList;

/**
 * This class is responsible for interfacing between the command processor and
 * the SkipList. The responsibility of this class is to further interpret
 * variations of commands and do some error checking of those commands. This
 * class further interpreting the command means that the two types of remove
 * will be overloaded methods for if we are removing by name or by coordinates.
 * Many of these methods will simply call the appropriate version of the
 * SkipList method after some preparation.
 *
 * @author Jiayue Zhou
 *
 * @version 2021-09-25
 */
public class Database {

    // this is the SkipList object that we are using
    // a string for the name of the rectangle and then
    // a rectangle object, these are stored in a KVPair,
    // see the KVPair class for more information
    private SkipList<String, Point> list;
    private QuadTree tree;

    /**
     * The method is to judge whether two rectangles are intersected
     *
     * @param a
     *            Rectangle a
     * @param b
     *            Rectangle b
     *
     * @return a boolean true or false
     */
    /*
     * private boolean isIntersec(Rectangle a, Rectangle b) {
     * int x1 = a.x;
     * int y1 = a.y;
     * int x2 = a.x + a.width;
     * int y2 = a.y + a.height;
     * int x3 = b.x;
     * int y3 = b.y;
     * int x4 = b.x + b.width;
     * int y4 = b.y + b.height;
     * boolean xOverLap = !(x3 >= x2 || x1 >= x4);
     * boolean yOverLap = !(y3 >= y2 || y1 >= y4);
     * return xOverLap && yOverLap;
     * }
     */

    /**
     * The constructor for this class initializes a SkipList object with String
     * and Rectangle a its parameters.
     */
    public Database() {
        list = new SkipList<String, Point>();
        tree = new QuadTree();
    }


    /**
     * Inserts the KVPair in the SkipList if the rectangle has valid coordinates
     * and dimensions, that is that the coordinates are non-negative and that
     * the rectangle object has some area (not 0, 0, 0, 0). This insert will
     * insert the KVPair specified into the sorted SkipList appropriately
     *
     * @param pair
     *            the KVPair to be inserted
     */
    public void insert(Point point) {
        String name = point.gettname();
        // Point point = pair.getValue();
        // Judge if the insert command is valid
        if (point.getX() > 1024 || point.getX() < 0 || point.getY() < 0 || point
            .getY() > 1024) {
            System.out.println("Point rejected: " + point.toString());
        }
        else {
            // list.insert(pair);
            KVPair<String, Point> temp = new KVPair<String, Point>(name, point);
            list.insert(temp);
            tree.insert(point);
            System.out.println("Point inserted: " + point.toString());
        }
        // System.out.println("insert now!");

    }


    /**
     * Removes a rectangle with the name "name" if available. If not an error
     * message is printed to the console.
     *
     * @param name
     *            the name of the rectangle to be removed
     */
    public void remove(String name) {
        // System.out.println("remove by name now!");
        // KVPair<String, Rectangle> res = list.remove(name);
        KVPair<String, Point> temp = list.remove(name);
        // Point pointTemp = temp.getValue();
        if (temp == null) {
            System.out.println("Point not removed: " + name);
        }
        else {
            Point pointTemp = temp.getValue();
            tree.remove(pointTemp);
            System.out.println("Point removed: " + pointTemp.toString());
        }
        /*
         * if (res == null) {
         * System.out.println("Rectangle not removed: (" + name + ")");
         * }
         * else {
         * Point point = res.getValue();
         * System.out.println("Rectangle removed: (" + name + ", "
         * + rectangle.x + ", " + rectangle.y + ", "
         * + rectangle.width + ", " + rectangle.height + ")");
         * }
         */
    }


    /**
     * Removes a rectangle with the specified coordinates if available. If not
     * an error message is printed to the console.
     *
     * @param x
     *            x-coordinate of the rectangle to be removed
     * @param y
     *            x-coordinate of the rectangle to be removed
     * @param w
     *            width of the rectangle to be removed
     * @param h
     *            height of the rectangle to be removed
     */
    public void remove(int x, int y) {
        // Judge if the remove command is valid
        // System.out.println("remove by x y now!");
        if (x > 1024 || x < 0 || y < 0 || y > 1024) {
            System.out.println("Point rejected: (" + x + ", " + y + ")");
            return;
        }
        String name = tree.remove(x, y, 0, 0, 1024);
        // System.out.println("removeInside");
        // Point p2 = new Point(x, y, re.getKey());
        if (name == null) {
            System.out.println("Point not found: (" + x + ", " + y + ")");
        }
        else {
            Point point = new Point(x, y, name);
            list.removeByValue(point);
            // KVPair<String, Point> re = list.remove(point);
            System.out.println("Point removed: " + point.toString());
        }
    }


    /**
     * Displays all the rectangles inside the specified region. The rectangle
     * must have some area inside the area that is created by the region,
     * meaning, Rectangles that only touch a side or corner of the region
     * specified will not be said to be in the region. You will need a
     * SkipList Iterator for this
     *
     * @param x
     *            x-Coordinate of the region
     * @param y
     *            y-Coordinate of the region
     * @param w
     *            width of the region
     * @param h
     *            height of the region
     */
    public void regionsearch(int x, int y, int w, int h) {
        if (h <= 0 || w <= 0) {
            System.out.println("Rectangle rejected: (" + x + ", " + y + ", " + w
                + ", " + h + ")");
            return;
        }
        int x1 = x;
        int y1 = y;
        int x2 = x + w;
        int y2 = y + h;
        System.out.println("Points Intersecting Region: (" + x + ", " + y + ", "
            + w + ", " + h + ")");
        int cnt = tree.regionsearch(x1, y1, x2, y2, 0, 0, 1024);
        System.out.println(cnt + " QuadTree Nodes Visited");
    }


    /**
     * Prints out all the rectangles that Intersect each other by calling the
     * SkipList method for intersections. You will need to use two SkipList
     * Iterators for this
     */
    public void duplicates() {
        System.out.println("Duplicate Points:");
        tree.duplicates();
    }


    /**
     * Prints out all the rectangles with the specified name in the SkipList.
     * This method will delegate the searching to the SkipList class completely.
     *
     * @param name
     *            name of the Rectangle to be searched for
     */
    public void search(String name) {
        ArrayList<KVPair<String, Point>> ans =
            new ArrayList<KVPair<String, Point>>();
        ans = list.search(name);
        if (ans.size() == 0) {
            System.out.println("Point not found: " + name);
            // return;
        }
        else {
            for (KVPair<String, Point> iter : ans) {
                System.out.print("Point Found: ");
                Point r = iter.getValue();
                System.out.println(r.toString());
            }
        }
    }


    /**
     * Prints out a dump of the SkipList which includes information about the
     * size of the SkipList and shows all of the contents of the SkipList. This
     * will all be delegated to the SkipList.
     */
    public void dump() {
        System.out.println("SkipList Dump:");
        list.dump();
        System.out.println("QuadTree Dump:");
        int cnt = tree.dump();
        System.out.println(cnt + " QuadTree Nodes Printed.");
    }
}
