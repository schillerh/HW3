package accountManager.view;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dialog;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import accountManager.controller.AcctController;
import accountManager.model.AcctModel;
import accountManager.model.ModelEvent;
import java.lang.*;

/**
 * The Class AcctView.
 */
public class AcctView extends JFrameView {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4193418810079590234L;
	
	/** The Constant DEPOSIT. */
	public static final String DEPOSIT = "Deposit";
	
	/** The Constant WITHDRAW. */
	public static final String WITHDRAW = "Withdraw";
	
	/** The Constant DISMISS. */
	public static final String DISMISS = "Dismiss";
	
	/** The currency that the Account is being viewed in */
	private String srcCurrency;
	
	/** The text field. */
	private JTextField textField = new JTextField(); 
	
	/** The balance field. */
	private JTextField balanceField = new JTextField();
	
	/** The entry field. */
	private JTextField entryField = new JTextField();
	//private String operation = PLUS; 
	
	/**
	 * Instantiates a new account view.
	 *
	 * @param model The model of the Account to be viewed
	 * @param controller The account controller in charge of this instance of the account view
	 * @param currency the currency the account is being edited in.
	 */
	public AcctView(final AcctModel model, AcctController controller, final String currency) { 
		super(model, controller); 
		textField.setText(model.getBalance().toString());
		this.srcCurrency=currency;	   
	    
	    try{
	    GridBagConstraints constraints = new GridBagConstraints();
	            JPanel rootPanel = new JPanel();
	            rootPanel.setLayout(new GridBagLayout());

	            

	            JPanel transactionPanel = new JPanel();
	            transactionPanel.setLayout(new GridBagLayout());
	            transactionPanel.setBorder(BorderFactory.createTitledBorder("Transaction"));

	            // Show available funds
	            JLabel fundsLabel = new JLabel("Available funds in " +currency, null, SwingConstants.RIGHT);
	            constraints.anchor = GridBagConstraints.EAST;
	            transactionPanel.add(fundsLabel, constraints);
	            balanceField = new JTextField(10);
	            
	         //   System.out.println("model.abbalance: "+model.abalance());
	           // System.out.println("model.abbalance to string: "+model.abalance().toString());
	            balanceField.setText(String.valueOf(model.fromUSD(model.getBalance(),currency)));
	            balanceField.setEditable(false);
	            constraints.gridx = 1;
	            transactionPanel.add(balanceField, constraints);

	            // Make a pretty separator
	            JSeparator separator = new JSeparator();
	            //separator.setBorder(BorderFactory.createEtchedBorder());
	            constraints.gridx = 0;
	            constraints.gridy = 1;
	            constraints.gridwidth = 2;
	            constraints.fill = GridBagConstraints.HORIZONTAL;
	            transactionPanel.add(separator, constraints);

	            // Add an entry field with a label
	            JLabel entryLabel = new JLabel("Enter amount in " + currency+ "", null, SwingConstants.RIGHT);
	            constraints.anchor = GridBagConstraints.EAST;
	            constraints.gridwidth = 1;
	            constraints.gridy = 2;
	            transactionPanel.add(entryLabel, constraints);

	            entryField = new JTextField("0.00");
	            constraints.fill = GridBagConstraints.HORIZONTAL;
	            constraints.gridx = 1;
	            constraints.gridy = 2;
	            transactionPanel.add(entryField, constraints);

	            // Add a button to make deposits
	            JButton button = new JButton("Deposit");
	            button.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent event) {
	                	((AcctController)getController()).operation(entryField.getText(),srcCurrency, "Deposit");
	                	entryField.setText("0.00");
	                }
	            });
	            constraints = new GridBagConstraints();
	            constraints.gridy = 3;
	            transactionPanel.add(button, constraints);

	            // Add a button to make withdrawals
	            button = new JButton("Withdraw");
	            button.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent event) {
	                	((AcctController)getController()).operation(entryField.getText(),srcCurrency, "Withdraw");
	                entryField.setText("0.00");
	                }
	            });
	            constraints.gridx = 1;
	            transactionPanel.add(button, constraints);

	            // Add the transaction panel
	            constraints = new GridBagConstraints();
	            constraints.insets = new Insets(10, 10, 10, 10);
	            constraints.ipadx = constraints.ipady = 10;
	            rootPanel.add(transactionPanel, constraints);

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


	            this.setContentPane(rootPanel);
	            this.pack();
	            this.setTitle(model.getName() + " " + model.getAcctNumber() + "; Operations in " + currency);
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
    	}

	    /**
    	 * Update balance.
    	 */
    	public void updateBalance() {
	        this.balanceField.setText(String.valueOf(((AcctModel)getModel()).getBalance(this.srcCurrency)));
	    }
	    
	
		/* (non-Javadoc)
		 * @see accountManager.model.ModelListener#modelChanged(accountManager.model.ModelEvent)
		 */
		public void modelChanged(ModelEvent event) {
			updateBalance();	
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

