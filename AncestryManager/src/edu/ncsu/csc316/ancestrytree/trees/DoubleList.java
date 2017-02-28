package edu.ncsu.csc316.ancestrytree.trees;

/**
 * The DoubleList class acts as the main list structure
 * for sorting and the children of the AncestryTree
 * @author Pranesh Kamalakanthan
 *
 */
public class DoubleList<E> extends LinkedList<E> {
	/**
	 * Creates a new Node with the given data and
	 * adds it to the end of the list
	 * @param d Node data
	 * @return added Node data
	 */
	public E add( E d ) {
		Node<E> e = new Node<>(d, trailer.getPrev(), trailer);
		trailer.getPrev().setNext( e );
		trailer.setPrev( e );
		size++;
		return e.getData();
	}
	/**
	 * Creates a new Node with the given data and
	 * adds it to the front of the list
	 * @param d Node data
	 * @return added Node data
	 */
	public E addFront( E d ) {
		Node<E> e = new Node<>(d, header, header.getNext() );
		header.getNext().setPrev( e );
		header.setNext( e );
		size++;
		return e.getData();
	}
	/**
	 * Removes the front Node and returns the removed data
	 * @return removed data
	 */
	public E remove() {
		return remove(header.getNext());
	}
	
	/**
	 * Removes the given Node
	 * @param e Node
	 * @return removed Node data
	 */
	public E remove( Node<E> e ) {
		e.getPrev().setNext(e.getNext());
		e.getNext().setPrev(e.getPrev());
		size--;
		return e.getData();
	}
}
