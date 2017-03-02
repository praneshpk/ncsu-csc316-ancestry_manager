package edu.ncsu.csc316.ancestrytree.manager;

import static org.junit.Assert.*;

import org.junit.Test;

public class AncestryTreeManagerTest {

	@Test
	public void testAncestryTreeManagerStringString() {
		AncestryTreeManager a = new AncestryTreeManager("input/small-preorder.txt", "input/small-postorder.txt");
		AncestryTreeManager b = new AncestryTreeManager("input/pre-multi.txt", "input/post-multi.txt");
		System.out.println(a.getRelationship("B B","T T"));
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
