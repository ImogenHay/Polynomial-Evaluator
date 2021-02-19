/*
 * Copyright (C) 1993-2020 ID Business Solutions Limited
 * All rights reserved
 */
package com.idbs.devassessment.solution;


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

        return DifficultyLevel.LEVEL_3;
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

        
        ReadData reader = null; // subclass of ReadData used will depend on input format
        
        
        try {
        	
        	if(data.startsWith("json")) { // can add alternative formats
            	reader = new ReadJson(json);
            }
            else if(data.startsWith("numeric")) {
            	reader = new ReadNumeric(json);
            }   
        	
        	return reader.getResult();
        	
        } 
        catch( IllegalArgumentException e ) { // if invalid format
        	return "Invalid Input Format";
        } 
        catch( Exception e ) { // if other error occurs
        	return null;
        }
        
            
            
    }

}
