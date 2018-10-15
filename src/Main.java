//package src;

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
    
    Set factor(Scanner in, HashMap hashTable) throws APException{
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
    		createdSet = read_complex_factor(in);
    	}
    	else {
    		//throw exception here
    	}
		return createdSet;
    }
    
    Set setHashTableFinder(Identifier ident, HashMap hashTable) {
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
    
    Set setReaderMaker (HashMap hashTable, Scanner in) {
    	Set<BigInteger> set1 = new Set<>();
    	nextChar(in);
    	
    	if (nextCharIsPositiveNumber(in)) {
    		System.out.printf("Test");
    	}
    	return  set1;
    }

	boolean statement(Scanner in, HashMap hashTable) {
		if(nextCharIs(in, '?')) {
			print_statement(in, hashTable);
			in.nextLine();
		}
		else if(nextCharIsLetter(in)) {
			assignment(in, hashTable);
			in.nextLine();
		}
		else {
			System.out.println("Incorrect input command");
			in.nextLine();
			return false;
		}
		return true;
	}

	void assignment (Scanner in, HashMap hashTable) {
		Identifier ident = read_identifier(in);
		BigInteger hashCodeOfSet = BigInteger.valueOf(ident.getIdent().hashCode());
		//return the set assigned to said hash
		//set2 created
		{
		//set3
		//set2 set3
		//set2
		}
		
		
	}

	void print_statement(Scanner in, HashMap hashTable){
		do{
			read_expression(in);
		}while(read_expression(in));
	}

	void read_comment(Scanner in){}

	Identifier read_identifier(Scanner in) {
		nextChar(in);
		Identifier ident = new Identifier();
		int checker = 0;
		do {

			if(nextCharIsLetter(in)) {
				ident.add(nextChar(in));
				checker = 1;
			}
			else if(nextCharIsNaturalNumber(in) && checker != 0) {
				ident.add(nextChar(in));
			}
			else if (nextCharIs(in, ' ')) {
				nextChar(in);
			}
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
// expression>term>factor>complexfactor>expression>term....
	boolean read_expression(Scanner in){
		while(read_term(in)){
			read_term(in);
			return true;
		}
		return false;
	}

	boolean read_term(Scanner in){
		while(read_factor(in)){
			read_factor(in);
			return true;
		}

		return false;
	}
//A+B*C-D*(E+F*G)
	//call expression on A
	//call term on A
	//call factor on A, factor returns A
	//term returns A
	//expression has A now
	//moves onto next term to add to A, reads until next addative: B*C
	//call term on B*C
	//term reads until muliplicative, reads until B
	//call factor on B
	//return set B
	//moves onto next term to multi with B, reads until end: C
	//calls factor on C
	//factor returns set C
	//term resolves B*C since it has both sets and returns the set to expression
	//expression now has set A and the set resulting of B*C : BC
//A+BC-D*(E+F*G)
	//expression having both sets returned can add them together forming ABC
//ABC-D*(E+F*G)
	//ABC is now set1, read next term : D*(E+F*G)
	//this gets sent to term
	//term splits D and calls factor on D
	//factor returns set D
	//term now has set1=D and for set2 calls factor on term (E+F*G)
	//factor calls complex factor on E+F*G
	//complex factor calls expression on E+F*G
	//expression calls term on E
	//term calls factor on E
	//factor returns E, which goes up to term which returns E to expression
	//expression now has set1=E and calls term on F*G
	//term calls factor on F
	//factor returns set F
	//term has set1=F and calls factor on G
	//factor returns set G
	//term has set1=F and set2=G, resolves to set FG and returns
	//expression now has set1=E set2=FG
//ABC-D*(E+FG)
	//expression resolves E+FG and gets EFG returns it to complex factor
//ABC-D*(EFG)
	//complex factor returns EFG to factor
	//factor returns set EFG to term
	//term now has set1=D set2=EFG and resolves to DEFG
	//returns to expression
//ABC-DEFG
	//expression now has set1=ABC and set2=DEFG
	//resolves to ABCDEFG
//ABCDEFG
	//expression has no more opperators and returns set ABCDEFG
	//assignment takes this set and assignes it to input
	boolean read_factor(Scanner in){
		if(nextCharIs(in, '(')){
			read_complex_factor(in);
		}
		else if(nextCharIs(in, '{')){
			Set<BigInteger> createdSet = read_set(in);
		}
		else if(nextCharIsLetter(in)){
			read_identifier(in);
			
			//set of said identifier
		}
		return true;
	}

	Set read_complex_factor(Scanner in){
    	Set<BigInteger> newSet1 = new Set<>();
    	Set<BigInteger> newSet2 = new Set<>();
    	Set<BigInteger> calculatedSet = new Set<>();

    	while(in.hasNext()){
    		// Reads a identifier
    		if(nextCharIsLetter(in)){
    			// find Identifer from hashmap
			}
			// Reads a set
			else if(nextCharIs(in, '{')){
    			if(newSet1.isEmpty())
    				newSet1 = read_set(in);
    			else
					newSet2 = read_set(in);
			}

			else if(nextCharIsMultiplicativeOperator(in)){
    			calculatedSet = (Set<BigInteger>) newSet1.intersection(newSet2);
    			newSet1.initSet();
    			newSet2.initSet();
    			newSet1 = calculatedSet;
			}

			else if(nextCharIsAdditiveOperator(in)){
    			if(nextCharIs(in, '+')){
    				calculatedSet = (Set<BigInteger>) newSet1.union(newSet2);
    				newSet1.initSet();
    				newSet2.initSet();
    				newSet1 = calculatedSet;
				}
				else if(nextCharIs(in, '-')){
    				calculatedSet = (Set<BigInteger>) newSet1.difference(newSet2);
					newSet1.initSet();
					newSet2.initSet();
					newSet1 = calculatedSet;
				}
				else if(nextCharIs(in, '|')){
    				calculatedSet = (Set<BigInteger>) newSet1.symmetricDifference(newSet2);
					newSet1.initSet();
					newSet2.initSet();
					newSet1 = calculatedSet;
				}
			}
		}
		return newSet1;
	}

	Set read_set(Scanner in){
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