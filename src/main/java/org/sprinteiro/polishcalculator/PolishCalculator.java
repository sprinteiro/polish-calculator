package org.sprinteiro.polishcalculator;

/**
 * Interface for processing arithmetic operation in Reverse Polish Notation.
 *
 */
public interface PolishCalculator {
	
    /**
     * Returns the result of calculating all arithmetic operations 
     * in Reverse Polish Notation.
     *
     * @param operationsLine arithmetic expression in Reverse Polish Notation with the operations to calculate
     * @return result of calculating all arithmetic operations
     * @throws ArithmeticException if {@code operationsLine} arithmetic expression is not Reverse Polish Notation compatible
     */
	double process(String operationsLine);
}
