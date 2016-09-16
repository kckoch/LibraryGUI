/** 
 * Individual account
 * 
 * @author Kyra Koch
 * @version 2.0 
 */

package cu.cs.cpsc2150.project2;

import java.io.Serializable;


public class Account implements Comparable<Account>, Serializable {
    private int myID;
    private String myName, myUsername, myPassword, myEmail, myPhone;
    private boolean staff;

    /**
     * construct for the account
     * @param id		the id for the new account
     * @param name 		the new account user's name
     * @param username	the new account's username
     * @param password	the new account's password
     * @param email		the new account's email
     * @param phone		the new account's phone number
     * @param staff		whether or not it is a staff account, if staff, variable will be true
     */
    public Account(int id, String name, String username, String password, String email, String phone, boolean staff) {
        this.myID = id;
        this.myName = name;
        this.myUsername = username;
        this.myPassword = password;
        this.staff = staff;
        this.myEmail = email;
        this.myPhone = phone;
    }

    /**
     * returns ID
     * @return			account's ID
     */
    public int getID() {
        return myID;
    }
    
    /**
     * returns username
     * @return			account's username
     */
    public String getUsername() {
        return myUsername;
    }
    
    /**
     * returns true if staff account
     * @return			staff
     */
    public boolean getStaff() {
    	return staff;
    }
    
    /**
     * returns user's name
     * @return			user's name
     */
    public String getMyName() {
    	return myName;
    }

    /**
     * checks to make sure username and password match
     * @param username	the username to check with this username
     * @param password	the password to check with the username
     * @return			if password matches, return true
     */
    public boolean authenticate(String username, String password) {
        return myUsername.equals(username) && myPassword.equals(password);
    }

	/**
	 * compares current account ID with given account ID
	 * @param o		given account
	 * @return		returns standard compareTo return values
	 */
    @Override
    public int compareTo(Account o) {
        return ((Integer)myID).compareTo(o.myID);
    }

    /**
     * returns the user's name
     * @return the user's name
     */
    @Override
    public String toString() {
        return myName;
    }

    /**
     * returns the user's password
     * @return	the user's password
     */
	public String getPassword() {
		return myPassword;
	}
	
	/**
	 * returns the account type as a String
	 * @return	the account type as a String
	 */
	public String getType() {
		if(staff)
			return "Staff";
		else
			return "Member";
	}
}
