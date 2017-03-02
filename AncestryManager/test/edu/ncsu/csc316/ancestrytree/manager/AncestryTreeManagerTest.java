package edu.ncsu.csc316.ancestrytree.manager;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * The AncestryTreeManagerTest class maintains all the functions
 * required for testing the AncestryTreeManager class
 * @author Pranesh Kamalakanthan
 *
 */
public class AncestryTreeManagerTest {

	/**
	 * Tests creation of the TraversalTree object and all its relevant functions
	 */
	@Test
	public void testTraversalTree() {
		AncestryTreeManager a = new AncestryTreeManager("input/small-preorder.txt", "input/small-postorder.txt");
		assertEquals(a.toString(), "{ Henry Leonard M; marked: false; parent: none; children: [ "
								+ "{ Claire Murray F; marked: false; parent: Henry Leonard M; children: [ "
								+ "{ Percy Perry M; marked: false; parent: Claire Murray F; children: [ ] } "
								+ "{ Richard Williams M; marked: false; parent: Claire Murray F; children: [ ] } ] } "
								+ "{ Teresa Smith F; marked: false; parent: Henry Leonard M; children: [ "
								+ "{ Zachary Doe M; marked: false; parent: Teresa Smith F; children: [ ] } "
								+ "{ Xena Jones F; marked: false; parent: Teresa Smith F; children: [ ] } ] } ] }");
		AncestryTreeManager b = new AncestryTreeManager("input/pre-multi.txt", "input/post-multi.txt");
		assertEquals(b.toString(), "{ D D M; marked: false; parent: none; children: [ "
								+ "{ Q Q M; marked: false; parent: D D M; children: [ "
								+ "{ N N F; marked: false; parent: Q Q M; children: [ ] } ] } "
								+ "{ H H F; marked: false; parent: D D M; children: [ "
								+ "{ C C M; marked: false; parent: H H F; children: [ "
								+ "{ P P F; marked: false; parent: C C M; children: [ ] } "
								+ "{ R R M; marked: false; parent: C C M; children: [ ] } ] } "
								+ "{ T T F; marked: false; parent: H H F; children: [ "
								+ "{ Z Z F; marked: false; parent: T T F; children: [ ] } "
								+ "{ X X M; marked: false; parent: T T F; children: [ ] } ] } "
								+ "{ B B M; marked: false; parent: H H F; children: [ "
								+ "{ F F F; marked: false; parent: B B M; children: [ ] } "
								+ "{ W W M; marked: false; parent: B B M; children: [ ] } "
								+ "{ M M F; marked: false; parent: B B M; children: [ ] } "
								+ "{ G G M; marked: false; parent: B B M; children: [ ] } ] } ] } ] }");
		assertEquals(a.getRelationship("Zachary Doe", "Henry Leonard"), "Zachary Doe is Henry Leonard's grandson");
		assertEquals(b.getRelationship("D D", "H H"), "D D is H H's father");
		assertEquals(b.getRelationship("H H", "D D"), "H H is D D's daughter");
		assertEquals(b.getRelationship("W W", "T T"), "W W is T T's nephew");
		assertEquals(b.getRelationship("T T", "W W"), "T T is W W's aunt");
		assertEquals(b.getRelationship("N N", "F F"), "N N is F F's 1st cousin 1 times removed");
		assertEquals(b.getRelationship("N N", "D D"), "N N is D D's granddaughter");
		
		new AncestryTreeManager("input/nonexistent.txt", "input/small-postorder.txt");
		new AncestryTreeManager("input/small-preorder.txt", "input/nonexistent.txt");
		new AncestryTreeManager("input/invalid.txt", "input/small-postorder.txt");
	}

	/**
	 * Tests creation of the AhnentafelTree object and all its relevant fuctions
	 */
	@Test
	public void testAhnentafelTree() {
		AncestryTreeManager a = new AncestryTreeManager("input/small-ahnentafel.txt");
		assertEquals(a.getRelationship("Sally Poole"), "Sally Poole is Billy Smith's great-grandmother");
		assertEquals(a.getRelationship("Billy Smith"), "Billy Smith is Billy Smith");
		new AncestryTreeManager("input/invalid2.txt");
	}

}
