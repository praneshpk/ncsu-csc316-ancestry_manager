package edu.ncsu.csc316.ancestrytree.trees;

import java.util.NoSuchElementException;


/**
 * The LinkedList abstract class contains the data structure that allows
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
	
	/**
	 * The nested ListIterator class maintains the functions and variables
	 * to traverse the List
	 * @author Pranesh Kamalakanthan
	 *
	 */
	private class ListIterator implements Iterator<E> {
		/** Current Node of iterator */
		private Node<E> curr;
		
		/** Current iterator position */
		private int i;
		
		/**
		 * Creates a new DoubleListIterator object
		 */
		public ListIterator() {
			curr = header.next;
			i = 0;
		}
		
		/**
		 * Checks if there is another Node in the List
		 * @return true if there is another Node
		 * 		   false if there is not
		 */
		public boolean hasNext() { return i < size; }
		
		/**
		 * Traverses the list by one and returns the current Node data
		 * @return curr.data
		 */
		public E next() {
			if( !hasNext() ) throw new NoSuchElementException();
			E e = curr.data;
			curr = curr.next;
			i++;
			return e;
		}
		
	}
	/** End nested ListIterator class */
	
	
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
	 * Gets a new ListIterator object
	 * @return new ListIterator object
	 */
	public ListIterator iterator() { return new ListIterator(); }
	
	/**
	 * Gets the size of the list
	 * @return size of the list
	 */
	public int size() { return size; }
	
	/**
	 * Determines whether List is empty or not
	 * @return true if it is empty, false otherwise
	 */
	public boolean isEmpty() { return size == 0; }
	
	/**
	 * Creates a new Node with the given data and
	 * adds it to the front of the List
	 * @param d Node data
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
