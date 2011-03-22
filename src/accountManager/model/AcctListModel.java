package accountManager.model;
import accountManager.model.AcctModel;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Vector;
//import java.io.*;
import java.lang.String;
//import java.nio.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class AcctListModel. It holds the list of Accounts.
 * 
 */
public class AcctListModel extends AbstractModel {
	
	/** The acct list. */
	private ArrayList<AcctModel> acctList;
	/** Array to hold a list of agents */
	private ArrayList<Integer>agentList;
	/** The filename from input. */
	private String fName;
	
	/** The DOLLAR. */
	public String DOLLAR = "Dollar";
	
	/** The YEN. */
	public String YEN = "Yen";
	
	/** The EURO. */
	public String EURO = "Euro";
    
    /** The aname. */
    private Vector<String> aname;
    
    /** The aid. */
    private Vector<Integer> aid;
    
    /** The abalance. */
    private Vector<Double> abalance;
    
	/**
	 * Instantiates a new account list model.
	 *
	 * @param fileName the file name from command line
	 * @throws FileNotFoundException the file not found exception
	 * @throws ParseException The file is does not meet the account name, ID, or balance requirements.
	 */
	public  AcctListModel(String fileName) throws FileNotFoundException, ParseException{
		this.acctList = new ArrayList<AcctModel>();
		fName=fileName;
		this.agentList = new ArrayList<Integer>();
		this.aname= new Vector<String>();
		this.aid= new Vector<Integer>();
		this.abalance= new Vector<Double>();
		BufferedReader buffer;
		try {
            buffer = new BufferedReader(new FileReader(fName));

            // Parse each line of the file
            int lineNumber = 0;
            String lineString = buffer.readLine();
            while(lineString != null) {
                // Temporary read holders
                String name;
                int id;
                double balance;
                
                String[] tokens = lineString.split(" ");
                if (tokens.length != 3) {
                    // Each lineString should contain only a name, id, and balance
                    throw new ParseException("A single line can only contain a name, numeric ID, and balance.\nLine with error:\n" + lineString, lineNumber);
                }

                // Validate name
                name = tokens[0].trim();
                if (!name.matches("^[a-zA-Z]+$"))
                    throw new ParseException("Name can only contain letters.\nError on Name: " + tokens[0], lineNumber);
                // Valid id
                if (!tokens[1].trim().matches("^[0-9]+$"))
                    throw new ParseException("Account number can only contain digits.\nError on Account Number: " + tokens[1], lineNumber);
                id = Integer.parseInt(tokens[1]);
                if(this.acctList.contains(id))
                	throw new ParseException("Duplicate ID: "+ tokens[1], lineNumber);
                if (!tokens[2].matches("^[0-9.]+$"))
                    throw new ParseException("Balance can only be positive numbers.\nError Balance: " + tokens[2], lineNumber);
                balance = Double.parseDouble(tokens[2]);
                this.addAccount(new AcctModel(name, id, balance));
                this.aname.addElement(name);
                aid.addElement(id);
                abalance.addElement(balance);
                // Get the next lineString for pocessing
                lineString = buffer.readLine();
                lineNumber++;
            }
            // Clean up
            buffer.close();
     
        } catch (NumberFormatException ex) {
            // Handle invalid account numbers and balances
            ex.printStackTrace();
        } catch (IOException ex) {
            // Handle I/O errors
            System.out.println("I/O Error: " + ex.getMessage());
            
        } catch (ParseException ex){
        	//Handle ParseException
        	System.out.println("Parse Error: " + ex.getMessage());
        } 
	}
	public  AcctListModel() {
		this.acctList = new ArrayList<AcctModel>();
		this.agentList = new ArrayList<Integer>();
		this.aname= new Vector<String>();
		this.aid= new Vector<Integer>();
		this.abalance= new Vector<Double>();
	}
/**
 * Gets the accounts.
 *
 * @return the accounts
 */
public ArrayList<AcctModel> getAccounts(){
		return acctList;
	}

/**
 * Adds the account.
 *
 * @param model an instance of AcctModel
 */
public void addAccount(AcctModel model){
	this.acctList.add(model);
}

public void addAgent(AcctAgentModel model){
	if(agentList.contains(model.getID()))
	{
		System.out.println("contained in agent list");
		throw new UnsupportedOperationException("Agent ID already exists");
	}
	else
	{
		System.out.println("didn't find in contained: "+model);
		System.out.println("model to string: "+model.toString());
		System.out.println("agentList: "+ agentList);
		System.out.println("agentList to String: " + agentList.toString());
		agentList.add(model.getID());
	}
}
	/**
	 * Account list size.
	 *
	 * @return the size of the list
	 */
	public int acctListSize(){
		return acctList.size();
	}
	
	/**
	 * Gets the name on the account.
	 *
	 * @return the account name
	 */
	public Vector<String> getName(){
		return aname;
	}
	
	/**
	 * Gets the filename.
	 *
	 * @return the filename
	 */
	public String getFilename(){
		return fName;
	}
			
}
	
