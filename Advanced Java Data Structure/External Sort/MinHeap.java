
// Max-heap implementation
/**
 * The min heap class
 * 
 * @version 01/23/2022
 * @author Administrator
 *
 */
class MinHeap {
    private Record[] heap; // Pointer to the heap array
    private int size; // Maximum size of the heap
    private int n; // Number of things now in heap

    // normal constructor
    /**
     * normal
     */
    public MinHeap() {

        size = 4096;
        heap = new Record[size];
        n = 0;

        buildheap();
    }


    /**
     * constructor
     * 
     * @param h
     *            record Arr
     * @param num
     *            current size
     * @param max
     *            max size
     */
    public MinHeap(Record[] h, int num, int max) {
        heap = h;
        n = num;
        size = max;

        buildheap();

    }


    /**
     * // Return current size of the heap
     * 
     * @return n
     */
    public int heapsize() {
        return n;
    }


    /**
     * This checks if heap is full
     * 
     * @return boolean
     */
    public boolean isHeapFull() {
        return (n == size);
    }


    /**
     * Return true if pos a leaf position, false otherwise
     * 
     * @param pos
     *            pos
     * @return boolean
     */
    public boolean isLeaf(int pos) {
        return (pos >= n / 2) && (pos < n);
    }


    /**
     * Return position for left child of pos
     * 
     * @param pos
     *            pos
     * @return integer
     */
    public int leftchild(int pos) {
        if (pos >= n / 2) {
            return -1;
        }
        return 2 * pos + 1;
    }


    /**
     * Return position for right child of pos
     * 
     * @param pos
     *            a
     * @return integer b
     */
    public int rightchild(int pos) {
        if (pos >= (n - 1) / 2) {
            return -1;
        }
        return 2 * pos + 2;
    }


    /**
     * Return position for parent
     * 
     * @param pos
     *            pos
     * @return integer
     */
    public int parent(int pos) {
        if (pos <= 0) {
            return -1;
        }
        return (pos - 1) / 2;
    }


    /**
     * Insert val into heap
     * 
     * @param key
     *            Record
     */
    public void insert(Record key) {
        if (n >= size) {
            System.out.println("Heap is full");
            return;
        }
        int curr = n++;

        heap[curr] = key; // Start at end of heap

        // Now sift up until curr's parent's key > curr's key
        while ((curr != 0) && (heap[parent(curr)].compareTo(heap[curr]) > 0)) {
            swap(curr, parent(curr));
            curr = parent(curr);
        }
    }


    /**
     * Heapify contents of Heap
     */
    public void buildheap() {
        for (int i = n / 2 - 1; i >= 0; i--) {
            siftdown(i);
        }
    }


    // Check ~
    // Put element in its correct place
    /**
     * Put element in its correct place
     * 
     * @param pos
     *            pos
     */
    public void siftdown(int pos) {
        if ((pos < 0) || (pos >= n)) {
            return;
        } // Illegal position
        while (!isLeaf(pos)) {
            int j = leftchild(pos);
            if ((j < (n - 1)) && (heap[j].compareTo(heap[j + 1]) > 0)) {
                j++; // j is now index of child with greater value
            }
            if (heap[pos].compareTo(heap[j]) <= 0) {
                return;
            }
            swap(pos, j);
            pos = j; // Move down
        }
    }


    /**
     * Remove and return minimum value
     * 
     * @return Record
     */
    public Record removemin() {
        if (n == 0) {
            return null;
        } // Removing from empty heap
        swap(0, --n); // Swap maximum with last value
        if (n != 0) {
            siftdown(0);
        } // Put new heap root val in correct place
        return heap[n];
    }


    /**
     * This get the record at the root of heap
     * 
     * @return record
     */
    public Record getRoot() {
        if (n == 0) {
            return null;
        }
        Record temp = heap[0];
        

        return temp;
    }


    /**
     * Remove and return element at specified position
     * 
     * @param pos
     *            pos
     * @return Record
     */
    public Record remove(int pos) {
        if ((pos < 0) || (pos >= n)) {
            return null;
        } // Illegal heap position
        if (pos == (n - 1)) {
            n--;
        } // Last element, no work to be done
        else {
            swap(pos, --n); // Swap with last value
            update(pos);
        }
        return heap[n];
    }


    /**
     * getCurrentSize
     * 
     * @return integer
     */
    public int currSize() {
        return n;
    }


    /**
     * isEmpty
     * 
     * @return boolean
     */
    public boolean isEmpty() {
        return n == 0;
    }


    /**
     * Modify the value at the given position
     * 
     * @param pos
     *            pos
     * @param newVal
     *            record
     */
    public void modify(int pos, Record newVal) {
        if ((pos < 0) || (pos >= n)) {
            return;
        } // Illegal heap position
        heap[pos] = newVal;
        update(pos);
    }


    /**
     * The value at pos has been changed, restore the heap property
     * 
     * @param pos
     *            pos
     */
    public void update(int pos) {
        // If it is a big value, push it up
        while ((pos > 0) && (heap[pos].compareTo(heap[parent(pos)]) < 0)) {
            swap(pos, parent(pos));
            pos = parent(pos);
        }
        if (n != 0) { // current size != 0
            siftdown(pos);
        } // If it is little, push down
    }


    /**
     * put the record in the next run
     * 
     * @param input
     *            record
     */
    public void recordNextRun(Record input) {
        heap[0] = input;
        swap(0, n - 1);
        n--;
        siftdown(0);
    }


    /**
     * restart the heap into the next run
     */
    public void heapnextRun() {
        for (int i = n; i < size; i++) {
            this.insert(heap[i]);
        }
    }


    /**
     * clear the heap
     */
    public void clear() {
        n = 0;

    }


    /**
     * Swaps two positions
     * 
     * @param pos
     *            original position
     * @param newPos
     *            new position
     */

    public void swap(int pos, int newPos) {
        Record temp = heap[pos];
        heap[pos] = heap[newPos];
        heap[newPos] = temp;
    }

}
