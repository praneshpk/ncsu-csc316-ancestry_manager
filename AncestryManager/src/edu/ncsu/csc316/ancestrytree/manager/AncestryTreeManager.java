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
	    DoubleList<TreeNode> unsorted = parseFile(ahnentafelFilePath, true);
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
		DoubleList<TreeNode> preorder = parseFile(preOrderFilePath, false);
		if( preorder == null ) {
			System.out.println("Error: PreOrder file is invalid!");
			System.exit(1);
		}
		DoubleList<TreeNode> postorder = parseFile(postOrderFilePath, false);
		if( postorder == null ) {
			System.out.println("Error: PostOrder file is invalid!");
			System.exit(1);
		}
		
		Iterator<TreeNode> pre = preorder.iterator();
		Iterator<TreeNode> post = postorder.riterator();
		/*Person root = pre.next();
		if( !root.equals(post.next())) {
			System.out.println("Error: Pre/PostOrder files are invalid!");
			System.exit(1);
		}*/
		
		TreeNode r = buildTree(pre.next(), preorder, pre, postorder, post);
		
		tree = new TraversalTree(r, preorder.size());
		//...
		
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
	public TreeNode buildTree(TreeNode root, DoubleList<TreeNode> preOrder, Iterator<TreeNode> pre,
			DoubleList<TreeNode> postOrder, Iterator<TreeNode> post ) {
		TreeNode left, right, newRoot;
		DoubleList<TreeNode> newPre = new DoubleList<>();
		DoubleList<TreeNode> newPost = new DoubleList<>();
		DoubleList<TreeNode> children = new DoubleList<>();
		Iterator<TreeNode> p = preOrder.riterator();
		
		TreeNode e1 = null, e2 = null;
		while(post.hasNext()) {
			e2 = post.next();
			if( root.getData().equals( e2.getData() ) ) {
				TreeNode child = buildTree(pre.next(), preOrder, pre, postOrder, post);
				child.setParent(root);
				root.getChildren().add( child );
				/*if( newPost.isEmpty() ) {
					return new TreeNode(root.getData(), root.getParent(), children );
				}*/
				if( ! newPost.isEmpty() ) {
					root.getParent().getChildren().add( buildTree(e1, newPre, newPre.iterator(),
							newPost, newPost.riterator()) );
				}

			}
			newPre.addFront(e1 = p.next());
			newPost.addFront(e2);
		}
		root.setChildren(newPre); 
		return root;
	}

	/**
	 * Builds an Ahnentafel tree from a DoubleList
	 * @param list a DoubleList containing TreeNodes
	 * @return a Tree object
	 */
	public Tree buildTree(DoubleList<TreeNode> list) {
		Person[] sorted = new Person[list.size()];
	    Iterator<TreeNode> it = list.iterator();
	    while(it.hasNext()) {
	    	Person p = it.next().getData();
	    	if(p.getId() > list.size() ||
	    			sorted[p.getId()-1] != null ){
				return null;
	    	}
	    	sorted[ p.getId()-1 ] = p;
	    }
	   
	    AhnentafelTree t = new AhnentafelTree(sorted[0]);
	    for(int i=1; i < sorted.length; i++ )
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
	public DoubleList<TreeNode> parseFile( String path, boolean ahnentafel ) {
		DoubleList<TreeNode> d = new DoubleList<>();
		try( Scanner in = new Scanner( new FileInputStream( path ), "UTF8") )
		{
			while( in.hasNext() ) {
				try( Scanner line = new Scanner(in.nextLine());)
				{
					if(ahnentafel) {
						int id = line.nextInt();
						String fname = line.next();
						String lname = line.next();
						d.add(new TreeNode(new Person(fname, lname, id)));
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
						d.add(new TreeNode(new Person(fname, lname, g)));
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
		DoubleList<Person> q = tree.getLevelOrder();
		Iterator<Person> i = q.iterator();
		
		Person p = i.next();
		String res = "LevelOrder[" + p.getLname() + ", " + p.getFname();
		while(i.hasNext()) {
			p = i.next();
			res += "; "+p.getLname() +", "+p.getFname();
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
		Person a = tree.search( new Person(name[0], name[1], 0) );
		name = nameB.split("\\s+");
		Person b = tree.search( new Person(name[0], name[1], 0) );
		
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
		String res = name + " is " + root.getFname() + " " + root.getLname() + "'s ";
		String[] full = name.split("\\s+");
		Person a = tree.search( new Person(full[0], full[1], 0) );
		int r = (int) Math.floor( Math.log(a.getId()) / Math.log(2) );
		if(a == null || r == 0)
			return null;
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
