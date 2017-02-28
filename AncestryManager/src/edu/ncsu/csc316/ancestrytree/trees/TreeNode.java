package edu.ncsu.csc316.ancestrytree.trees;
/**
 * Contains all the functions and variables required for the
 * TreeNode class that is to be implemented in BinaryTree
 * @author Pranesh Kamalakanthan
 *
 * @param <T> type of data in TreeNode 
 */
public class TreeNode<T> {
	/** Data contained in node */
	private T data;
	
	/** Parent node */
	private TreeNode<T> parent;
	
	/** Left node */
	private TreeNode<T> left;
	
	/** Right node */
	private TreeNode<T> right;
	
	/** Children nodes */
	private DoubleList<TreeNode<T>> children;
	
	/** Marked/unmarked */
	private boolean marked;
	
	/**
	 * Creates the TreeNode object for an Ahnentafel structure
	 * @param d data
	 * @param p parent node
	 * @param l left node
	 * @param r right node
	 */
	public TreeNode(T d ) {
		data = d;
		parent = left = right = null;
		children = null;
		marked = false;
	}
	
	/**
	 * Creates the TreeNode object for a pre/postorder structure
	 * @param d data
	 * @param p parent node
	 * @param c children node list
	 */
	public TreeNode(T d, DoubleList<TreeNode<T>> c) {
		data = d;
		children = c;
		parent = left = right = null;
		marked = false;
	}
	
	/**
	 * Switches the node mark on/off
	 */
	public void mark() { marked = !marked; }
	
	/**
	 * Checks if the node is marked
	 * @return true if the node is marked, false otherwise
	 */
	public boolean isMarked() { return marked; }
	
	/**
	 * Returns the data contained in node
	 * @return data
	 */
	public T getData() { return data; }
	
	/**
	 * Returns the parent node
	 * @return parent
	 */
	public TreeNode<T> getParent() { return parent; }
	
	/**
	 * Returns the left node
	 * @return left
	 */
	public TreeNode<T> getLeft() { return left; }
	
	/**
	 * Returns the right node
	 * @return right
	 */
	public TreeNode<T> getRight() { return right; }
	
	/**
	 * Returns the children nodes
	 * @return children
	 */
	public DoubleList<TreeNode<T>> getChildren() { return children; }
	
	/**
	 * Sets the data in node
	 * @param d data
	 */
	public void setData( T d ) { data = d; }
	
	/**
	 * Sets the parent of node
	 * @param p parent
	 */
	public void setParent(TreeNode<T> p) { parent = p; }
	
	/**
	 * Sets the left node
	 * @param l left node
	 */
	public void setLeft(TreeNode<T> l) { left = l; }
	
	/**
	 * Sets the right node
	 * @param r right
	 */
	public void setRight(TreeNode<T> r) { right = r; }

	/**
	 * Sets the children node list
	 * @param c children
	 */
	public void setChildren(DoubleList<TreeNode<T>> c) { children = c; }
	
}
