/**
 * 
 */
package com.idbs.devassessment.solution;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 * @author imogenhay
 *  Defines the fields and methods of different json input type
 *
 */
public class ReadJson extends ReadData {
	
	private String data = null;
	private JsonObject jsonObject = null;
	
	/**
	 * @param data
	 */
	public ReadJson(String data) {
		super(data);
		this.data = data;
		// use the json api to read the json to give a JsonObject representing the Json
		JsonReader reader = Json.createReader(new StringReader(this.data));
		this.jsonObject = reader.readObject();
		reader.close();
	}
	
	
	
	 /**
	 * @return get X value from data
	 */
	@Override
	public int getXValue() {
		// get the x value from the Json
		return this.jsonObject.getInt("xValue");
	}

	
	
	 /**
	 * creates term objects and adds to Equation
	 */
	@Override
	protected void generateTerms() {
		// read the terms array from the json
		JsonArray terms = this.jsonObject.getJsonArray("terms");
		
		 // create term object for each term in Json Array
        for (int i = 0; i < terms.size(); i++)
        {
     	   JsonObject json_term = terms.getJsonObject(i);
         	
     	   Term term = new Term(json_term.getInt("power"),json_term.getInt("multiplier"),json_term.getString("action"));
         	
     	   this.equation.addTerm(term); // add terms to equation object  	
         	
         	//System.out.println(term.toString() + "=" + term.evaluate(xValue)); // for debugging
        }
        //System.out.println(equation.toString() + "=" + equation.evaluate(xValue) + "\n"); // for debugging

	}

}
