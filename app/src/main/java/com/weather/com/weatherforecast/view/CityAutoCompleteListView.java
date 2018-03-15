package com.weather.com.weatherforecast.view;

import com.weather.com.weatherforecast.models.Result;

import java.util.ArrayList;

/**
 * Created by aartichittala on 3/10/18.
 */

public interface CityAutoCompleteListView {

    void getCityList(ArrayList<Result> autoCompleteCityResults);
}

