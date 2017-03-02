package edu.ncsu.csc316.ancestrytree.manager;

import java.io.FileInputStream;
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
	 */
	public AncestryTreeManager(String ahnentafelFilePath) {
	    ArrayList<TreeNode> unsorted = parseFile(ahnentafelFilePath, true);
	    if((tree = buildTree( unsorted )) == null ) {
    		System.out.println("Error: Ahnentafel file is invalid!");
    		System.exit(1);
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
		if( preOrder == null ) {
			System.out.println("Error: PreOrder file is invalid!");
			System.exit(1);
		}
		ArrayList<TreeNode> postOrder = parseFile(postOrderFilePath, false);
		if( postOrder == null ) {
			System.out.println("Error: PostOrder file is invalid!");
			System.exit(1);
		}
		
		TreeNode r = buildTree(preOrder.get(0), preOrder, 0, preOrder.size() - 1, postOrder, 0, postOrder.size() - 1);
		tree = new TraversalTree(r, preOrder.size());
		System.out.println(getLevelOrder());
	}
	
	/**
	 * Builds a TraversalTree and returns the root Node
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
		if(preMin >= preMax || postMin > postMax )
			System.out.println(root.getData()+ "\n"+preMin+ " "+preMax+" "+postMin+" "+ postMax+"\n");
		int split = -1;
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
			System.out.println(root.getData());
			root.setChildren(children);
			return root;
		}
		TreeNode temp = buildTree( preOrder.get(preMin), preOrder, preMin + 1, preMax, postOrder, postMin, split - 1 );
		if(temp != null)
			temp.setParent(root);
		if(split != postMax)
			buildTree( root, preOrder, split + 2, preMax, postOrder, split + 1, postMax );
		
		if(root.getParent() == null)
			if(temp != null ) {
				root.getChildren().addLast(temp);
			}
			else
				return root;	

		return null;
		
		
	}

	/**
	 * Builds an Ahnentafel tree from a list
	 * @param list a DoubleList containing TreeNodes
	 * @return a Tree object
	 */
	public Tree buildTree(ArrayList<TreeNode> list) {
		Person[] sorted = new Person[list.size()];
	    for(int i = 0; i < list.size(); i++ ) {
	    	Person p = list.get(i).getData();
	    	if(p.getId() > list.size() ||
	    			sorted[p.getId()-1] != null )
				return null;
	    	sorted[ p.getId()-1 ] = p;
	    }
	   
	    AhnentafelTree t = new AhnentafelTree(sorted[0]);
	    for(int i = 1; i < sorted.length; i++ )
	    	t.add(sorted[i]);
	    return (Tree) t;
	}

	/**
	 * Parses the file and returns a DoubleList of People
	 * @param path the file path
	 * @param ahnentafel true if it is an ahnentafel file,
	 * false if it is a pre/postorder file
	 * @return a DoubleList of people, or null if an error was encountered
	 */
	public ArrayList<TreeNode> parseFile( String path, boolean ahnentafel ) {
		ArrayList<TreeNode> d = new ArrayList<>();
		try( Scanner in = new Scanner( new FileInputStream( path ), "UTF8") )
		{
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
					return null;
				}
			}
		} catch(Exception e) {
			System.out.println("Error: File "+path+" not found");
			System.exit(0);
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
		TreeNode a, b;
		if((a = tree.search(new Person(name[0], name[1], 0))) == null )
			return null;
		name = nameB.split("\\s+");
		if((b = tree.search(new Person(name[0], name[1], 0) )) == null )
			return null;
		a.markAncestors(a);
		b.markAncestors(b);
			
		System.out.println(a);
		System.out.println(b);

		
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
		Person a = tree.search( new Person(full[0], full[1], 0) ).getData();
		int r = (int) Math.floor( Math.log(a.getId()) / Math.log(2) );
		String str = "";
		for(int i=r; i > 0; i-- ) {
			if( i > 2 )
				str += "great-";
			else if( i > 1 )
				str += "grand";
			else if(a.getGender())
				str += "mother";
			else
				str += "father";
		}
		return res + str;
	}

}
