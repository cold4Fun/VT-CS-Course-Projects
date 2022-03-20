
import java.util.LinkedList;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here.
 * Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author zjy
 * @version 2021-10-23
 */
public interface QuadNode {

    /**
     *
     */
    public EmptyNode emptyNode = new EmptyNode();
    /**
     *
     */
    static final double size = 0;

    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return
     */
    public static double gettSize() {
        return size;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param point
     * @param x
     * @param y
     * @param size
     * @return
     */
    public QuadNode insert(Point point, int x, int y, int size);
    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param point
     */


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param x
     * @param y
     * @param size
     * @param level
     * @return
     */
    public int dump(int x, int y, int size, int level);


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void duplicates();


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param y
     * @param x
     * @param name
     * @return
     */
    public QuadNode remove(Point p);


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return
     */
    public int getSize();


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return
     */
    public int regionsearch(
        int x1,
        int y1,
        int x2,
        int y2,
        int x,
        int y,
        int size);


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param x
     * @param y
     * @return
     */
    public String remove(int px, int py, int x, int y, int size);

}
