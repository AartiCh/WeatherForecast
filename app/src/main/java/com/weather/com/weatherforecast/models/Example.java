package com.weather.com.weatherforecast.models;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by aartichittala on 3/10/18.
 */

public class Example {
        @SerializedName("RESULTS")
        @Expose
        private ArrayList<Result> results = null;

        public ArrayList<Result> getRESULTS() {
            return results;
        }

        public void setRESULTS(ArrayList<Result> results) {
            this.results = results;
        }

    }
