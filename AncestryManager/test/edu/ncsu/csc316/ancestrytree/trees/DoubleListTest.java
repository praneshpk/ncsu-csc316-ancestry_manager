package edu.ncsu.csc316.ancestrytree.trees;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoubleListTest {

	@Test
	public void testAdd() {
		DoubleList<String> q = new DoubleList<>();
		for(int i=0; i<10; i++)
			q.add(i+"");
		Iterator<String> i = q.iterator();
		while(i.hasNext())
			System.out.println(i.next());
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

}
