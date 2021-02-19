package com.idbs.devassessment.solution;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author imogenhay
 * Tests calculations correctly tracks previous calculations
 */

public class CalculationsTest {

	@Test // tests assertTrue is working
	public void shouldAnswerWithTrue() { 
		assertTrue( true );
	}
	
		
	@Test // tests assertEquals is working
	public void shouldBeEqual() {
		assertEquals("true" ,"true");
	}
	
	

	@Test // tests Calculation is storing all previous calculations for one polynomial
	public void testCalculation1() {
		Equation equation = new Equation();
		
		Term term1 = new Term(3, 9, "-");
		Term term2 = new Term(2, 8, "+");
		Term term3 = new Term(1, 5, "+");
		Term term4 = new Term(0, 5, "+");
		
		equation.addTerm(term1);
		equation.addTerm(term2);
		equation.addTerm(term3);
		equation.addTerm(term4);
		
		equation.evaluate(42);
		assertEquals(93, equation.getCalculations().size());
	}
	
	
	@Test // tests Calculation is storing all previous calculations including for previous polynomials
	public void testCalculation2() {
		Equation equation = new Equation();
		
		Term term1 = new Term(3, 9, "-");
		Term term2 = new Term(2, 8, "+");
		Term term3 = new Term(1, 5, "+");
		Term term4 = new Term(0, 10, "+");
		
		equation.addTerm(term1);
		equation.addTerm(term2);
		equation.addTerm(term3);
		equation.addTerm(term4);
		
		equation.evaluate(42);
		assertEquals(94, equation.getCalculations().size());
		
	}

}
