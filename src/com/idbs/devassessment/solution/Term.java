/**
 * 
 */
package com.idbs.devassessment.solution;

/**
 * @author imogenhay
 * Defines fields and methods of Term and allows you to evaluate term for given x.
 */
public class Term {

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
		if (power < 0) { //field validation to prevent creation of invalid terms
			throw new IllegalArgumentException("Invalid power");
		}
		if (multiplier < 0) {
			throw new IllegalArgumentException("Invalid multiplier");
		}
		if (action != "add" && action!= "subtract") {
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
		return power;
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
		return multiplier;
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
		return action;
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
	public int evaluate(int xValue) {
		int result = exponent(xValue, this.power); //calculate indices first (BIDMAS)
		
		if (result > this.multiplier) { //smaller value first so less loops required
			result = multiply(result,this.multiplier); //multiply result by multiplier
		}
		else {
			result = multiply(this.multiplier, result);
		}
		
		if(this.action == "subtract") { //makes result negative if action is subtract
			result = -result;
		}
		
		return result;
	}
	
	
	
	/**
	 * @param a, b terms to be multiplied by each other
	 * @return result of multiplication
	 */
	private int multiply(int a, int b) {
		int result = a;
		for (int i = 1; i < b; i++) { //b should be smaller than a to reduce amount of loops
			result = result + a; //add a to itself b times
		}
		return result;
	}
	
	
	
	/**
	 * @param x the given value of x
	 * @param p the given value of power
	 * @return result of x to the power of p
	 */
	private int exponent(int x, int p) {
		int result = x; 
		
		if(p == 0) { //anything to the power of 0 evaluates to 1
			result = 1;
		}
		else { 
			for(int i = 1; i < p; i++) { //multiply x by x, p times
				result = multiply(result,x);
			}
		}
		
		return result;
	}
	
}
