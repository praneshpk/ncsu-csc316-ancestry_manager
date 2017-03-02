package edu.ncsu.csc316.ancestrytree.trees;
/**
 * The Person class has all the variables that define
 * a person in the AncestryTree
 * @author Pranesh Kamalakanthan
 *
 */
public class Person {
	/** First name */
	private String fname;
	/** Last name */
	private String lname;
	/** Gender */
	private boolean gender;
	/** Ahnentafel id */
	private int id;
	
	/**
	 * Creates a new Person object with gender
	 * @param fn first name
	 * @param ln last name
	 * @param g gender, female = true, male = false
	 */
	public Person( String fn, String ln, boolean g ) {
		fname = fn;
		lname = ln;
		gender = g;
		id = -1;
	}
	/**
	 * Creates a new Person object with Ahnentafel id
	 * @param fn first name
	 * @param ln last name
	 * @param i Ahnentafel id
	 */
	public Person( String fn, String ln, int i ) {
		fname = fn;
		lname = ln;
		id = i;
		if(i % 2 == 0)
			gender = false;
		else
			gender = true;
	}
	/**
	 * Gets the ahnentafel id
	 * @return id
	 */
	public int getId() { return id; }
	
	/**
	 * Gets the first name
	 * @return fname
	 */
	public String getFname() { return fname; }
	
	/**
	 * Gets the last name
	 * @return lname
	 */
	public String getLname() { return lname; }
	
	/**
	 * Returns whether the Person is female or male
	 * @return true if female, false if male
	 */
	public boolean isFemale() { return gender; }
	
	/**
	 * Checks if they have the same name
	 * @param obj Person being compared to
	 * @return true if they are the same, false otherwise
	 */
	public boolean equals(Object obj) {
		Person p = (Person)obj;
		return p.fname.equals(fname) && p.lname.equals(lname);
	}
	
	/**
	 * Returns the Person object's information
	 * @return a String representation of the object
	 */
	public String toString() {
		if(id == -1)
			return fname + " " + lname + " " + ( (gender) ? "F" : "M" );
		else
			return id + " " + fname + " " + lname + " " + gender;
	}
	

}
