package edu.ncsu.csc316.ancestrytree.trees;

public class TraversalTree<E> implements Tree<E> {
	/** The root TreeNode of the tree */
	private TreeNode<E> root;
	/** The size of the tree */
	private int size;
	
	/**
	 * Creates the Tree Object
	 * @param r the data of the root node
	 */
	public TraversalTree( E r ) {
		root = new TreeNode<>(r, null);
		size = 1;
	}
	
	public TreeNode<E> search(TreeNode<E> r, E data) {
		// TODO Auto-generated method stub
		return null;
	}

	public TreeNode<E> searchForMark(TreeNode<E> r) {
		// TODO Auto-generated method stub
		return null;
	}

	public DoubleList<E> getLevelOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns the root node of the tree
	 * @return root node
	 */
	public TreeNode<E> getRoot() { return root; }

	public TreeNode<E> get(int index) {
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

	public void add(E data) {
		// TODO Auto-generated method stub
	}

	public E search(E data) {
		// TODO Auto-generated method stub
		return null;
	}

}
