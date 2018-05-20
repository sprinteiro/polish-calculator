# A Reverse Polish Notation Calculator

## Assumptions

Existence of an input file (i.e. reversePolishArithmeticOperations.txt) with the arithmetic operations. 

File size is small (magnitude of KB). 

Each file line represents an expression to be evaluated and calculate its result by following the Reverse Polish Notation. The values/operands and operators are space-separated.

** Examples: ** 

 ```
 
    Input     | Result                                         | Note
  ----------- | ---------------------------------------------- | -----
   1.0 2.0 +  |  3.0                                           |
   16 sqrt    |  4.0                                           |
   1 + 1      | Not Reverse Polish Notation try backwards      | Error
   2 atan     | The atan is not supported. Unable to calculate | Error
 		
 ```

** Commands used in the following sections to build, test, run the application and generate the Javadoc are for Linux/Ubuntu as you will notice by sudo when issuing a command. **

** If you are using Windows, the sudo should be omitted. **

The following tools are installed to run the application:
- Apache Maven 3
- Java JDK 8


## Running

** Option A (recommended). Using mvn exec:java. Exec Maven Plugin **

To bootstrap the application in this way, it has been *incorporated and configured exec-maven-plugin* in the pom.xml file.

From command line, make sure or change to the ** project's directory polish-calculator ** (you should see the pom.xml file).

``` 
 $ cd polish-calculator 
```

By using ** Maven tool (mvn) **, clean, compile and bootstrap the Reverse Polish Calculator application.

As the application expects a file to process the arithmetic operations, there are a couple of files (reversePolishArithmeticOperations.txt and reversePolishArithmeticOperations2.txt) under src/test/resources project's directory that can be used.
 
```
~/polish-calculator$ sudo mvn clean compile && sudo mvn exec:java -Dexec.args="src/test/resources/reversePolishArithmeticOperations.txt"
```

** Option B. Using java -jar <JarWithDependencies>. Maven Assemply Plugin **

Bootstrap the application with java tool which will run the application contained in a previously generated fat JAR.
For this, it has been *incorporated and configured maven-assembly-plugin* in the pom.xml file.
 
From command line, make sure or change to the ** project's directory polish-calculator ** (you should see the pom.xml file).

``` 
 $ cd polish-calculator 
```

By using ** Maven tool (mvn) **, clean and package Reverse Polish Calculator application in a fat JAR file.

```
 ~/polish-calculator$ sudo mvn clean package
```
As per Maven output, the fat JAR file should have been created under /target with polish-calculator-0.0.1-SNAPSHOT-jar-with-dependencies.jar name.

Make sure that the application's fat JAR has execution permissions.

```
 sudo chmod +x ./target/polish-calculator-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

Run the application with ** java -jar ** by providing a file with the arithmetic operations to calculate.
There are a couple of files (reversePolishArithmeticOperations.txt and reversePolishArithmeticOperations2.txt) under src/test/resources project's directory that can be used.

```
 ~/polish-calculator$ java -jar target/polish-calculator-0.0.1-SNAPSHOT-jar-with-dependencies.jar src/test/resources/reversePolishArithmeticOperations.txt
```

## How to run test cases

To run unit and integration tests is needed to use ** Maven tool **.

1. Make sure you are in the ** project's directory polish-calculator ** (there should be the pom.xml)
2. Unit test ``sudo mvn clean package``
3. Integration test plus unit test ``sudo mvn clean package verify``


## How to generate Javadoc

To generate the Javadoc from the project it is needed to use ** Maven tool **.

1. Make sure you are in the ** project's directory polish-calculator ** (there should be the pom.xml)
2. ``sudo mvn javadoc:javadoc``
3. The HTML should have been generated under ./target/site/apidocs/
   You can open it up with a browser like Firefox ``firefox ./target/site/apidocs/overview-summary.html`` 


## Platform used

Ubuntu 14.04.1 x86_64 GNU/Linux

Java 8
 Java(TM) SE Runtime Environment (build 1.8.0_161-b12)
 Java HotSpot(TM) 64-Bit Server VM (build 25.161-b12, mixed mode)

Apache Maven 3.0.5


## Implementation notes

The application processes a file (i.e. reversePolishArithmeticOperations.txt) with arithmetic operations (one per file line) to calculate from the file system, parses the file, and loads the possible arithmetic operations to be evaluated and calculated in memory.

The application outputs the arithmetic expression in Reverse Polish Notation processed and its result if successful. Otherwise, a error message is display along with the processed arithmetic expression.

Examples:

```
6 3 * 2 - sqrt = 4.0
1 + 1 = Not Reverse Polish Notation try backwards
```

To process the post-fix arithmetic expression, the application internally uses a stack (LIFO -Last Input First Output) data structure in order to evaluate the arithmetic operation (binary or unary). Once evaluated, the result is stored (push) in the stack after popping out value/s and operator from the stack and calculated the expression's result.

** The supported operations by the calculator ** are configured as part of the component initialization ** in a key-value pair data structure that maps each supported operator (binary or unary) to its corresponding arithmetic operation as a Java 8 Lamda expression **.

Examples:

```
Sum in Map data structure as pair ("+", (x, y) -> x + y) 
Square root in Map data structure as pair ("sqrt", (x) -> sqrt(x))

```
** Supported arithmetic operations: **

 Binary:
 - Addition/Sum
 - Substraction/Minus
 - Multiplication
 - Division
 - Average
 - Modulus
 
 Unary:
 - Square root
 - Sinus
 - Cosines

** Reverse Polish Notation of arithmetic expressions: **

- Minimum valid expressions.


  ```
 	A. <value> <UnaryOperand> (sqrt, sin, cos)
 	B. <value1> <value2> BinaryOperand (+, -, *, /, mod, avg)
  ```

- Exceptional cases.

 
  ```
	Unsupported Operator (Invalid operation)
	Unsupported Operation Format (Not Reverse Polish Notation try backwards)
  ```

Application logs are in polishCalculator.log


### Application Architecture - Main components

** ApplicationMain **

Component responsible for running the application in order to calculate arithmetic operations in Reverse Polish Notation which are passed in a file from the Operating System command line.

It prints out on the standard output the expression evaluated and calculated plus its result.

It uses the following components:
- SimplePolishCalculator
- ReversePolishNotationOperationsFileReader 


** SimplePolishCalculator **

Component responsible for processing simple arithmetic operations in Reverse Polish Notation.

Evaluate the arithmetic expression and if correct and supported, it calculates it result.

For the arithmetic operation processing in post-fix notation, the calculator internally uses a stack (LIFO -Last Input First Output) data structure in order to evaluate the arithmetic operation (binary or unary). Once evaluated, the result is stored (push) in the stack after popping out value/s and operator from the stack and calculated the expression's result.

It uses the following components:

- SimplePostfixCalculator. To calculate a valid post-fix arithmetic expression.
- PostFixCalculatorUtil


** SimplePostfixCalculator **

Component responsible to calculate unary (<value> <operator>) and binary (<value1> <value2> <operator>) arithmetic operations in post-fix notation.

For the post-fix arithmetic operation calculation, the calculator internally uses a map data structure in order with the supported operations. These ** operations are configured as part of the component initialization ** in a ** key-value pair data structure that maps each supported operator (binary or unary) to its corresponding arithmetic operation as a Java 8 Lamda expression **.


** PostFixCalculatorUtil **
Utility component for supporting arithmetic operations post-fix notation.


** ReversePolishNotationOperationsFileReader **
Component responsible for reading arithmetic operations in Reverse Polish Notation..

