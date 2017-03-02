package edu.ncsu.csc316.ancestrytree.trees;
/**
 * This interface has all the functions available for a Tree
 * @author Pranesh Kamalakanthan
 *
 */
public interface Tree {
	/**
	 * Returns a list of the tree in level order
	 * @return an ArrayList of the tree in level order
	 */
	public ArrayList<Person> getLevelOrder();
	
	/**
	 * Gets the root node of the tree
	 * @return tree root node
	 */
	public TreeNode getRoot();
	
	/**
	 * Searches for the given Person and returns the node
	 * @param person given Person object
	 * @return The found node, otherwise returns null
	 */
	public TreeNode search(Person person);
}
