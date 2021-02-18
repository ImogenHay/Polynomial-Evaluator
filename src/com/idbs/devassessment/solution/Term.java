/**
 * 
 */
package com.idbs.devassessment.solution;

import java.util.ArrayList;

import com.idbs.devassessment.harness.DigitalTaxTracker;

/**
 * @author imogenhay
 * Defines fields and methods of Term and allows you to evaluate term for given x.
 */
public class Term implements Calculations{

	private int power = 0;
	private int multiplier = 0;
	private String action = null;
	
	
	/**
	 * @param power
	 * @param multiplier
	 * @param action
	 */
	public Term(int power, int multiplier, String action) {
		super();
		if (power < 0) { // field validation to prevent creation of invalid terms
			throw new IllegalArgumentException("Invalid power");
		}
		if (multiplier < 0) {
			throw new IllegalArgumentException("Invalid multiplier");
		}
		if (!action.equals("add") && !action.equals("+") && !action.equals("subtract") && !action.equals("-")) {
			throw new IllegalArgumentException("Invalid action");
		}
		this.power = power;
		this.multiplier = multiplier;
		this.action = action;
	}

	
	
	/**
	 * @return the power
	 */
	public int getPower() {
		return this.power;
	}

	
	
	/**
	 * @param power the power to set
	 */
	public void setPower(int power) {
		this.power = power;
	}

	
	
	/**
	 * @return the multiplier
	 */
	public int getMultiplier() {
		return this.multiplier;
	}

	
	
	/**
	 * @param multiplyer the multiplier to set
	 */
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}

	
	
	/**
	 * @return the action
	 */
	public String getAction() {
		return this.action;
	}

	
	
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
	
	
	/**
	 * @param xValue the given value of x
	 * @return the evaluated term
	 */
	public long evaluate(int xValue) {
		
		if (this.multiplier == 0) { // anything multiplied by 0 is 0
			return 0;
		}
		else {
			long result = exponent(xValue, this.power); // calculate indices first (BIDMAS)

			if (result > this.multiplier) { // smaller value first so less loops required
				result = multiply(result,this.multiplier); // multiply result by multiplier
			}
			else {
				result = multiply(this.multiplier, result);
			}
			
			if(this.action.equals("subtract") || this.action.equals("-")) { // makes result negative if action is subtract
				result = -result;
			}
			
			return result;
		}

	}
	
	
	
	/**
	 * @param a, b, checks if they have been added together previously
	 * @return previous calculation, or null if not found
	 */
	public ArrayList<Long> previouslyCalculated(long a, long b) {
		ArrayList<Long> prevCalc = null;
		for(ArrayList<Long> calculation : calculations) { // checks if current calculation has previously been calculated
			if(calculation.get(0) == b && calculation.get(1) == a) {
				prevCalc = calculation;
				break; // so does not loop through rest of array if found answer
			}
			else if(calculation.get(0) == a && calculation.get(1) == b) { // order of addition does not effect result
				prevCalc = calculation;
				break;
			}
		}
		return prevCalc;
	}
	
	
	
	/**
	 * @param a, b terms to be multiplied by each other
	 * @return result of multiplication
	 */
	private long multiply(long a, long b) {
		long result = a;
		for (int i = 1; i < b; i++) { // b should be smaller than a to reduce amount of loops
			long current = result;
			ArrayList<Long> previousCalculation = previouslyCalculated(current,a);
			
			if(previousCalculation != null) { // if previously calculated use previous result
				result = previousCalculation.get(2);
			}
			else {
				result = DigitalTaxTracker.add(current, a); // add a to itself b times
				
				ArrayList<Long> calculation = new ArrayList<Long>(); // stores calculation
				calculation.add(current);
				calculation.add(a);
				calculation.add(result);
				calculations.add(calculation);
			}			
			
		}
		return result;
	}
	
	
	
	/**
	 * @param x the given value of x
	 * @param p the given value of power
	 * @return result of x to the power of p
	 */
	private long exponent(long x, int p) {
		long result = x; //so if p == 1 default to x
		
		if(p == 0) { // anything to the power of 0 evaluates to 1
			result = 1;
		}
		else { 
			for(int i = 1; i < p; i++) { // multiply x by x, p times
				result = multiply(result,x);
			}
		}
		
		return result;
	}



	@Override
	public String toString() {
		String action_symbol = "+ ";
		
		if(this.action.equals("subtract") || this.action.equals("-")) { 
			action_symbol = "- ";
		}
		
		return action_symbol + this.multiplier + "x^(" + this.power + ")";
	}
	
}
