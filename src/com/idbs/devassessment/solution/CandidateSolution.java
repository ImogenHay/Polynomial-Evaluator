/*
 * Copyright (C) 1993-2020 ID Business Solutions Limited
 * All rights reserved
 */
package com.idbs.devassessment.solution;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.idbs.devassessment.core.IDBSSolutionException;
import com.idbs.devassessment.core.DifficultyLevel;

/**
 * Example solution for the example question
 */

public class CandidateSolution extends CandidateSolutionBase
{
    @Override
    public DifficultyLevel getDifficultyLevel()
    {
        /*
         * 
         * CHANGE this return type to YOUR selected choice of difficulty level to which you will code an answer to.
         * 
         */

        return DifficultyLevel.LEVEL_2;
    }

    @Override
    public String getAnswer() throws IDBSSolutionException
    {



        // first get Json as a String for the question using the inherited method...
        String data = getDataForQuestion();
       
        String json = "";
        int prefix = data.indexOf(":");
        if (prefix != -1) {
        	json = data.substring(prefix+1,data.length()); // this will remove prefix
        }


       
       if(data.startsWith("json")) { //if Json format
    	// now use the json api to read the json to give a JsonObject representing the Json...
           JsonReader reader = Json.createReader(new StringReader(json));

           JsonObject jsonObject = reader.readObject();
           reader.close();

            // now start extracting the data you need from the json....
           // JsonObject json_format = jsonObject.getJsonObject("json");
            
            // get the x value from the Json
           int xValue = jsonObject.getInt("xValue");

            // read the terms array from the json
           JsonArray terms = jsonObject.getJsonArray("terms");
            
           Equation equation = new Equation();

            // create term object for each term in Json Array
           for (int i = 0; i < terms.size(); i++)
           {
        	   JsonObject json_term = terms.getJsonObject(i);
            	
        	   Term term = new Term(json_term.getInt("power"),json_term.getInt("multiplier"),json_term.getString("action"));
            	
        	   equation.addTerm(term); // add terms to equation object  	
            	
            	//System.out.println(term.toString() + "=" + term.evaluate(xValue)); // for debugging
           }
           //System.out.println(equation.toString() + "=" + equation.evaluate(xValue) + "\n"); // for debugging


           return Long.toString(equation.evaluate(xValue));
       }   
       
       else if(data.startsWith("numeric")) { // if numeric format
    	   
    	   Equation equation = new Equation();
    	   int colon = json.indexOf(";");
    	   
    	   int xValue;
    	   try {
    		   xValue = Integer.parseInt(json.substring(4,colon)); // get number before colon which is x
    	   }
    	   catch (NumberFormatException e)
    	   {
    		   xValue = 1; // if no multiplier default to 1
    	   }

                 
    	   String[] terms = json.substring(colon+6,json.length()).split("(?=-|\\+)"); // splits string by "-" or "+" into array of terms
    	   for (String numeric_term : terms) {
    		   
    		   int power;
        	   try {
        		   power = Integer.parseInt(numeric_term.charAt(numeric_term.length()-1) + "");
        	   } catch (NumberFormatException e) {
        		   power = 1;
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

    	   return Long.toString(equation.evaluate(xValue));
       }
       
       else {
    	   return null;
       }  
      
    }

}
