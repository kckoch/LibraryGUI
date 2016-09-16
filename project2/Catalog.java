/**
 * Holds all of the Books in the Catalog
 * 
 * @author Kyra Koch
 * @version 2.0
 */

package cu.cs.cpsc2150.project2;

import java.io.Serializable;
import java.util.*;

public class Catalog implements Serializable {
    private ArrayList<Book> myBooks;

    /**
     * Creates a new Catalog
     */
	public Catalog() {
        myBooks = new ArrayList<Book>();
    }
	
	/**
	 * Creates a new Catalog with a given list of books
	 * @param book	the list of Books to be added
	 */
	public Catalog(ArrayList<Book> book) {
		myBooks = book;
	}

	/**
	 * adds a Book to the Catalog
	 * @param book	the Book to add to the Catalog
	 */
    public void addBook(Book book) {
        myBooks.add(book);
        Collections.sort(myBooks);
    }

    /**
     * removes a Book from the Catalog
     * @param id	the ID of the Book to remove
     */
    public void removeBook(int id) {
        for (int i = 0; i < myBooks.size(); ++i) {
            if (myBooks.get(i).getID() == id) {
                myBooks.remove(i);
                return;
            }
        }
    }
    
    /**
     * returns all the Books in the Catalog
     * @return
     */
    public ArrayList<Book> getMyBooks() {
		return myBooks;
	}
    
    /**
     * returns the Book of a given ID
     * @param ID	the Id of the book to find
     * @return		the Book with a given ID
     */
    public Book findBook(int ID) {
    	for(int i = 0; i < myBooks.size(); i++) {
    		if(myBooks.get(i).getID() == ID) {
    			return myBooks.get(i);
    		}
    	}
    	return null;
    }

    /**
     * returns a list of all Books in the Catalog
     */
	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myBooks.size(); ++i) {
            sb.append(i);
            sb.append(") ");
            sb.append(myBooks.get(i));
            sb.append("\n");
        }
        return sb.toString();
    }
	
	/**
	 * returns the size of the catalog
	 * @return	the number of entries in the Catalog
	 */
	public int getSize() {
		return myBooks.size();
	}
	
	/**
	 * returns the Book of a given index
	 * @param ndx	the index of the Book to get
	 * @return		the Book at a given index
	 */
	public Book getBook(int ndx) {
		return myBooks.get(ndx);
	}
	
	/**
	 * Finds all of the Books a given Account has checked out
	 * @param acc	the Account to find the Books for
	 * @return		an ArrayList of all the Books the given Account has checked out
	 */
	public ArrayList<Book> userBooks(Account acc) {
		ArrayList<Book> arr = new ArrayList<Book> ();
		for (int i = 0; i < myBooks.size(); ++i) {
            if(myBooks.get(i).getAccount() != null && myBooks.get(i).getAccount().getID() == acc.getID())
            	arr.add(myBooks.get(i));
        }
		return arr;
	}
}
