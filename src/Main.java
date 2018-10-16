package src;

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
    	//the "Set" is a placeholder, this is the type our hasmap will hold im fairly sure it should be the sets though
    	HashMap<BigInteger, Set> hashTable = new HashMap<BigInteger, Set>();
    	
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

	Set<BigInteger> factor(Scanner in, HashMap hashTable){
    	Set<BigInteger> createdSet = new Set<>();

    	if (nextCharIsLetter(in)) {
    		Identifier ident = read_identifier(in);
    		return setHashTableFinder(ident, hashTable);
    		//retrieve the set that belongs with that identifier
    	}
    	else if(nextCharIs(in, '{')) {
    		createdSet = read_set(in);
    	}
    	else if(nextCharIs(in, '(')) {
    		createdSet = read_complex_factor(hashTable, in);
    	}
    	else {
    		//throw exception here
    	}
		return createdSet;
    }

	Set<BigInteger> setHashTableFinder(Identifier ident, HashMap hashTable) {
    	Set<BigInteger> set1 = null;
    	BigInteger hashCodeOfSet = BigInteger.valueOf(ident.getIdent().hashCode());
        for (Object name: hashTable.keySet()){
            if (hashCodeOfSet.equals(name)) {
                System.out.println("Found a key");
                set1 = (Set) hashTable.get(hashCodeOfSet);
            }
        }
        if (set1==null) {
        	//throw error
        }
        return set1;
    }

    /*
    Set<BigInteger> setReaderMaker (HashMap hashTable, Scanner in) {
    	Set<BigInteger> set1 = new Set<>();
    	nextChar(in);
    	
    	if (nextCharIsPositiveNumber(in)) {
    		System.out.printf("Test");
    	}
    	return  set1;
    }*/

	boolean statement(Scanner in, HashMap hashTable) {
		if(nextCharIs(in, '?')) {
			print_statement(in, hashTable);
			in.nextLine();
		}
		else if(nextCharIsLetter(in)) {
			assignment(in, hashTable);
			in.nextLine();
		}
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
    	Set<BigInteger> toIdent = read_expression(hashTable, in);
		Identifier ident = read_identifier(in);
		BigInteger hashCodeOfSet = BigInteger.valueOf(ident.getIdent().hashCode());

		// Hash table contains the key
		if(hashTable.containsKey(hashCodeOfSet))
			hashTable.replace(hashCodeOfSet, toIdent);
		else
			hashTable.put(hashCodeOfSet, toIdent);
	}

	void print_statement(Scanner in, HashMap hashTable){
		read_expression(hashTable, in).printSet();
	}

	void read_comment(Scanner in){
    	in.nextLine();
	}

	// Creates the identifier
	Identifier read_identifier(Scanner in) {
		nextChar(in);
		Identifier ident = new Identifier();
		int checker = 0;
		do {

			if(nextCharIsLetter(in)) {
				ident.add(nextChar(in));
				checker = 1;
			}
			else if(nextCharIsNaturalNumber(in) && checker != 0)
				ident.add(nextChar(in));

			else if (nextCharIs(in, ' '))
				nextChar(in);

			else if (nextCharIsAdditiveOperator(in) | nextCharIsAdditiveOperator(in) | nextCharIs(in, '=') ) {
				nextChar(in);
				checker =2;
			}
			else {
				System.out.println("Invalid input, bad name formatting");
			}

		}while(checker!=2);
		return ident;
	}

	Set<BigInteger> read_expression(HashMap hashTable, Scanner in){
    	Set<BigInteger> expressionSet = read_term(hashTable, in);
    	Set<BigInteger> setToAdd;
    	while(nextCharIsAdditiveOperator(in)){
    		setToAdd = read_term(hashTable, in);
    		if(nextCharIs(in, '+'))
    			return (Set) expressionSet.intersection(setToAdd);

			else if(nextCharIs(in, '-'))
    			return (Set) expressionSet.difference(setToAdd);

			else if(nextCharIs(in, '|'))
    			return (Set) expressionSet.symmetricDifference(setToAdd);
		}

		return expressionSet;
	}


	Set<BigInteger> read_term(HashMap hashTable, Scanner in){
    	Set<BigInteger> termSet = read_factor(hashTable, in);
		Set<BigInteger> setToMultiply;

		if(nextCharIsMultiplicativeOperator(in)) {
			setToMultiply = read_factor(hashTable, in);
			termSet.intersection(setToMultiply);
		}

		return  termSet;
	}

	Set<BigInteger> read_factor(HashMap hashTable, Scanner in){
    	Set<BigInteger> createdSet = new Set<>();
		if(nextCharIs(in, '(')){
			read_complex_factor(hashTable, in);
		}
		else if(nextCharIs(in, '{')){
			createdSet = read_set(in);
		}
		else if(nextCharIsLetter(in)){
			Identifier toFind = read_identifier(in);
			if(hashTable.get(toFind) == null){
				System.out.printf("ERROR, IDENTIFIER NOT FOUND\n");
			}
			else
				createdSet = (Set<BigInteger>) hashTable.get(toFind);
		}
		return createdSet;
	}

	Set<BigInteger> read_complex_factor(HashMap hashTable, Scanner in){
    	return read_expression(hashTable, in);
	}

	Set<BigInteger> read_set(Scanner in){
    	Set<BigInteger> newSet = new Set<>();
		while(!nextCharIs(in, '}')){
			read_row_natural_numbers(in, newSet);
		}
		return newSet;
	}

	void read_row_natural_numbers(Scanner in, Set newSet){
    	StringBuffer natNumb = new StringBuffer();
    	while(in.hasNext()) {
			while (!nextCharIs(in, ',')) {
				natNumb.append(in.next());
			}
			String toAdd = natNumb.toString();
			natNumb.delete(0,natNumb.length());			// May have to change it to length-1
			newSet.add(new BigInteger(toAdd));
		}
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

	public boolean nextCharIsPositiveNumber(Scanner in){
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

}