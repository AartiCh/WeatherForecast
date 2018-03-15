
package com.weather.com.weatherforecast.models;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Simpleforecast {

    @SerializedName("forecastday")
    @Expose
    private ArrayList<Forecastday_> forecastday = new ArrayList<>();

    public ArrayList<Forecastday_> getForecastday() {
        return forecastday;
    }

    public void setForecastday(ArrayList<Forecastday_> forecastday) {
        this.forecastday = forecastday;
    }

}
