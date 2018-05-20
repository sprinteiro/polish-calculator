package org.sprinteiro.polishcalculator.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.sprinteiro.polishcalculator.SimplePolishCalculator.VALID_ARITHMETIC_OPERATORS;
import static org.sprinteiro.polishcalculator.util.PostFixCalculatorUtil.ARITHMETIC_OPERATOR_AND_ARITY;
import static org.sprinteiro.polishcalculator.util.PostFixCalculatorUtil.calculateOperatorArity;
import static org.sprinteiro.polishcalculator.util.PostFixCalculatorUtil.checkIsANumber;
import static org.sprinteiro.polishcalculator.util.PostFixCalculatorUtil.checkIsAValidOperator;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PostFixCalculatorUtilTest {

    @BeforeAll
    static void initAll() {
    }

	@BeforeEach
    void init() {
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    }

    
    @Test
    void shouldTheExpressionBeAValidNumberOfTypeDouble() {
    	// Given
    	
    	// When
    	boolean actualResult = checkIsANumber("123.21");

    	// Then
    	assertEquals(true, actualResult);
    }
    
    @Test
    void shouldTheExpressionBeAValidNumberOfTypeInteger() {
    	// Given
    	
    	// When
    	boolean actualResult = checkIsANumber("3");

    	// Then
    	assertEquals(true, actualResult);
    }
    
    
    @Test
    void shouldTheExpressionBeAInvalidAsMalformedCommaSeparated() {
    	// Given
    	
    	// When
    	boolean actualResult = checkIsANumber("123,21");

    	// Then
    	assertEquals(false, actualResult);
    }
    
    @Test
    void shouldTheExpressionBeAInvalidAsMalformedLetters() {
    	// Given
    	
    	// When
    	boolean actualResult = checkIsANumber("12a,21");

    	// Then
    	assertEquals(false, actualResult);
    }
    
    @Test
    void shouldTheExpressionBeAValidOperator() {
    	// Given
    	
    	// When
    	boolean actualResult = checkIsAValidOperator("-", VALID_ARITHMETIC_OPERATORS);

    	// Then
    	assertEquals(true, actualResult);    	
    }
    
    @Test
    void shouldTheExpressionBeAnInvalidOperator() {
    	// Given
    	
    	// When
    	boolean actualResult = checkIsAValidOperator("-", new HashSet<>(Arrays.asList("atan")));

    	// Then
    	assertEquals(false, actualResult);        	
    }
    
    @Test
    void shouldCalculateTheOperatorArityOfOne() {
    	// Given
    	
    	// When
    	int actualResult = calculateOperatorArity("sin", ARITHMETIC_OPERATOR_AND_ARITY);

    	// Then
    	assertEquals(1, actualResult);       	
    }
    
    @Test
    void shouldCalculateTheOperatorArityOfTwo() {
    	// Given
    	
    	// When
    	int actualResult = calculateOperatorArity("-", ARITHMETIC_OPERATOR_AND_ARITY);

    	// Then
    	assertEquals(2, actualResult);	
    }
    
}
