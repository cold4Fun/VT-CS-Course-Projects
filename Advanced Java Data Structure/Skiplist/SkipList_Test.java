

import student.TestCase;
/**
 *  This is a test case for skiplist class
 * @author Weiting Li
 * @version 09/16/2021
 */
public class SkipList_Test extends TestCase {
  private SkipList<String,NewRectangle> test = new SkipList<String,NewRectangle>();
  private NewRectangle t1 = new NewRectangle(1,2,3,4); 
  private NewRectangle t2 = new NewRectangle(4,5,6,7);
  private NewRectangle t3 = new NewRectangle(8,9,10,11);
  private NewRectangle t4 = new NewRectangle(4,3,2,1);
  private NewRectangle t5 = new NewRectangle(5,3,2,1);
  //Random random = new TestableRandom();
  //SkipListIterator a = new SkipListIterator();
  private KVPair<String,NewRectangle> kvp1 = new KVPair<String,NewRectangle> ("z1",t1);
  private KVPair<String,NewRectangle> kvp2 = new KVPair<String,NewRectangle> ("z2",t2); 
  private KVPair<String,NewRectangle> kvp3 = new KVPair<String,NewRectangle> ("z3",t3);
  private KVPair<String,NewRectangle> kvp4 = new KVPair<String,NewRectangle> ("z1",t4); 
  private KVPair<String,NewRectangle> kvp5 = new KVPair<String,NewRectangle> ("z1",t5);
  
  
  /**
      * Set up
      */
     public void setUp() {
         //nothing
     }

      /**
       * Test case for insert method
       */
     
     public void test_Insert() {
      //setup();
      test.insert(kvp1);
      assertEquals(test.size(),1);
      // // initiate rectangle t2
      NewRectangle d = new NewRectangle(100,2000,3,42);
      //Assign values 
      KVPair<String,NewRectangle> c = new KVPair<String,NewRectangle> ("z2",d); 
      test.insert(c);
      test.dump();
      assertEquals(test.size(),2);
     }
     
     
     /**
      * Test case 2 for insert method
      * adding two same key into the skiplist
      */
     public void test_Insert2() {
         
         test.insert(kvp1);
         //System.out.println(test1.search("z1").toString());
         //System.out.println(test1.size());
         test.insert(kvp4);
         //systemOut().getHistory();
         assertEquals(test.size(),2);
         
        }
     
     /**
      * Test case for search method
     */
      
     public void test_search(){
         //add
         //assertNull(test.search("z1"));
    	 test.dump();
         test.insert(kvp1);
         test.insert(kvp2);
         test.insert(kvp3);
         test.insert(kvp4);
         test.insert(kvp5);
         test.dump();
         //assertNotNull(test.search("z2"));
         //System.out.println(test.search("z1"));
         //System.out.println(test.search("z1").get(0));
         assertEquals(test.search("z1").size(),3);
         
     }
     /**
     public void test_dump(){
        // public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();
         test.insert(kvp1);
         test.insert(kvp2);
         test.insert(kvp3);
         test.insert(kvp4);
         test.insert(kvp5);
         //assertTrue(test.dump());
         //systemOut().clearHistory();
         
     }
     public void test_dump2(){
         //test.dump();
         //assertFalse(test.dump());
         //assertEquals(test.size(), 0);
         test.dump();
         //systemOut().getHistory();
         //assertTrue(systemOut().getHistory().contains("SkipList size is: 0"));
         //assertTrue(.contains("SkipList dump:"));
     }
     */
     public void test_remove(){
     //add
     //.remove("z");
     assertNull(test.remove("z"));
     test.insert(kvp1);
     test.insert(kvp2);
     test.insert(kvp3);
     test.insert(kvp4);
     test.insert(kvp5);
     
     //systemOut().clearHistory();
     //assertEquals(test.remove("z2"),kvp2);
     assertEquals(test.size(),5);
     test.remove("z3");
     assertEquals(test.size(),4);
     test.remove("z1");
     assertEquals(test.size(),3);
     test.remove("z1");
    
     assertNull(test.remove("zzz"));
     
     assertEquals(test.size(),2);
     
     test.dump();
     }
     
     public void test_remove2(){
         assertNull(test.remove("z1"));
         //if(test.remove("z1")== null) System.out.println("remove z1\n" 
         //+ "Rectangle not found: (z1)");
     }
     
     public void test_removeByValue(){
    	 
    	 NewRectangle t7 = new NewRectangle(9,9,9,9);
    	 KVPair<String,NewRectangle> kvp7 = new KVPair<String,NewRectangle> ("z7",t7);
    	 assertNull(test.removeByValue(t7));
    	 test.insert(kvp7);
    	 test.removeByValue(t7);
    	 assertEquals(test.size(),0);
         test.insert(kvp1);
         test.insert(kvp2);
         test.insert(kvp3);
         test.insert(kvp4);
         test.insert(kvp5);
         
         //System.out.println(test.remove("1, 2, 3, 4"));
         //System.out.println(test.size());
         //System.out.println(test.removeByValue(t1));
         //System.out.println(test.removeByValue(t3));
         //assertNull(test.removeByValue(t7));
         
         test.removeByValue(t3);
         assertEquals(test.size(),4);
         test.removeByValue(t1);
         assertEquals(test.size(),3);
         test.dump();
         assertNull(test.removeByValue(t7));
         //System.out.println(test.removeByValue(t1));
         //System.out.println(test.removeByValue(t1));
         //System.out.println(test.removeByValue(t1));
         //System.out.println(test.removeByValue(t3).getValue());
         //System.out.println(test.removeByValue(t5).getValue());
         //assertNotNull(test.removeByValue(kvp1.getValue()));
         // assertEquals(test.size(),4);
         //assertNull(test.removeByValue());
     }
     
    
     
}
