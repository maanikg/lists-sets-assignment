public class Fraction implements Comparable<Fraction>{
	//Fraction Class - used for creating Fraction objects

	//Variable initialization
	private String fractionStr;
	private int numerator, denominator;
	private double decimalFraction;

	//Fraction constructor
	public Fraction(int numeratorInput, int denominatorInput) {
		numerator = numeratorInput;
		denominator = denominatorInput;
		decimalFraction = (double)numeratorInput/(double)denominatorInput;
		fractionStr = "" + numeratorInput + "/" + denominatorInput;
	}

	//toString method for output of Fraction objects
	public String toString() {
		return fractionStr;
	}
	
	//Getter method for the decimal value of the fraction
	public double getDecimalFraction() {
		return decimalFraction;
	}

	//compareTo method used to sort fractions by their values
	public int compareTo(Fraction fract) {
		return ((numerator*fract.denominator)-(fract.numerator*denominator));
	}
}