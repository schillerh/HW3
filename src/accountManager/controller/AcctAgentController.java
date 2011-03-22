package accountManager.controller;

import accountManager.model.AcctListModel;
import accountManager.model.AcctModel;
import accountManager.model.AcctAgentModel;
import accountManager.view.AcctAgentView;
import accountManager.view.AcctListView;
import accountManager.view.AcctView;
import accountManager.view.AgentRunView;
import accountManager.view.JFrameView;
import java.lang.*;


/**
 * The Class AcctController.
 * 
 */
public class AcctAgentController extends AbstractController {
	public static final String DAGENT = "Create Deposit Agent";
	public static final String WAGENT = "Create Withdraw Agent";
	private AcctModel acc;
	private String Option;
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
		this.Option=option;
		this.AcctList=listModel;
		this.Agent= new AcctAgentModel(acct,option);
		
		setModel(this.Agent);
		setView(new AcctAgentView(this.acc, this, option));
		((JFrameView)getView()).setVisible(true);
		
		/*setModel(acct);
		AcctModel temp =(AcctModel)getModel();
		setModel(agent);
		setView(new AcctAgentView(temp, this,(AcctAgentModel)getModel()));
		((JFrameView)getView()).setVisible(true);*/
	}
	/**
	 * Operation.
	 *
	 * @param amount the amount to be deposited or withdrawn
	 * @param srcCurrency the currency the the operation is to be performed in
	 * @param option the operation, either withdraw or deposit.
	 */
	/*public void operation(String amount, String option) {
		try{
		if (option=="Withdraw"){
			//this.acc.withdraw(amount, "Dollar");
			//((AcctModel)getModel()).withdraw(amount, "Withdraw");
			thread = new Thread(new Runnable(){
	             
    			public void run() {
    				while(((AcctAgentModel)getModel()).getState()=="Running"){
    				//((AcctAgentModel)getModel()).addModelListener(ModelListener l)
    				//((AcctAgentController)getController()).runAgent();
    					acc.withdraw(((AcctAgentModel)getModel()).getAmount().toString(), "Dollar");
    					((AcctAgentModel)getModel()).incrementCount();
    					((AcctAgentView)getModel()).updateTransfer();
    				System.out.println("Count: "+((AcctAgentModel)getModel()).getCount());
    				long l= (new Double(((AcctAgentModel)getModel()).getRate())).longValue();
    				l=1000/l;
    				try {
						thread.sleep(l);						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				}
    			}
            });
            thread.start();
		}
		else if (option == "Deposit"){
			//this.acc.deposit(amount,"Dollar");
			//((AcctAgentModel)getModel()).getAccount((AcctAgentModel)getModel()).deposit(amount, "Dollar");
			thread = new Thread(new Runnable(){
	             
    			public void run() {
    				while(((AcctAgentModel)getModel()).getState()=="Running"){
    				//((AcctAgentModel)getModel()).addModelListener(ModelListener l)
    				//((AcctAgentController)getController()).runAgent();
    					acc.deposit(((AcctAgentModel)getModel()).getAmount().toString(), "Dollar");
    					((AcctAgentModel)getModel()).incrementCount();
    					((AcctAgentView)getModel()).updateTransfer();
    				System.out.println("Count: "+((AcctAgentModel)getModel()).getCount());
    				long l= (new Double(((AcctAgentModel)getModel()).getRate())).longValue();
    				l=1000/l;
    				try {
						thread.sleep(l);						
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				}
    			}
            });
            thread.start();
		}
		else throw new UnsupportedOperationException();
		}catch(UnsupportedOperationException e){
			((AcctAgentView)getView()).errorDialog(e.getMessage());
		}
	}
	*/
	
	public synchronized void operation(String agentID, String amount, String rate, String option) {
		if(Double.valueOf(amount)>acc.getBalance()){
			System.out.println("Prechecked");
			((AcctAgentView)getView()).errorDialog("Agent withdraw amount is greater than the balance");	
		}
		else if (!amount.trim().matches("^[0-9]+$")&& !amount.trim().matches("^[0-9].+$"))
			((AcctAgentView)getView()).errorDialog("Entry field only allows digits");
		else{
		try
		{
		((AcctAgentModel)getModel()).setAgent(agentID, amount, rate);
		AcctList.addAgent(Agent);
		((AcctAgentView)getView()).renderRun((AcctAgentModel)getModel());
		
		//try{
			if (option=="Withdraw")
			{
				//this.acc.withdraw(amount, "Dollar");
				//((AcctModel)getModel()).withdraw(amount, "Withdraw");
				thread = new Thread(new Runnable(){
		             
	    			public synchronized void run() 
	    			{
	    				
	    				while(((AcctAgentModel)getModel()).getState()!="Stopped")
	    				//while(true)
	    				{
	    					System.out.println("Inside of withdraw while loop");
	    				//((AcctAgentModel)getModel()).addModelListener(ModelListener l)
	    				//((AcctAgentController)getController()).runAgent();
	    					System.out.println("Agent get amount : "+((AcctAgentModel)getModel()).getAmount());
	    					System.out.println("Account balance: "+ acc.getBalance());
	    					if(((AcctAgentModel)getModel()).getAmount()>acc.getBalance())
	    					{
	    						System.out.println("Inside withdraw if setting blocked.");
	    						((AcctAgentModel)getModel()).setState("Blocked");
	    						((AcctAgentView)getView()).updateTransfer();
	    						//try {
								//	this.wait();
								//} catch (InterruptedException e) {
									// TODO Auto-generated catch block
								//	e.printStackTrace();
								//}
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
				//this.acc.deposit(amount,"Dollar");
				//((AcctAgentModel)getModel()).getAccount((AcctAgentModel)getModel()).deposit(amount, "Dollar");
				thread = new Thread(new Runnable(){
		             
	    			public synchronized void run() {
	    				while(((AcctAgentModel)getModel()).getState()!="Stopped"){
	    				//while(true){
	    				//((AcctAgentModel)getModel()).addModelListener(ModelListener l)
	    				//((AcctAgentController)getController()).runAgent();
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
			//else throw new UnsupportedOperationException();
		}
		catch(UnsupportedOperationException e)
		{
				((AcctAgentView)getView()).errorDialog(e.getMessage());
		}
		}
		//runAgent();
		//if (option=="Deposit"){
			//setModel(new Agent)
			
			/*
			setView(new AgentRunView((AcctAgentModel)getModel(), this, "Deposit"));
			((JFrameView)getView()).setVisible(true);
			System.out.println("State: "+((AcctAgentModel)getModel()).getState());
			*/
			
			/*//while(((AcctAgentModel)getModel()).getState()=="Running"){
			while(((AcctAgentModel)getModel()).getCount()<=10){	
				System.out.println("inside while");
				System.out.println("Count: "+((AcctAgentModel)getModel()).getCount());
				((AcctAgentModel)getModel()).getAccount((AcctAgentModel)getModel()).deposit(((AcctAgentModel)getModel()).getAmount().toString(), "Dollar");
				System.out.println("Deposit called");
				System.out.println("Balance: "+((AcctAgentModel)getModel()).getAccount((AcctAgentModel)getModel()).getBalance());
				((AcctAgentModel)getModel()).incrementCount();
				((AcctAgentModel)getModel()).getAccount((AcctAgentModel)getModel()).deposit(((AcctAgentModel)getModel()).getAmount().toString(), "Dollar");
				Thread thread =Thread.currentThread();
				
				System.out.println("Before Sleep");
				thread.sleep(1000);
				System.out.println("After Sleep");
				//((AcctAgentModel)getModel()).threadTime();
			}*/
		
		//}
			//((AcctAgentModel)getModel()).getAccount((AcctAgentModel)getModel()).deposit(amount, "Dollar");
			//((AcctModel)getModel()).deposit(amount, "Dollars");
		//else if (option == "Withdraw"){
	
		//////////////////////////////////////////////////////////////////
		//TODO
		//setView(new AgentRunView((AcctAgentModel)getModel(), this, "Withdraw"));
		//	((JFrameView)getView()).setVisible(true);
		////////////////////////////////////////////////////////////	
		
		//((AcctAgentModel)getModel()).getAccount((AcctAgentModel)getModel()).withdraw(amount, "Dollar");
			//((AcctModel)getModel()).withdraw(amount,"Dollars" );
		//}
		//else throw new UnsupportedOperationException();
//		}catch (UnsupportedOperationException e){
//			((AcctAgentView)getView()).errorDialog(e.getMessage());
//		}
		/*catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	/*public void runAgent(){
		try{
			if (Option=="Withdraw"){
		
			//while(((AcctAgentModel)getModel()).getState()=="Running"){
				this.acc.withdraw(((AcctAgentModel)getModel()).getAmount().toString(), "Dollar");
				//((AcctAgentModel)getModel()).threadTime();
			//}
		}
		else if(Option=="Deposit"){
			//while(((AcctAgentModel)getModel()).getState()=="Running"){
				//System.out.println("inside while");
				this.acc.deposit(((AcctAgentModel)getModel()).getAmount().toString(), "Dollar");
				//System.out.println("Deposit called");

				//((AcctAgentModel)getModel()).getAccount((AcctAgentModel)getModel()).deposit(((AcctAgentModel)getModel()).getAmount().toString(), "Dollar");Thread thread =Thread.currentThread();
				//((AcctAgentModel)getModel()).threadTime();
			}
		}catch(UnsupportedOperationException e){
			((AcctAgentView)getView()).errorDialog(e.getMessage());
		}
	}
	*/
		
		
	public void stopAgent(){
		((AcctAgentModel)getModel()).setState("Stopped");
	}
}