/* Author: Blair Durkee */

package cu.cs.cpsc2150.project1;

public class Book implements Comparable<Book> {
    private int myID;
    private String myTitle, myAuthor;

    public Book(int id, String title, String author) {
        this.myID = id;
        this.myTitle = title;
        this.myAuthor = author;
    }

    public int getID() {
        return myID;
    }

    @Override
    public int compareTo(Book o) {
        return ((Integer)myID).compareTo(o.myID);
    }

    @Override
    public String toString() {
        return myTitle + ", by " + myAuthor;
    }
}
