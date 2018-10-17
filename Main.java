import java.io.PrintStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main  {
	PrintStream out;

	private void start() {
		// Create a scanner on System.in
		// While there is input, read line and parse it.
		//the "Set" is a placeholder, this is the type our hash map will hold im fairly sure it should be the sets though
		HashMap<BigInteger, Set> hashTable = new HashMap<>();

		Scanner in = new Scanner(System.in);
		in.useDelimiter("");
        while(parserChecker(in, hashTable)) {
    	   System.out.println("Parsed a line");

       }
	}

	public static void main(String[] argv) {
		new Main().start();
	}

	boolean parserChecker(Scanner in, HashMap hashTable) {
		do {
			if (! in.hasNextLine()) {
				out.printf("\n"); // otherwise line with ^D will be overwritten
				return false;
			}
		} while (! statement(in, hashTable));
		return true;
	}

	Set<BigInteger> setHashTableFinder(Identifier ident, HashMap hashTable) {
		Set<BigInteger> set1 = null;
		BigInteger hashCodeOfSet = BigInteger.valueOf(ident.getIdent().hashCode());
		for (Object name: hashTable.keySet()){
			if (hashCodeOfSet.equals(name)) {
				return (Set) hashTable.get(hashCodeOfSet);
			}
		}
		if (set1==null) {
			System.out.printf("IDENTIFIER NOT FOUND, BUDDY\n");
			//throw error
		}
		return set1;
	}

	boolean statement(Scanner in, HashMap hashTable) {
		// Skip spaces before print | Identifier | Comment
		skipSpaces(in);

		// Assignment
		if(nextCharIs(in, '?')) {
			print_statement(in, hashTable);
			in.nextLine();
		}
		// Print Statement
		else if(nextCharIsLetter(in)) {
			assignment(in, hashTable);
			in.nextLine();
		}
		// Comment
		else if(nextCharIs(in, '/'))
			read_comment(in);
		else {
			System.out.println("Incorrect input command");
			in.nextLine();
			return false;
		}
		return true;
	}

	void assignment (Scanner in, HashMap hashTable) {
		Set<BigInteger> toIdent;

		// First skip spaces
		skipSpaces(in);

		// Identifier
		Identifier ident = read_identifier(in);

		// Read the '='
		skipSpaces(in);
		if(!nextCharIs(in, '=')){
			System.out.printf("THROW AN ERROR");
		}
		else{
			// Moves scanner past '='
			nextChar(in);
			skipSpaces(in);
		}

		// Read expression
		toIdent = read_expression(hashTable, in);

		// Create hash code for the created Set
		BigInteger hashCodeOfSet = BigInteger.valueOf(ident.getIdent().hashCode());

		// Either replace the element of the hash map or create a new element in the hash map
		if(hashTable.containsKey(hashCodeOfSet)) {
			hashTable.replace(hashCodeOfSet, toIdent);
		}
		else{
			hashTable.put(hashCodeOfSet, toIdent);
		}

	}


	void print_statement(Scanner in, HashMap hashTable){
		Set<BigInteger> toPrint;

		// Moves past the '?'
		nextChar(in);

		skipSpaces(in);

		// Expression
		toPrint = read_expression(hashTable, in);

		// Prints the set created by expression
		toPrint.printSet();
	}

	void read_comment(Scanner in){
		in.nextLine();
	}

	// Creates the identifier
	Identifier read_identifier(Scanner in) {
		// Scanner is at the first letter of the identifier
		Identifier ident = new Identifier();

		int checker = 0;

		do {
			// Adds a letter to the Identifier
			if(nextCharIsLetter(in)) {
				ident.add(nextChar(in));
				checker = 1;
			}
			// Adds a natural Number to the Identifier
			else if(nextCharIsNaturalNumber(in) && checker != 0)
				ident.add(nextChar(in));

			// After an identifier one can either enter a ' '
			else if (nextCharIs(in, ' '))
				checker = 2;

			// Or a '='
			else if (nextCharIs(in, '=')) {
				System.out.printf("EQUAL IS READ\n");
				checker = 2;
			}

			// Additive and multiplicative operators should not be inputted, so throw an error
			else if(nextCharIsAdditiveOperator(in) || nextCharIsMultiplicativeOperator(in)) {
				System.out.printf("THROW AN ERROR");
				checker = 2;
			}
			else {
				//if((String.valueOf(nextChar(in)).matches("\r"))) {
				checker = 2;
				//System.out.println("Invalid input, bad name formatting");
				//}
			}

		}while(checker!=2);

		return ident;
	}


	Set<BigInteger> read_expression(HashMap hashTable, Scanner in) {
		// Read term
		Set<BigInteger> expressionSet = read_term(hashTable, in);
		skipSpaces(in);

		// There is just one term
		if (!nextCharIsAdditiveOperator(in))
			return expressionSet;

		else {
			skipSpaces(in);
			// Read additive operator and one or more terms
			while (nextCharIsAdditiveOperator(in)) {
				skipSpaces(in);
				if (nextCharIs(in, '+')) {
					// Scanner moves past +
					nextChar(in);
					skipSpaces(in);
					Set<BigInteger> toAdd = read_term(hashTable, in);
					expressionSet = (Set) expressionSet.union(toAdd);

				} else if (nextCharIs(in, '-')) {
					nextChar(in);
					skipSpaces(in);
					Set<BigInteger> toAdd = read_term(hashTable, in);
					expressionSet = (Set) expressionSet.difference(toAdd);
				} else if (nextCharIs(in, '|')) {
					nextChar(in);
					skipSpaces(in);
					Set<BigInteger> toAdd = read_term(hashTable, in);
					expressionSet = (Set) expressionSet.symmetricDifference(toAdd);
				}

				skipSpaces(in);
			}

			return expressionSet;
		}
	}

	Set<BigInteger> read_term(HashMap hashTable, Scanner in){
		// Read factor
		Set<BigInteger> termSet = read_factor(hashTable, in);

		skipSpaces(in);

		// Read a multiplicative operator
		if(nextCharIsMultiplicativeOperator(in)) {
			nextChar(in);
			skipSpaces(in);
			// Read another factor
			termSet = (Set)termSet.intersection(read_factor(hashTable, in));
		}

		return  termSet;
	}

	Set<BigInteger> read_factor(HashMap hashTable, Scanner in){
		// Identifier
		if(nextCharIsLetter(in))
			return setHashTableFinder(read_identifier(in), hashTable);
		// Complex factor
		else if(nextCharIs(in, '('))
			return read_complex_factor(hashTable, in);
		// Or a Set
		else if(nextCharIs(in,'{'))
			return read_set(in);

		// Throw an error if this returns null
		return null;
	}

	Set<BigInteger> read_complex_factor(HashMap hashTable, Scanner in){
		// Move scanner past '('
		nextChar(in);
		// Read expression
		return read_expression(hashTable, in);

		// NOTE : Scanner is at ')' after the return
	}

	Set<BigInteger> read_set(Scanner in){
		// Move scanner past '{'
		nextChar(in);
		// Create an empty set
		Set<BigInteger> newSet = new Set<>();

		// Adds a row of natural numbers to the set
		while(!nextCharIs(in, '}')){
			read_row_natural_numbers(in, newSet);
			if(nextCharIs(in, ','))
				nextChar(in);
		}

		// Move scanner past '}'
		nextChar(in);

		return newSet;
	}

	void read_row_natural_numbers(Scanner in, Set newSet){
		StringBuffer natNumb = new StringBuffer();

		skipSpaces(in);

		while(nextCharIsNaturalNumber(in)){
			natNumb.append(nextChar(in));
		}

		String toAdd = natNumb.toString();

		// Add the string buffer to the set
		newSet.add(new BigInteger(toAdd));

	}

	boolean nextCharIsAdditiveOperator(Scanner in){
		if(nextCharIs(in, '+')){
			return true;
		}
		else if(nextCharIs(in, '|')){
			return true;
		}
		else if(nextCharIs(in, '-')){
			return true;
		}
		return false;
	}

	boolean nextCharIsMultiplicativeOperator(Scanner in){
		if(nextCharIs(in, '*'))
			return true;
		return false;
	}

	boolean nextCharIsNaturalNumber(Scanner in){
		if(nextCharIsPositiveNumber(in) || nextCharIsZero(in))
			return true;

		return false;
	}

	public boolean nextCharIsZero(Scanner in){
		return in.hasNext("[0]");
	}

	boolean nextCharIsPositiveNumber(Scanner in){
		return in.hasNext("[1-9]");
	}

	boolean nextCharIsLetter(Scanner in) {
		return in.hasNext("[a-zA-Z]");
	}

	public boolean nextCharIs(Scanner in, char c){
		return in.hasNext(Pattern.quote(c+""));
	}

	public char nextChar(Scanner in){
		return in.next().charAt(0);
	}

	void skipSpaces(Scanner in){
		while(nextCharIs(in, ' ')){
			in.next();
		}
	}

}