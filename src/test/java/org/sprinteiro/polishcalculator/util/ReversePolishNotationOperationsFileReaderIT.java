package org.sprinteiro.polishcalculator.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ReversePolishNotationOperationsFileReaderIT {
	private ArithmeticOperationsFileReader operationsFileProcessor;
	
    @BeforeAll
    static void initAll() {
    }

	@BeforeEach
    void init() {
		operationsFileProcessor = new ReversePolishNotationOperationsFileReader();
    }

    @AfterEach
    void tearDown() {
    	operationsFileProcessor = null;
    }

    @AfterAll
    static void tearDownAll() {
    }
    
    
    @Test
    void shouldParseOperationsToCalculate() throws FileNotFoundException, IOException {
    	// Given
    	
    	// When
    	List<String> arithmethicOperations = operationsFileProcessor.read("src/test/resources/reversePolishArithmeticOperations.txt");
    	// Then
    	assertNotNull(arithmethicOperations);
    	assertEquals(5, arithmethicOperations.size());
    }

}