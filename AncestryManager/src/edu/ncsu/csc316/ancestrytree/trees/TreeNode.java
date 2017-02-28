package edu.ncsu.csc316.ancestrytree.trees;

/**
 * Contains all the functions and variables required for the
 * TreeNode class that is to be implemented in BinaryTree
 * @author Pranesh Kamalakanthan
 *
 */
public class TreeNode {
	/** Data contained in node */
	private Person data;
	
	/** Parent node */
	private TreeNode parent;
	
	/** Left node */
	private TreeNode left;
	
	/** Right node */
	private TreeNode right;
	
	/** Children nodes */
	private DoubleList<TreeNode> children;
	
	/** Marked/unmarked */
	private boolean marked;
	
	/**
	 * Creates the TreeNode object
	 * @param d data
	 */
	public TreeNode(Person d ) {
		data = d;
		parent = left = right = null;
		children = null;
		marked = false;
	}
	
	/**
	 * Creates the TreeNode object with parent and children nodes
	 * @param d data
	 * @param p parent node
	 * @param c children nodes
	 */
	public TreeNode(Person d, TreeNode p, DoubleList<TreeNode> c) {
		data = d;
		left = right = null;
		parent = p;
		children = c;
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
	public Person getData() { return data; }
	
	/**
	 * Returns the parent node
	 * @return parent
	 */
	public TreeNode getParent() { return parent; }
	
	/**
	 * Returns the left node
	 * @return left
	 */
	public TreeNode getLeft() { return left; }
	
	/**
	 * Returns the right node
	 * @return right
	 */
	public TreeNode getRight() { return right; }
	
	/**
	 * Returns the children nodes
	 * @return children
	 */
	public DoubleList<TreeNode> getChildren() { return children; }
	
	/**
	 * Sets the data in node
	 * @param d data
	 */
	public void setData( Person d ) { data = d; }
	
	/**
	 * Sets the parent of node
	 * @param p parent
	 */
	public void setParent(TreeNode p) { parent = p; }
	
	/**
	 * Sets the left node
	 * @param l left node
	 */
	public void setLeft(TreeNode l) { left = l; }
	
	/**
	 * Sets the right node
	 * @param r right
	 */
	public void setRight(TreeNode r) { right = r; }

	/**
	 * Sets the children node list
	 * @param c children
	 */
	public void setChildren(DoubleList<TreeNode> c) { children = c; }
	
}
