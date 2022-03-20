import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here.
 * Follow it with additional details about its purpose, what abstraction
 * it represents, and how to use it.
 *
 * @author zjy
 * @version 2021
 */
public class InternalNode implements QuadNode {
    // ~ Fields ................................................................
    private QuadNode nw;
    private QuadNode ne;
    private QuadNode sw;
    private QuadNode se;

    // ~ Constructors ..........................................................
    // ----------------------------------------------------------
    /**
     * Create a new InternalNode object.
     * 
     * @param list
     * @param size
     */
    InternalNode(LinkedList<Point> list, int size) {
        if (list.size() > 3 && !allSameXY(list)) {
            decompose(list, 0, 0, size);
        }
        if (list.size() <= 3) {
            compose();
        }
    }


    // ----------------------------------------------------------
    /**
     * Create a new InternalNode object.
     * 
     * @param nw
     * @param ne
     * @param sw
     * @param se
     */
    public InternalNode(QuadNode nw, QuadNode ne, QuadNode sw, QuadNode se) {
        // TODO Auto-generated constructor stub
        this.nw = nw;
        this.ne = ne;
        this.sw = sw;
        this.se = se;
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     * 
     * @param list1
     * @return
     */
    private QuadNode decompose(LinkedList<Point> list, int x, int y, int size) {
        // TODO Auto-generated method stub
        // System.out.println(size + ": " + list.size());
        /*
         * System.out.println("decompose:" + allSameXY(list));
         * if (allSameXY(list)) {
         * //System.out.println("decompose:" + allSameName(list));
         * return new LeafNode(list);
         * }
         */
        if (list.size() == 0) {
            // return QuadNode.emptyNode;
            return new EmptyNode();
        }
        else if (list.size() <= 3) {
            return new LeafNode(list);
        }
        else if (allSameXY(list)) {
            return new LeafNode(list);
        }
        LinkedList<Point> list1 = new LinkedList<Point>();
        LinkedList<Point> list2 = new LinkedList<Point>();
        LinkedList<Point> list3 = new LinkedList<Point>();
        LinkedList<Point> list4 = new LinkedList<Point>();
        // Point cur = list;
        for (Point iter : list) {
            double px = iter.getX();
            double py = iter.getY();
            // System.out.println("x: " + x);
            // System.out.println("y: " + y);
            // System.out.println("size: " + size);
            if (px <= x + size / 2 && py <= y + size / 2) {
                list1.add(iter);
            }
            else if (px >= x + size / 2 && py <= y + size / 2) {
                list2.add(iter);
            }
            else if (px <= x + size / 2 && py >= y + size / 2) {
                list3.add(iter);
            }
            // (px >= x + size / 2 && py >= y + size / 2)
            else {
                list4.add(iter);
            }
        }
        // System.out.println("size: " + size + " " + list1.size() + " " +
        // list2.size() + " " + list3.size() + " " + list4.size());
        nw = decompose(list1, x, y, size / 2);
        ne = decompose(list2, x + size / 2, y, size / 2);
        sw = decompose(list3, x, y + size / 2, size / 2);
        se = decompose(list4, x + size / 2, y + size / 2, size / 2);
        return new InternalNode(nw, ne, sw, se);
    }


    /*
     * nw.dump(x, y, size / 2, level + 1);
     * ne.dump(x + size / 2, y, size / 2, level + 1);
     * sw.dump(x, y + size / 2, size / 2, level + 1);
     * se.dump(x + size / 2, y + size / 2, size / 2, level + 1);//
     * //~Public Methods
     * ........................................................
     */
    private boolean allSameXY(LinkedList<Point> link2) {
        // TODO Auto-generated method stub
        ArrayList<Point> arr = new ArrayList<Point>();
        for (Point it : link2) {
            arr.add(it);
        }
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


    public QuadNode insert(Point point, int x, int y, int size) {
        int px = point.getX();
        int py = point.getY();
        if (px < size / 2 && py < size / 2) {
            nw = nw.insert(point, x, y, size / 2);
        }
        else if (px >= size / 2 && py <= size / 2) {
            // System.out.println("ne1");
            ne = ne.insert(point, x + size / 2, y, size / 2);
            // System.out.println("ne2");
            // System.out.println(ne.getSize());
        }
        else if (px < size / 2 && py > size / 2) {
            sw = sw.insert(point, x, y + size / 2, size / 2);
        }
        else {
            // System.out.println("se");
            se = se.insert(point, x + size / 2, y + size / 2, size / 2);
        }
        // System.out.println(ne.getSize());
        return this;
    }


    // ----------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public int dump(int x, int y, int size, int level) {
        // TODO Auto-generated method stub
        int cnt = 1;
        for (int i = 0; i < level; ++i) {
            System.out.print(" ");
        }
        System.out.println("Node at " + x + ", " + y + ", " + size + ":"
            + " Internal");
        cnt += nw.dump(x, y, size / 2, level + 1);
        cnt += ne.dump(x + size / 2, y, size / 2, level + 1);
        cnt += sw.dump(x, y + size / 2, size / 2, level + 1);
        cnt += se.dump(x + size / 2, y + size / 2, size / 2, level + 1);
        return cnt;
    }


    // ----------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public void duplicates() {
        // TODO Auto-generated method stub
        nw.duplicates();
        ne.duplicates();
        sw.duplicates();
        se.duplicates();
    }


