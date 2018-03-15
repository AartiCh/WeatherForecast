package com.weather.com.weatherforecast.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by aartichittala on 3/10/18.
 */
public class Results {
        @SerializedName("result")
        @Expose
        private ArrayList<Result> results = new ArrayList<>();

        public ArrayList<Result> getResults() {
            return results;
        }

        public void setResults(ArrayList<Forecastday_> forecastday) {
            this.results = results;
        }
    }
