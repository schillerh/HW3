package accountManager.controller;

import accountManager.model.AcctModel;
import accountManager.view.AcctView;
import accountManager.view.JFrameView;


/**
 * The Class AcctController.
 * 
 */
public class AcctController extends AbstractController {

	/**
	 * Instantiates a new Account Controller.
	 *
	 * @param acct The AcctModel object to be displayed in the view
	 * @param currency the currency to be displayed
	 */
	public AcctController(AcctModel acct, String currency) {
		setModel(acct);
		setView(new AcctView((AcctModel)getModel(), this, currency));
		((JFrameView)getView()).setVisible(true);
	}
	

	/**
	 * Operation.
	 *
	 * @param amount the amount to be deposited or withdrawn
	 * @param srcCurrency the currency the the operation is to be performed in
	 * @param option the operation, either withdraw or deposit.
	 */
	public void operation(String amount, String srcCurrency, String option ) {
		try{
		if (option=="Deposit"){
			((AcctModel)getModel()).deposit(amount, srcCurrency);
		}
		else if (option == "Withdraw"){
			
			((AcctModel)getModel()).withdraw(amount, srcCurrency);
		}
		else throw new UnsupportedOperationException();
		}catch(UnsupportedOperationException e){
			((AcctView)getView()).errorDialog(e.getMessage());
		}
	}
}