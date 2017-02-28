package edu.ncsu.csc316.ancestrytree.manager;

import static org.junit.Assert.*;

import org.junit.Test;

public class AncestryTreeManagerTest {

	@Test
	public void testAncestryTreeManagerStringString() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAncestryTreeManagerString() {
		AncestryTreeManager m = new AncestryTreeManager("input/small-ahnentafel.txt");
		assertEquals(m.getRelationship("Sally Poole"),"Sally Poole is Billy Smith's great-grandmother");
		assertEquals(m.getRelationship("Billy Smith"),null);
		//fail("Not yet implemented");
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
