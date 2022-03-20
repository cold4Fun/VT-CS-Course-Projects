import student.TestCase;
public class KVPair_Test extends TestCase{
	private NewRectangle t1 = new NewRectangle(1,2,3,4); 
	private NewRectangle t2 = new NewRectangle(4,5,6,7);
	private NewRectangle t3 = new NewRectangle(8,9,10,11);
	private NewRectangle t4 = new NewRectangle(4,3,2,1);
	//private NewRectangle t5 = new NewRectangle(5,3,2,1);
	private KVPair<String,NewRectangle> kvp1 = new KVPair<String,NewRectangle> ("z1",t1);
	private KVPair<String,NewRectangle> kvp2 = new KVPair<String,NewRectangle> ("z2",t2); 
	private KVPair<String,NewRectangle> kvp3 = new KVPair<String,NewRectangle> ("z3",t3);
	private KVPair<String,NewRectangle> kvp4 = new KVPair<String,NewRectangle> ("z1",t4); 
	//private KVPair<String,NewRectangle> kvp5 = new KVPair<String,NewRectangle> ("z1",t5);

	/**
     * Set up
     */
    public void setUp() {
        //nothing
    }
    
    /**
     * Test case for compareTo method
     */
   
   public void test_compareTo() {
	   assertEquals(kvp1.compareTo(kvp1), 0);
	   assertEquals(kvp1.compareTo(kvp4), 0);
	   assertEquals(kvp1.compareTo(kvp2), -1);
	   assertEquals(kvp3.compareTo(kvp2), 1);
   }
   
   /**
    * Test case for toString method
    */
  
  public void test_toString() {
	   assertEquals(kvp1.toString(), "(z1, 1, 2, 3, 4)");
	   assertEquals(kvp2.toString(), "(z2, 4, 5, 6, 7)");
	   //assertEquals(kvp1.compareTo(kvp2), -1);
	   //assertEquals(kvp3.compareTo(kvp2), 1);
  }


}
