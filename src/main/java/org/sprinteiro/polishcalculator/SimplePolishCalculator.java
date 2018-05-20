package org.sprinteiro.polishcalculator;


import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.sprinteiro.polishcalculator.util.SimplePostfixCalculator;

import static org.sprinteiro.polishcalculator.util.PostFixCalculatorUtil.checkIsANumber;
import static org.sprinteiro.polishcalculator.util.PostFixCalculatorUtil.checkIsAValidOperator;
import static org.sprinteiro.polishcalculator.util.PostFixCalculatorUtil.calculateOperatorArity;
import static org.sprinteiro.polishcalculator.util.PostFixCalculatorUtil.ARITHMETIC_OPERATOR_AND_ARITY;

/**
 * A simple Reverse Polish calculator implementation of the {@code PolishCalculator} 
 * interface. Supports basic binary and unary arithmetic operations.
 *
 * @see     PolishCalculator
 */
public class SimplePolishCalculator implements PolishCalculator {
	private static final Logger LOG = LoggerFactory.getLogger(SimplePolishCalculator.class);
	
	/**
	 * Supported arithmetic operations.
	 */
	public static final Set<String> VALID_ARITHMETIC_OPERATORS = new HashSet<>(
		Arrays.asList("+", "-", "*", "/", "avg", "mod", "sqrt", "sin", "cos"));
	
	/**
	 * Represents a last-in-first-out (LIFO) stack of strings whose each element could be either a operand/value or operator.
	 */
	private Deque<String> stack = new LinkedList<>();

	
    /**
     * Returns the result of calculating all arithmetic operations in Reverse Polish Notation.
     *
     * @param operationsLine arithmetic expression in Reverse Polish Notation with the operations to calculate
     * @return result of calculating all arithmetic operations
     * @throws ArithmeticException if {@code operationsLine} arithmetic expression is not Reverse Polish Notation compatible
     */
	@Override
	public double process(String operationsLine) {
		String[] elements = operationsLine.split("\\s+");
	
		boolean isANumberCurrentElement = false; // By default, the current element is assumed not being a number (value to evaluate in the expression)
		@SuppressWarnings("unused")
		int expressionOffset = 0;	// Offset in current evaluation (value1 value2 binaryOperation, value1 unaryOperator)
		for (int currentIndex = 0;	// Current index element in the input of the arithmetic expression to calculate 
			 currentIndex < elements.length; 
			 currentIndex++) {
			
			isANumberCurrentElement = checkIsANumber(elements[currentIndex]);
			
			if (isANumberCurrentElement) {
				push(elements[currentIndex], true);
				expressionOffset++;
				continue;
			}
				
			// Current element is an operator, evaluate arithmetic expressions if applies	
			// Operator, check its arity, evaluate, switch to isANumberCurrentElement to push in next iteration
			// Check if unary or binary, or invalid expression
			int operatorArity = calculateOperatorArity(elements[currentIndex], ARITHMETIC_OPERATOR_AND_ARITY);
				
			push(elements[currentIndex], false);
			double result = evaluateArithmeticExpression(elements, currentIndex, operatorArity);
			push(String.valueOf(result), true);
			expressionOffset = 0;  // Reset offset the arithmetic expression as evaluated
		}
		
		return Double.parseDouble(stack.removeFirst());
	}

	/**
	 * Returns the result of evaluating the arithmetic expression compound of <value><operand> or <value1><value2><operand>
	 *
	 * @param elements value/s and operand to evaluate as an arithmetic expression in Reverse Polish Notation
	 * @param currentIndex Current index position of the operator
	 * @param operatorArity Arity of the expression to evaluate (one if unary operator or two if binary operator)
	 * @return result of the arithmetic evaluation
	 * @throws ArithmeticException if invalid arithmetic expression (not Reverse Polish Notation compatible, unsupported arity)
	 */
	private double evaluateArithmeticExpression(String[] elements, int currentIndex, int operatorArity) {
		// The stack must contains operands and operator to evaluate
		// <value1 unaryOperator> or <value1 value2 binaryOperator>
		int invariant = stack.size() - (operatorArity + 1);
		
		if (invariant != 0) {
			// Empty the stack as unable to calculate. Not in Reverse Polish Notation!!!!
			stack.clear();
			
			throw new ArithmeticException("Not Reverse Polish Notation try backwards");
		}
		
		double result = 0;

		String operator = pop();
		
		if (operatorArity == 2) {
			// Binary operation
			double value2 = Double.parseDouble(pop());
			double value1 = Double.parseDouble(pop());
			result = SimplePostfixCalculator.getInstance().calculate(value1, value2, operator);	
		} else if (operatorArity == 1){
			// Unary operation
			double value = Double.parseDouble(pop());
			result = SimplePostfixCalculator.getInstance().calculate(value, operator);
		} else {
			String errorMessage = "Unsupported arity";
			LOG.error(errorMessage);
			
			throw new ArithmeticException(errorMessage);
		}
		
		return result;
	}

	/**
	 * Push {@code element} (value/operand or arithmetic operator) in the stack.
	 * @param element Element to push in stack
	 * @param isAValue Indicate whether the element is a value (true) or an operand (false)
	 */
	private void push(String element, boolean isAValue) {
		if (isAValue) {
			checkIsANumber(element);
		} else {
			checkIsAValidOperator(element, VALID_ARITHMETIC_OPERATORS);
		}
		synchronized (stack) {
			stack.addFirst(element);
		}
	}

	/**
	 * Pop out an element from the stack.
	 * @return Element (value/operand or operand)
	 */
	private String pop() {
		synchronized (stack) {
			return stack.removeFirst();
		}
	}

}
