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
        /*
         * This is the default solution and provides some example code on how to extract data from Json in java.
         *
         * As an initial start we suggest you comment ALL the code below and return a null value from the method. Run
         * this in the assessment application and you'll see many examples of the Json that question produces.
         */

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

//        // now sum the array
//        int arraySum = 0;
//
//        for (int i = 0; i < jsonArray.size(); i++)
//        {
//            arraySum += jsonArray.getInt(i);
//        }
//
//        // calculate the answer..
//        int answer = startValue - arraySum;

        return Integer.toString(xValue);
    }

}
