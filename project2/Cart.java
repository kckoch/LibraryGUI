/**
 * The Cart class holds instances of CartItems
 * 
 * @author Kyra Koch
 * @version 1.0
 */
package cu.cs.cpsc2150.project2;

import java.util.ArrayList;

public class Cart {
	ArrayList<CartItem> cart;
	
	/**
	 * Constructor of the Cart
	 */
	public Cart() {
		cart = new ArrayList<CartItem>();
	}
	
	/**
	 * creates a new CartItem with the command CHECKOUT
	 * @param b		the Book to be checked out
	 * @param acc	the Account with which to check it out to
	 */
	public void checkoutBook(Book b, Account acc) {
		CartItem it = new CartItem(b, acc, CartCommand.CHECKOUT);
		cart.add(it);
	}
	
	/**
	 * creates a new CartItem with the command RETURN
	 * @param b		the Book to be returned
	 * @param acc	the Account with whom it was checked out
	 */
	public void returnBook(Book b, Account acc) {
		CartItem it = new CartItem(b, acc, CartCommand.RETURN);
		cart.add(it);
	}
	
	/**
	 * Actually checks out and/or returns the Books
	 */
	public void completeCheckout() {
		for(int i = 0; i < cart.size(); i++) {
			cart.get(i).complete();
		}
		cart.clear();
	}
	
	/**
	 * returns the number of items in the cart
	 * @return	the number of items in the cart
	 */
	public int getSize() {
		return cart.size();
	}
	
	/**
	 * returns the CartItem at a specified index
	 * @param ndx	the index of the item you want
	 * @return		the index
	 */
	public CartItem get(int ndx) {
		return cart.get(ndx);
	}
}
