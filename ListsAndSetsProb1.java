/* Maanik Gogna
 * Assignment 5 - Lists and Sets Problem 1
 * May 9, 2021
 * 
 * Program allows user to view the number of distinct substrings from any strings the user inputs from a file.
 */

import java.io.*;
import java.util.*;
public class ListsAndSetsProb1 {

	public static void main(String[] args) throws IOException, FileNotFoundException{
		//Variable initialization
		Scanner fileInput = new Scanner(new File("listProblem.txt"));
		int numCases = Integer.parseInt(fileInput.nextLine());
		HashSet <String> substrings = new HashSet<String>();
		
		if (numCases!=0) {
			System.out.println("Finding the number of distinct substrings - ");
			for (int count = 1; count<=numCases; count++) {
				String string = fileInput.nextLine();
				//Getting distinct substrings and adding to hashset
				for (int firstLetterCount = 0; firstLetterCount < string.length(); firstLetterCount++) {
					for (int lastLetterCount = firstLetterCount; lastLetterCount <= string.length(); lastLetterCount++) {
						substrings.add(string.substring(firstLetterCount, lastLetterCount));
					}
				}
				//Output
				System.out.println("\nString: " + string);
				System.out.println("Number of distinct substrings: " + substrings.size());
				substrings.clear();
			}
		}else
			System.out.println("There were no inputs to analyze.");
		fileInput.close();
	}
}