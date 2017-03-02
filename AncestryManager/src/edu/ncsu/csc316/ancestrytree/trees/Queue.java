package edu.ncsu.csc316.ancestrytree.trees;

/**
 * The Queue class contains the data structure that allows
 * multi-purpose lists to be derived from
 * @author Pranesh Kamalakanthan
 *
 * @param <E> the type of data stored in the List
 */
public class Queue<E> {
	
	/**
	 * The nested Node class acts as the data structure contained within List
	 * with left and right Nodes to maintain the DoublyLinkedList structure
	 * @author Pranesh Kamalakanthan
	 *
	 * @param <E> the type of data stored in the Node
	 */
	protected static class Node<E> {
		/** Holds Node data */
		private E data;
		
		/** References previous node */
		private Node<E> prev;
		
		/** References next node */
		private Node<E> next;
		
		/**
		 * Creates new Node object
		 * @param d Node data
		 * @param p previous Node
		 * @param n next Node
		 */
		public Node( E d, Node<E> p, Node<E> n ) {
			data = d;
			prev = p;
			next = n;
		}
		
		/**
		 * Get the previous Node.
		 * @return previous Node
		 */
		public Node<E> getPrev() { return prev; }
		
		/**
		 * Get the next Node.
		 * @return next Node
		 */
		public Node<E> getNext() { return next; }
		
		/**
		 * Gets the node data
		 * @return node data
		 */
		public E getData() { return data; }
		
		/**
		 * Sets the previous Node.
		 * @param p previous Node
		 */
		public void setPrev( Node<E> p ) { prev = p; }
		
		/**
		 * Sets the next Node
		 * @param n next Node
		 */
		public void setNext( Node<E> n ) { next = n; }
	}
	/** End nested Node class */
	
	
	
	/** Header sentinel Node */
	protected Node<E> header;
	
	/** Trailer sentinel Node */
	protected Node<E> trailer;
	
	/** Size of Queue */
	protected int size;
	
	/**
	 * Creates a new Queue object
	 */
	public Queue() {
		header = new Node<>( null, null, null );
		trailer = new Node<>( null, header, null );
		header.setNext(trailer);
	}
	
	/**
	 * Creates a new Node with the given data and
	 * adds it to the front of the List
	 * @param d Node data
	 * @return added data
	 */
	public E add( E d ) {
		Node<E> e = new Node<>(d, header, header.getNext());
		header.getNext().setPrev(e);
		header.setNext(e);
		size++;
		return e.getData();
	}
	
	/**
	 * Removes the last element of the List
	 * @return the data of the removed element, 
	 * or null if there is no more
	 */
	public E remove() {
		if( size == 0 )
			return null;
		Node<E> old = trailer.getPrev();
		trailer.setPrev(old.getPrev());
		size--;
		return old.getData();
	}
	
}
