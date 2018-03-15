package com.weather.com.weatherforecast.presenter;

import com.weather.com.weatherforecast.models.Example;
import com.weather.com.weatherforecast.models.Result;
import com.weather.com.weatherforecast.retrofit.RetroClient;
import com.weather.com.weatherforecast.view.CityAutoCompleteListView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aartichittala on 3/10/18.
 */

public class AutoCompleteCityDataPresenter {

    private CityAutoCompleteListView cityListView;
    private RetroClient retroClient;

    public AutoCompleteCityDataPresenter(CityAutoCompleteListView cityListView) {
        this.cityListView = cityListView;
        if (this.retroClient == null) {
            this.retroClient = new RetroClient();
        }
    }

    /** Method for making the network call to query autocomplete city list data.
     *
     * @param queryString
     */
    public void getAutoCompleteCityResults(String queryString) {
        RetroClient.getAutoApiService()
                .getMyJSON(queryString)
                .enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {

                        if (response.isSuccessful()) {
                            /**
                             * Got Successfully
                             */

                            Example obj = response.body();
                            ArrayList<Result> autoCompleteList = obj.getRESULTS();
                            cityListView.getCityList(autoCompleteList);

                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {

                        try {
                            throw new InterruptedException("Failed while retrieving autocomplete results");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });

    }
}
