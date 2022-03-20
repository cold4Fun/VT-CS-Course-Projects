import student.TestCase;
public class NewRectangle_test extends TestCase {
    private NewRectangle test = new NewRectangle(1,2,3,4);
    private NewRectangle test2 = new NewRectangle(1,3,3,4);
    private NewRectangle test3 = new NewRectangle(1,2,4,4);
    private NewRectangle test4 = new NewRectangle(1,2,3,5);
    private NewRectangle test5 = new NewRectangle(1,2,3,4);
    private NewRectangle test6 = new NewRectangle(2,2,3,4);
    /**
     * Set up
     */
    public void setUp() {
        //nothing
    }
    /**
     * Test case for getX method
     */
    public void test_getX() {
        assertEquals((int)test.getX(),1);
    }
    /**
     * Test case for getY method
     */
    public void test_getY() {
        assertEquals((int)test.getY(),2);
    }
    /**
     * Test case for getWitdth method
     */
    public void test_getWidth() {
        assertEquals((int)test.getWidth(),3);
    }
    /**
     * Test case for getHeight method
     */
    public void test_getHeight() {
        assertEquals((int)test.getHeight(),4);
    }
    /**
     * Test case for Equals method
     */
    public void test_Equals() {
        assertFalse(test.equals(test2));
        assertFalse(test.equals(test3));
        assertFalse(test.equals(test4));
        assertTrue(test.equals(test5));
        assertFalse(test.equals(test6));
    }
    /**
     * Test case for toString method
     */
    public void test_toString() {
        //test.toString();
        assertEquals("1, 2, 3, 4",test.toString());
        assertEquals("1, 3, 3, 4",test2.toString());
    }
    /**
     * Test case for do method
     */
    public void test_doOverLap() {
        test = new NewRectangle(2,3,4,5);
        NewRectangle test7 = new NewRectangle(0,3,1,5);
        NewRectangle test8 = new NewRectangle(7, 3, 4, 5);
        NewRectangle test9 = new NewRectangle(2,3,4,5);
        NewRectangle test10 = new NewRectangle(2,1,4,2);
        assertFalse(test.doOverlap(test7));
        assertFalse(test.doOverlap(test8));
        assertTrue(test.doOverlap(test9));
        assertFalse(test.doOverlap(test10));
    }
    
}
