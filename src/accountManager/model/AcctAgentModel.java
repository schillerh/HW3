package accountManager.model;
import accountManager.controller.AcctAgentController;
import accountManager.controller.AcctListController;
import accountManager.model.AcctModel;
import accountManager.model.AcctListModel;

import java.text.ParseException;
import java.util.*;

//import java.io.*;
import java.lang.String;
//import java.nio.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Double;

// TODO: Auto-generated Javadoc
/**
 * The Class AcctListModel. It holds the list of Accounts.
 * 
 */
public class AcctAgentModel extends AbstractModel {
	private Integer agentID;
	private Double entryAmount;
	private Double opRate;
	private AcctModel acct;
	private String State;
	private Double transfered;
	private Integer opCount;
	private String STOP="Stop Agent";
	private String DISMISS = "Dismiss Agent";
	private String agentOp;
	private Thread thread;
	
public AcctAgentModel(AcctModel model, String option){
	/*this.acct = model;
	this.agentID=ID;
	this.entryAmount = amount;
	this.opRate=rate;
	*/
	this.agentOp=option;
	this.acct=model;
	}

	public AcctModel getAccount(AcctAgentModel model){
	
		return this.acct;
	}
	public void setAgent(String ID, String Amount, String Rate){
		this.agentID=Integer.valueOf(ID);
		this.entryAmount= Double.valueOf(Amount);
		this.opRate=Double.valueOf(Rate);
		this.opCount=0;
		this.State="Running";
		this.transfered=0.0;
		            
		
	}
	public int getID(){
		return this.agentID;
	}
	public Integer getAcctID(){
		return this.acct.getAcctNumber();
	}

	public Double getAmount(){
		return this.entryAmount;
	}
	public Double getRate(){
		return this.opRate;
	}
	public int getCount(){
		return this.opCount;
	}
	public void setTransferred(Double amount){
		this.transfered+=amount;
	}
	public void setTransferred(){
		this.transfered+=entryAmount;
	}
	public Double getTransferred(){
		return this.transfered;
	}
	public String getState(){
		return this.State;
	}
	public String getAgentOp(){
		return this.agentOp;
	}
	public void incrementCount(){
		this.opCount++;
		
	}
	public void setState(String newState){
		this.State=newState;
	}
	
	public void threadTime(){
		
			try {
				thread=Thread.currentThread();
				thread.sleep(Double.doubleToRawLongBits(1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
	}
	public Thread getThread(){
		return this.thread;
	}
	
}


