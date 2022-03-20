// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here.
 * Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author zjy
 * @version 2021-10-22
 */
public class Point {
    // ~ Fields ................................................................
    private String name;
    private int x;
    private int y;

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param x
     * @param y
     * @param name
     * @return
     */
    // ~ Constructors ..........................................................
    Point(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }


    // ~Public Methods ........................................................
    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return
     */

    public boolean equals(Object o) {
        if (o instanceof Point) {
            Point point = (Point)o;
            if (this.x == point.getX() && this.y == point.getY()) {
                return true;
            }
        }
        return false;
    }


    String gettname() {
        return name;
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public String toString() {
        return "(" + gettname() + ", " + getX() + ", " + getY() + ")";
    }


    public int compareTo(Object o) {
        int X = ((Point)o).x;
        int Y = ((Point)o).y;

        if (X == this.x && Y == this.y) {
            return 0;
        }
        return -1;
    }

}
