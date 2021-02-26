package com.idbs.devassessment.solution;

import static org.junit.Assert.*;

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
	public void testGetMultiplier() {
		Term term = new Term(9, 10, "-");
		assertEquals(10 ,term.getMultiplier());
	}
	
	
	@Test
	public void testGetAction() {
		Term term = new Term(9, 10, "+");
		assertEquals("+" ,term.getAction());
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
