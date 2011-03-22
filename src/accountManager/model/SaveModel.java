package accountManager.model;

import java.io.*;

/**
 * The Class SaveModel.
 */
public class SaveModel {

    /**
     * Writes the Accounts back to the filename that was input via command line arguments.
     *
     * @param accounting the AcctListModel that holds all of the accounts
     * @return true, if successful
     */
    public static boolean write(AcctListModel accounting) {
        return write(accounting, null);
    }
    
    /**
     * Writes the Accounts to a filename specified.
     *
     * @param accounting the AcctListModel that holds all of the accounts
     * @param filename the filename to be written to
     * @return true, if successful
     */
    public static boolean write(AcctListModel accounting, String filename) {
        BufferedWriter buffer = null;
        try {
            if (filename == null)
            	
                filename = accounting.getFilename();
            
            buffer = new BufferedWriter(new FileWriter(filename));

            // Write each account to the file
            for (AcctModel account : accounting.getAccounts()) {
                synchronized (account) {
                    buffer.write(account.getName() + " " + String.valueOf(account.getAcctNumber()) + " " + String.valueOf(account.getBalance()));
                }
                buffer.newLine();
            }
            return true;
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            try {
                if (buffer != null) {
                    buffer.flush();
                    buffer.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

}
