package org.sprinteiro.polishcalculator.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class for supporting arithmetic operation in post-fix and Reverse Polish notation. 
 *
 */
public class PostFixCalculatorUtil {
	private static final Logger LOG = LoggerFactory.getLogger(PostFixCalculatorUtil.class);
	
	/**
	 * Regular expression for a valid value for an arithmetic expression.
	 * Valid expressions: #### (four digits maximum) or ####.## (Four digits maximum and two decimals maximum)
	 */
	public static String VALID_VALUE_IN_ARITHMETIC_EXPRESSION = "(^[0-9]{1,4}$)|(^[0-9]{1,4}(\\.[0-9]{1,2}$))";
	
	/**
	 * Arity of each supported arithmetic operator/operation contained in map data structure where 
	 * each a key-value pair represents the operator/operation's arity
	 * 
	 * Key=Operator and the value is its arity.
	 */
	public final static Map<String, Integer> ARITHMETIC_OPERATOR_AND_ARITY = new HashMap<>();
	
	static {
		ARITHMETIC_OPERATOR_AND_ARITY.put("+", 2);
		ARITHMETIC_OPERATOR_AND_ARITY.put("-", 2);
		ARITHMETIC_OPERATOR_AND_ARITY.put("*", 2);
		ARITHMETIC_OPERATOR_AND_ARITY.put("/", 2);
		ARITHMETIC_OPERATOR_AND_ARITY.put("avg", 2);
		ARITHMETIC_OPERATOR_AND_ARITY.put("mod", 2);
		ARITHMETIC_OPERATOR_AND_ARITY.put("sqrt", 1);
		ARITHMETIC_OPERATOR_AND_ARITY.put("sin", 1);
		ARITHMETIC_OPERATOR_AND_ARITY.put("cos", 1);
	}

	/**
	 * Checks that the element is a number as it represents a value in the arithmetic expression.
	 * @param element Numeric value/operand
	 * @return Returns {@code true} if the {@code element} is a number or numeric value
	 */
	public static boolean checkIsANumber(String element) {
		Pattern pattern = Pattern.compile(VALID_VALUE_IN_ARITHMETIC_EXPRESSION);
		Matcher matcher = pattern.matcher(element);
		
		return matcher.matches();
	}

	/**
	 * Checks if the {@code operator} is valid as contained in the {@code operators}
	 * @param operator Arithmetic operator
	 * @param opertators Supported arithmetic operators
	 * @return Returns {@code true} if the {@code operator} is supported
	 */
	public static boolean checkIsAValidOperator(String operator, Set<String> opertators) {
		return opertators.contains(operator);
	}
	
	/**
	 * Calculates the {@code operator} aritity if contained in {@code opertatorsAndArityMap} and supported. 
	 * @param operator Arithmetic operator to calculate arity
	 * @param opertatorsAndArityMap Supported arithmetic operators
	 * @return Returns the opperator's arity if supported
	 * @throws ArithmeticException when the operator is not supported and unable to calculate its arity
	 */
	public static int calculateOperatorArity(String operator, Map<String, Integer> opertatorsAndArityMap) {
		Integer arity = opertatorsAndArityMap.get(operator);
		
		if (arity == null) {
			// Zero-arity as the operator is not supported
			String errorMessage = "Unable to calculate arity for " + operator + " as not supported.";
			LOG.debug("Unable to calculate arity for {} as not supported.", operator);
			
			throw new ArithmeticException("The " + operator + " is not supported. Unable to calculate.");
		}

		return arity.intValue();
	}
	
}
