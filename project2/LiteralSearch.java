/**
 * Performs a literal search on a given catalog. Ignores case sensitivity
 * Employs the Singleton pattern
 * 
 * @author Kyra Koch
 * @version 1.0
 */
package cu.cs.cpsc2150.project2;

import java.util.ArrayList;

public class LiteralSearch implements Search {
	private static final LiteralSearch literal = new LiteralSearch();
	
	private LiteralSearch() {};
	
	/**
	 * uses Singleton Pattern
	 * @return	returns the instance of the literal search
	 */
	public static LiteralSearch getInstance()
	{
		return literal;
	}

	/**
	 * searches through a given catalog. Ignores case sensitivity
	 * 
	 * @param in	the String with which to search by
	 * @param cata	the Catalog to search
	 * @return		all of the Books that fit a given criteria
	 */
	public ArrayList<Book> search(String in, ArrayList<Book> cata) {
		ArrayList<Book> retList = new ArrayList<Book>();
    	for(int i = 0; i < cata.size(); i++) {
    		if(cata.get(i).myLitCompare(in))
    		{
    			retList.add(cata.get(i));
    		}
    	}
    	return retList;
	}

}
