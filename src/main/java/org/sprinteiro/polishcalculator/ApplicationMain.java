package org.sprinteiro.polishcalculator;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.sprinteiro.polishcalculator.util.ArithmeticOperationsFileReader;
import org.sprinteiro.polishcalculator.util.ReversePolishNotationOperationsFileReader;

/**
 * Bootstrap of the Rerverse Polish Notation calculator.
 */
public class ApplicationMain {
    private static final Logger LOG = LoggerFactory.getLogger(ApplicationMain.class);
	
    /**
     * Polish calculator
     */
	private static PolishCalculator polishCalculator = new SimplePolishCalculator();
	
	/**
	 * File reader to read arithmetic expression from a file
	 */
	private static ArithmeticOperationsFileReader reversePolishNotationFileReader = 
		new ReversePolishNotationOperationsFileReader();
	
	/**
	 * Main method to run the Rerverse Polish Notation calculator.
	 * @param args Path location and file name with arithmetic operations in Reverse Polish Notation
	 */
	public static void main(String[] args) {
    	if(args.length < 1) {
    		String commandExamples = "\n\tsudo mvn exec:java -Dexec.args=\"src/test/resources/reversePolishArithmeticOperations.txt"
    				+ "\n\tjava -jar ./target/polish-calculator-0.0.1-SNAPSHOT-jar-with-dependencies.jar src/test/resources/reversePolishArithmeticOperations.txt";
    		
        	String errorMessage = "Error! >> Unable to run the program. Required file name (included path) as a parameter.\n"
        		+ "\n  Examples: " + commandExamples;
        	
    		System.out.println(errorMessage);
    		System.exit(1);
        }
    	
    	try {
			reversePolishNotationFileReader.read(args[0])
			.stream()
			.forEach(arithmeticOperations -> {
					try {
						double result = polishCalculator.process(arithmeticOperations);
						System.out.println(arithmeticOperations + " = " + result);						
					} catch (RuntimeException e) {
						System.out.println(arithmeticOperations + " = " + e.getMessage());
						LOG.error(arithmeticOperations + " = " + e.getMessage());
					}
				}
			);
		} catch (IOException e) {
			LOG.error("Error when processing file {}", args[0], e);
			System.exit(2);
		}
	}
}
