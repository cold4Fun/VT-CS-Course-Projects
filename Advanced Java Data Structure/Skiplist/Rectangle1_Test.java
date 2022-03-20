import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import student.TestCase;

public class Rectangle1_Test extends TestCase{
	
	//String[] stupidfile;
	/**
     * Set up
     */
    public void setUp() {
        //nothing
    }
    
    public void test_Main2() throws Exception {
    	Exception thrown = null;
    	Rectangle1.main(new String[]{"Data/P1test1.txt"});
    	try

    	{

    	    //call the method that should throw a NoSuchElementException

    	    Rectangle1.main(new String[]{"Data/P1ttest3.txt"});
    	    

    	}

    	catch (Exception exception)

    	{

    	    //¡±Catch¡± and store the exception
    		String message = "Invalid file";
    	    thrown  = exception;
    	    assertEquals(message, exception.getMessage());
    	    throw exception;
    	}

    	//assert that an exception was thrown

    	//assertNotNull(thrown);

    	//assert that the correct exception was thrown

    	//sassertTrue(thrown instanceof FileNotFoundException);
    	//assertNotNull(thrown);
    	//thrown.getMessage();
    }
}
