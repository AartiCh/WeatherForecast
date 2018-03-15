package com.weather.com.weatherforecast.retrofit;

import com.weather.com.weatherforecast.models.Example;
import com.weather.com.weatherforecast.models.ResultsResponse;

import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Query;

/**
 * Created by aartichittala on 3/10/18.
 */

public interface AutoCompleteApiService {
    @GET("aq?/")
    Call<Example> getMyJSON(@Query("query") String query);
    }
