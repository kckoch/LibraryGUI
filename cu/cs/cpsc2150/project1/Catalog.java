/* Author: Blair Durkee */

package cu.cs.cpsc2150.project1;

import java.util.*;

public class Catalog {
    private ArrayList<Book> myBooks;

    public Catalog() {
        myBooks = new ArrayList<>();
    }

    public void addBook(Book book) {
        myBooks.add(book);
        Collections.sort(myBooks);
    }

    public void removeBook(int id) {
        for (int i = 0; i < myBooks.size(); ++i) {
            if (myBooks.get(i).getID() == id) {
                myBooks.remove(i);
                return;
            }
        }
    }

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
}
