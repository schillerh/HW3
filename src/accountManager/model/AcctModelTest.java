	package accountManager.model;
	import junit.framework.TestCase;
	import java.text.DecimalFormat;
	


/**
	 * The Class AcctModelTest.
	 */
	public class AcctModelTest extends TestCase {

		/** The account model. */
		private AcctModel acctModel;
		
		/** The Deposit value */
		private String Deposit;
		
		/** The Withdraw. */
		private String Withdraw;
		
		/** The currency. */
		private String currency;
		
		/** The balance. */
		private Double balance;
		
		/** The currency2. */
		private String currency2;
		
		/** The decimal formatter */
		DecimalFormat df= new DecimalFormat("#0.00");
		
		/* (non-Javadoc)
		 * @see junit.framework.TestCase#setUp()
		 */
		protected void setUp() throws Exception {
			super.setUp();
			balance= 200.00;
			acctModel = new AcctModel("abcd", 123, balance);
			Deposit= "100";
			Withdraw="50";
			currency="USD";
			currency2="Yen";
			
		}

		/* (non-Javadoc)
		 * @see junit.framework.TestCase#tearDown()
		 */
		protected void tearDown() throws Exception {
			super.tearDown();
			acctModel = null;
			Withdraw=null;
			Deposit=null;
			currency=null;
			System.gc();  
		}

		/**
		 * Tests the AcctModel method deposit().
		 */
		public final void testDeposit() {
			try
			{
			acctModel.deposit(Deposit, currency);
			}
			catch( Exception e )
			{
				System.out.println( e.getMessage() );
			}
			
			assertEquals( acctModel.getBalance(currency), Double.valueOf(df.format(balance+Double.valueOf(Deposit))));
			
		}

		/**
		 * Tests the AcctModel method withdraw().
		 */
		public final void testWithdraw() {
			try
			{
			acctModel.withdraw(Withdraw, currency);
			}
			catch( Exception e )
			{
				System.out.println( e.getMessage() );
			}
			
			assertEquals( acctModel.getBalance(currency), balance - Double.valueOf(Withdraw));
			
		}
		
		/**
		 * Tests the AcctModel method toUSD().
		 */
		public final void testToUSD() {
			try
			{
			acctModel.toUSD(balance, currency2);
			}
			catch( Exception e )
			{
				System.out.println( e.getMessage() );
			}
			
			assertEquals( acctModel.toUSD(balance, currency2), Double.valueOf(df.format(balance/94.1)));
			
		}
		
		/**
		 * Tests the AcctModel method fromUSD().
		 */
		public final void testFromUSD() {
			try
			{
			acctModel.fromUSD(balance, currency2);
			}
			catch( Exception e )
			{
				System.out.println( e.getMessage() );
			}
			
			assertEquals( acctModel.fromUSD(balance, currency2), Double.valueOf(df.format(balance*94.1)));
			
		}

	}
