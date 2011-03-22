package accountManager.model;
import junit.framework.TestCase;
import accountManager.model.AcctListModel;



/**
 * The Class AcctListModelTest.
 */
public class AcctListModelTest extends TestCase {

	/** The account list model. */
	private AcctListModel acctListModel;
	
	/** The account model. */
	private AcctModel acctModel;
	
	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		acctListModel = new AcctListModel();
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
		acctListModel = null;
		System.gc();  
	}

	/**
	 * Test add account.
	 */
	public final void testAddAccount() {
		try
		{
		acctModel= new AcctModel("name", 1432, 2938.00);
		acctListModel.addAccount(acctModel);
		}
		catch( Exception e )
		{
			System.out.println( e.getMessage() );
		}
		
		assertEquals( acctListModel.getAccounts().get(0), acctModel);
		
	}


}
