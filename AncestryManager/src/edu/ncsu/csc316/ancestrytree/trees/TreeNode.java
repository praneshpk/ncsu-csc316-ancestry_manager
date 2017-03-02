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
	private ArrayList<TreeNode> children;
	
	/** Marked/unmarked */
	private boolean marked;
	
	/**
	 * Creates the TreeNode object
	 * @param d data
	 */
	public TreeNode(Person d ) {
		data = d;
		parent = null;
		left = null;
		right = null;
		children = new ArrayList<>();
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
	public ArrayList<TreeNode> getChildren() { return children; }
	
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
	public void setChildren(ArrayList<TreeNode> c) { children = c; }
	
	/**
	 * Returns a String representation of the TreeNode object
	 * @return a String representation of the TreeNode object
	 */
	public String toString() {
		String sparent;
		if( parent == null )
			sparent = "none";
		else
			sparent = parent.getData().toString();
		return "{ " + data.toString() + "; marked: " + marked + "; parent: " + sparent + "; children: " + children + " }";
	}
	
}
