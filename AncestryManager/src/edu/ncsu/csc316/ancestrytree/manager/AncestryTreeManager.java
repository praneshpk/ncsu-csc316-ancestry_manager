package edu.ncsu.csc316.ancestrytree.manager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.ancestrytree.trees.*;

/**
 * The AncestryTreeManager class has all of the required functions 
 * and variables to generate an AncestryTree from the given file
 * @author Pranesh Kamalakanthan
 *
 */
public class AncestryTreeManager {
	/** The tree that will contain the ancestry data */
	private Tree tree;
	
	/**
	 * Constructor to initialize an instance of your AncestryTreeManager object
	 * @param ahnentafelFilePath the path to the file that contains the ahnentafel
	 * @throws FileNotFoundException 
	 */
	public AncestryTreeManager(String ahnentafelFilePath) throws FileNotFoundException {
	    ArrayList<TreeNode> unsorted = parseFile(ahnentafelFilePath, true);
	    if(unsorted == null)
	    	System.exit(1);
	    tree = buildTree( unsorted );
	    if(tree == null ) {
	    	System.out.println("Error: Invalid file!");
    		throw new FileNotFoundException();
	    }
	    System.out.println(getLevelOrder());	    
	    
	}
	
	/**
	 * Constructor to initialize an instance of your AncestryTreeManager object
	 * @param preOrderFilePath the path to the file that contains the preOrder traversal
	 * @param postOrderFilePath the path to the file that contains the postOrder traversal
	 */
	public AncestryTreeManager(String preOrderFilePath, String postOrderFilePath) {
		ArrayList<TreeNode> preOrder = parseFile(preOrderFilePath, false);
		if( preOrder == null )
			System.exit(1);
		ArrayList<TreeNode> postOrder = parseFile(postOrderFilePath, false);
		if( postOrder == null )
			System.exit(1);
		TreeNode r = buildTree(preOrder.get(0), preOrder, 0, preOrder.size() - 1, postOrder, 0, postOrder.size() - 1);
		if( r == null )
			System.exit(1);
		tree = new TraversalTree(r);
		System.out.println(getLevelOrder());
	}
	
	/**
	 * Builds a TraversalTree and returns the root Node
	 * @param root the current root node
	 * @param preOrder the list of elements in preOrder traversal
	 * @param preMin the min index to consider in preOrder traversal
	 * @param preMax the max index to consider in the preOrder traversal
	 * @param postOrder the list of elements in the postOrder traversal
	 * @param postMin the min index to consider in the postOrder traversal
	 * @param postMax the max index to consider in the postOrder traversal
	 * @return a reference to the root node of the tree
	 */
	public TreeNode buildTree( TreeNode root, ArrayList<TreeNode> preOrder, int preMin, int preMax,
			ArrayList<TreeNode> postOrder, int postMin, int postMax ) {
		int split = -1;
		if(preMin + preMax + postMin + postMax == 0 )
			return root;
		ArrayList<TreeNode> children = new ArrayList<>();
		for(int i = postMax; i >= postMin; i-- ) {
			TreeNode child = postOrder.get(i);
			child.setParent(root);
			children.addLast(child);
			if( child.getData().equals(preOrder.get(preMin).getData()) ) {
				split = i;
				break;
			}
		}
		if(split == postMin) {
			root.setChildren(children);
			return root;
		}
		TreeNode temp;
		if(split == postMax )
			temp = buildTree( preOrder.get(preMin), preOrder, preMin + 1, split, postOrder, postMin, split - 1 );
		else
			temp = buildTree( preOrder.get(preMin), preOrder, preMin + 1, preMax - (postMax - split),
					postOrder, postMin, split - 1 );
		if(temp != null)
			temp.setParent(root);
		if(split != postMax)
			buildTree( root, preOrder, preMin + 1 + (split - postMin), preMax, postOrder, split + 1, postMax );
		if(temp != null ) {
			if(preMin > 0 || preMax < preOrder.size() - 1)
				root.getChildren().addLast(temp);
			else
				root.setParent(null);
			return root;
		}
		else
			return null;	
		
	}

