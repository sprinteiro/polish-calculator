package org.sprinteiro.polishcalculator.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Interface for reading a text file with arithmetic operations.
 * Each line in the file represents an arithmetic expression.
 */
public interface ArithmeticOperationsFileReader {
	
	/**
	 * Read arithmetic expressions (one per line) from a file whose location and name
	 * is passed in {@code filePathAndNameStr}, and returns all the arithmetic 
	 * expression in a list of strings.
	 * 
	 * @param filePathAndNameStr Path location and file name in file system 
	 * @return List of strings with one arithmetic expressions per string
	 * @throws FileNotFoundException File not found for {@code filePathAndNameStr}
	 * @throws IOException I/O operation when processing file
	 */
	List<String> read(String filePathAndNameStr) throws FileNotFoundException, IOException;

}
