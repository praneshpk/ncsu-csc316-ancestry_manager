package edu.ncsu.csc316.ancestrytree.trees;

/**
 * The AhnentafelTree class acts as the main data structure for AncestryManager
 * and the Ahnentefal-style tree
 * @author Pranesh Kamalakanthan
 *
 * @param <E> type of node used in Tree
 */
public class AhnentafelTree<E> implements Tree<E> {
	/** The root TreeNode of the tree */
	private TreeNode<E> root;
	/** The size of the tree */
	private int size;
	
	/**
	 * Creates the Tree Object
	 * @param r a sorted array containing E
	 */
	public AhnentafelTree( E r ) {
		root = new TreeNode<>(r);
		size = 1;
	}
	/**
	 * Searches for the given data and returns the data if found
	 * @param data search data
	 * @return data if found, null otherwise
	 */
	public E search(E data) {
		TreeNode<E> res;
		if( (res = search(root, data)) != null )
			return res.getData();
		else
			return null;
	}
	
	/**
	 * Searches for the given data in tree and returns
	 * the node if found
	 * @param r root node
	 * @param data search data
	 * @return returns the node if found, null otherwise
	 */
	private TreeNode<E> search(TreeNode<E> r, E data) {
		TreeNode<E> res;
		if(r.getData().equals(data))
			return r;
		if(r.getLeft() != null)
			if((res = search(r.getLeft(), data)) != null)
				return res;
		if(r.getRight() != null)
			if((res = search(r.getRight(), data)) != null)
				return res;
		return null;
	}
	
	/**
	 * Searches for a marked node from given start node
	 * @param r node to start search from
	 * @return marked TreeNode
	 */
	public TreeNode<E> searchForMark(TreeNode<E> r) {
		TreeNode<E> res;
		if(r.isMarked())
			return r;
		if(r.getLeft() != null)
			if((res = searchForMark(r.getLeft())) != null)
				return res;
		if(r.getRight() != null)
			if((res = searchForMark(r.getRight())) != null)
				return res;
		return null;
	}
	
	/**
	 * Returns the tree in level order
	 * @return a Queue of the tree in level order
	 */
	public DoubleList<E> getLevelOrder() {
		Queue<TreeNode<E>> q = new Queue<>();
		DoubleList<E> res = new DoubleList<>();
		TreeNode<E> node = root;
		
		while( node != null ) {
			res.add(node.getData());
			if(node.getLeft() != null )
				q.enqueue(node.getLeft());
			if(node.getRight() != null )
				q.enqueue(node.getRight());
			node = q.dequeue();
		}
		return res;
	}
	
	/**
	 * Returns the root node of the tree
	 * @return root node
	 */
	public TreeNode<E> getRoot() { return root; }
	
	/**
	 * Returns the node at the given index
	 * @param index position of node
	 * @return the node at index
	 */
	public TreeNode<E> get( int index ) {
		Queue< TreeNode<E> > q = new Queue<>();
		TreeNode<E> node = root;
		int i = 0;
		while( node != null ) {
			if( i++ == index )
				return node;
			if(node.getLeft() != null )
				q.enqueue(node.getLeft());
			if(node.getRight() != null )
				q.enqueue(node.getRight());
			node = q.dequeue();
		}
		throw new IndexOutOfBoundsException();
	}
	
	/**
	 * Returns the size of the tree
	 * @return size of tree
	 */
	public int getSize() { return size; }
	
	/**
	 * Determines if the tree is empty or not
	 * @return true if the tree is empty, false otherwise
	 */
	public boolean isEmpty() { return size == 0; }
	
	/**
	 * Creates a node with given data and adds it to tree
	 * @param data given data
	 */
	public void add(E data) {
		root = insert(root, data);
		size++;
	}
	/**
	 * A recursive function to add a new node to the tree
	 * @param root current root node
	 * @param data given data
	 * @return root node
	 */
	private TreeNode<E> insert(TreeNode<E> r, E data) {
		if(r == null)
			return new TreeNode<E>(data);
		if( r.getLeft() == null )
			r.setLeft( insert(r.getLeft(), data) );
		else if( r.getRight() == null )
			r.setRight( insert(r.getRight(), data) );
		else
			insert(r.getLeft(), data);
		
		return r;
	}
}
