package com.weather.com.weatherforecast.presenter;

import com.weather.com.weatherforecast.models.ForeCastResponse;
import com.weather.com.weatherforecast.models.Forecast;
import com.weather.com.weatherforecast.models.Forecastday_;
import com.weather.com.weatherforecast.models.Simpleforecast;
import com.weather.com.weatherforecast.retrofit.RetroClient;
import com.weather.com.weatherforecast.view.WeatherDataView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aartichittala on 3/10/18.
 */

public class WeatherPresenter {

    private WeatherDataView weatherView;
    private RetroClient retroClient;
    private ArrayList<Forecastday_> forecastList;

    public WeatherPresenter(WeatherDataView weatherView) {
        this.weatherView = weatherView;
        if (this.retroClient == null) {
            this.retroClient = new RetroClient();
        }
    }

    /**  /** Method for making the network call to query weather data.
     *
     * @param city
     */
    public void getWeatherData(String city) {

        RetroClient.getApiService()
                .getMyJSON(city)
                .enqueue(new Callback<ForeCastResponse>() {
                    @Override
                    public void onResponse(Call<ForeCastResponse> call, Response<ForeCastResponse> response) {
                        if (response.isSuccessful()) {
                            /**
                             * Got Successfully
                             */

                            if (response != null) {
                                Forecast Obj = response.body().getForecast();
                                if (Obj != null) {
                                    Simpleforecast temp = Obj.getSimpleforecast();
                                    if (temp != null) {
                                        forecastList = temp.getForecastday();
                                        weatherView.getWeatherDataList(forecastList);
                                    } else {
                                        weatherView.getWeatherDataList(null);
                                    }
                                } else {
                                    weatherView.getWeatherDataList(null);
                                }
                            } else {
                                weatherView.getWeatherDataList(null);
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ForeCastResponse> call, Throwable t) {

                        try {
                            throw new InterruptedException("Failed while retrieving autocomplete results");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }
}
