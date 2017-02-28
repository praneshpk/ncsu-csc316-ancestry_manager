package edu.ncsu.csc316.ancestrytree.trees;

public class TraversalTree implements Tree {
	/** The root TreeNode of the tree */
	private TreeNode root;
	/** The size of the tree */
	private int size;
	
	/**
	 * Creates the Tree Object
	 * @param r the data of the root node
	 */
	public TraversalTree( Person r ) {
		root = new TreeNode(r);
		size = 1;
	}
	
	/**
	 * Creates the Tree Object given a root node and size
	 * @param r the data of the root node
	 * @param s size of the node
	 */
	public TraversalTree(TreeNode r, int s ) {
		root = r;
		size = s;
	}
	
	public TreeNode search(TreeNode r, Person data) {
		// TODO Auto-generated method stub
		return null;
	}

	public TreeNode searchForMark(TreeNode r) {
		// TODO Auto-generated method stub
		return null;
	}

	public DoubleList getLevelOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the root node of the tree
	 * @return root node
	 */
	public TreeNode getRoot() { return root; }

	public TreeNode get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the size of the tree
	 * @return size
	 */
	public int getSize() { return size; }

	/**
	 * Determines if the tree is empty or not
	 * @return true if the tree is empty, false otherwise
	 */
	public boolean isEmpty() { return size == 0; }

	public void add(Person data) {
		// TODO Auto-generated method stub
	}

	public Person search(Person data) {
		// TODO Auto-generated method stub
		return null;
	}

}
