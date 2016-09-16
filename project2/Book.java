/**
 * Book class contains all of the necessary info about a book, as well as the account that checks it out
 * 
 * @author Kyra Koch
 * @version 2.0
 */

package cu.cs.cpsc2150.project2;

import java.io.Serializable;
import java.util.ArrayList;

public class Book implements Comparable<Book>, Serializable {
    private int myID;
    private String myTitle, myAuthor, myGenre;
    private ArrayList<String> myTags;
    private Account acc;

    /**
     * creates an instance of a Book object
     * @param id		the ID of the new book, unique 
     * @param title		the title of the book
     * @param author	the author of the book
     * @param genre		the genre of the book
     * @param tags		the tags of the new book
     */
    public Book(int id, String title, String author, String genre, ArrayList<String> tags) {
        this.myID = id;
        this.myTitle = title;
        this.myAuthor = author;
        this.myGenre = genre;
        this.myTags = tags;
        this.acc = null;
    }

    /**
     * returns the id of the book
     * @return the id of the book
     */
    public int getID() {
        return myID;
    }
    
    /**
     * sets the account that has checked out this book
     * @param acc	the account that has this book checked out
     */
    public void setAccount(Account acc) {
    	this.acc = acc;
    }
    
    /**
     * returns the account that has this book checked out
     * @return	the account that has this book checked out
     */
    public Account getAccount() {
    	return acc;
    }
    
    /**
     * compare the two ids and return what the original compareTo does
     */
    @Override
    public int compareTo(Book o) {
        return ((Integer)myID).compareTo(o.myID);
    }
    
    /**
     * compares a given string to every field within the book and returns if it matches
     * used for suzzy search
     * @param in	the string to compare
     * @return		true if a field within the book matches the given string roughly
     */
    public boolean myCompare(String in) {
    	if(in.toLowerCase().equals(myTitle.toLowerCase())|| in.toLowerCase().equals(myGenre.toLowerCase()) 
    			|| in.toLowerCase().equals(myAuthor.toLowerCase())) {
    		return true;
    	}
    	else
    	{
    		for(int i = 0; i < myTags.size(); i++) {
    			if(myTags.get(i).contains(in)) {
    				return true;
    			}
    		}
    	}
		return false;
    }
    
    /**
     * compares a given string to every field within the book and returns if it matches
     * used for literal search
     * @param in	the input string to compare
     * @return		true if a field within the book matches the given string exactly
     */
    public boolean myLitCompare(String in) {
    	if(in.toLowerCase().equals(myTitle.toLowerCase())|| in.toLowerCase().equals(myGenre.toLowerCase()) 
    			|| in.toLowerCase().equals(myAuthor.toLowerCase())) {
    		return true;
    	}
    	else
    	{
    		for(int i = 0; i < myTags.size(); i++) {
    			if(myTags.get(i).equals(in)) {
    				return true;
    			}
    		}
    	}
		return false;
    }
    
    /**
     * prints the tags of this book
     * @return	a single string of all of the tags separated by commas
     */
    public String printTags()
    {
		String retStr = "";
		for(int i = 0; i < myTags.size(); i++)
			retStr = retStr + " " + myTags.get(i);
		return retStr;
    }

    /**
     * prints out the relevant data on the book
     * 
     * @return <Title>, by <Author> \nGenre: <Genre> \nTags: <Tags>
     */
    @Override
    public String toString() {
        return myTitle + ", by " + myAuthor + "\nGenre: " + myGenre + "\nTags: " + printTags();
    }

    /**
     * returns the id of the book
     * @return	the id of the book
     */
	public int getMyID() {
		return myID;
	}

	/**
	 * returns the title of the book
	 * @return	the title of the book
	 */
	public String getMyTitle() {
		return myTitle;
	}

	/**
	 * returns the author of the book
	 * @return	the author of the book
	 */
	public String getMyAuthor() {
		return myAuthor;
	}

	/**
	 * returns the genre of the book
	 * @return	the genre of the book
	 */
	public String getMyGenre() {
		return myGenre;
	}

	/**
	 * returns the tags as an arraylist
	 * @return	the tags as an arraylist
	 */
	public ArrayList<String> getMyTags() {
		return myTags;
	}
	
	/**
	 * checks whether or not a book is checked out
	 * @return	true if the book is checked out, false if otherwise
	 */
	public boolean checkedout() {
		if(acc == null)
			return false;
		else
			return true;
	}
}
