/**
 * 
 */
package com.idbs.devassessment.solution;

import java.util.ArrayList;
import java.util.List;



/**
 * @author imogenhay
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
}
