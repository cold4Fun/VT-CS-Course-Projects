import student.TestCase;

/**
 * This is the run test
 * 
 * @version 01/23/2022
 * @author Administrator
 *
 */
public class RunTest extends TestCase {
    private Run newRun = new Run(0, 511, (long)512);
    private Run r1 = new Run(0, 100, 1);

    /**
     * setup
     */
    public void setUp() {
        Run newR = new Run(0, 512);
    }


    /**
     * test
     */
    public void testRun() {
        newRun.moveCurrIndex(100);
        assertFalse(newRun.isEmpty());
        assertEquals(newRun.getCurrIndex(), 100);
        r1.setEnd(99);
        assertEquals(r1.getEnd(), 99);
        r1.reset();
        assertEquals(r1.getCurrIndex(), 0);
        newRun.moveCurrIndex(10000);
        assertTrue(newRun.isEmpty());

        assertEquals(newRun.getStart(), 0);

        assertEquals(newRun.getLength(), 512);

        assertEquals(r1.toString(), "Start 0; end 99");

    }
}
