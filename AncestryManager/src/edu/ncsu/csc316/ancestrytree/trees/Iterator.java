package edu.ncsu.csc316.ancestrytree.trees;

/**
 * This contains the methods required for the Iterator interface
 * @author Pranesh Kamalakanthan
 *
 * @param <E> type of data being iterated
 */
public interface Iterator<E> {
	/**
	 * Checks if there are more elements
	 * @return true if there are more elements, false otherwise
	 */
	public boolean hasNext();
	
	/**
	 * Returns the next element in the iteration
	 * @return the next element
	 */
	public E next();
	
}
