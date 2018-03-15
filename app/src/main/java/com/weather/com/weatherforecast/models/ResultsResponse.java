package com.weather.com.weatherforecast.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by aartichittala on 3/10/18.
 */
public class ResultsResponse {
    @SerializedName("results")
    @Expose
    private Results result = new Results();

    public Results getResult() {
        return result;
    }
}




