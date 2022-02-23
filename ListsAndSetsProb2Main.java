/* Maanik Gogna
 * Assignment 5 - Lists and Sets Problem 2
 * May 9, 2021
 * 
 * Program allows to set a maximum denominator and view the number of fractions that have a denominator no greater than the one the user inputted falling between 0 and 1.
 * Program also asks for a range of fractions, and the program will output the number of fractions that fall inside that range. 
 */

import java.util.*;
public class ListsAndSetsProb2Main {

	public static void main(String[] args) {
		//Variable initialization
		Scanner userInput = new Scanner(System.in);
		String strInput = "";
		int fractNumerator = 0, fractDenominator = 0, maxDenominator = 0, counter = 0;
		boolean validInput = false, goAgain = true;
		TreeSet <Fraction> fractions = new TreeSet<Fraction>();
		Fraction lowerRange = null, upperRange = null;
		Iterator<Fraction> fractionIterator = null;

		while (goAgain) {
			//Asking for max denominator input from user
			System.out.print("Enter the maximum denominator(you can also enter a blank field to leave now): ");
			strInput = userInput.nextLine().trim();
			if (strInput.length()!=0) {
				while (!validInput && goAgain) {
					try {
						maxDenominator = Integer.parseInt(strInput);
						if (maxDenominator <=0 || maxDenominator >= 1001) {
							System.out.println("\nERROR - Please enter an integer between 1 and 1000 inclusive.");
							System.out.println("You can also enter a blank field to leave.");
							System.out.print("Enter the maximum denominator: ");
							strInput = userInput.nextLine().trim();
						}else
							validInput = true;
					}catch(NumberFormatException e) {
						if (strInput.length()!=0) {
							System.out.println("\nERROR - Please enter a valid integer.");
							System.out.println("You can also enter a blank field to leave.");
							System.out.print("Enter the maximum denominator: ");
							strInput = userInput.nextLine().trim();
						}else 
							goAgain = false;
					}
				}


				if (validInput && goAgain) {
					//Adding all the fractions between 0 and 1 that have a denominator less than or equal to the max denominator to the treeset 
					for (int numerator = 0; numerator <= maxDenominator; numerator++) 
						for (int denominator = 1; denominator <= maxDenominator; denominator++)
							if (numerator <= denominator)
								fractions.add(new Fraction(numerator, denominator));


					//Asking the user for the lower and upper ranges of the fractions 
					for (int fractionInput = 1; fractionInput <= 2; fractionInput++) {
						validInput = false;
						String fractionTypeInput = "";
						if (fractionInput == 1)
							fractionTypeInput = "lower";
						else
							fractionTypeInput = "upper";

						System.out.printf("%nEnter the %s limit of the range of fractions as a fraction of the form(##/##): ", fractionTypeInput);
						strInput = userInput.nextLine().trim();
						while (!validInput) {
							try {
								fractNumerator = Integer.parseInt(strInput.substring(0, strInput.indexOf("/")));
								fractDenominator = Integer.parseInt(strInput.substring(strInput.indexOf("/") + 1));

								//Error checking for invalid inputs
								if (fractDenominator == 0 || (fractionInput == 1 && ((double)fractNumerator/(double)fractDenominator < 0 || (double)fractNumerator/(double)fractDenominator >= 1)) || 
										(fractionInput == 2 && ((double)fractNumerator/(double)fractDenominator <=0 || (double)fractNumerator/(double)fractDenominator > 1))) {
									if (fractDenominator == 0) 
										System.out.print("\nERROR - Please ensure that your denominator is greater than 0.");
									else if (fractionInput == 1)
										System.out.print("\nERROR - Please ensure that the fraction's value is greater than or equal to 0 and less than 1.");
									else if (fractionInput == 2)
										System.out.print("\nERROR - Please ensure that the fraction's value is greater than 0 and less than or equal to 1.");
									System.out.printf("%nEnter the %s limit of the range of fractions as a fraction of the form(##/##): ", fractionTypeInput);
									strInput = userInput.nextLine().trim();
								}else {
									//If user inputs a proper value
									if (fractionInput == 1) {
										if (fractNumerator <= 0 && fractDenominator < 0) {
											fractNumerator*=-1;
											fractDenominator*=-1;
											System.out.printf("%nYour fraction has been converted to %d/%d.", fractNumerator, fractDenominator);
										}
										lowerRange = new Fraction(fractNumerator, fractDenominator);
										validInput = true;
									}else {
										//If lower range is higher than upper range
										if (new Fraction(fractNumerator, fractDenominator).getDecimalFraction() <= lowerRange.getDecimalFraction()) {
											System.out.print("\nERROR - Please ensure that the upper limit of the range of fractions is greater than the lower limit of the range of fractions.");
											System.out.printf("%nEnter the %s limit of the range of fractions as a fraction of the form(##/##): ", fractionTypeInput);
											strInput = userInput.nextLine().trim();
										}else {
											//Converting negative fraction to positive if applicable
											if (fractNumerator <= 0 && fractDenominator < 0) {
												fractNumerator*=-1;
												fractDenominator*=-1;
												System.out.printf("%nYour fraction has been converted to %d/%d.", fractNumerator, fractDenominator);
											}
											upperRange = new Fraction(fractNumerator, fractDenominator);
											validInput = true;
										}
									}
								}
							}catch (NumberFormatException e) {
								//Error checking for invalid inputs
								System.out.println("\nERROR - Please enter the fraction in the format(##/##).");
								System.out.printf("Enter the %s limit of the range of fractions as a fraction of the form(##/##): ", fractionTypeInput);
								strInput = userInput.nextLine().trim();
							}catch (StringIndexOutOfBoundsException e) {
								//Error checking for invalid inputs
								System.out.println("\nERROR - Please enter the fraction in the format(##/##).");
								System.out.printf("Enter the %s limit of the range of fractions as a fraction of the form(##/##): ", fractionTypeInput);
								strInput = userInput.nextLine().trim();
							}
						}
					}

					fractionIterator = fractions.iterator();
					//Getting fractions within range set by user
					while (fractionIterator.hasNext()) {
						Fraction currFraction = fractionIterator.next();
						if (currFraction.getDecimalFraction()>=lowerRange.getDecimalFraction() && currFraction.getDecimalFraction()<=upperRange.getDecimalFraction())
							counter++;
						else if (currFraction.getDecimalFraction()>upperRange.getDecimalFraction())
							break;
					}
					//Output
					System.out.printf("%nTotal number of fractions: %d%n", fractions.size());
					System.out.printf("Number of fractions between %s and %s inclusive: %d%n", lowerRange, upperRange, counter);
					counter = 0;
					validInput = false;
				}

			}else
				goAgain = false;
		}
		System.out.println("\nHave a nice day!");
		userInput.close();
	}
}