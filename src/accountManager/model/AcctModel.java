package accountManager.model;
import java.lang.*;
import java.text.DecimalFormat;

/**
 * The Class AcctModel. It is the holder class for an individual account's data.
 */
public class AcctModel extends AbstractModel {
	
	/** The account name. */
	private String acctName;
	
	/** The account number. */
	private int acctNumber;
	
	/** The account balance. */
	private double acctBalance;
	
	/** The formatter for double values. */
	DecimalFormat df= new DecimalFormat("#0.00");
	
	/**
	 * Instantiates a new account model.
	 *
	 * @param aName the name on the account
	 * @param aNumber the account ID number
	 * @param aBalance the account balance in USD
	 */
	public AcctModel(String aName, int aNumber, double aBalance) {
		this.acctName=aName;
		this.acctNumber=aNumber;
		this.acctBalance= aBalance;
		printAcct();
	}

	/**
	 * Gets the balance.
	 *
	 * @return the balance
	 */
	public Double getBalance(){
		return acctBalance;
	}
	
	/**
	 * Gets the balance in specified currency.
	 *
	 * @param currency the currency to return the balance in
	 * @return the balance
	 */
	public Double getBalance(String currency){
		return this.fromUSD(acctBalance, currency);
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return this.acctName;
	}
	
	/**
	 * Gets the account number.
	 *
	 * @return the account number
	 */
	public Integer getAcctNumber(){
		return this.acctNumber;
	}
	
	/**
	 * Deposit.
	 *
	 * @param amount amount to be deposited
	 * @param srcCurrency the currency the deposit is in
	 */
synchronized public void deposit(String amount, String srcCurrency) {
		if (!amount.trim().matches("^[0-9]+$")&& !amount.trim().matches("^[0-9].+$"))
			  throw new UnsupportedOperationException("Entry field only allows digits");
		double aAmount = Double.valueOf(amount.trim()).doubleValue();
		this.acctBalance+=this.toUSD(aAmount, srcCurrency);
		ModelEvent me = new ModelEvent(this, 3, srcCurrency, Double.valueOf(df.format(this.fromUSD(this.acctBalance, srcCurrency))));
		notifyChanged(me);
		notifyAll();
		}

	/**
	 * Withdraw.
	 *
	 * @param amount the amount to withdraw
	 * @param srcCurrency the currency the withdraw is in
	 * @throws InterruptedException 
	 */
synchronized public void agentwithdraw(String amount, String srcCurrency) throws InterruptedException {
		  if (!amount.trim().matches("^[0-9]+$")&& !amount.trim().matches("^[0-9].+$"))
			  throw new UnsupportedOperationException("Entry field only allows digits");
		double aAmount = Double.valueOf(amount.trim()).doubleValue();
		while (this.toUSD(aAmount, srcCurrency)>this.acctBalance){
			wait();
			//throw new UnsupportedOperationException("Insufficient funds: amount to withdraw "+amount+" is greater than available funds: "+String.valueOf(this.fromUSD(acctBalance,srcCurrency)));
		}
		this.acctBalance= this.acctBalance-this.toUSD(aAmount, srcCurrency);
		
		ModelEvent me = new ModelEvent(this, 2, srcCurrency, Double.valueOf(df.format(this.fromUSD(acctBalance, srcCurrency))));
		notifyChanged(me);
		//return Double.valueOf(df.format(this.fromUSD(this.acctBalance, srcCurrency)));
	}
synchronized public void withdraw(String amount, String srcCurrency) {
	if (!amount.trim().matches("^[0-9]+$")&& !amount.trim().matches("^[0-9].+$"))
		  throw new UnsupportedOperationException("Entry field only allows digits");
	double aAmount = Double.valueOf(amount.trim()).doubleValue();
	if (this.toUSD(aAmount, srcCurrency)>this.acctBalance){
		throw new UnsupportedOperationException("Insufficient funds: amount to withdraw "+amount+" is greater than available funds: "+String.valueOf(this.fromUSD(acctBalance,srcCurrency)));
	}
	else{
	this.acctBalance= this.acctBalance-this.toUSD(aAmount, srcCurrency);
	
	ModelEvent me = new ModelEvent(this, 2, srcCurrency, Double.valueOf(df.format(this.fromUSD(acctBalance, srcCurrency))));
	notifyChanged(me);
	//return Double.valueOf(df.format(this.fromUSD(this.acctBalance, srcCurrency)));
	}
}
	/**
	 * Converts the balance to a currency from USD
	 *
	 * @param aAmount the Balance of the account in USD
	 * @param toCurrency the currency to be converted to
	 * @return the account balance in specified currency
	 */
	public double fromUSD(double aAmount, String toCurrency) {
		if (toCurrency=="Euro"){
			return  Double.valueOf(df.format(aAmount*.79));
		}
		else if (toCurrency=="Yen"){
			return Double.valueOf(df.format(aAmount*94.1));
		}
		else return Double.valueOf(df.format(aAmount));
	}
	
	/**
	 * Gets the Account name
	 *
	 * @return the Account name
	 */
	//public String aname(){
		//return this.acctName;
//	}
	
	/**
	 * Gets the Account Number ID
	 *
	 * @return the Account Number ID
	 */
	//public Integer aid(){
		//return this.acctNumber;
	//}
	
	/**
	 * Get the Account balance
	 *
	 * @return the Account balance
	 */
	//public Double abalance(){
		//return this.acctBalance;
//	}
	
	/**
	 * Prints the acct.
	 */
	public void printAcct(){
		System.out.println(this.acctName+" : "+ this.acctNumber +" : "+ this.acctBalance);
	}
	
	/**
	 * Converts Account balance to USD from specified currency
	 *
	 * @param aAmount the Account balance
	 * @param fromCurrency the currency that the account Balance is in
	 * @return Account balance in USD
	 */
	public double toUSD(double aAmount, String fromCurrency){
		if (fromCurrency=="Yen"){
			return Double.valueOf(df.format(aAmount/94.1));
		}
		else if (fromCurrency=="Euro"){
			return Double.valueOf(df.format(aAmount/.79));
		}
		else return Double.valueOf(df.format(aAmount));
	}
}