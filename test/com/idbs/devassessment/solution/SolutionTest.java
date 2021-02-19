package com.idbs.devassessment.solution;

import static org.junit.Assert.*;

import org.junit.Test;

import com.idbs.devassessment.core.IDBSSolutionException;

public class SolutionTest {

	@Test // tests assertTrue is working
	public void shouldAnswerWithTrue() { 
		assertTrue( true );
	}
	
		
	@Test // tests assertEquals is working
	public void shouldBeEqual() {
		assertEquals("true" ,"true");
	}
	
	
	@Test // tests CandidateSolution can be created
	public void testTerm() throws IDBSSolutionException {
		CandidateSolution solution = new CandidateSolution();
		assertTrue(solution != null);
	}
	
	
	@Test // tests determineInputFormat works for Json
	public void testDetermineInputFormatJson() throws IDBSSolutionException {
		CandidateSolution solution = new CandidateSolution();
		String result = solution.determineInputFormat("json:{\"xValue\" : 7,\"terms\" : [{ \"power\" : 0,  \"multiplier\" : 1, \"action\" :  \"add\"  },{ \"power\" : 2,  \"multiplier\" : 2, \"action\" :  \"subtract\"  },{ \"power\" : 3,  \"multiplier\" : 3, \"action\" :  \"add\"  },{ \"power\" : 1,  \"multiplier\" : 1, \"action\" :  \"subtract\"  }]}");
		assertEquals("925",result);
	}
	
	
	@Test // tests determineInputFormat works for Numeric
	public void testDetermineInputFormatNumeric() throws IDBSSolutionException {
		CandidateSolution solution = new CandidateSolution();
		String result = solution.determineInputFormat("numeric:x = 3; y = -9.x^3+8.x^1+5.x^0-4.x^2");
		assertEquals("-250",result);
	}

}
