package com.idbs.devassessment.solution;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author imogenhay
 * Tests functionality of ReadNumeric class
 */

public class ReadNumericTest {

	@Test // tests assertTrue is working
	public void shouldAnswerWithTrue() { 
		assertTrue( true );
	}
	
		
	@Test // tests assertEquals is working
	public void shouldBeEqual() {
		assertEquals("true" ,"true");
	}
	
	
	@Test // tests ReadNumeric can be created and evaluated
	public void testReadNumeric() {
		ReadNumeric reader = new ReadNumeric("x = 3; y = -9.x^3+8.x^1+5.x^0-4.x^2");
		assertEquals("-250" ,reader.getResult());
	}
	
	
	@Test
	public void testGetXValue() {
		ReadNumeric reader = new ReadNumeric("x = 3; y = -9.x^3+8.x^1+5.x^0-4.x^2");
		assertEquals(3 ,reader.getXValue());
	}
	
	
	@Test
	public void testGetData() {
		ReadNumeric reader = new ReadNumeric("x = 3; y = -9.x^3+8.x^1+5.x^0-4.x^2");
		assertEquals("x = 3; y = -9.x^3+8.x^1+5.x^0-4.x^2" ,reader.getData());
	}
	
	
	@Test
	public void testGetEquation() {
		ReadNumeric reader = new ReadNumeric("x = 3; y = -9.x^3+8.x^1+5.x^0-4.x^2");
		reader.getResult();
		
		assertEquals("- 9x^(3) + 8x^(1) + 5x^(0) - 4x^(2) \n" ,reader.getEquation().toString());
	}
	
	
	@Test
	public void testToString() {
		ReadNumeric reader = new ReadNumeric("x = 3; y = -9.x^3+8.x^1+5.x^0-4.x^2");
		reader.getResult();
		assertEquals("- 9x^(3) + 8x^(1) + 5x^(0) - 4x^(2) \n = -250 (x=3)\n" ,reader.toString());
	}
	
	
	// test if invalid numeric format with throw error
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidReadNumeric() {
		ReadNumeric reader = new ReadNumeric("invalid input");
	}

}
