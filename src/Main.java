package src;

import java.io.PrintStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;
public class Main {
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
        return in.hasNext("[0-9]");
    }

    boolean nextCharIsLetter(Scanner in) {
        return in.hasNext("[a-zA-Z]");
    }
    
    boolean parser(Scanner in, HashMap hashTable) {
    	int typeOfO = 0;
    	int checker = 0;
    	BigInteger hashCodeOfSet = BigInteger.ZERO;
        boolean exitCondition = true;
    	if(nextCharIs(in, '?')) {
    		typeOfO = 1;
    	}
    	else if(nextCharIsLetter(in)) {
    		typeOfO = 2;
    	}
    	else {
    		System.out.println("Incorrect input command");
    		in.nextLine();
    		return false;
    	}
    	
    	switch (typeOfO) {
    	case 0:
    		return false;
    	case 1:
    		return false;
    	case 2:
    		do {
    			Identifier ident = new Identifier();
        		if(nextCharIsLetter(in)) {
        			ident.add(nextChar(in));
        			checker = 1;
        		}
        		else if(nextCharIsDigit(in) && checker != 0) {
        			ident.add(nextChar(in));
        		}
        		else if (nextCharIs(in, '=')) {
        			nextChar(in);
        			hashCodeOfSet = BigInteger.valueOf(ident.getIdent().hashCode());
        			exitCondition = false;
        		}
        		else {
        			System.out.println("Invalid input, bad name formating");
        			return false;
        		}
        		
    		}while(exitCondition);
    		
        	for (Object name: hashTable.keySet()){
        		System.out.println("mehere");
        		if (hashCodeOfSet == name) {
        			System.out.println("Found a key");
        			checker = 4;
        		}
        		
        		if (checker != 4) {
        			System.out.println("No key");
        		}
        	} 
    		
        	in.nextLine();
    		return true;
    	default:
    		return false;
    	}
    	
    	
    }
    
    boolean parserChecker(Scanner in, HashMap hashTable) {
    	do {
            if (! in.hasNextLine()) {
                out.printf("\n"); // otherwise line with ^D will be overwritten
                return false;
            }
        } while (! parser(in, hashTable));
        return true;
    }
}