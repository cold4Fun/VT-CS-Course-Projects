import student.TestCase;
import java.nio.ByteBuffer;
import java.util.Random;

/**
 * This is the record test
 * 
 * @version 01/23/2022
 * @author Administrator
 *
 */
public class RecordTest extends TestCase {
    static private Random value = new Random();
    private long id = value.nextLong();
    private double key = value.nextDouble();

    private Record a = new Record(id, key);
    private Record b = new Record(a, 0);
    private Record c = new Record(0, 0);

    /**
     * setup
     */
    public void setUp() {
        ByteBuffer temp = ByteBuffer.allocate(16);
        Record[] rec1 = new Record[1];
        rec1[0] = new Record((long)0.111111111, 0.55555555);

        temp.putLong(rec1[0].getID());
        temp.putDouble(rec1[0].getKey());

        byte[] out = temp.array();

        Record newRec1111 = new Record(out);

        byte[] ddd = newRec1111.getRecord();
    }


    /**
     * test
     */
    public void test() {
        assertEquals(b.compareTo(a), 0);
        assertEquals(b.getFlag(), 0);
        b.setID(111);
        assertEquals(b.getID(), 111);
        b.setData((double)120);
        assertEquals((int)b.getKey(), 120);
        assertEquals(b.toString(), "111 120.0");
        assertEquals(b.compareTo(c), 1);
        assertEquals(c.compareTo(b), -1);
        // assertEquals()
    }
}
