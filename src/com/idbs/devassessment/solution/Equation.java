/**
 * 
 */
package com.idbs.devassessment.solution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.idbs.devassessment.harness.DigitalTaxTracker;



/**
 * @author imogenhay
 * Defines fields and methods of Equation and allows you to evaluate its terms 
 *
 */
public class Equation {
	
	/** Hash map linking calculation to result */
	static HashMap<String, Long> calculations = new HashMap<String, Long>();
		
	/** List of Term objects in Equation*/
	private List<Term> terms = null;


	public Equation() {
		super();
		this.terms = new ArrayList<Term>(); // when object created a new empty array list is created
	}
	
	
	
	/**
	 * @param TermTest to be added to list of terms
	 */
	public void addTerm(Term term){
		terms.add(term);
	}
	
	
	
	/**
	 * @return list of terms
	 */
	public List<Term> getTerms() {
		return this.terms;
	}
	
	
	/**
	 * @return the stored calculations
	 */
	public HashMap<String, Long> getCalculations() {
		return calculations;
	}
	
	
	/**
	 * @param a, b, checks if they have been added together previously
	 * @return previous calculation, or null if not found
	 */
	public Long previouslyCalculated(long a, long b) {
		Long prevCalcResult = calculations.get(a + "+" + b);  // checks if current calculation has previously been calculated
		if (prevCalcResult == null) {
			prevCalcResult = calculations.get(b + "+" + a); // so order of addition does not effect result
		}
		return prevCalcResult;
	}
	
	
	
	/**
	 * @param xValue to substituted into equation
	 * @return the evaluated equation
	 */
	public long evaluatePolynomial(int xValue) {
		long result = 0;
		for(Term term : this.terms) {	
			long evaluated = this.evaluateTerm(term, xValue);
			
			if (evaluated != 0) { // so wont do addition of zeros
				
				if (result == 0) { // if result is 0 no point in adding to 0
					result = evaluated;
				}
				
				else {
					long current = result;
					Long previousCalculation = previouslyCalculated(current,evaluated);
					
					if(previousCalculation != null) { // if previously calculated use previous result
						result = previousCalculation;
					}
					else {
						if (current < 0 || evaluated < 0) {
							result = DigitalTaxTracker.add(current, evaluated);
						}
						else {
							result = this.columnAddition(current, evaluated); //calculates result and stores calculation
						}
						String calculation = current + "+" + evaluated; // stores calculation
						calculations.put(calculation,result);
						
						
					}	
				}			
			}			
		}
		//System.out.println(calculations.size()); // for debugging
		return result;
	}
	
	
	
	/**
	 * @param xValue the given value of x
	 * @return the evaluated term
	 */
	public long evaluateTerm(Term term, int xValue) {
		
		if (term.getMultiplier() == 0 || xValue == 0) { // anything multiplied by 0 is 0
			return 0;
		}
		else {
			long result = exponent(xValue, term.getPower()); // calculate indices first (BIDMAS)

			result = multiply(result,term.getMultiplier()); // multiply result by multiplier

			if(term.getAction().equals("subtract") || term.getAction().equals("-")) { // makes result negative if action is subtract
				result = -result;
			}
			
			return result;
		}

	}
	
	
	
	/**
	 * @param a, b terms to be multiplied by each other
	 * @return result of multiplication
	 */
	private long multiply(long a, long b) {
		
		if(b > a) { // b should be smaller than a to reduce amount of loops
			long temp = a;
			a = b;
			b = temp;			
		}
			
		long result = a; // if a multiplied by 1 result will be a
		long twoA = 0; // will store value of a + a
		long valueToAdd = 0;
		
		for (int i = 1; i < b; i++) { 
			long current = result;
			
			// if a is going to be added to result more than one more time, and twoA has been calculated
			if (b > (i+1) && i != 1) { 
				valueToAdd = twoA; // add two a's in one addition since already know what a+a is
				i++; // skips two steps since did two additions in one
			}
			else {
				valueToAdd = a;
			}
			
			Long previousCalculation = previouslyCalculated(current,valueToAdd);
			
			if(previousCalculation != null) { // if previously calculated use previous result
				result = previousCalculation;
			}
			else {
				result = this.columnAddition(current, valueToAdd);	
				//result = DigitalTaxTracker.add(current, valueToAdd);
			}	
			
			if (i == 1) { // first iteration will be when value is added to itself, this will be 2* a
				twoA = result; // stores so that 2 steps can be done in one addition
			}
			//System.out.println(i); // for debugging
			//System.out.println(current + " + " + valueToAdd + " = " + result); // for debugging
		}
		//System.out.println(a + " * " + b + " = " + result); // for debugging
		return result;
	}
	
	
	
	/**
	 * @param values to be added together
	 * @return result of value1 + value2
	 */
	private long columnAddition(long a, long b) {

		String longer = Long.toString(a); // for column addition need to know length of longer number since that is number of rows
		String shorter = Long.toString(b);
		if (a < b) {
			longer = Long.toString(b);
			shorter = Long.toString(a);
		}
					
		String finalResult = "";
		int carryDigit = 0; // if result of addition > 1 digit first digit must be carried over
		
		for (int i = 1; i <= longer.length(); i++) {
			
			
			char topDigit = longer.charAt(longer.length()-i); 
			int rowResult = Character.getNumericValue(topDigit); 
			
			if (i-1 < shorter.length()) { // only add bottom digit if value in this column
				char bottomDigit = shorter.charAt(shorter.length()-i);
				rowResult = (int)this.add((long)rowResult, (long)Character.getNumericValue(bottomDigit));

			}
			
						
			if (carryDigit != 0) { // if carryDigit from previous addition it must be added
				rowResult = (int)this.add((long)rowResult, carryDigit);
				carryDigit = 0;
			}
			
			
			if (String.valueOf(rowResult).length() > 1) { // if result of addition > 1 digit first digit must be carried over
				carryDigit = Integer.parseInt(Integer.toString(rowResult).substring(0, 1)); // carryDigit is fist digit in result of addition
				rowResult = rowResult % 10; // result of this row is last digit in result of addition
			}
			
			finalResult = rowResult + finalResult; // this is not an addition, just concatenating strings
			
		}
	
		if (carryDigit != 0) { 
			finalResult = carryDigit + finalResult; // this is not an addition, just concatenating strings
		}
	
		
		//if (Long.parseLong(finalResult) != (a+b)) {
			//System.out.println(a + " + " + b + " = " + Long.parseLong(finalResult) + " = " + (a+b));
		//}
		return Long.parseLong(finalResult);
	}
	
	
	
	/**
	 * @param values to be added together
	 * @return result of value1 + value2
	 */
	private long add(long value1, long value2) {
		long result = 0;
		if (value1 == 0) { // adding 0 to value will return value
			result = value2;
		}
		else if (value2 == 0) {
			result = value1;
		}
		else {
			Long previousCalculation = previouslyCalculated(value1,value2);
			
			if(previousCalculation != null) { // if previously calculated use previous result
				result = previousCalculation;
			}
			
			else {
				result = DigitalTaxTracker.add(value1, value2);			
				String calculation = value1 + "+" + value2; // stores calculation
				calculations.put(calculation,result);
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
		StringBuffer buffer = new StringBuffer(); // creates StringBuffer object to make it easy to append more Strings
		for (Term term : terms) { // loop repeated for each property in list of properties
			buffer.append(term.toString() + " "); // adds output of toString method in House class to buffer
		}
		return buffer.toString() + "\n"; // returns string displaying info on all properties in list depending on availability
	}
	
}
