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
public class AcctAgentView extends JFrameView {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4193418810079590239L;

	
	private static final String STOP= "Stop Agent";
	
	/** Transfer field. To display total transferred. */
	private JTextField tranField = new JTextField();

	private JTextField countField = new JTextField();
	
	private JButton Disbutton = new JButton("Dismiss");

	private JTextField stateField = new JTextField();
	
	/** The balance field. */
	private JTextField amountField = new JTextField();
	
	
	/** The Constant DEPOSIT. */
	public static final String DEPOSIT = "Deposit";
	
	/** The Constant WITHDRAW. */
	public static final String WITHDRAW = "Withdraw";
	
	/** The Constant DISMISS. */
	public static final String DISMISS = "Dismiss";
	
	/** The currency that the Account is being viewed in */
	private String srcCurrency;
	private String Option;
	
	/** The text field. */
	private JTextField textField = new JTextField(); 
	
	/** The balance field. */
	private JTextField agentIDField = new JTextField();
	
	/** The entry field. */
	private JTextField entryField = new JTextField();
	
	/** The rate of operation field */
	private JTextField rateField = new JTextField();
	
	/** The rate of operation field */
	private JTextField rateOpField = new JTextField();
	
	//private Thread thread;
	private AcctAgentModel agent;
	
	/**
	 * Instantiates a new account view.
	 *
	 * @param model The model of the Account to be viewed
	 * @param controller The account controller in charge of this instance of the account view
	 * @param currency the currency the account is being edited in.
	 */
	public AcctAgentView(final AcctModel model, AcctAgentController controller, final String option) { 
		super(model, controller); 
		//textField.setText(model.abalance().toString());
		this.Option=option;	   
	    
	    try{
	    GridBagConstraints constraints = new GridBagConstraints();
	            JPanel rootPanel = new JPanel();
	            rootPanel.setLayout(new GridBagLayout());

	            

	            JPanel agentPanel = new JPanel();
	            agentPanel.setLayout(new GridBagLayout());
	            agentPanel.setBorder(BorderFactory.createTitledBorder("Transaction"));

	            // Show available funds
	            JLabel agentIDLabel = new JLabel("Agent ID", null, SwingConstants.RIGHT);
	            constraints.anchor = GridBagConstraints.EAST;
	            agentPanel.add(agentIDLabel, constraints);
	            agentIDField = new JTextField(10);
	            
	            agentIDField.setText("0");
	            agentIDField.setEditable(true);
	            constraints.gridx = 1;
	            agentPanel.add(agentIDField, constraints);

	            // Make a pretty separator
	            JSeparator separator = new JSeparator();
	            //separator.setBorder(BorderFactory.createEtchedBorder());
	            constraints.gridx = 0;
	            constraints.gridy = 1;
	            constraints.gridwidth = 2;
	            constraints.fill = GridBagConstraints.HORIZONTAL;
	            agentPanel.add(separator, constraints);

	            // Add an entry field with a label
	            JLabel entryLabel = new JLabel("Amount in $", null, SwingConstants.RIGHT);
	            constraints.anchor = GridBagConstraints.EAST;
	            constraints.gridwidth = 1;
	            constraints.gridy = 2;
	            agentPanel.add(entryLabel, constraints);

	            entryField = new JTextField("0.0");
	            constraints.fill = GridBagConstraints.HORIZONTAL;
	            constraints.gridx = 1;
	            constraints.gridy = 2;
	            agentPanel.add(entryField, constraints);

	            // Add an Operation rate field with label
	            JLabel rateLabel = new JLabel("Operations per Second", null, SwingConstants.RIGHT);
	            constraints.anchor = GridBagConstraints.EAST;
	            constraints.gridwidth = 1;
	            constraints.gridx=0;
	            constraints.gridy = 3;
	            agentPanel.add(rateLabel, constraints);

	            rateField = new JTextField("0.0");
	            constraints.fill = GridBagConstraints.HORIZONTAL;
	            constraints.gridx = 1;
	            constraints.gridy = 3;
	            agentPanel.add(rateField, constraints);

	         	            // Add a button to start the Agent
	            JButton button = new JButton("Start Agent");
	            button.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent event) {
	                	((AcctAgentController)getController()).operation(agentIDField.getText(),entryField.getText(),rateField.getText(), Option );
	                	//entryField.setText("0.00");
	                }
	            });
	            constraints = new GridBagConstraints();
	            constraints.gridy = 4;
	            agentPanel.add(button, constraints);

	            // Add a button to dismiss
	            //button = new JButton("Dismiss");
	            Disbutton.addActionListener(new ActionListener() {
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
	            this.setTitle(model.getName() + " " + model.getAcctNumber() + "; Operations in ");
	            this.setVisible(true);
	        } catch (Exception ex) {
	            ex.printStackTrace();   
	        }
	        
	    }

	    /**
    	 * Clear entry.
    	 */
      	public void errorDialog(String msg){
    		 JOptionPane.showMessageDialog(rootPane, msg);
    		 stateField.setText("Blocked");
    		 /////////////////////////////////////////
    		 /*
    		 try {
				this.thread.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			*/////////////////////////////////////////
    	}

	    /**
    	 * Update balance.
    	 */
    	public void updateBalance() {
	        this.agentIDField.setText(String.valueOf(((AcctModel)getModel()).getBalance(this.srcCurrency)));
	    }
	
    	public void updateTransfer(){
    	//agent.setTransferred(agent.getAmount());
       	tranField.setText(this.agent.getTransferred().toString());
       	countField.setText(String.valueOf(this.agent.getCount()));
       	stateField.setText(agent.getState());
    	}
	
		/* (non-Javadoc)
		 * @see accountManager.model.ModelListener#modelChanged(accountManager.model.ModelEvent)
		 */
		public void modelChanged(ModelEvent event) {
			//updateBalance();	
		}
		public void renderRun(AcctAgentModel model){
		try{
			this.agent=model;
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
            
         // Add state field with label
            JLabel stateLabel = new JLabel("Agent's State", null, SwingConstants.RIGHT);
            constraints.anchor = GridBagConstraints.EAST;
            constraints.gridwidth = 1;
            constraints.gridx=0;
            constraints.gridy = 5;
            agentPanel.add(stateLabel, constraints);

            stateField = new JTextField(this.agent.getState());
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridx = 1;
            constraints.gridy = 5;
            agentPanel.add(stateField, constraints);
            
            //Declare the dismiss button
            //final JButton Disbutton = new JButton("Dismiss");
            
            
            // Add a button to start the Agent
            JButton button = new JButton(STOP);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                	((AcctAgentController)getController()).stopAgent();
                	stateField.setText("Stopped");
                	Disbutton.setEnabled(true);
                	//entryField.setText("0.00");
                }
            });
            constraints = new GridBagConstraints();
            constraints.gridx = 0;
            constraints.gridy = 6;
            agentPanel.add(button, constraints);

            // Add a button to dismiss
            Disbutton.setEnabled(false);  
            Disbutton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    dispose();
                }
            });
            constraints.gridx = 1;
            agentPanel.add(Disbutton, constraints);

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
            this.setTitle(this.Option + " agent " + model.getID() + " for account "+ model.getAcctID());
            this.setVisible(true);
            ////////////////////////////////////////////////
            /* TODO
              thread = new Thread(new Runnable(){
             
    			public void run() {
    				while(agent.getState()=="Running"){
    				//((AcctAgentModel)getModel()).addModelListener(ModelListener l)
    				((AcctAgentController)getController()).runAgent();
    				agent.incrementCount();
    				updateTransfer();
    				System.out.println("Count: "+agent.getCount());
    				long l= (new Double(agent.getRate())).longValue();
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
           */

        } catch (Exception ex) {
            ex.printStackTrace();   
        }
        
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
	}

