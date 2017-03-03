package edu.ncsu.csc316.ancestrytree.manager;

import java.util.Scanner;

/**
 * The AncestryTreeManagerUI class allows the user to interface with the
 * AncestryTreeManager and input files to be made into trees
 * @author Pranesh Kamalakanthan
 */
public class AncestryTreeManagerUI {
	/**
	 * Starts the program
	 * @param args command line arguments
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) {
		AncestryTreeManager a = null;
		// Asks for user input
		Scanner in = new Scanner(System.in);
		System.out.println("Ahnentafel (1) or PreOrder/PostOrder (2)? ");
		if(in.nextLine().equals("1")) {
			System.out.print("Enter your Ahnentafel filename: ");
			try {
				a = new AncestryTreeManager("input/" + in.nextLine());
			} catch( Exception e) {
				System.exit(1);
			}
			System.out.print("Enter a name to find the relationship to: ");
			String output = a.getRelationship(in.nextLine());
			if(output == null)
				System.exit(1);
			System.out.println(output);
		}
		else {
			System.out.print("Enter your PreOrder / PostOrder filenames separated by spaces: ");
			String input = in.nextLine();
			String[] ainput = input.split("\\s+");
			if(ainput.length == 1) {
				System.out.println("Error: Need to enter two files!");
				System.exit(1);
			}
			try {
				a = new AncestryTreeManager("input/" + ainput[0], "input/" + ainput[1]);
			} catch( Exception e) {
				System.exit(1);
			}
			System.out.print("Enter two names separated by the return key: ");
			String output = a.getRelationship(in.nextLine(), in.nextLine());
			if(output == null)
				System.exit(1);	
			System.out.println(output);
			
		}
		
		in.close();
	}
}
