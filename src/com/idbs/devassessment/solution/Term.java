/**
 * 
 */
package com.idbs.devassessment.solution;



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
	 * @return the multiplier
	 */
	public int getMultiplier() {
		return this.multiplier;
	}

	
	
	/**
	 * @return the action
	 */
	public String getAction() {
		return this.action;
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
