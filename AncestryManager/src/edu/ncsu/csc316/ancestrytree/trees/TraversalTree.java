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
				if((res = search(children.get(i), data)) != null)
					return res;
			}
		}
		return null;
	}

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

}
