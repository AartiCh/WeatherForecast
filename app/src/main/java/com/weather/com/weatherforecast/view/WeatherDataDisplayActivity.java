package com.weather.com.weatherforecast.view;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.weather.com.weatherforecast.R;
import com.weather.com.weatherforecast.WeatherContentAdapter;
import com.weather.com.weatherforecast.models.Forecastday_;
import com.weather.com.weatherforecast.presenter.WeatherPresenter;

import java.util.ArrayList;

/**
 * Created by aartichittala on 3/11/18.
 */

public class WeatherDataDisplayActivity extends AppCompatActivity implements WeatherDataView {

    private WeatherContentAdapter mWeatherContentAdapter;
    private ListView mListView;
    private String mCity;
    private TextView mEmptyContent;
    public static final String CITY_KEY = "City";
    public static final String DISPLAY_CITY_KEY = "DisplayCity";

    /**
     * Method for displaying the queried weather data.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        mListView = findViewById(R.id.weather_list);
        mEmptyContent = findViewById(R.id.empty);
        mEmptyContent.setVisibility(View.INVISIBLE);

        Bundle bundle = getIntent().getExtras();
        if (bundle.containsKey(CITY_KEY)) {
            mCity = bundle.getString(CITY_KEY);
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.weather_content_header) +
                    bundle.getString(DISPLAY_CITY_KEY));
        }

        WeatherPresenter weatherPresenter = new WeatherPresenter(this);
        weatherPresenter.getWeatherData(mCity);

    }

    /**
     * Method for retrieving weather data response and adding the data to the view by passing
     * the data through the adapter. Displays no result when data is null.
     *
     * @param forecastList
     */
    @Override
    public void getWeatherDataList(ArrayList<Forecastday_> forecastList) {
        if (forecastList != null) {
            mWeatherContentAdapter = new WeatherContentAdapter(this, forecastList);
            mListView.setAdapter(mWeatherContentAdapter);
        } else {
            mEmptyContent.setVisibility(View.VISIBLE);
            mListView.setEmptyView(mEmptyContent);
        }

    }
}
