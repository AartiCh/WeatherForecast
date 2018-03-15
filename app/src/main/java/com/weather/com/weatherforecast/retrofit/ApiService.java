package com.weather.com.weatherforecast.retrofit;

import com.weather.com.weatherforecast.models.ForeCastResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by aartichittala on 3/10/18.
 */

public interface ApiService {
    @GET("api/24f0b7e4ed53f605/forecast10day/q/zmw:{Id}.json")
    Call<ForeCastResponse> getMyJSON(
            @Path("Id") String cityCoordinates
    );
}
