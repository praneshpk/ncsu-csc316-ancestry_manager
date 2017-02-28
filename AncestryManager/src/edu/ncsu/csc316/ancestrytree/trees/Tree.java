package edu.ncsu.csc316.ancestrytree.trees;
/**
 * This interface has all the functions available for a Tree
 * @author Pranesh Kamalakanthan
 *
 * @param <E> Type of node data
 */
public interface Tree<E> {
	public E search(E data);
	public TreeNode<E> searchForMark(TreeNode<E> r);
	public DoubleList<E> getLevelOrder();
	public TreeNode<E> getRoot();
	public TreeNode<E> get(int index);
	public int getSize();
	public boolean isEmpty();
	public void add(E data);
}
