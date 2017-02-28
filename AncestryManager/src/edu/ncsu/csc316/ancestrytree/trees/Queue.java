package edu.ncsu.csc316.ancestrytree.trees;

public class Queue<E> extends List<E>{
	/**
	 * Creates a new Node with the given data and
	 * adds it to the front of the List
	 * @param d Node data
	 */
	public void enqueue( E d ) {
		Node<E> e = new Node<>(d, header, header.getNext());
		header.getNext().setPrev(e);
		header.setNext(e);
		size++;
	}
	
	/**
	 * Removes the last element of the List
	 * @return the data of the removed element, 
	 * or null if there is no more
	 */
	public E dequeue() {
		if( size == 0 )
			return null;
		Node<E> old = trailer.getPrev();
		trailer.setPrev(old.getPrev());
		size--;
		return old.getData();
	}
}
