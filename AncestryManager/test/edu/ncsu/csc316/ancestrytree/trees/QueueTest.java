package edu.ncsu.csc316.ancestrytree.trees;

import static org.junit.Assert.*;

import org.junit.Test;

public class QueueTest {

	/**
	 * Tests if Queue object has been created
	 */
	@Test
	public void testQueue() {
		Queue<String> list = new Queue<>();
		assertFalse( list == null );
	}

	/**
	 * Tests if enqueue method works as expected
	 */
	@Test
	public void testEnqueue() {
		Queue<String> list = new Queue<>();
		for(int i = 0; i < 5; i++ )
			list.enqueue( i + "" );
		Iterator<String> it = list.iterator();
		for(int i = 0; i < 5; i++ )
			assertEquals( it.next(), (4-i) + "" );
			
	}
	
	/**
	 * Tests if Queue object returns the size correctly
	 */
	@Test
	public void testSize() {
		Queue<String> list = new Queue<>();
		for(int i = 0; i < 5; i++ )
			list.enqueue( i + "" );
		assertEquals( list.size(), 5 );
	}
	
	/**
	 * Tests if dequeue method works as expected
	 */
	@Test
	public void testDequeue() {
		Queue<String> list = new Queue<>();
		for(int i = 0; i < 10; i++ )
			list.enqueue( i + "" );
		for(int i = 0; i < 5; i++ )
			list.dequeue();
		assertEquals( list.size(), 5 );
		
		Iterator<String> it = list.iterator();
		for(int i = 0; i < 5; i++ )
			assertEquals( it.next(), (9-i) + "" );
		for(int i = 0; i < 5; i++ )
			list.dequeue();
		try {
			list.dequeue();
			fail("Did not throw an exception");
		} catch( Exception e ) {
			// Supposed to throw an exception
		}
	}


}
