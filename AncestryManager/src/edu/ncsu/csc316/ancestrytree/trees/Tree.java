package edu.ncsu.csc316.ancestrytree.trees;
/**
 * This interface has all the functions available for a Tree
 * @author Pranesh Kamalakanthan
 *
 */
public interface Tree {
	public ArrayList<Person> getLevelOrder();
	public TreeNode getRoot();
	public TreeNode get(int index);
	public int getSize();
	public boolean isEmpty();
	public TreeNode search(Person person);
	public TreeNode searchForMark(TreeNode b);
}
