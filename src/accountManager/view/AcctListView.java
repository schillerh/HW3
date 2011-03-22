package accountManager.view;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import accountManager.controller.AcctListController;
import accountManager.model.AcctListModel;
import accountManager.model.ModelEvent;
import accountManager.model.SaveModel;




/**
 * The Class AcctListView. 
 */
public class AcctListView extends JFrameView {

 
    /** The list of Accounts */
    private AcctListModel acctListModel;

    /** The combo box to select the account from */
    private JComboBox comboBox;
    
    /** The Constant USD. */
    public static final String USD = "Edit Account in $";
	
	/** The Constant YEN. */
	public static final String YEN = "Edit Account in Yen";
	
	/** The Constant EURO. */
	public static final String EURO = "Edit Account in Euro";
	
	/** The Constant SAVE. */
	public static final String SAVE = "Save";
	
	/** The Constant EXIT. */
	public static final String EXIT = "Exit";
    
	public static final String DAGENT = "Create Deposit Agent";
	public static final String WAGENT = "Create Withdraw Agent";
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 4193418810079590485L;

	/**
	 * Instantiates a new Account list view.
	 *
	 * @param model the model of the account list
	 * @param controller The controller for the account list
	 */
	public AcctListView(AcctListModel model, AcctListController controller) { 
		super(model, controller); 
 
    	this.acctListModel = model;
    

       try {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            GridBagConstraints constraints = new GridBagConstraints();

            // Main container
            JPanel rootPanel = new JPanel();
            rootPanel.setLayout(new GridBagLayout());

            // Accounting container
            JPanel panel = new JPanel();
            panel.setBorder(BorderFactory.createTitledBorder("Accounting"));
            panel.setLayout(new GridBagLayout());

            // Add a selection for accounts
            constraints.insets = new Insets(4,4,4,4);
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridwidth = 3;
            constraints.gridx = 0;
            constraints.gridy = 0;
           comboBox = new JComboBox(this.acctListModel.getName());
            panel.add(comboBox, constraints);

            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridwidth = 1;
            constraints.gridx = 0;
            constraints.gridy = 1;

            JButton jButtonUSD = new JButton(USD); 
    		jButtonUSD.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent event) {
    				((AcctListController)getController()).operation("Dollars",comboBox.getSelectedIndex());
                }
    		});
    		panel.add(jButtonUSD, constraints);
            constraints.gridx++;
    		JButton jButtonEuro = new JButton(EURO); 
    		jButtonEuro.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                   ((AcctListController)getController()).operation("Euro",comboBox.getSelectedIndex());
    				
                }
    		});
    		panel.add(jButtonEuro, constraints);
            constraints.gridx++;
    		JButton jButtonYen = new JButton(YEN); 
    		jButtonYen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                   ((AcctListController)getController()).operation("Yen",comboBox.getSelectedIndex());
    				
                }
    		}); 
    		panel.add(jButtonYen, constraints);
            constraints.gridx++;
            
            //constraints.fill = GridBagConstraints.HORIZONTAL;
            //constraints.gridwidth = 1;
            constraints.gridx = 0;
            constraints.gridy = 2;

            JButton jButtonDAgent = new JButton(DAGENT); 
    		jButtonDAgent.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent event) {
    				((AcctListController)getController()).operation(DAGENT,comboBox.getSelectedIndex());
                }
    		});
    		panel.add(jButtonDAgent, constraints);
    		constraints.gridx++;
    		

            JButton jButtonWAgent = new JButton(WAGENT); 
    		jButtonWAgent.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent event) {
    				((AcctListController)getController()).operation(WAGENT,comboBox.getSelectedIndex());
                }
    		});
    		panel.add(jButtonWAgent, constraints);
    		constraints.gridx++;
            // Add all account panel
            constraints = new GridBagConstraints();
            constraints.gridx = constraints.gridy = 0;
            constraints.fill = GridBagConstraints.HORIZONTAL;
            constraints.gridwidth = 3;
            constraints.insets = new Insets(10, 10, 10, 10);
            rootPanel.add(panel, constraints);

            // Make some blank space
            constraints = new GridBagConstraints();
            constraints.weightx = 1.0;
            constraints.gridx = 0;
            constraints.gridy = 1;
            rootPanel.add(new JLabel(), constraints);

            // Add a Save button
            JButton jButtonSave = new JButton(SAVE); 
    		jButtonSave.addActionListener(new ActionListener(){
    			public void actionPerformed(ActionEvent event){
    			SaveModel.write(acctListModel);	
    			}
    		}); 
    		
            
            constraints.weightx = 0.0;
            constraints.gridx = 1;
            constraints.gridy = 1;
            constraints.anchor = GridBagConstraints.LAST_LINE_END;
            constraints.insets = new Insets(0, 0, 10, 0);
            rootPanel.add(jButtonSave, constraints);

            // And an Exit button
            JButton jButtonExit = new JButton(EXIT);
            jButtonExit.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                	SaveModel.write(acctListModel);
                	dispose();
                    System.exit(0);
                }
            });
            constraints.gridx = 2;
            constraints.insets = new Insets(0, 0, 10, 10);
            rootPanel.add(jButtonExit, constraints);


            this.setContentPane(rootPanel);
            this.pack();
            this.setTitle("Accounting ");
            this.setVisible(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

	// Now implement the necessary event handling code 
	/* (non-Javadoc)
	 * @see accountManager.model.ModelListener#modelChanged(accountManager.model.ModelEvent)
	 */
	public void modelChanged(ModelEvent event) {
	comboBox.getSelectedItem();
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
			((AcctListController)getController()).operation(e.getActionCommand(),comboBox.getSelectedIndex()); 
	    } }
		
}
	