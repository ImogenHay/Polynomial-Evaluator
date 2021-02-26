/**
 * 
 */
package com.idbs.devassessment.solution;


/**
 * @author imogenhay
 * Defines the fields, methods and abstract methods of different input types
 *
 */
public abstract class ReadData {

	private String data = null;
	protected Equation equation = null;
	
	/**
	 * @param data
	 */
	public ReadData(String data) {
		super();
		this.data = data;
		this.equation = new Equation();
	}
	
	
	
	 /**
	 * @return get X value from data
	 */
	public abstract int getXValue(); // method to get X will depend on input format
	
	
	
	 /**
	 * creates term objects and adds to Equation
	 */
	protected abstract void generateTerms(); // method to generate terms will depend on input format
	
	
	
	/**
	 * @return data
	 */
	public String getData() {
		return this.data;
	}
	
	
	
	/**
	 * @return equation
	 */
	public Equation getEquation() {
		return this.equation;
	}
	
	
	
	/**
	 * @return result of evaluation of equation
	 */
	public String getResult() {
		int xValue = this.getXValue();
		this.generateTerms();
		//System.out.println(this.toString()); // for debugging
		return Long.toString(this.equation.evaluatePolynomial(xValue));
	}



	@Override
	public String toString() {
		// shows equation, x value and result of evaluation
		return this.equation.toString() + " = " + this.equation.evaluatePolynomial(this.getXValue()) + " (x=" + this.getXValue() + ")\n";
	}
	
}
