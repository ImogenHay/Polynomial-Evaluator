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
		// use the json api to read the json to give a JsonObject representing the Json
		JsonReader reader = Json.createReader(new StringReader(this.data));
		this.jsonObject = reader.readObject();
		reader.close();
	}
	
	
	
	@Override
	public int getXValue() {
		// get the x value from the Json
		return this.jsonObject.getInt("xValue");
	}

	
	
	@Override
	protected void generateTerms() {
		// read the terms array from the json
		JsonArray terms = jsonObject.getJsonArray("terms");
		
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
