package edu.ncsu.csc316.ancestrytree.trees;

/**
 * The TraversalTree class acts as the main data structure for AncestryManager
 * @author Pranesh Kamalakanthan
 *
 */
public class TraversalTree implements Tree {
	/** The root TreeNode of the tree */
	private TreeNode root;
	
	/**
	 * Creates the Tree Object given a root node and size
	 * @param r the data of the root node
	 */
	public TraversalTree(TreeNode r) {
		root = r;
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
	public TreeNode search(TreeNode r, Person data) {
		TreeNode res;
		if(r.getData().equals(data))
			return r;
		if(r.getChildren() != null) {
			ArrayList<TreeNode> children = r.getChildren();
			for( int i = 0; i < children.size(); i++ ) {
				res = search(children.get(i), data);
				if(res != null)
					return res;
			}
		}
		return null;
	}
	
	/**
	 * Marks the ancestors of the given root recursively, including itself
	 * @param r given root node
	 * @return the root node
	 */
	public TreeNode markAncestors( TreeNode r ) {
		r.mark();
		if( r.getParent() != null)
			markAncestors(r.getParent());
		return null;
	}
	
	/**
	 * Finds the path length from the root node to the destination node recursively
	 * @param i initial path length, used in recursion (should pass in 0)
	 * @param r root node
	 * @param b destination node
	 * @return the path length, -1 if not found
	 */
	public int getPathLength(int i, TreeNode r, TreeNode b ) {
		if(r.getData().equals(b.getData()))
			return i;
		if(r.getParent() != null)
			return getPathLength(i + 1, r.getParent(), b);
		else
			return -1;
	}
		
	/**
	 * Unmarks all of the nodes, starting from the given root node
	 * @param r given root node
	 */
	public void unmarkAll(TreeNode r) {
		if(r.isMarked())
			r.mark();
		if(r.getChildren() != null) {
			ArrayList<TreeNode> children = r.getChildren();
			for(int i = 0; i < children.size(); i++)
				unmarkAll(children.get(i));
		}
			
	}
	
	/**
	 * Searches for a marked node, starting from the given root node
	 * @param r given root node
	 * @return the found TreeNode, null otherwise
	 */
	public TreeNode searchForMark(TreeNode r ) {
		if(r.isMarked())
			return r;
		if( r.getParent() != null)
			return searchForMark(r.getParent());
		return null;
	}

	/**
	 * Returns a list of the tree in level order
	 * @return an ArrayList of the tree in level order
	 */
	public ArrayList<Person> getLevelOrder() {
		Queue<TreeNode> q = new Queue<>();
		ArrayList<Person> res = new ArrayList<>();
		TreeNode node = root;
		
		while( node != null ) {
			res.addLast(node.getData());
			if(node.getChildren() != null ) {
				ArrayList<TreeNode> list = node.getChildren();
				for(int i = list.size() - 1; i >= 0; i-- )
					q.add(list.get(i));
			}
			node = q.remove();
		}
		return res;
	}

	/**
	 * Returns the root node of the tree
	 * @return root node
	 */
	public TreeNode getRoot() { return root; }

}
