package edu.ncsu.csc316.ancestrytree.trees;

/**
 * The AhnentafelTree class acts as the main data structure for AncestryManager
 * and the Ahnentefal-style tree
 * @author Pranesh Kamalakanthan
 *
 */
public class AhnentafelTree implements Tree {
	/** The root TreeNode of the tree */
	private TreeNode root;
	
	/**
	 * Creates the Tree Object
	 * @param r a sorted array containing E
	 */
	public AhnentafelTree( Person r ) {
		root = new TreeNode(r);
	}
	
	/**
	 * Returns the tree in level order
	 * @return a Queue of the tree in level order
	 */
	public ArrayList<Person> getLevelOrder() {
		Queue<TreeNode> q = new Queue<>();
		ArrayList<Person> res = new ArrayList<>();
		TreeNode node = root;
		
		while( node != null ) {
			res.addLast(node.getData());
			if(node.getLeft() != null )
				q.add(node.getLeft());
			if(node.getRight() != null )
				q.add(node.getRight());
			node = q.remove();
		}
		return res;
	}
	
	/**
	 * Returns the root node of the tree
	 * @return root node
	 */
	public TreeNode getRoot() { return root; }
	
	/**
	 * Creates a node with given data and adds it to tree
	 * @param data given data
	 */
	public void add(Person data) {
		root = insert(root, data);
	}
	/**
	 * A recursive function to add a new node to the tree
	 * @param root current root node
	 * @param data given data
	 * @return root node
	 */
	private TreeNode insert(TreeNode r, Person data) {
		if(r == null)
			return new TreeNode(data);
		if( r.getLeft() == null )
			r.setLeft( insert(r.getLeft(), data) );
		else if( r.getRight() == null )
			r.setRight( insert(r.getRight(), data) );
		else
			insert(r.getLeft(), data);
		
		return r;
	}
	
	/**
	 * Searches for the given data and returns the data if found
	 * @param data search data
	 * @return data if found, null otherwise
	 */
	public TreeNode search(Person data) { return search(root, data); }

	/**
	 * Searches for the given data in tree and returns
	 * the node if found
	 * @param r root node
	 * @param data search data
	 * @return returns the node if found, null otherwise
	 */
	private TreeNode search(TreeNode r, Person data) {
		TreeNode res;
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
}
