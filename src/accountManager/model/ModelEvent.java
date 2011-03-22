package accountManager.model;
import java.awt.event.ActionEvent;

/**
 * The Class ModelEvent.
 */
public class ModelEvent extends ActionEvent {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5747756222457981235L;

	/** The amount. */
	private double amount;
	
	/** The currency. */
	private String currency;
	
	/**
	 * Instantiates a new model event.
	 *
	 * @param obj Object of the event
	 * @param id ID of the event
	 * @param currency the currency of the event
	 * @param amount the account balance
	 */
	public ModelEvent(Object obj, int id, String currency, double amount){
		super(obj, id, currency);
		this.amount = amount;
		this.currency = currency;
	}
	
	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount(){return amount;}
	
	/**
	 * Gets the currency.
	 *
	 * @return the currency
	 */
	public String  getCurrency(){return currency;}
}
