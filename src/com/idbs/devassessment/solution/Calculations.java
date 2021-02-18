/**
 * 
 */
package com.idbs.devassessment.solution;

import java.util.ArrayList;

/**
 * @author imogenhay
 * Interface allowing global access to variable containing previous calculations to reduce repetitions
 * Also defines some of the expected behaviour for Term and Calculation class
 *
 */
public interface Calculations {
	
	// in each sub array third value is result, first and second values are valued added
	ArrayList<ArrayList<Long>> calculations = new ArrayList<ArrayList<Long>>();

	
	/**
	 * @param xValue to substituted into equation
	 * @return the evaluated equation
	 */
	public long evaluate(int xValue);
	
	
	/**
	 * @param a, b, checks if they have been added together previously
	 * @return previous calculation, or null if not found
	 */
	public ArrayList<Long> previouslyCalculated(long a, long b);
}
