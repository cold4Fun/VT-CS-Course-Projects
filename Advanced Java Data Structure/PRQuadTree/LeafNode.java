import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here.
 * Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author zjy
 * @version 2021-10-23
 */
public class LeafNode implements QuadNode {
    // ~ Fields ................................................................
    private LinkedList<Point> link = new LinkedList<Point>();

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new LeafNode object.
     * 
     * @param list1
     */
    LeafNode(LinkedList<Point> list1) {
        link = list1;
    }


    // ~Public Methods ........................................................
    public QuadNode insert(Point point, int x, int y, int size1) {
        if (link.size() < 3 || (link.size() >= 3 && allSameXY(link, point))) {
            link.add(point);
            // System.out.println("Leaf Node Insert!");
            return this;
        }
        link.add(point);
        InternalNode newNode = new InternalNode(link, size1);
        return newNode;

    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param link2
     * @return
     */
    private boolean allSameXY(LinkedList<Point> link2, Point p) {
        // TODO Auto-generated method stub
        ArrayList<Point> arr = new ArrayList<Point>();
        for (Point it : link2) {
            arr.add(it);
        }
        arr.add(p);
        for (int i = 0; i < arr.size(); ++i) {
            for (int j = i + 1; j < arr.size(); ++j) {
                // System.out.println(arr.get(i).gettname());
                // System.out.println(arr.get(j).gettname());
                if ((arr.get(i).getX() != arr.get(j).getX()) || (arr.get(i)
                    .getY() != arr.get(j).getY())) {
                    return false;
                }
            }
        }
        return true;
    }


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
        System.out.println("Node at " + x + ", " + y + ", " + size + ": ");
        for (Point it : link) {
            for (int i = 0; i < level; ++i) {
                System.out.print(" ");
            }
            System.out.println(it.toString());
        }
        return 1;
    }


    // ----------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public void duplicates() {
        ArrayList<Point> arr = new ArrayList<Point>();
        Set<Integer> ans = new HashSet<Integer>();
        // TODO Auto-generated method stub
        for (Point it1 : link) {
            arr.add(it1);
        }
        for (int i = 0; i < arr.size(); ++i) {
            for (int j = i + 1; j < arr.size(); ++j) {
                if (arr.get(i).getX() == arr.get(j).getX() && arr.get(i)
                    .getY() == arr.get(j).getY()) {
                    /*
                     * System.out.println("(" + arr.get(i).getX()
                     * + ", " + arr.get(i).getY() + ")");
                     */
                    int temp = arr.get(i).getX() * 1024 + arr.get(i).getY();
                    ans.add(temp);
                }
            }
        }
        for (Integer it : ans) {
            int x = it / 1024;
            int y = it - x * 1024;
            System.out.println("(" + x + ", " + y + ")");
        }
    }


    // ----------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public QuadNode remove(Point p) {
        link.remove(p);
        // TODO Auto-generated method stub
        /*
         * for (Point it : link) {
         * //System.out.println(it.gettname().compareTo(name));
         * if (it.equals(p)) {
         * System.out.println("Pre : Point" + it.gettname() +"remove.");
         * link.remove(p);
         * System.out.println("Point " + it.gettname() +" remove.");
         * }
         * System.out.println(it.gettname());
         * }
         */
        return this;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return
     */
    public int getSize() {
        return link.size();
    }


    public LinkedList<Point> getLink() {
        return link;

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
        for (Point it : link) {
            if (include(x1, y1, x2, y2, it.getX(), it.getY(), size)) {
                System.out.println("Point Found: " + it.toString());
            }
        }
        return 1;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @return
     */
    private boolean include(
        int x1,
        int y1,
        int x2,
        int y2,
        int x,
        int y,
        int size) {
        // TODO Auto-generated method stub
        return (x >= x1 && y >= y1 && x <= x2 && y <= y2);
    }


    // ----------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public String remove(int px, int py, int x, int y, int size) {
        // TODO Auto-generated method stub
        for (Point it : link) {
            // System.out.println(it.toString());
            // System.out.println("x: " + x + "y: " + y);
            if (px == it.getX() && py == it.getY()) {
                link.remove(it);
                return it.gettname();
            }
        }
        return null;
    }
}
