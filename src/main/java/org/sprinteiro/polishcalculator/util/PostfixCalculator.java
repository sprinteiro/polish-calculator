package org.sprinteiro.polishcalculator.util;

/**
 * Interface for calculating arithmetic operations in postfix notation.
 * The representation is read from left to right.
 * 
 * <p>Postfix notation: {@literal <operand><unaryOperator>}, {@literal <operand1><operand2><binaryOperator>}</p>
 */
public interface PostfixCalculator {

	/**
	 * Calculate an arithmetic binary operation {@literal <operand1><operand2><binaryOperator>}.
	 * 
	 * @param valueX First operand or value
	 * @param valueY Second operand or value
	 * @param operator Arithmetic operator
	 * @return Result of the arithmetic calculation
	 */
	double calculate(double valueX, double valueY, String operator);

	/**
	 * Calculate an arithmetic unary operation ({@literal <operand><unaryOperator>}).
	 * @param value Operand or value
	 * @param operator Arithmetic operator
	 * @return Result of the arithmetic calculation
	 */
	double calculate(double value, String operator);

}
