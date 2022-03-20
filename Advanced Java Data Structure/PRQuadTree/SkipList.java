import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 *
 * @author Jiayue Zhou
 *
 * @version 2021-09-25
 * @param <K>
 *            Key
 * @param <V>
 *            Value
 */
public class SkipList<K extends Comparable<? super K>, V>
    implements Iterable<KVPair<K, V>> {
    private SkipNode head; // First element of the top level
    private int size; // number of entries in the Skip List

    /**
     * Initializes the fields head, size and level
     */
    public SkipList() {
        head = new SkipNode(null, 0);
        size = 0;
    }


    /**
     * Returns a random level number which is used as the depth of the SkipNode
     *
     * @return a random level number
     */
    int randomLevel() {
        int lev;
        Random value = new Random();
        for (lev = 0; Math.abs(value.nextInt()) % 2 == 0; lev++) {
            // Do nothing
        }
        return lev; // returns a random level
    }


    /**
     * Searches for the KVPair using the key which is a Comparable object.
     *
     * @param key
     *            key to be searched for
     * @return an ArrayList of search result
     */
    public ArrayList<KVPair<K, V>> search(K key) {
        ArrayList<KVPair<K, V>> ans = new ArrayList<KVPair<K, V>>();
        SkipNode x = head;
        for (int i = head.level; i >= 0; --i) {
            while ((x.forward[i] != null) && (x.forward[i].pair.getKey()
                .compareTo(key) < 0)) {
                x = x.forward[i];
            }
        }
        x = x.forward[0];
        while ((x != null) && (x.pair.getKey().compareTo(key) == 0)) {
            ans.add(x.pair);
            x = x.forward[0];
        }
        return ans;
    }


    /**
     * @return the size of the SkipList
     */
    public int size() {
        return size;
    }


    /**
     * Inserts the KVPair in the SkipList at its appropriate spot as designated
     * by its lexicoragraphical order.
     *
     * @param it
     *            the KVPair to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insert(KVPair<K, V> it) {
        int lev = randomLevel();
        if (lev > head.level) {
            adjustHead(lev);
        }
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, head.level + 1);
        SkipNode x = head;
        for (int i = head.level; i >= 0; --i) {
            while ((x.forward[i] != null) && (x.forward[i].pair.compareTo(
                it) < 0)) {
                x = x.forward[i];
            }
            update[i] = x;
        }
        x = new SkipNode(it, lev);
        for (int i = 0; i <= lev; ++i) {
            x.forward[i] = update[i].forward[i];
            update[i].forward[i] = x;
        }
        size++;
        // System.out.println("Success Insert!");
    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices than the head.
     *
     * @param newLevel
     *            the number of levels to be added to head
     */
    private void adjustHead(int newLevel) {
        SkipNode temp = head;
        head = new SkipNode(null, newLevel);
        for (int i = 0; i <= temp.level; ++i) {
            head.forward[i] = temp.forward[i];
        }
        head.level = newLevel;
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     *
     * @param key
     *            the KVPair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */

    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipList.SkipNode.class, head.level + 1);
        SkipNode x = head;
        for (int i = head.level; i >= 0; --i) {
            while ((x.forward[i] != null) && (x.forward[i].pair.getKey()
                .compareTo(key) < 0)) {
                x = x.forward[i];
            }
            update[i] = x;
        }
        x = x.forward[0];
        if ((x == null) || (x.pair.getKey().compareTo(key) != 0)) {
            return null;
        }
        for (int i = 0; i <= head.level; ++i) {
            if (i <= x.level) {
                update[i].forward[i] = x.forward[i];
            }
        }
        size--;
        return x.pair;
    }


    /**
     * Removes a KVPair with the specified value.
     *
     * @param val
     *            the value of the KVPair to be removed
     * @return returns true if the removal was successful
     */
    @SuppressWarnings("unchecked")
    public KVPair<K, V> removeByValue(V val) {
        SkipNode pos = head;
        for (int i = 0; i < size; ++i) {
            pos = pos.forward[0];
            V r = pos.pair.getValue();
            if (val.equals(r)) {
                for (int j = head.level; j >= 0; --j) {
                    SkipNode x = head;
                    while (x.forward[j] != null && x.forward[j] != pos) {
                        x = x.forward[j];
                    }
                    if (x.forward[j] == pos) {
                        x.forward[j] = pos.forward[j];
                    }
                }
                size--;
                return pos.pair;
            }
        }
        return null;
    }


    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {
        SkipNode pos = head;
        System.out.println("Node has depth " + (pos.level + 1) + ", Value ("
            + pos.element() + ")");
        pos = pos.forward[0];
        for (int i = 1; i < size + 1; ++i) {
            Point point = (Point)pos.pair.getValue();
            System.out.println("Node has depth " + (pos.level + 1) + ", Value ("
                + pos.pair.getKey() + ", " + point.getX() + ", " + point.getY()
                + ")");
            pos = pos.forward[0];
        }
        System.out.println("SkipList size is: " + size);
    }

    /**
     * This class implements a SkipNode for the SkipList data structure.
     *
     * @author CS Staff
     *
     * @version 2016-01-30
     */
    private class SkipNode {

        // the KVPair to hold
        private KVPair<K, V> pair;
        // what is this
        private SkipNode[] forward;
        // the number of levels
        private int level;

        /**
         * Initializes the fields with the required KVPair and the number of
         * levels from the random level method in the SkipList.
         *
         * @param tempPair
         *            the KVPair to be inserted
         * @param level
         *            the number of levels that the SkipNode should have
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, V> tempPair, int level) {
            pair = tempPair;
            forward = (SkipNode[])Array.newInstance(SkipList.SkipNode.class,
                level + 1);
            this.level = level;
        }


        /**
         * Returns the KVPair stored in the SkipList.
         *
         * @return the KVPair
         */
        public KVPair<K, V> element() {
            return pair;
        }

    }


    private class SkipListIterator implements Iterator<KVPair<K, V>> {
        private SkipNode current;
        private int len;

        public SkipListIterator() {
            current = head;
            this.len = 0;
        }


        @Override
        public boolean hasNext() {
            return (len != size);
        }


        @Override
        public KVPair<K, V> next() {
            current = current.forward[0];
            len++;
            return current.pair;
        }

    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        return new SkipListIterator();
    }

}
