package org.sprinteiro.polishcalculator.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import static java.util.stream.Collectors.toList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A file reader class to read arithmetic operations in Reverse Polish Notation.
 */
public class ReversePolishNotationOperationsFileReader implements ArithmeticOperationsFileReader {
	private static final Logger LOG = LoggerFactory.getLogger(ReversePolishNotationOperationsFileReader.class);

	
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
	@Override
	public List<String> read(String filePathAndNameStr) throws FileNotFoundException, IOException {
		// Read a file from disk and load in memory logic
		Path filePath = Paths.get(filePathAndNameStr);
		
		if (!Files.exists(filePath)) {
			String errorMessage = String.format("Error processing file %s. File not found.", filePathAndNameStr);
			LOG.error(errorMessage);
			
			throw new FileNotFoundException(errorMessage);
		}

		try {
			return Files.lines(filePath, Charset.defaultCharset()).collect(toList());
		} catch (IOException e) {
			LOG.error("I/O error processing file {}", filePathAndNameStr, e);

			throw e;
		}
	}

}
