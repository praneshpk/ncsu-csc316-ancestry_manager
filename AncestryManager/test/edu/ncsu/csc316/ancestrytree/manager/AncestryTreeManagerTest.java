package edu.ncsu.csc316.ancestrytree.manager;

import static org.junit.Assert.*;

import org.junit.Test;

public class AncestryTreeManagerTest {

	@Test
	public void testAncestryTreeManagerStringString() {
		AncestryTreeManager a = new AncestryTreeManager("input/small-preorder.txt", "input/small-postorder.txt");
		AncestryTreeManager b = new AncestryTreeManager("input/pre-multi.txt", "input/post-multi.txt");
		assertEquals(b.getRelationship("D D", "H H"), "D D is H H's father");
		assertEquals(b.getRelationship("W W", "T T"), "W W is T T's nephew");
		assertEquals(b.getRelationship("N N", "F F"), "N N is F F's 1st cousin 1 times removed");
	}

	@Test
	public void testAncestryTreeManagerString() {
		AncestryTreeManager a = new AncestryTreeManager("input/small-ahnentafel.txt");
		assertEquals(a.getRelationship("Sally Poole"), "Sally Poole is Billy Smith's great-grandmother");
		assertEquals(a.getRelationship("Billy Smith"), "Billy Smith is Billy Smith");
	}

	@Test
	public void testParseFile() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetLevelOrder() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetRelationshipStringString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGetRelationshipString() {
		//fail("Not yet implemented");
	}

}
