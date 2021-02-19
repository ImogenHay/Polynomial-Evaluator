package com.idbs.devassessment.solution;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Test;

/**
 * @author imogenhay
 * Tests functionality of Term class
 */


public class TermTest {

	@Test // tests assertTrue is working
	public void shouldAnswerWithTrue() { 
		assertTrue( true );
	}
	
		
	@Test // tests assertEquals is working
	public void shouldBeEqual() {
		assertEquals("true" ,"true");
	}
	
	
	@Test // tests Term can be created and toString works
	public void testTerm() {
		Term term = new Term(9, 10, "-");
		assertEquals("- 10x^(9)" ,term.toString());
	}

	
	@Test
	public void testGetPower() {
		Term term = new Term(9, 10, "-");
		assertEquals(9 ,term.getPower());
	}
	
	
	@Test
	public void testSetPower() {
		Term term = new Term(9, 10, "-");
		term.setPower(0);
		assertEquals(0 ,term.getPower());
	}
	
	
	@Test
	public void testGetMultiplier() {
		Term term = new Term(9, 10, "-");
		assertEquals(10 ,term.getMultiplier());
	}
	
	
	@Test
	public void testSetMultiplier() {
		Term term = new Term(9, 10, "-");
		term.setMultiplier(5);
		assertEquals(5 ,term.getMultiplier());
	}
	
	
	@Test
	public void testGetAction() {
		Term term = new Term(9, 10, "+");
		assertEquals("+" ,term.getAction());
	}
	
	
	@Test
	public void testSetAction() {
		Term term = new Term(9, 10, "-");
		term.setAction("add");
		assertEquals("add" ,term.getAction());
	}
	
	
	@Test // tests evaluation works 
	public void testEvaluate() { 
		Term term = new Term(9, 10, "-");		
		assertEquals(-100776960 ,term.evaluate(6));
	}
	
	
	@Test // tests evaluation works when x is zero
	public void testEvaluateZeroX() { 
		Term term = new Term(9, 10, "+");	
		assertTrue(0 == term.evaluate(0));
	}
	
	
	@Test // tests evaluation works when multiplier is zero
	public void testEvaluateZeroMultiplier() {
		Term term = new Term(9, 0, "-");
		assertEquals(0 ,term.evaluate(6));
	}
	
	
	@Test // test it will return calculation if previously calculated
	public void testPreviouslyCalculated() {
		Term term = new Term(9, 10, "-");
		
		ArrayList<Long> calculation = new ArrayList<Long>();
		calculation.add((long) 1);
		calculation.add((long) 2);
		calculation.add((long) 3);
		term.getCalculations().add(calculation);
		
		assertEquals(calculation ,term.previouslyCalculated(2,1));
	}
	
	
	@Test // test it will not return calculation if not previously calculated
	public void testNotPreviouslyCalculated() {
		Term term = new Term(9, 10, "-");
		
		assertNull(term.previouslyCalculated(4,1));
	}
	
	
	// test if invalid parameters with throw error
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidPower() {
		Term term = new Term(-1, 10, "-");
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidMultiplier() {
		Term term = new Term(9, -1, "-");
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidAction() {
		Term term = new Term(9, 10, "-+");
	}
	
	

}
