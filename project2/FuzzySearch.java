/**
 * Performs a "fuzzy" search on a given catalog. Checks for plural and ignores case sensitivity
 * Employs the Singleton pattern
 * 
 * @author Kyra Koch
 * @version 1.0
 */
package cu.cs.cpsc2150.project2;

import java.util.ArrayList;

public class FuzzySearch implements Search{
	private static final FuzzySearch fuzzy = new FuzzySearch();
	
	private FuzzySearch() {};
	
	/**
	 * uses Singleton Pattern
	 * @return	returns the instance of the fuzzy search
	 */
	public static FuzzySearch getInstance()
	{
		return fuzzy;
	}
	
	/**
	 * searches through a given catalog. Checks for plural and ignores case sensitivity
	 * 
	 * @param in	the String with which to search by
	 * @param cata	the Catalog to search
	 * @return		all of the Books that fit a given criteria
	 */
	public ArrayList<Book> search(String in, ArrayList<Book> cata) {
		ArrayList<Book> retList = new ArrayList<Book>();
    	for(int i = 0; i < cata.size(); i++) {
    		if(cata.get(i).myCompare(in))
    		{
    			retList.add(cata.get(i));
    		}
    		else
    		{
    			if(in.endsWith("s"))
    			{
					if(in.endsWith("ies"))
					{
						if(cata.get(i).myCompare(in.substring(0, in.length()- 3)+"y"))
							retList.add(cata.get(i));
					}
					else
					{
						if(cata.get(i).myCompare(in.substring(0, in.length()-1)))
							retList.add(cata.get(i));
					}
    			}
    		}
    	}
    	return retList;
	}
}