	/**
	 * Builds an Ahnentafel tree from a list
	 * @param list an ArrayList containing TreeNodes
	 * @return a Tree object
	 */
	public Tree buildTree(ArrayList<TreeNode> list) {
		Person[] sorted = new Person[list.size()];
	    for(int i = 0; i < list.size(); i++ ) {
	    	Person p = list.get(i).getData();
	    	if(p.getId() > list.size() ||
	    			sorted[p.getId() - 1] != null )
				return null;
	    	sorted[ p.getId() - 1 ] = p;
	    }
	   
	    AhnentafelTree t = new AhnentafelTree(sorted[0]);
	    for(int i = 1; i < sorted.length; i++ )
	    	t.add(sorted[i]);
	    return (Tree) t;
	}

	/**
	 * Parses the file and returns a ArrayList of People
	 * @param path the file path
	 * @param ahnentafel true if it is an ahnentafel file,
	 * false if it is a pre/postorder file
	 * @return an ArrayList of people, or null if an error was encountered
	 */
	public ArrayList<TreeNode> parseFile( String path, boolean ahnentafel ) {
		ArrayList<TreeNode> d = new ArrayList<>();
		try( Scanner in = new Scanner( new FileInputStream( path ), "UTF8") )
		{
			if(! in.hasNext()) {
				System.out.println("Error: File is empty!");
				return null;
			}
			while( in.hasNext() ) {
				try( Scanner line = new Scanner(in.nextLine());)
				{
					if(ahnentafel) {
						int id = line.nextInt();
						String fname = line.next();
						String lname = line.next();
						d.addLast(new TreeNode(new Person(fname, lname, id)));
					}
					else {	
						String fname = line.next();
						String lname = line.next();
						String gender = line.next();
						boolean g;
						if(gender.equals("M"))
							g = false;
						else if( gender.equals("F"))
							g = true;
						else
							throw new IllegalArgumentException();
						d.addLast(new TreeNode(new Person(fname, lname, g)));
					}
				} catch( Exception e ) {
					System.out.println("Error: Invalid file!");
					return null;
				}
			}
		} catch(Exception e) {
			System.out.println("Error: File " + path + " not found");
			return null;
		}
		return d;
	}
	
	/**
	 * Returns the level-order traversal for the tree
	 * as a String in the format:
	 * LevelOrder[lastName, firstName; lastName2, firstName2]
	 * @return the level-order traversal for the tree
	 */
	public String getLevelOrder() {
		ArrayList<Person> q = tree.getLevelOrder();
		Person p = q.get(0);
		String res = "LevelOrder[" + p.getLname() + ", " + p.getFname();
		for(int i = 1; i < q.size(); i++ ) {
			p = q.get(i);
			res += "; " + p.getLname() + ", " + p.getFname();
		}
		res += "]";
		return res;
	}

