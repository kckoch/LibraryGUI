/* Author: Blair Durkee */

package cu.cs.cpsc2150.project1;

public class Account implements Comparable<Account> {
    private int myID;
    private String myName, myUsername, myPassword;

    public Account(int id, String name, String username, String password) {
        this.myID = id;
        this.myName = name;
        this.myUsername = username;
        this.myPassword = password;
    }

    public int getID() {
        return myID;
    }

    public String getUsername() {
        return myUsername;
    }

    public boolean authenticate(String username, String password) {
        return myUsername.equals(username) && myPassword.equals(password);
    }

    @Override
    public int compareTo(Account o) {
        return ((Integer)myID).compareTo(o.myID);
    }

    @Override
    public String toString() {
        return myName;
    }
}
