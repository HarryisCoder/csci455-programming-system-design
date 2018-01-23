
// CS 455 PA2
// Spring 2017

import java.util.Arrays;
import java.util.ArrayList;
import java.lang.Integer;
import java.lang.Double;
import java.util.Scanner;

/**
	An interactive interface that lets users build and do computations on several Polynomial objects.
*/

public class PolynomialCalculator{

	private final static int NUM_POLY = 10;

	public static void main(String[] args){

		Polynomial[] polynomial = new Polynomial[NUM_POLY];
		// initialize polynomial array
		for (int i = 0; i < polynomial.length; i++){
			polynomial[i] = new Polynomial();
		}

		Scanner in = new Scanner(System.in);
		String inputString;
		// print command summary for users
		printHelp();

		while(true){

			// read in command
			System.out.print("cmd> ");
			in = new Scanner(System.in);
			inputString = in.nextLine();
			in = new Scanner(inputString);
			String command; 
			//boolean hasNext = in.hasNext();
			if(in.hasNext()){
				command = in.next();
			}
			else{
				command = "";
			}

			// when no argument or illegal command encountered, ask user to reinput
			while((command == "") || !isValidCommand(command)){

				if(!isValidCommand(command)){
					System.out.println("ERROR: Illegal command. Type 'help' for command options.");
				}
				System.out.print("cmd> ");
				in = new Scanner(System.in);
				inputString = in.nextLine();
				in = new Scanner(inputString);
				if(in.hasNext()){
					command = in.next();
				}
				else{
					command = "";
				}

			}

			if(command.equalsIgnoreCase("help")){
				printHelp();
			}

			else if(command.equalsIgnoreCase("quit")){
				return;
			}

			else if(command.equalsIgnoreCase("create")){
				if(in.hasNextInt()){
					int polyId = in.nextInt();
					//check input polyId
					if(isValidPolyId(polyId)){
						polynomial[polyId] = new Polynomial();
						System.out.println("Polynomial " + polyId + " initialized!");
						System.out.println("Enter a space-separated sequence of coeff-power pairs terminated by <nl>");
						in = new Scanner(System.in);
						inputString = in.nextLine();
						in = new Scanner(inputString);
						//only do "create" when there is input sequence of coeff-power pairs
						if(in.hasNext()){
							polynomial[polyId] = doCreate(in);
						}
					}
				}
			}

			else if(command.equalsIgnoreCase("print")){
				if(in.hasNextInt()){
					int polyId = in.nextInt();
					//check input polyId
					if(isValidPolyId(polyId)){
						System.out.println(polynomial[polyId].toFormattedString());
					}
				}
			}

			else if(command.equalsIgnoreCase("add")){
				if(in.hasNextInt()){
					int polyId3 = in.nextInt();
					int polyId1 = in.nextInt();
					int polyId2 = in.nextInt();
					//check input polyId
					if(isValidPolyId(polyId1) && isValidPolyId(polyId2) && isValidPolyId(polyId3)){
						polynomial[polyId3] = doAdd(polynomial[polyId1], polynomial[polyId2]);
						System.out.println("Add successfully!");
					}
				}
			}

			else if(command.equalsIgnoreCase("eval")){
				if(in.hasNextInt()){
					int polyId = in.nextInt();
					//check input polyId
					if(isValidPolyId(polyId)){
						System.out.print("Enter a floating point value for x: ");
						in = new Scanner(System.in);
						//only do "eval" when there is input double value
						if(in.hasNextDouble()){
							double x = in.nextDouble();
							System.out.printf("%.1f", doEval(polynomial[polyId], x));
							System.out.println();
						}
					}
				}
			}

		}

	}

    /**
       Returns true if input command is valid
    */
	private static boolean isValidCommand(String command){

		if (command.equalsIgnoreCase("create") || command.equalsIgnoreCase("print") || command.equalsIgnoreCase("add") || command.equalsIgnoreCase("eval") || command.equalsIgnoreCase("help") || command.equalsIgnoreCase("quit") || command.equalsIgnoreCase("")){
			return true;
		}
		return false;
	}

    /**
       Returns true if input polynomial ID is valid
    */
	private static boolean isValidPolyId(int polyId){

		if (polyId >= NUM_POLY || polyId < 0){
			System.out.println("ERROR: illegal index for a poly. must be between 0 and " + (NUM_POLY-1) +", inclusive.");
			return false;
		}
		return true;
	}

	private static void printHelp(){

		System.out.println("---------------------------------------------------------");
		System.out.println("Commands: ");
		System.out.println("--create: create a polynomial with id");
		System.out.println("  ex. create 0 (create a polynomial0)");
		System.out.println("--print: print formatted polynoial");
		System.out.println("  ex. print 0 (print polynomial0)");
		System.out.println("--add: calculate sum of two polynomials");
		System.out.println("  ex. add 3 1 2 (polynomial3 = polynomial1 + polynomial2) ");
		System.out.println("--eval polyid: ");
		System.out.println("  ex. eval 0 (calculate the value of polynomial0)");
		System.out.println("--help: show command summary");
		System.out.println("--quit: exit the program");
		System.out.println("---------------------------------------------------------");
	}

    /**
       Creates a polynomial according to sequence of coeff-expon pairs input by user
    */
	private static Polynomial doCreate(Scanner in){

		ArrayList<Integer> exponArray = new ArrayList<Integer>();
		ArrayList<Double> coeffArray = new ArrayList<Double>();
		Polynomial result = new Polynomial();

		// read in coeff-power pairs
		while(in.hasNextDouble() || in.hasNextInt()){
			// store coefficients in coeffArray
			if(in.hasNextDouble()){
				Double nextDouble = in.nextDouble();
				coeffArray.add(nextDouble);
			}
			// store exponents in exponArray
			if(in.hasNextInt()){
				int expon = in.nextInt();
				// check if exponent is negtive, if yes, use the absolute value instead
				if (expon < 0){
					System.out.println("WARNING: Negavtive exponent is not allowed! Use the absolute value instead.");
					Integer nextInteger = -expon;
					exponArray.add(nextInteger);
				}
				else{
					Integer nextInteger = expon;
					exponArray.add(nextInteger);
				}
			}
		}
		// check if the number of input values is odd, if yes, ignore the last exponent
		if (coeffArray.size() != exponArray.size()){
			System.out.println("WARNING: Missing the last exponent! Ignore the last value entered.");
		}
		if(coeffArray.size() > 0 && exponArray.size() > 0){
			for (int i = 0; i < (coeffArray.size() + exponArray.size())/2; i++){
				Polynomial temp = new Polynomial();
				result = result.add(new Polynomial(new Term(coeffArray.get(i), exponArray.get(i))));
			}
			System.out.println("Create polynomial successfully!");
		}
		return result;

	}

    /**
       Returns the Polynomial that is the sum of two input Polynomials.
    */
	private static Polynomial doAdd(Polynomial poly1, Polynomial poly2){

		return poly1.add(poly2);
	}

    /**
       Returns the value of the polynomial at a given value of x.
    */
	private static double doEval(Polynomial poly, double x){

		return poly.eval(x);
	}
}

