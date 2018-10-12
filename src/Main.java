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
    
    public char nextChar(Scanner in){
        return in.next().charAt(0);
    }

    public boolean nextCharIs(Scanner in, char c){
        return in.hasNext(Pattern.quote(c+""));
    }


    public boolean nextCharIsDigit(Scanner in){
        return in.hasNext("[1-9]");
    }
    
    public boolean nextCharIsZero(Scanner in){
        return in.hasNext("[0]");
    }

    boolean nextCharIsLetter(Scanner in) {
        return in.hasNext("[a-zA-Z]");
    }
    
    boolean statement(Scanner in, HashMap hashTable) {
    	if(nextCharIs(in, '?')) {
    		print_statement(in, hashTable);
    	}
    	else if(nextCharIsLetter(in)) {
    		assignment(in, hashTable);
    	}
    	else {
    		System.out.println("Incorrect input command");
    		in.nextLine();
    		return false;
    	}
    }
    
    Identifier identifierReader(Scanner in) {
    	nextChar(in);
		Identifier ident = new Identifier();
		int checker = 0;
		do {
			
    		if(nextCharIsLetter(in)) {
    			ident.add(nextChar(in));
    			checker = 1;
    		}
    		else if(nextCharIsDigit(in) && checker != 0) {
    			ident.add(nextChar(in));
    		}
    		else if (nextCharIs(in, ' ')) {
    			nextChar(in);
    		}
    		else if (nextCharIs(in, '+') | nextCharIs(in, '|') | nextCharIs(in, '-') | nextCharIs(in, '*') | nextCharIs(in, '=') ) {
    			nextChar(in);
    			checker =2;
    		}
    		else {
    			System.out.println("Invalid input, bad name formatting");
    		}
    		
		}while(checker!=2);
		return ident;
    }
    
    void assignment (Scanner in, HashMap hashTable) {
    	Identifier ident = identifier(in);
    	BigInteger hashCodeOfSet = BigInteger.valueOf(ident.getIdent().hashCode());
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
    	if (nextCharIsLetter(in)) {
    		Identifier ident = identifierReader(in);
    		return setHashTableFinder(ident, hashTable);
    		//retrieve the set that belongs with that identifier
    	}
    	else if(nextCharIs(in, '{')) {
    		//read set
    	}
    	else if(nextCharIs(in, '(')) {
    		//determine the set that is the result of the complex factor
    	}
    	else {
    		//throw exception here
    	}
		return null;
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
    	
    	if (nextCharIsDigit(in)) {
    		
    	}
    }
    
}