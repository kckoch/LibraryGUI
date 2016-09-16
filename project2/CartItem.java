/**
 * The CartItem hold a Book, an Account, and a CartCommand
 * 
 * @author Kyra Koch
 * @version 1.0
 */
package cu.cs.cpsc2150.project2;

public class CartItem {
	Book book;
	Account account;
	CartCommand command;
	
	/**
	 * Constructor for the CartItem
	 * @param b		the Book to perform that action on
	 * @param acc	the Account associated/to associated with Book
	 * @param comm	the CartCommand
	 */
	public CartItem(Book b, Account acc, CartCommand comm) {
		this.book = b;
		this.account = acc;
		this.command = comm;
	}
	
	/**
	 * executes the given CartCommand
	 */
	public void complete() {
		if(command == CartCommand.RETURN) {
			if(book.getAccount() != account) {
				return;
			}
			else {
				book.setAccount(null);
				return;
			}
		}
		else if(command == CartCommand.CHECKOUT) {
			if(book.getAccount() != null) {
				return;
			}
			else {
				book.setAccount(account);
				return;
			}
		}
		else {
			System.out.println("Error! Something went wrong!");
			return;
		}
	}
	
	/**
	 * returns the book for CartItem
	 * @return	Book
	 */
	public Book getBook() {
		return book;
	}
	
	/**
	 * returns the CartCommand for CartItem
	 * @return	CartCommand
	 */
	public CartCommand getCommand() {
		return command;
	}
}
