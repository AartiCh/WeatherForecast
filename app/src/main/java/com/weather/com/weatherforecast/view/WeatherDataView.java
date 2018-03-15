package com.weather.com.weatherforecast.view;

import com.weather.com.weatherforecast.models.Forecastday_;
import java.util.ArrayList;

/**
 * Created by aartichittala on 3/13/18.
 */

public interface WeatherDataView {
    void getWeatherDataList(ArrayList<Forecastday_> forecastResults);
}

