package accountManager.controller;

import accountManager.model.AcctListModel;
import accountManager.model.AcctModel;
import accountManager.model.AcctAgentModel;
import accountManager.view.AcctAgentView;
import accountManager.view.JFrameView;


/**
 * The Class AcctController.
 * 
 */
public class AcctAgentController extends AbstractController {
	public static final String DAGENT = "Create Deposit Agent";
	public static final String WAGENT = "Create Withdraw Agent";
	private AcctModel acc;
	private Thread thread;
	private AcctAgentModel Agent;
	private AcctListModel AcctList;
	/**
	 * Instantiates a new Account Controller.
	 *
	 * @param acct The AcctModel object to be displayed in the view
	 * @param listModel 
	 * @param currency the currency to be displayed
	 */
	public AcctAgentController(AcctModel acct, AcctListModel listModel, String option) {
		this.acc=acct;
		this.AcctList=listModel;
		this.Agent= new AcctAgentModel(acct,option);
		
		setModel(this.Agent);
		setView(new AcctAgentView(this.acc, this, option));
		((JFrameView)getView()).setVisible(true);
		
	}
	/**
	 * Operation.
	 *
	 * @param amount the amount to be deposited or withdrawn
	 * @param srcCurrency the currency the the operation is to be performed in
	 * @param option the operation, either withdraw or deposit.
	 */
		
	public synchronized void operation(String agentID, String amount, String rate, String option) {
		if(Double.valueOf(amount)>acc.getBalance()){
			System.out.println("Prechecked");
			((AcctAgentView)getView()).errorDialog("Agent withdraw amount is greater than the balance");	
		}
		else if (!amount.trim().matches("^[0-9]+$")&& !amount.trim().matches("^[0-9].+$"))
			((AcctAgentView)getView()).errorDialog("Amount field only allows positive digits");
		else if (!agentID.trim().matches("^[0-9]+$"))
			((AcctAgentView)getView()).errorDialog("Agent ID field only allows positive digits");
		else if (!rate.trim().matches("^[1-9]+$")&& !amount.trim().matches("^[1-9].+$"))
			((AcctAgentView)getView()).errorDialog("Amount field only allows positive non-zero digits");
		else{
		try
		{
		((AcctAgentModel)getModel()).setAgent(agentID, amount, rate);
		AcctList.addAgent(Agent);
		((AcctAgentView)getView()).renderRun((AcctAgentModel)getModel());
		
		
			if (option=="Withdraw")
			{
				//this.acc.withdraw(amount, "Dollar");
				//((AcctModel)getModel()).withdraw(amount, "Withdraw");
				thread = new Thread(new Runnable(){
		             
	    			public synchronized void run() 
	    			{
	    				
	    				while(((AcctAgentModel)getModel()).getState()!="Stopped")
	    				{
	    					System.out.println("Inside of withdraw while loop");
	    					System.out.println("Agent get amount : "+((AcctAgentModel)getModel()).getAmount());
	    					System.out.println("Account balance: "+ acc.getBalance());
	    					if(((AcctAgentModel)getModel()).getAmount()>acc.getBalance())
	    					{
	    						System.out.println("Inside withdraw if setting blocked.");
	    						((AcctAgentModel)getModel()).setState("Blocked");
	    						((AcctAgentView)getView()).updateTransfer();
	    					}
	    					try{
	    						acc.agentwithdraw(((AcctAgentModel)getModel()).getAmount().toString(), "Dollar");
	    						((AcctAgentModel)getModel()).setState("running");
	    						((AcctAgentModel)getModel()).incrementCount();
	    						((AcctAgentModel)getModel()).setTransferred();
	    						((AcctAgentView)getView()).updateTransfer();
	    						System.out.println("Count: "+((AcctAgentModel)getModel()).getCount());
	    						long l= (new Double(((AcctAgentModel)getModel()).getRate())).longValue();
	    						l=1000/l;
	    						Thread.sleep(l);						
	    						} 
	    						catch (InterruptedException e) 
	    						{
	    							// TODO Auto-generated catch block
	    							e.printStackTrace();
	    						}
	    					}
	    				}
	    				
	    			});
	            thread.start();
			}
			else if (option == "Deposit"){
				thread = new Thread(new Runnable(){
		             
	    			public synchronized void run() {
	    				while(((AcctAgentModel)getModel()).getState()!="Stopped"){
	    					acc.deposit(((AcctAgentModel)getModel()).getAmount().toString(), "Dollar");
	    					((AcctAgentModel)getModel()).incrementCount();
	    					((AcctAgentModel)getModel()).setTransferred();
	    					((AcctAgentView)getView()).updateTransfer();
	    				System.out.println("Count: "+((AcctAgentModel)getModel()).getCount());
	    				long l= (new Double(((AcctAgentModel)getModel()).getRate())).longValue();
	    				l=1000/l;
	    				try {
							Thread.sleep(l);						
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						this.notify();
	    				}
	    			}
	            });
	            thread.start();
			}
		}
		catch(UnsupportedOperationException e)
		{
				((AcctAgentView)getView()).errorDialog(e.getMessage());
		}
		}
	}
			
	public void stopAgent(){
		((AcctAgentModel)getModel()).setState("Stopped");
	}
}