    // ----------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public QuadNode remove(Point p) {
        // TODO Auto-generated method stub
        int curNodeNum = 1000;
        nw = nw.remove(p);
        ne = ne.remove(p);
        sw = sw.remove(p);
        se = se.remove(p);
        if ((nw instanceof LeafNode) || (nw instanceof EmptyNode)
            && ((ne instanceof LeafNode) || (ne instanceof EmptyNode))
            && ((sw instanceof LeafNode) || (sw instanceof EmptyNode))
            && ((se instanceof LeafNode) || (se instanceof EmptyNode))) {
            curNodeNum = 0;
            curNodeNum += nw.getSize() + ne.getSize() + sw.getSize() + se
                .getSize();
        }
        if (curNodeNum <= 3) {
            return compose();
        }
        return new InternalNode(nw, ne, sw, se);
    }


    // ----------------------------------------------------------
    /**
     * Place a description of your method here.
     */
    private QuadNode compose() {
        LinkedList<Point> list1 = new LinkedList<Point>();
        LinkedList<Point> list2 = new LinkedList<Point>();
        LinkedList<Point> list3 = new LinkedList<Point>();
        LinkedList<Point> list4 = new LinkedList<Point>();
        if (!(nw instanceof EmptyNode)) {
            list1 = ((LeafNode)nw).getLink();
        }
        if (!(ne instanceof EmptyNode)) {
            list2 = ((LeafNode)ne).getLink();
        }
        if (!(sw instanceof EmptyNode)) {
            list3 = ((LeafNode)sw).getLink();
        }
        if (!(se instanceof EmptyNode)) {
            list4 = ((LeafNode)se).getLink();
        }
        LinkedList<Point> list = new LinkedList<Point>();
        for (Point it : list1) {
            list.add(it);
        }
        for (Point it : list2) {
            list.add(it);
        }
        for (Point it : list3) {
            list.add(it);
        }
        for (Point it : list4) {
            list.add(it);
        }
        // System.out.println("composing..");
        LeafNode newNode = new LeafNode(list);
        // System.out.println(newNode.getSize());
        return newNode;
        // TODO Auto-generated method stub

    }


    // ----------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        // TODO Auto-generated method stub
        return 0;
    }


    // ----------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    /*
     * nw.dump(x, y, size / 2, level + 1);
     * ne.dump(x + size / 2, y, size / 2, level + 1);
     * sw.dump(x, y + size / 2, size / 2, level + 1);
     * se.dump(x + size / 2, y + size / 2, size / 2, level + 1);//
     * ----------------------------------------------------------
     * /**
     * {@inheritDoc}
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
        int cnt = 1;
        if (isIntersec(x1, y1, x2, y2, x, y, size / 2)) {
            cnt += nw.regionsearch(x1, y1, x2, y2, x, y, size / 2);
        }
        if (isIntersec(x1, y1, x2, y2, x + size / 2, y, size / 2)) {
            cnt += ne.regionsearch(x1, y1, x2, y2, x + size / 2, y, size / 2);
        }
        if (isIntersec(x1, y1, x2, y2, x, y + size / 2, size / 2)) {
            cnt += sw.regionsearch(x1, y1, x2, y2, x, y + size / 2, size / 2);
        }
        if (isIntersec(x1, y1, x2, y2, x + size / 2, y + size / 2, size / 2)) {
            cnt += se.regionsearch(x1, y1, x2, y2, x + size / 2, y + size / 2,
                size / 2);
        }
        // System.out.println("cnt : " + cnt);
        return cnt;
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
     * @param i
     * @return
     */
    private boolean isIntersec(
        int x1,
        int y1,
        int x2,
        int y2,
        int x,
        int y,
        int size) {
        // TODO Auto-generated method stub
        int x3 = x;
        int y3 = y;
        int x4 = x + size;
        int y4 = y + size;
        boolean xOverLap = !(x3 >= x2 || x1 >= x4);
        boolean yOverLap = !(y3 >= y2 || y1 >= y4);
        return xOverLap && yOverLap;
    }


    // ----------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    /*
     * nw.dump(x, y, size / 2, level + 1);
     * ne.dump(x + size / 2, y, size / 2, level + 1);
     * sw.dump(x, y + size / 2, size / 2, level + 1);
     * se.dump(x + size / 2, y + size / 2, size / 2, level + 1);//
     * ----------------------------------------------------------
     * /**
     * {@inheritDoc}
     */
    public String remove(int px, int py, int x, int y, int size) {
        // TODO Auto-generated method stub
        String name = null;
        // System.out.println("removeInside");
        name = nw.remove(px, py, x, y, size / 2);
        if (name != null) {
            return name;
        }
        name = ne.remove(px, py, x + size / 2, y, size / 2);
        if (name != null) {
            return name;
        }
        name = sw.remove(px, py, x, y + size / 2, size / 2);
        if (name != null) {
            return name;
        }
        name = se.remove(px, py, x + size / 2, y + size / 2, size / 2);
        if (name != null) {
            return name;
        }
        return null;
    }
}
