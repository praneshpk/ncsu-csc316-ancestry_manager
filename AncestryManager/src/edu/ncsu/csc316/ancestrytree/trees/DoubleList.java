package edu.ncsu.csc316.ancestrytree.trees;

/**
 * The DoubleList class acts as the main list structure
 * for sorting and the children of the AncestryTree
 * @author Pranesh Kamalakanthan
 *
 */
public class DoubleList<E> extends List<E> {
	/**
	 * Creates a new Node with the given data and
	 * adds it to the end of the list
	 * @param d Node data
	 */
	public void add( E d ) {
		Node<E> e = new Node<>(d, trailer.getPrev(), trailer);
		trailer.getPrev().setNext( e );
		trailer.setPrev( e );
		size++;
	}
	
	/**
	 * Removes the given Node
	 * @param e Node
	 */
	public void remove( Node<E> e ) {
		e.getPrev().setNext(e.getNext());
		e.getNext().setPrev(e.getPrev());
		e = null;
		size--;
	}
}
