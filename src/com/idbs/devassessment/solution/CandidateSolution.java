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

        return DifficultyLevel.LEVEL_1;
    }

    @Override
    public String getAnswer() throws IDBSSolutionException
    {
    	//Issues:
    	//when multiplier is 0
    	//when answer greater than 10 digits
    	//


        // first get Json as a String for the question using the inherited method...
        String json = getDataForQuestion();

        // now use the json api to read the json to give a JsonObject representing the Json...
        JsonReader reader = Json.createReader(new StringReader(json));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        // now start extracting the data you need from the json....

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
        	
        	 System.out.println(term.toString() + "=" + term.evaluate(xValue));
        }
        System.out.println(equation.toString() + "=" + equation.evaluate(xValue) + "\n");


        return Long.toString(equation.evaluate(xValue));
    }

}