	/**
	 * Returns a description of the relationship between
	 * two individuals in the tree, formatted as:
	 * [nameA] is [nameB]'s [relationship]
	 * @param nameA the first person to find in the tree
	 * @param nameB the second person to find in the tree
	 * @return a description of how the two people are related
	 */
	public String getRelationship(String nameA, String nameB) {
		String[] name = nameA.split("\\s+");
		if(name.length < 2) {
			System.out.println("Error: Need to enter full names!");
			return null;
		}
		TreeNode a = tree.search(new Person(name[0], name[1], 0));
		if(a == null )
			return null;
		name = nameB.split("\\s+");
		if(name.length < 2) {
			System.out.println("Error: Need to enter full names!");
			return null;
		}
		TreeNode b = tree.search(new Person(name[0], name[1], 0) );
		if(b == null )
			return null;
		((TraversalTree)tree).markAncestors(a);
		TreeNode found = ((TraversalTree)tree).searchForMark(b);
		if(found == null) {
			((TraversalTree)tree).unmarkAll(tree.getRoot());
			((TraversalTree)tree).markAncestors(b);
			found = ((TraversalTree)tree).searchForMark(a);
		}
			
		int apath = ((TraversalTree)tree).getPathLength(0, a, found);
		if(apath == -1) {
			((TraversalTree)tree).unmarkAll(tree.getRoot());
			((TraversalTree)tree).markAncestors(b);
			found = ((TraversalTree)tree).searchForMark(a);
			apath = ((TraversalTree)tree).getPathLength(0, a, found);
		}
		int bpath = ((TraversalTree)tree).getPathLength(0, b, found);
		if(bpath == -1) {
			((TraversalTree)tree).unmarkAll(tree.getRoot());
			((TraversalTree)tree).markAncestors(a);
			found = ((TraversalTree)tree).searchForMark(b);
			bpath = ((TraversalTree)tree).getPathLength(0, b, found);
		}
		String res = a.getData().getFname() + " " + a.getData().getLname() +
				" is " + b.getData().getFname() + " " + b.getData().getLname();
		
		if(apath == 0 && bpath == 0)
			return res;
		res += "'s ";
		if(apath == 0 && bpath == 1)
			if(a.getData().isFemale())
				return res + "mother";
			else
				return res + "father";
		if(apath == 0 && bpath == 2)
			if(a.getData().isFemale())
				return res + "grandmother";
			else
				return res + "grandfather";
		if(apath == 0 && bpath >= 3) {
			for(int i = 0; i < bpath - 2; i++ )
				res += "great-";
			if(a.getData().isFemale())
				return res + "grandmother";
			else
				return res + "grandfather";
		}
		if(apath == 1 && bpath == 0 )
			if(a.getData().isFemale())
				return res + "daughter";
			else
				return res + "son";
		if(apath == 2 && bpath == 0 )
			if(a.getData().isFemale())
				return res + "granddaughter";
			else
				return res + "grandson";
		if(apath >= 3 && bpath == 0 ) {
			for(int i = 0; i < bpath - 2; i++ )
				res += "great-";
			if(a.getData().isFemale())
				return res + "daughter";
			else
				return res + "son";	
		}
		if(apath == 1 && bpath == 1)
			if(a.getData().isFemale())
				return res + "sister";
			else
				return res + "brother";
		if(apath == 1 && bpath == 2)
			if(a.getData().isFemale())
				return res + "aunt";
			else
				return res + "uncle";
		if(apath == 1 && bpath > 2) {
			for(int i = 0; i < bpath - 2; i++ )
				res += "great-";
			if(a.getData().isFemale())
				return res + "aunt";
			else
				return res + "uncle";
		}
		if(apath == 2 && bpath == 1)
			if(a.getData().isFemale())
				return res + "niece";
			else
				return res + "nephew";
		if(apath > 2 && bpath == 1) {
			for(int i = 0; i < bpath - 2; i++ )
				res += "great-";
			if(a.getData().isFemale())
				return res + "aunt";
			else
				return res + "uncle";
		}
		if(apath >= 2 && bpath >= 2) {
			String suffix = "";
			int num = Math.min(apath, bpath) - 1;
			if(num == 1)
				suffix += "st";
			else if(num == 2) 
				suffix += "nd";
			else if(num == 3)
				suffix += "rd";
			else
				suffix += "th";
			return res + num + suffix + " cousin " +
					Math.abs(apath - bpath) + " times removed";
		}
		
		((TraversalTree)tree).unmarkAll(tree.getRoot());
		return null;
	}

	/**
	 * Returns a description of the relationship between
	 * two individuals in the tree, formatted as:
	 * [name] is [root]'s [relationship]
	 * @param name the person to find in the tree
	 * @return a description of how the person is related to the person at the root of the tree
	 */
	public String getRelationship(String name) {
		Person root = tree.getRoot().getData();
		if( name.equals(root.getFname() + " " + root.getLname() ))
			return name + " is " + name;
		String res = name + " is " + root.getFname() + " " + root.getLname() + "'s ";
		String[] full = name.split("\\s+");
		if(full.length < 2) {
			System.out.println("Error: Need to enter full names!");
			return null;
		}
		Person a = tree.search( new Person(full[0], full[1], 0) ).getData();
		if(a == null) {
			System.out.println("Error: Person is not in the tree!");
			return null;
		}
		int r = (int) Math.floor( Math.log(a.getId()) / Math.log(2) );
		String str = "";
		for(int i = r; i > 0; i-- ) {
			if( i > 2 )
				str += "great-";
			else if( i > 1 )
				str += "grand";
			else if(a.isFemale())
				str += "mother";
			else
				str += "father";
		}
		return res + str;
	}
	
	/**
	 * Returns a String representation of the tree
	 * @return String representation of the tree
	 */
	public String toString() {
		return tree.getRoot().toString();
		
	}

}
