package com.weather.com.weatherforecast.retrofit;

/**
 * Created by aartichittala on 3/10/18.
 */

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Aarti Chittala
 */
public class RetroClient {

    private static final String ROOT_URL = "http://api.wunderground.com/";

    private static final String AUTO_URL = "http://autocomplete.wunderground.com/";

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitInstance() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);

        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get Retrofit Instance
     */
    private static Retrofit getRetrofitAutoCompleteApiInstance() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);
        return new Retrofit.Builder()
                .baseUrl(AUTO_URL)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static AutoCompleteApiService getAutoApiService() {
        return getRetrofitAutoCompleteApiInstance().create(AutoCompleteApiService.class);
    }
}