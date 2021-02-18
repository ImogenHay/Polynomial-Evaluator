/**
 * 
 */
package com.idbs.devassessment.solution;


/**
 * @author imogenhay
 * Defines the fields and methods of different numeric input type
 *
 */
public class ReadNumeric extends ReadData {

	private String data = null;
	private int semiColonIndex = 0;
	
	/**
	 * @param data
	 */
	public ReadNumeric(String data) {
		super(data);
		this.data = data;
		// use index of colon to determine between x value and terms
		this.semiColonIndex = this.data.indexOf(";");
	}
	
	
	
	 /**
	 * @return get X value from data
	 */
	@Override
	public int getXValue() {
		int xValue;
  	   try {
  		   xValue = Integer.parseInt(this.data.substring(4,this.semiColonIndex)); // get number before colon which is x
  	   }
  	   catch (NumberFormatException e)
  	   {
  		   xValue = 1; // if no multiplier default to 1
  	   }
		return xValue;
	}

	
	
	 /**
	 * creates term objects and adds to Equation
	 */
	@Override
	protected void generateTerms() {
		try {			
			String[] terms = this.data.substring(this.semiColonIndex+6,this.data.length()).split("(?=-|\\+)"); // splits string by "-" or "+" into array of terms
			
			for (String numeric_term : terms) {
	 		   
				int power;
	     	    try {
	     		    power = Integer.parseInt(numeric_term.charAt(numeric_term.length()-1) + "");
	     	    } catch (NumberFormatException e) {
	     		    power = 1; // to the power of 1 same as having no power
	     	    }
	     	   
	 		    int multiplier;
	     	    try {
	     		    multiplier =  Integer.parseInt(numeric_term.substring(1,3)); // works if 2 digit multiplier
	     	    } catch (NumberFormatException e) {
	     		    multiplier =  Integer.parseInt(numeric_term.charAt(1) + ""); // works if 1 digit multiplier
	     	    }
	 		   
	 		    String action = numeric_term.charAt(0) + "";
	 		   
	 		    Term term = new Term(power, multiplier, action);
	 		    equation.addTerm(term); // add terms to equation object 
	 		   
			}
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid Numeric Format");
		}

	}

}
