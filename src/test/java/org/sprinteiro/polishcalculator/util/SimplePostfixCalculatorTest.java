package org.sprinteiro.polishcalculator.util;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SimplePostfixCalculatorTest {
	private static PostfixCalculator calculator;

    @BeforeAll
    static void initAll() {
    	calculator = SimplePostfixCalculator.getInstance();
    }

	@BeforeEach
    void init() {
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    static void tearDownAll() {
    	calculator = null;
    }
    
    
    @Test
    void shouldCalculateTheSumOfOneAndTwo() {
    	// Given
    	
    	// When
    	double actualResult = calculator.calculate(1.0, 2.0, "+");

    	// Then
    	assertEquals(3.0, actualResult);
    }
    
    @Test
    void shouldCalculateTheSubstrationOfOneAndTwo() {
    	// Given
    	
    	// When
    	double actualResult = calculator.calculate(1, 2, "-");

    	// Then 
    	assertEquals(-1.0, actualResult);
    }
    
    @Test
    void shouldCalculateTheMultiplicationOfOneAndTwo() {
    	// Given
    	
    	// When
    	double actualResult = calculator.calculate(1, 2, "*");

    	// Then 
    	assertEquals(2.0, actualResult);
    }
    
    @Test 
    void shouldCalculateTheDivisionOfOneAndTwo() {
    	// Given
    	
    	// When
    	double actualResult = calculator.calculate(1, 2, "/");
    	
    	// Then
    	assertEquals(0.5, actualResult);
    }
    
    @Test 
    void shouldCalculateModulusOfOneAndTwo() {
    	// Given
    	
    	// When
    	double actualResult = calculator.calculate(1, 2, "mod");
    	
    	// Then
    	assertEquals(1.0, actualResult);
    }
    
    @Test 
    void shouldCalculateTheAverageOfSixAndEighteen() {
    	// Given
    	
    	// When
    	double actualResult = calculator.calculate(6, 18, "avg");
    	
    	// Then
    	assertEquals(12.0, actualResult);
    }
    
    @Test 
    void shouldCalculateTheSinusOfOne() {
    	// Given
    	
    	// When
    	double actualResult = calculator.calculate(Math.PI / 2, "sin");
    	
    	// Then
    	assertEquals(1.0, actualResult);
    }
    
    @Test 
    void shouldCalculateTheCosinesOfOne() {
    	// Given
    	
    	// When
    	double actualResult = calculator.calculate(0, "cos");
    	
    	// Then
    	assertEquals(1.0, actualResult);
    }    
    
}
