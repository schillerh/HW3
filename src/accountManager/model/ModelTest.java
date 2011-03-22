package accountManager.model;

import junit.framework.*;

/**
 * The Class ModelTest.
 */
public class ModelTest extends TestCase{



	/**
	 * Creates a new TestSuite
	 *
	 * @return the test suite
	 */
	public static TestSuite suite()
	{
		TestSuite acctModel = new TestSuite(AcctModelTest.class);
		TestSuite acctListModel = new TestSuite(AcctListModelTest.class);
		
		TestSuite result = new TestSuite();
		
		result.addTest( acctModel );
		result.addTest( acctListModel );
		
		return result;
	}
	
}
