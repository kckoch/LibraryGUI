/* Author: Blair Durkee */

package cu.cs.cpsc2150.project1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class AccountDatabase {
    private ArrayList<Account> myAccounts;

    public AccountDatabase() {
        myAccounts = new ArrayList<>();
        myAccounts.add(new Account(0, "Administrator", "admin", "pass"));
    }

    public void addAccount(Account account) {
        myAccounts.add(account);
        Collections.sort(myAccounts);
    }

    public void removeAccount(int id) {
        for (int i = 0; i < myAccounts.size(); ++i) {
            if (myAccounts.get(i).getID() == id) {
                myAccounts.remove(i);
                return;
            }
        }
    }

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
}
