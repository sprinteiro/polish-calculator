package org.sprinteiro.polishcalculator.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Math.sqrt;
import static java.lang.Math.sin;
import static java.lang.Math.cos;

/**
 * A simple calculator in postfix notation to calculate arithmetic operations.
 * 
 * Postfix notation: {@literal <operand><unaryOperator>}, {@literal <operand1><operand2><binaryOperator>}
 */
public class SimplePostfixCalculator implements PostfixCalculator 
{
	private static final Logger LOG = LoggerFactory.getLogger(SimplePostfixCalculator.class);
	
	/**
	 * Valid/Supported arithmetic operations in pair key-value map.
	 * 
	 * The key is the arithmetic operator and the value is the arithmetic operation 
	 * implemented as a Lambda expression.
	 */
	private static final Map<String, Object> operations = new HashMap<>();
	
	/**
	 * Single instance.
	 */
	private static PostfixCalculator postfixCalculator = new SimplePostfixCalculator();
	
	/**
	 * Returns a single instance.
	 * @return Class instance.
	 */
	public static PostfixCalculator getInstance() {
		return postfixCalculator;
	}
	
	/**
	 * Constructor which initializes the the postfix calculator with supported arithmetic operations.
	 */
    private SimplePostfixCalculator() {
    	initializeSupportedOperations();
    }

    /**
     * Initialize the arithmetic supported operations, once per each key-value pair
     * in the map data structure.
     * The key is the arithmetic operand and the value is the arithmetic operation
     * implemented as a Lambda expression.
     * 
     * Example: Sum, Key="+" and Value= operandX + operandY (x + y)
     */
	private void initializeSupportedOperations() {
		operations.put("+", ((DoubleBinaryOperator) (x, y) -> x + y));
		operations.put("-", ((DoubleBinaryOperator) (x, y) -> x - y));
		operations.put("*", ((DoubleBinaryOperator) (x, y) -> x * y));
		operations.put("/", ((DoubleBinaryOperator) (x, y) -> x / y));
		operations.put("avg", ((DoubleBinaryOperator) (x, y) -> (x + y) / 2));
		operations.put("mod", ((DoubleBinaryOperator) (x, y) -> x % y));
		operations.put("sqrt", ((DoubleUnaryOperator) (x) -> sqrt(x)));
		operations.put("sin", ((DoubleUnaryOperator) (x) -> sin(x)));
		operations.put("cos", ((DoubleUnaryOperator) (x) -> cos(x)));
	}

	/**
	 * Calculate an arithmetic binary operation {@literal <operand1><operand2><binaryOperator>}.
	 * 
	 * @param valueX First operand or value
	 * @param valueY Second operand or value
	 * @param operator Arithmetic operator
	 * @return Result of the arithmetic calculation
	 */
	public double calculate(double valueX, double valueY, String operator) {
		DoubleBinaryOperator binaryOperator = (DoubleBinaryOperator) operations.get(operator);
		
		if (binaryOperator == null) {
			String errorMessage = "Unsupported arithmetic operation for " + operator;
			LOG.error(errorMessage);
			
			throw new ArithmeticException(errorMessage);
		}		
		
		return ((DoubleBinaryOperator) operations.get(operator)).applyAsDouble(valueX, valueY);
	}

	/**
	 * Calculate an arithmetic unary operation ({@literal <operand><unaryOperator>}).
	 * @param value Operand or value
	 * @param operator Arithmetic operator
	 * @return Result of the arithmetic calculation
	 */
	public double calculate(double value, String operator) {
		DoubleUnaryOperator unaryOperator = (DoubleUnaryOperator) operations.get(operator);
		
		if (unaryOperator == null) {
			String errorMessage = "Unsupported arithmetic operation for " + operator;
			LOG.error(errorMessage);
			
			throw new ArithmeticException(errorMessage);
		}
		
		return unaryOperator.applyAsDouble(value);
	}
}
