/**
 * The primary class holding the main function.
 * @author Schiller Hill
 * @version 1.0, February 28 2011
 */
package accountManager;

import java.io.FileNotFoundException;
import java.text.ParseException;

import accountManager.controller.AcctListController;


public class AccountManager {

	/**
	 * @param args Command line input of the filename to be read
	 */
	public static void main(String [] args) throws FileNotFoundException, ParseException { 
		String temp;
		temp= args[0];
		System.out.println(temp);
		new AcctListController(temp); }

	}

