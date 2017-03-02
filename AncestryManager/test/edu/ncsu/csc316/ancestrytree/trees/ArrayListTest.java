package edu.ncsu.csc316.ancestrytree.trees;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Class contains all the test methods for checking ArrayList
 * @author Pranesh Kamalakanthan
 *
 */
public class ArrayListTest {

	/**
	 * Tests if the ArrayList can expand its capacity and throws
	 * proper exceptions
	 */
	@Test
	public void test() {
		ArrayList<String> list = new ArrayList<>();
		for(int i = 0; i < 17; i++ )
			list.addLast(i + "");
		try {
			System.out.println(list.get(18));
			fail("Should have thrown an exception");
		} catch(IndexOutOfBoundsException e) {
			// This is OK
		}
		assertEquals(list.toString(), "[ 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 ]");
	}

}
