package edu.ncsu.csc316.ancestrytree.manager;

import static org.junit.Assert.*;

import org.junit.Test;

public class AncestryTreeManagerTest {

	
	@Test
	public void testTraversalTree() {
		AncestryTreeManager a = new AncestryTreeManager("input/small-preorder.txt", "input/small-postorder.txt");
		AncestryTreeManager b = new AncestryTreeManager("input/pre-multi.txt", "input/post-multi.txt");
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

	@Test
	public void testAnhnentafelTree() {
		AncestryTreeManager a = new AncestryTreeManager("input/small-ahnentafel.txt");
		assertEquals(a.getRelationship("Sally Poole"), "Sally Poole is Billy Smith's great-grandmother");
		assertEquals(a.getRelationship("Billy Smith"), "Billy Smith is Billy Smith");
		new AncestryTreeManager("input/invalid2.txt");
	}

}
