/**
 * 
 */
package com.idbs.devassessment.solution;

import java.util.ArrayList;
import java.util.List;

import com.idbs.devassessment.harness.DigitalTaxTracker;



/**
 * @author imogenhay
 * Defines fields and methods of Equation and allows you to evaluate its terms 
 *
 */
public class Equation implements Calculations {
	
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
	public ArrayList<ArrayList<Long>> getCalculations() {
		return calculations;
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
	 * @param xValue to substituted into equation
	 * @return the evaluated equation
	 */
	public long evaluate(int xValue) {
		long result = 0;
		for(Term term : terms) {	
			long evaluated = term.evaluate(xValue);
			
			if (evaluated != 0) { // so wont do addition of zeros
				
				if (result == 0) { // if result is 0 no point in adding to 0
					result = evaluated;
				}
				
				else {
					long current = result;
					ArrayList<Long> previousCalculation = previouslyCalculated(current,evaluated);
					
					if(previousCalculation != null) { // if previously calculated use previous result
						result = previousCalculation.get(2);
					}
					else {
						result = DigitalTaxTracker.add(current, evaluated); // add a to itself b times
						
						ArrayList<Long> calculation = new ArrayList<Long>(); // stores calculation
						calculation.add(current);
						calculation.add(evaluated);
						calculation.add(result);
						calculations.add(calculation);
						//System.out.println(calculation); // for debugging
					}	
				}			
			}			
		}
		//System.out.println(calculations.size()); // for debugging
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
