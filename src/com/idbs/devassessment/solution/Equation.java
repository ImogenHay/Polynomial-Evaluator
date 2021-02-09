/**
 * 
 */
package com.idbs.devassessment.solution;

import java.util.ArrayList;
import java.util.List;



/**
 * @author imogenhay
 * Defines fields and methods of Equation and allows you to evaluate its terms 
 *
 */
public class Equation {
	
	/** List of Term objects in Equation*/
	private List<Term> terms = null;


	public Equation() {
		super();
		this.terms = new ArrayList<Term>(); //when object created a new empty array list is created
	}
	
	
	
	/**
	 * @param Term to be added to list of terms
	 */
	public void addTerm(Term term) {
		terms.add(term);
	}
	
	
	
	/**
	 * @return list of terms
	 */
	public List<Term> getTerms() {
		return this.terms;
	}
	
	
	
	/**
	 * @param xValue to substituted into equation
	 * @return the evaluated equation
	 */
	public int evaluate(int xValue) {
		int result = 0;
		for(Term term : terms) {
			result = result + term.evaluate(xValue);
		}
		return result;
	}



	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer(); //creates StringBuffer object to make it easy to append more Strings
		for (Term term : terms) { //loop repeated for each property in list of properties
			buffer.append(term.toString() + " "); //adds output of toString method in House class to buffer
		}
		return buffer.toString() + "\n"; //returns string displaying info on all properties in list depending on availability
	}
	
}
