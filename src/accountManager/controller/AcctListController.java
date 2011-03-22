package accountManager.controller;
import accountManager.model.AcctListModel;
import accountManager.view.AcctListView;
import accountManager.model.AcctAgentModel;
import accountManager.view.JFrameView;


import java.io.*;
import java.text.ParseException;


/**
 * The Class AcctListController.
 */
public class AcctListController extends AbstractController {
	public static final String DAGENT = "Create Deposit Agent";
	public static final String WAGENT = "Create Withdraw Agent";
	
	
	/**
	 * Instantiates a new Account List Controller.
	 *
	 * @param fileName the file name of the input file passed from commandline
	 * @throws FileNotFoundException the file not found exception
	 * @throws ParseException the parse exception
	 */
	public AcctListController(String fileName) throws FileNotFoundException, ParseException {
		setModel(new AcctListModel(fileName));
		setView(new AcctListView((AcctListModel)getModel(), this));
		((JFrameView)getView()).setVisible(true);
	}

	public void addAgent(AcctAgentModel model){
	((AcctListModel)getModel()).addAgent(model);
	}
	
	/**
	 * Operation.
	 *
	 * @param option the currency to view the Account in
	 * @param index the Account to be viewed
	 */
	public void operation(String option, int index ){
		if (option == DAGENT){
			new AcctAgentController(((AcctListModel)getModel()).getAccounts().get(index),((AcctListModel)getModel()),"Deposit");
		}
		else if (option == WAGENT){
			new AcctAgentController(((AcctListModel)getModel()).getAccounts().get(index),((AcctListModel)getModel()),"Withdraw");
		}
		else
		new AcctController(((AcctListModel)getModel()).getAccounts().get(index),option);
		
	
}}