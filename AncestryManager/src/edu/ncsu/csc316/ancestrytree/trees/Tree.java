package edu.ncsu.csc316.ancestrytree.trees;
/**
 * This interface has all the functions available for a Tree
 * @author Pranesh Kamalakanthan
 *
 */
public interface Tree {
	public Person search(Person data);
	public TreeNode searchForMark(TreeNode r);
	public DoubleList<Person> getLevelOrder();
	public TreeNode getRoot();
	public TreeNode get(int index);
	public int getSize();
	public boolean isEmpty();
	public void add(Person data);
}
