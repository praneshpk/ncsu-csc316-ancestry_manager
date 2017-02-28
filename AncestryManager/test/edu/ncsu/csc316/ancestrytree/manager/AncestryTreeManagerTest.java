package edu.ncsu.csc316.ancestrytree.manager;

import static org.junit.Assert.*;

import org.junit.Test;

public class AncestryTreeManagerTest {

	@Test
	public void testAncestryTreeManagerStringString() {
		AncestryTreeManager p = new AncestryTreeManager("input/small-preorder.txt", "input/small-postorder.txt");
		
	}

	@Test
	public void testAncestryTreeManagerString() {
		AncestryTreeManager a = new AncestryTreeManager("input/small-ahnentafel.txt");
		assertEquals(a.getRelationship("Sally Poole"), "Sally Poole is Billy Smith's great-grandmother");
		assertEquals(a.getRelationship("Billy Smith"), null);
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
