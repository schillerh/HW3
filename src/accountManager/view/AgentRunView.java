package accountManager.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import accountManager.controller.AcctAgentController;
import accountManager.controller.AcctController;
import accountManager.model.AcctAgentModel;
import accountManager.model.AcctModel;
import accountManager.model.ModelEvent;
import java.lang.*;

/**
 * The Class AcctView.
 */
public class AgentRunView extends JFrameView {
	
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4193418819379590239L;
	
	/** The Constant DEPOSIT. */
	public static final String DEPOSIT = "Deposit";
	
	/** The Constant WITHDRAW. */
	public static final String WITHDRAW = "Withdraw";
	
	/** The Constant DISMISS. */
	public static final String DISMISS = "Dismiss";
	
	private static final String STOP= "Stop Agent";
	
	/** The currency that the Account is being viewed in */
	private String srcCurrency;
	private String Option;
	
	/** The text field. */
	private JTextField textField = new JTextField(); 

	/** Transfer field. To display total transferred. */
	private JTextField tranField = new JTextField();

	private JTextField countField = new JTextField();

	private JTextField stateField = new JTextField();
	
	/** The balance field. */
	private JTextField amountField = new JTextField();
	
	/** The entry field. */
	private JTextField entryField = new JTextField();
	
	
	/** The rate of operation field */
	private JTextField rateOpField = new JTextField();
	
	private Thread thread;
	private AcctAgentModel agent;
	
