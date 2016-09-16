/**
 * AccountDatabase class holds all of the accounts and performs action on them
 * 
 * @author Kyra Koch
 * @version 2.0
 */

package cu.cs.cpsc2150.project2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class AccountDatabase implements Serializable {
    private ArrayList<Account> myAccounts;

    /**
     * Initial AccountDatabase hold one account: Administrator
     */
    public AccountDatabase() {
        myAccounts = new ArrayList<>();
        myAccounts.add(new Account(0, "Administrator", "admin", "pass", "", "", true));
    }

    /**
     * adds an account to the database
     * @param account	the account to add
     */
    public void addAccount(Account account) {
        myAccounts.add(account);
        Collections.sort(myAccounts);
    }

    /**
     * removes an account from the database
     * @param id		the ID of the account to remove
     */
    public void removeAccount(int id) {
        for (int i = 0; i < myAccounts.size(); ++i) {
            if (myAccounts.get(i).getID() == id) {
                myAccounts.remove(i);
                return;
            }
        }
    }

    /**
     * loops through all of the accounts to see there is an account with a given username password combo
     * @param username	the username to find
     * @param password	the password o test
     * @return			returns the account if found
     */
    public Account login(String username, String password) {
        Account login = null;
        for (Account account : myAccounts) {
            if (account.authenticate(username, password)) {
                login = account;
                break;
            }
        }
        return login;
    }

    /**
     * returns a String of all of the usernames in the database
     * @return String of all of the usernames in the database
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myAccounts.size(); ++i) {
            sb.append(i);
            sb.append(") ");
            sb.append(myAccounts.get(i).getUsername());
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * returns the size of the database
     * @return	how many accounts are in the database
     */
	public int getSize() {
		return myAccounts.size();
	}
	
	/**
	 * returns the account at a specific index
	 * @param ndx	the index at which to return
	 * @return		the account at the index
	 */
	public Account getAccount(int ndx) {
		return myAccounts.get(ndx);
	}
}
