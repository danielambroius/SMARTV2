package tests;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import smrt2.EquationParser2;

public class TestEquationParser2 extends TestCase {
	private String equation;
	
	public void testCreateEquationParser2() {
		equation = "-k1+k2";
		EquationParser2 myParser2 = new EquationParser2();
		assertNotNull(myParser2);
	}
	
	public void testParseVariables() {
		equation = "-k1+k2";
		EquationParser2 myParser2 = new EquationParser2();
		myParser2.parseVariables(equation);
		String[] expectedList = new String[]  {"","k1","k2"};
//		System.out.println(Arrays.toString(myParser2.getVariables()));
		assertEquals(Arrays.toString(expectedList), Arrays.toString(myParser2.getVariables(equation)));
	}
	
	public void testParseOperators() {
		equation = "-k1+k2";
		EquationParser2 myParser2 = new EquationParser2();
		String[] expectedList = new String[] {"-","+"};
//		System.out.println(Arrays.toString(myParser2.getOperators(equation)));
		assertEquals(Arrays.toString(expectedList), Arrays.toString(myParser2.getOperators(equation)));
	}
	
	public void testParse1() {
		equation = "(A+1)*k1";
		EquationParser2 myParser2 = new EquationParser2();
		List<List<String>> results = myParser2.parse(equation);
		List<String> expectedVariables = new ArrayList<String>(Arrays.asList("","A","k1"));
		List<String> expectedOperators = new ArrayList<String>(Arrays.asList("(","+1)*"));
		assertEquals(expectedVariables, results.get(0));
		assertEquals(expectedOperators, results.get(1));
	}
	
	public void testParse2() {
		equation = "A*2+k1";
		EquationParser2 myParser2 = new EquationParser2();
		List<List<String>> results = myParser2.parse(equation);
		List<String> expectedVariables = new ArrayList<String>(Arrays.asList("A","k1"));
		List<String> expectedOperators = new ArrayList<String>(Arrays.asList("","*2+"));
		assertEquals(expectedVariables, results.get(0));
		assertEquals(expectedOperators, results.get(1));
	}
	
	public void testParse3() {
		equation = "-sin(2+k1)";
		EquationParser2 myParser2 = new EquationParser2();
		List<List<String>> results = myParser2.parse(equation);
		List<String> expectedVariables = new ArrayList<String>(Arrays.asList("","k1"));
		List<String> expectedOperators = new ArrayList<String>(Arrays.asList("-sin(2+",")"));
		assertEquals(expectedVariables, results.get(0));
		assertEquals(expectedOperators, results.get(1));
	}
	
//	public void testRemoveUnwantedMatches() {
//		EquationParser2 myParser2 = new EquationParser2(equation);
//		String[] paramArray = new String[] {"A","B","C","D"};;
//		String[] expectedArray = new String[]  {"-","*","-(","*",")"};
//		String[] adjustedArray = myParser2.removeUnwantedMatches(paramArray);
//		System.out.println(Arrays.toString(adjustedArray));
//		assertEquals(Arrays.toString(expectedArray), Arrays.toString(adjustedArray));
//	}


}