	public AgentRunView(final AcctAgentModel model, AcctAgentController controller, final String option) { 
		super(model, controller); 
		//textField.setText(modelabalance().toString());
		this.Option=option;	   
		this.agent=model;
	    
	    try{
	    GridBagConstraints constraints = new GridBagConstraints();
	            JPanel rootPanel = new JPanel();
	            rootPanel.setLayout(new GridBagLayout());

	            

	            JPanel agentPanel = new JPanel();
	            agentPanel.setLayout(new GridBagLayout());
	            agentPanel.setBorder(BorderFactory.createTitledBorder("Transaction"));

	            // Show available funds
	            JLabel amountLabel = new JLabel("Amount in $:", null, SwingConstants.RIGHT);
	            constraints.anchor = GridBagConstraints.EAST;
	            agentPanel.add(amountLabel, constraints);
	            amountField = new JTextField(10);
	            
	            amountField.setText(model.getAmount().toString());
	            amountField.setEditable(false);
	            constraints.gridx = 1;
	            agentPanel.add(amountField, constraints);

	            // Make a pretty separator
	            JSeparator separator = new JSeparator();
	            //separator.setBorder(BorderFactory.createEtchedBorder());
	            constraints.gridx = 0;
	            constraints.gridy = 1;
	            constraints.gridwidth = 2;
	            constraints.fill = GridBagConstraints.HORIZONTAL;
	            agentPanel.add(separator, constraints);

	            // Add an entry field with a label
	            JLabel rateOpLabel = new JLabel("Operations Per Second: ", null, SwingConstants.RIGHT);
	            constraints.anchor = GridBagConstraints.EAST;
	            constraints.gridwidth = 1;
	            constraints.gridy = 2;
	            agentPanel.add(rateOpLabel, constraints);

	            rateOpField = new JTextField(model.getRate().toString());
	            rateOpField.setEditable(false);
	            constraints.fill = GridBagConstraints.HORIZONTAL;
	            constraints.gridx = 1;
	            constraints.gridy = 2;
	            agentPanel.add(rateOpField, constraints);

	            // Add an Operation rate field with label
	            JLabel tranLabel = new JLabel("Amount in $ transferred: ", null, SwingConstants.RIGHT);
	            constraints.anchor = GridBagConstraints.EAST;
	            constraints.gridwidth = 1;
	            constraints.gridx=0;
	            constraints.gridy = 3;
	            agentPanel.add(tranLabel, constraints);

	            tranField = new JTextField(model.getTransferred().toString());
	            tranField.setEditable(false);
	            constraints.fill = GridBagConstraints.HORIZONTAL;
	            constraints.gridx = 1;
	            constraints.gridy = 3;
	            agentPanel.add(tranField, constraints);

	            // Add a count field
	            JLabel countLabel = new JLabel("Count: ", null, SwingConstants.RIGHT);
	            constraints.anchor = GridBagConstraints.EAST;
	            constraints.gridwidth = 1;
	            constraints.gridx=0;
	            constraints.gridy = 4;
	            agentPanel.add(countLabel, constraints);

	            countField = new JTextField(model.getTransferred().toString());
	            countField.setEditable(false);
	            constraints.fill = GridBagConstraints.HORIZONTAL;
	            constraints.gridx = 1;
	            constraints.gridy = 4;
	            agentPanel.add(countField, constraints);
	            
	            // Add a button to start the Agent
	            JButton button = new JButton(STOP);
	            button.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent event) {
	                	((AcctAgentController)getController()).stopAgent();
	                	//entryField.setText("0.00");
	                }
	            });
	            constraints = new GridBagConstraints();
	            constraints.gridx = 0;
	            constraints.gridy = 5;
	            agentPanel.add(button, constraints);

	            // Add a button to dismiss
	            button = new JButton("Dismiss");
	            button.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent event) {
	                    dispose();
	                }
	            });
	            constraints.gridx = 1;
	            agentPanel.add(button, constraints);

	            // Add the transaction panel
	            constraints = new GridBagConstraints();
	            constraints.insets = new Insets(10, 10, 10, 10);
	            constraints.ipadx = constraints.ipady = 10;
	            rootPanel.add(agentPanel, constraints);

	            /*
	            // Add a button to close the window
	            button = new JButton("Dismiss");
	            button.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent event) {
	                    dispose();
	                }
	            });
	            constraints = new GridBagConstraints();
	            constraints.insets = new Insets(0, 10, 10, 10);
	            constraints.gridx = 0;
	            constraints.gridy = 1;
	            constraints.anchor = GridBagConstraints.LAST_LINE_END;
	            rootPanel.add(button, constraints);

				*/

	            this.setContentPane(rootPanel);
	            this.pack();
	            this.setTitle(option + " agent " + model.getID() + "for account "+ model.getAcctID());
	            this.setVisible(true);
	            thread = new Thread(new Runnable(){
	    			public void run() {
	    				while(model.getState()=="Running"){
	    				//((AcctAgentModel)getModel()).addModelListener(ModelListener l)
	    				((AcctAgentController)getController()).runAgent();
	    				model.incrementCount();
	    				updateTransfer();
	    				System.out.println("Count: "+model.getCount());
	    				long l= (new Double(((AcctAgentModel)getModel()).getRate())).longValue();
	    				l=1000/l;
	    				try {
							Thread.sleep(l);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	    				}
	    			}
	            });
	            thread.start();
	           

	        } catch (Exception ex) {
	            ex.printStackTrace();   
	        }
	        
	    }

	    /**
    	 * Clear entry.
    	 */
      	public void errorDialog(String msg){
    		 JOptionPane.showMessageDialog(rootPane, msg);
    	}

	    /**
    	 * Update balance.
    	 */
    	public void updateTransfer(){
    	 this.agent.setTransferred(this.agent.getAmount());
    	tranField.setText(this.agent.getTransferred().toString());
    	countField.setText(String.valueOf(this.agent.getCount()));
    	}
		/* (non-Javadoc)
		 * @see accountManager.model.ModelListener#modelChanged(accountManager.model.ModelEvent)
		 */
		public void modelChanged(ModelEvent event) {
			updateTransfer();
		}
		 // Inner classes for Event Handling 
		/**
 		 * The Class Handler.
 		 */
 		class Handler implements ActionListener { 
			// Event handling is handled locally
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			public void actionPerformed(ActionEvent e) {
				((AcctController)getController()).operation(e.getActionCommand(),"",""); 
		    } }
 	public void updateTrans(){
 		
 	}
}

