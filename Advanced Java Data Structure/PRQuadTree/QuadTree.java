// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here.
 * Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author zjy
 * @version 2021-10-23
 */
public class QuadTree {
    // ~ Fields ................................................................
    private EmptyNode emptynode;
    private QuadNode root = emptynode;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new QuadTree object.
     */
    QuadTree() {
        emptynode = QuadNode.emptyNode;
        root = emptynode;
    }


    // ~Public Methods ........................................................
    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param point
     */
    public void insert(Point point) {
        // System.out.println("QuadTree insert ");
        root = root.insert(point, 0, 0, 1024);
    }


    // ----------------------------------------------------------
    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return
     */
    public int dump() {
        // TODO Auto-generated method stub
        return root.dump(0, 0, 1024, 0);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param name
     * @param x
     * @param y
     */
    public void remove(Point p) {
        // TODO Auto-generated method stub
        root = root.remove(p);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param px
     * @param py
     * @param x
     * @param y
     * @param size
     * @return
     */
    public String remove(int px, int py, int x, int y, int size) {
        // System.out.println("removeInside");
        return root.remove(px, py, x, y, size);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    public void duplicates() {
        // TODO Auto-generated method stub
        root.duplicates();
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x
     * @param y
     * @param size
     * @return
     */
    public int regionsearch(
        int x1,
        int y1,
        int x2,
        int y2,
        int x,
        int y,
        int size) {
        // TODO Auto-generated method stub
        return root.regionsearch(x1, y1, x2, y2, x, y, size);

    }
}
