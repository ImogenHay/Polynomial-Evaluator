package com.idbs.devassessment.solution;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

/**
 * @author imogenhay
 * Tests functionality of Equation class
 */

public class EquationTest {

	@Test // tests assertTrue is working
	public void shouldAnswerWithTrue() { 
		assertTrue( true );
	}
	
		
	@Test // tests assertEquals is working
	public void shouldBeEqual() {
		assertEquals("true" ,"true");
	}
	
	
	@Test // tests Equation can be created and toString works
	public void testEquation() {
		Equation equation = new Equation();
		
		assertEquals("\n" ,equation.toString());
	}

	
	@Test // test terms can be added to equation and shown in toString
	public void testAddTerm() {
		Equation equation = new Equation();
		
		Term term1 = new Term(2, 7, "-");
		Term term2 = new Term(1, 0, "-");
		Term term3 = new Term(3, 8, "-");
		Term term4 = new Term(0, 4, "+");
		
		equation.addTerm(term1);
		equation.addTerm(term2);
		equation.addTerm(term3);
		equation.addTerm(term4);
		assertEquals("- 7x^(2) - 0x^(1) - 8x^(3) + 4x^(0) \n" ,equation.toString());
	}
	
	
	@Test
	public void testGetTerms() {
		Equation equation = new Equation();
		
		Term term1 = new Term(2, 7, "-");
		Term term2 = new Term(1, 0, "-");
		Term term3 = new Term(3, 8, "-");
		Term term4 = new Term(0, 4, "+");
		
		equation.addTerm(term1);
		equation.addTerm(term2);
		equation.addTerm(term3);
		equation.addTerm(term4);

		assertEquals("[- 7x^(2), - 0x^(1), - 8x^(3), + 4x^(0)]" ,equation.getTerms().toString());
	}
	
	
	@Test // tests evaluation works 
	public void testEvaluatePolynomial() { 
		
		Equation equation = new Equation();
		
		Term term1 = new Term(2, 7, "-");
		Term term2 = new Term(1, 0, "-");
		Term term3 = new Term(3, 8, "-");
		Term term4 = new Term(0, 4, "+");
		
		equation.addTerm(term1);
		equation.addTerm(term2);
		equation.addTerm(term3);
		equation.addTerm(term4);	
		
		assertEquals(-1976 ,equation.evaluatePolynomial(6));
	}
	
	
	@Test // tests evaluation works when x is zero
	public void testEvaluatePolynomialZeroX() { 
		Equation equation = new Equation();
		
		Term term1 = new Term(2, 7, "-");
		Term term2 = new Term(1, 0, "-");
		Term term3 = new Term(3, 8, "-");
		Term term4 = new Term(0, 4, "+");
		
		equation.addTerm(term1);
		equation.addTerm(term2);
		equation.addTerm(term3);
		equation.addTerm(term4);	
		
		assertEquals(0 ,equation.evaluatePolynomial(0));
	}
	
	
	@Test // tests evaluation works when no terms
	public void testEvaluatePolynomialZeroMultiplier() {
		Equation equation = new Equation();
		assertEquals(0 ,equation.evaluatePolynomial(6));
	}
	
	
	@Test // test it will return calculation if previously calculated
	public void testPreviouslyCalculated() {
		Equation equation = new Equation();
		
		ArrayList<Long> calculation = new ArrayList<Long>();
		calculation.add((long) 1);
		calculation.add((long) 2);
		calculation.add((long) 3);
		equation.getCalculations().add(calculation);
		
		assertEquals(calculation ,equation.previouslyCalculated(2,1));
	}
	
	
	@Test // test it will not return calculation if not previously calculated
	public void testNotPreviouslyCalculated() {
		Equation equation = new Equation();
		
		assertNull(equation.previouslyCalculated(4,1));
	}
	
	
	@Test // tests evaluation works 
	public void testEvaluateTerm() { 
		Equation equation = new Equation();
		Term term = new Term(9, 10, "-");		
		assertEquals(-100776960 ,equation.evaluateTerm(term,6));
	}
	
	
	@Test // tests evaluation works when x is zero
	public void testEvaluateZeroX() { 
		Equation equation = new Equation();
		Term term = new Term(9, 10, "+");	
		assertTrue(0 == equation.evaluateTerm(term,0));
	}
	
	
	@Test // tests evaluation works when multiplier is zero
	public void testEvaluateZeroMultiplier() {
		Equation equation = new Equation();
		Term term = new Term(9, 0, "-");
		assertEquals(0 ,equation.evaluateTerm(term,6));
	}
	

}
