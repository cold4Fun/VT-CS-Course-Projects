import student.TestCase;
import java.util.Random;

/**
 * This is the test for minheap
 * 
 * @version 01/23/2022
 * @author Administrator
 *
 */
public class MinHeapTest extends TestCase {
    static private Random value = new Random();
    private MinHeap heap = new MinHeap(); // this is the heap

    /**
     * setup
     */
    public void setUp() {
        for (int i = 0; i < 4095; i++) {
            long val = value.nextLong();
            double val2 = value.nextDouble();
            Record newR = new Record(val, val2);
            heap.insert(newR);
        }
        heap.buildheap();
    }


    /**
     * test
     */
    public void test() {
        // test insert
        assertEquals(heap.currSize(), 4095);

        long val = value.nextLong();
        double val2 = value.nextDouble();
        Record newR = new Record(val, val2);
        assertEquals(heap.isHeapFull(), false);
        heap.insert(newR);
        assertEquals(heap.isEmpty(), false);
        heap.insert(newR);
        assertEquals(heap.heapsize(), 4096);
        assertEquals(heap.isHeapFull(), true);

        // test remove
        Record a = heap.getRoot();
        Record rec1 = heap.removemin();
        assertEquals(a, rec1);
        for (int i = 0; i < 50; i++) {
            Record rec2 = heap.removemin();
            assertTrue(rec1.compareTo(rec2) <= 0);
            rec1 = rec2;
        }
        heap.siftdown(5000);
        heap.siftdown(-1);
        for (int i = 0; i < 4045; i++) {
            Record rec2 = heap.removemin();
            assertTrue(rec1.compareTo(rec2) <= 0);
            rec1 = rec2;
        }

        assertNull(heap.removemin());
        assertNull(heap.getRoot());
        heap.clear();
        assertEquals(heap.isEmpty(), true);

        setUp();

        rec1 = heap.getRoot();

        assertEquals(heap.remove(0), rec1);
        assertEquals(heap.remove(-1), null);
        assertEquals(heap.remove(5000), null);
        System.out.println(heap.currSize());
        heap.remove(heap.currSize() - 1);

        assertEquals(heap.parent(0), -1);
        heap.clear();

        heap.buildheap();
        heap.insert(new Record(0, 2));
        heap.insert(new Record(0, 1));
        heap.insert(newR);

        heap.insert(new Record(0, 1));
        heap.insert(new Record(0, 0.1));
        System.out.println(heap.currSize());
        assertEquals(heap.leftchild(0), 1);
        assertEquals(heap.leftchild(3), -1);

        assertFalse(heap.isLeaf(0));

        assertTrue(heap.isLeaf(4));

        assertFalse(heap.isLeaf(7));

        assertEquals(heap.rightchild(0), 2);
        assertEquals(heap.rightchild(3), -1);

        heap.modify(0, new Record(0, 1));
        heap.modify(-1, new Record(0, 1));
        heap.modify(99, new Record(0, 1));

        heap.update(-1);
        heap.update(-1);

        heap.recordNextRun(newR);
        assertEquals(heap.currSize(), 4);
        heap.recordNextRun(newR);
        heap.recordNextRun(newR);
        heap.recordNextRun(newR);
        heap.recordNextRun(newR);
        assertEquals(heap.currSize(), 0);
        heap.heapnextRun();
        assertEquals(heap.currSize(), 4096);

        heap.clear();
        Record[] newR1 = new Record[5];
        newR1[0] = newR;
        newR1[1] = new Record(0, 1);
        newR1[2] = new Record(0, 3);
        newR1[3] = new Record(0, 11);
        newR1[4] = new Record(0, 0);

        MinHeap heap2 = new MinHeap(newR1, 5, 5);
        assertEquals(heap.currSize(), 0);
        heap.update(0);
    }

}
