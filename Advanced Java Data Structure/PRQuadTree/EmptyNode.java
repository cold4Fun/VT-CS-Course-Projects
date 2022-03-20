
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
public class EmptyNode implements QuadNode {

    // ----------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public QuadNode insert(Point point, int x, int y, int size) {

        LinkedList<Point> list = new LinkedList<Point>();
        list.add(point);
        LeafNode newNode = new LeafNode(list);
        // System.out.println("Emptynode Insert");
        return newNode;
        // TODO Auto-generated method stub

    }
    // ~ Fields ................................................................


    // ----------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public int dump(int x, int y, int size, int level) {
        // TODO Auto-generated method stub
        for (int i = 0; i < level; ++i) {
            System.out.print(" ");
        }
        System.out.println("Node at " + x + ", " + y + ", " + size + ": Empty");
        return 1;
    }


    // ----------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public void duplicates() {
        // TODO Auto-generated method stub

    }


    // ----------------------------------------------------------
    /**
     * {@inheritDoc}
     * 
     * @return
     */
    @Override
    public QuadNode remove(Point p) {
        return this;
        // TODO Auto-generated method stub

    }

    // ~ Constructors ..........................................................


    // ~Public Methods ........................................................
    public int getSize() {
        return 0;
    }


    // ----------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public int regionsearch(
        int x1,
        int y1,
        int x2,
        int y2,
        int x,
        int y,
        int size) {
        // TODO Auto-generated method stub
        return 1;
    }


    // ----------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public String remove(int px, int py, int x, int y, int size) {
        // TODO Auto-generated method stub
        return null;
    }
}
