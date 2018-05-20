package org.sprinteiro.polishcalculator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class SimplePolishCalculatorTest {
	private PolishCalculator calculator;

    @BeforeAll
    static void initAll() {
    }

	@BeforeEach
    void init() {
		calculator = new SimplePolishCalculator();
    }

    @AfterEach
    void tearDown() {
    	calculator = null;
    }

    @AfterAll
    static void tearDownAll() {
    }
    
    
    @Test
    void shouldCalculateTheSumOfOneAndTwo() {
    	// Given
    	String operationsLine = "1.0 2.0 +";
    	
    	// When
    	double actualResult = calculator.process(operationsLine);
    	
    	// Then
    	assertEquals(3.0, actualResult);
    }

    @Test
    void shouldCalculateTheProductOfThreeAndFour() {
    	// Given
    	String operationsLine = "3 4 *";
    	
    	// When
    	double actualResult = calculator.process(operationsLine);
    	
    	// Then
    	assertEquals(12.0, actualResult);
    }    
    
    @Test
    void shouldCalculateTheProductOfSixAndThreeAndThenSubstractionOfTwoAndThenSquareRoot() {
    	// Given
    	String operationsLine = "6 3 * 2 - sqrt";
    	
    	// When
    	double actualResult = calculator.process(operationsLine);
    	
    	// Then
    	assertEquals(4.0, actualResult);
    }

    @Test
    void shouldCalculateTheSubstractionOfFourAndTwoAndThenSubstractionOfTwoAndThenMultiplicationByOneThoushand() {
    	// Given
    	String operationsLine = "4 2 - 2 - 1000 *";
    	
    	// When
    	double actualResult = calculator.process(operationsLine);
    	
    	// Then
    	assertEquals(0.0, actualResult);
    }
    
    @Test
    void shouldCalculateTheSumOfOneAndTwoAndThenMultiplyByFourAndThenSumFiveAndThenMinusThree() {
    	// Given
    	String operationsLine = "1 2 + 4 * 5 + 3 -";
    	
    	// When
    	double actualResult = calculator.process(operationsLine);
    	
    	// Then
    	assertEquals(14.0, actualResult);
    }

}
