/**
 * The interface by which FuzzySearch and LiteralSearch adhere
 * 
 * @author Kyra Koch
 * @version 1.0
 */

package cu.cs.cpsc2150.project2;

import java.util.ArrayList;

public interface Search {
	public ArrayList<Book> search(String in, ArrayList<Book> cata);
}
