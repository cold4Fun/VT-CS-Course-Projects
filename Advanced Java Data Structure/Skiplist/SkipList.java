import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


/**
 * This class implements SkipList data structure and contains an inner SkipNode
 * class which the SkipList will make an array of to store data.
 * 
 * @author Weiting Li
 * 
 * @version 2021-08-23
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
     * @return an Arraylist from search
     */
    public ArrayList<KVPair<K, V>> search(K key) {
        
        //intiate an arraylist to return
        KVPair<K, V> x;
        ArrayList<KVPair<K, V>> theta = new ArrayList<KVPair<K, V>>();
        SkipListIterator a = new SkipListIterator();
        
        while (a.hasNext()) {
            
            x = a.next();
            if (x.getKey().equals(key)) {
                theta.add(x);
            }
            
            
        }
        return theta;
        
        // Start traversing from the top of the existing
        /**
        if(head.forward[0]!= null) {
            for(int i= head_level ; i>=0; i--) {
                while((newb.forward[i] != null) 
                && (newb.forward[i].pair.getKey().compareTo(key)<0)) {
                    newb = newb.forward[i];
                }
            }
        newb = newb.forward[0]; // move to actual record
        theta.add(newb.pair);
        //check if any duplicate
        while(newb.forward[0].pair.getKey().compareTo(key) == 0) {
            newb = newb.forward[0];
            theta.add(newb.pair);
            }
        }
            
        
        //return statement
        if(theta.isEmpty()) {
            return null;
        }else {
            return theta;
        }
    */
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
        int newLevel = randomLevel(); // New node's level
        
        if (newLevel > head.level) { // If new node is deeper
            adjustHead(newLevel); // adjust the header
        }
        
        // Track end of level
        SkipNode[] update = (SkipNode[])Array.newInstance(
        SkipList.SkipNode.class,
        head.level + 1); //new SkipNode[head.level + 1];
        
        SkipNode x = head; // Start at header node
        for (int i = head.level; i >= 0; i--) { // Find insert position
            while ((x.forward[i] != null) && 
            		(x.forward[i].pair.getKey().compareTo(it.getKey()) < 0)) {
                x = x.forward[i];
        	}
            update[i] = x; // Track end at level i
        }
        x = new SkipNode(it, newLevel);
        
       
        //System.out.println(("Rectangle inserted.....\n" 
        //+ x.pair.getKey() + " "+ x.pair.getValue()));
        
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            x.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = x; // Who points to x
        }
        size++; // Increment dictionary size
    }


    /**
     * Increases the number of levels in head so that no element has more
     * indices than the head.
     * 
     * @param newLevel
     *            the number of levels to be added to head
     */
    @SuppressWarnings("unchecked")
    private void adjustHead(int newLevel) {
        SkipNode temp = head;
        int stat1 = head.level;
        head = new SkipNode(null, newLevel);
        for (int i = 0; i <= stat1; i++) {
            head.forward[i] = temp.forward[i];
        }
        head.level = newLevel;
        
    }


    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     * 
     * @param key
     *            the key of the kvpair to be removed
     * @return returns the removed pair if the pair was valid and null if not
     */

    @SuppressWarnings("unchecked")
    public KVPair<K, V> remove(K key) {
        int stat = head.level;
        // current node
        SkipNode cur = head;
        //initiate update array
        SkipNode[] update = 
        		(SkipNode[])Array.newInstance(SkipList.SkipNode.class,
            stat + 1); //new SkipNode[head.level + 1];
        
        for (int i = stat; i >= 0; i--) { // Find insert position
        	while ((cur.forward[i] != null) 
        			&& (cur.forward[i].pair.getKey().compareTo(key) < 0)) {
        	    cur = cur.forward[i];
            }
            update[i] = cur; // Track end at level i
        }
          
        cur = cur.forward[0];
          
          //System.out.println(cur.level);
          
        if ((cur != null) && (cur.element().getKey().equals(key)) ) {
            for (int i = 0; i <= cur.level; i++) { // Splice into list
                update[i].forward[i] = cur.forward[i]; // Who x points to 
            }
                                      
        
            size--;
            return cur.pair;
        }
               
        return null;
    }
    
    /**
     * Removes the KVPair that is passed in as a parameter and returns true if
     * the pair was valid and false if not.
     * 
     * @param key 
     *          to be removed
     * @param val 
     *        	the value
     * @return returns the removed pair if the pair was valid and null if not
     */

    @SuppressWarnings("unchecked")
    public KVPair<K, V> removebykeyval(K key, V val) {
        int stat = head.level;
        // current node
        SkipNode cur = head;
        //initiate update array
        SkipNode[] update = (SkipNode[])Array.newInstance(
        		SkipList.SkipNode.class,
            stat + 1); //new SkipNode[head.level + 1];
        
        for (int i = stat; i >= 0; i--) { // Find insert position
        	while ((cur.forward[i] != null) 
        			&& (cur.forward[i].pair.getKey().compareTo(key) <= 0)
        			&& (!cur.forward[i].pair.getValue().equals(val))) {    
                    //System.out.println(cur.forward[i].pair.getValue());
        		cur = cur.forward[i]; 
                    //System.out.println("infinite\n");
    
                    
            }
            update[i] = cur; // Track end at level i
        }
          
        cur = cur.forward[0];
          
          //System.out.println(cur.level);
          
        if ((cur != null) && (cur.element().getValue().equals(val)) ) {
        	for (int i = 0; i <= cur.level; i++) { // Splice into list
        		update[i].forward[i] = cur.forward[i]; // Who x points to 
        	}
            size--;
            return cur.pair;
        }
               
        return null;
    }
    
    /**
     * Removes a KVPair with the specified value.
     * 
     * @param val
     *            the value of the KVPair to be removed
     * @return returns true if the removal was successful
     */
    public KVPair<K, V> removeByValue(V val) {
        SkipListIterator bb = new SkipListIterator();
 
        K key = null;        //define a empty key variable
        
        
           //LOOP
        if (bb != null) {
        	while (bb.hasNext()) {
                
        		bb.next(); //Move to next
                   
                //check if equals, get the key 
                if (bb.current.element().getValue().equals(val)) { 
                    key = bb.current.pair.getKey();
                    //KVPair<K, V> newkvp = remove(key);
                    return removebykeyval(key, val);
                }
                
   
            }
        }
        return null;
   
    }
  
    /**
     * Prints out the SkipList in a human readable format to the console.
     */
    public void dump() {
        
        SkipListIterator a = new SkipListIterator();
        
        
        int stat = a.current.level;
        int count = this.size;
  
        System.out.println("SkipList dump:\n"
            + "Node has depth " + (stat + 1) + ", Value (null)");
        
        
        while (a.hasNext()) {
            a.next();
                
            System.out.println("Node has depth " + (a.current.level + 1)  
                + ", Value (" + a.current.pair.getKey()  
                	+ ", " + a.current.pair.getValue().toString() + ")");  
        }
 
       
        System.out.println("SkipList size is: " + count);  
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
        private SkipNode [] forward;
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

        public SkipListIterator() {
            current = head;
        }


        @Override
        public boolean hasNext() {
            
            return (current.forward[0] != null);
              
        }


        @Override
        public KVPair<K, V> next() {
                
        	KVPair<K, V> val = current.forward[0].element();
        	
            current = current.forward[0];

            return val;
            
        }
        
    
     

    }

    @Override
    public Iterator<KVPair<K, V>> iterator() {
        
        return new SkipListIterator();
    }
    
    
}

    

       
