package com.weather.com.weatherforecast.view;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.weather.com.weatherforecast.AutoCompleteAdapter;
import com.weather.com.weatherforecast.R;
import com.weather.com.weatherforecast.models.Result;
import com.weather.com.weatherforecast.presenter.AutoCompleteCityDataPresenter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by aartichittala on 3/10/18.
 */

public class CitySearchActivity extends AppCompatActivity implements CityAutoCompleteListView {

    public static final String APPLICATION_LOG_TAG = "WeatherForecast";
    private ListView mListView;
    private AutoCompleteAdapter mAdapter;
    private TextView mCityContentButton;
    public static final String CITY_KEY = "City";
    public static final String DISPLAY_CITY_KEY = "DisplayCity";

    /** Renders the city search view.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auto_list_view);

    }

    /** Method for search functionality and autocompleting city search.
     *
     * @param menu
     * @return boolean
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(final String query) {

                mListView = findViewById(R.id.auto_list);
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        TextView cityCoordinates = view.findViewById(R.id.city_zmw);
                        String city = cityCoordinates.getText().toString();

                        TextView cityName = view.findViewById(R.id.auto_complete_content);
                        String cityDisplay = cityName.getText().toString();

                        final Bundle bundle = new Bundle();
                        bundle.putString(CITY_KEY, city);
                        bundle.putString(DISPLAY_CITY_KEY, cityDisplay);
                        Intent myIntent = new Intent(CitySearchActivity.this, WeatherDataDisplayActivity.class);
                        myIntent.putExtras(bundle);
                        CitySearchActivity.this.startActivity(myIntent);
                    }
                });


                if (query.length() > 2) {
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            AutoCompleteCityDataPresenter weatherPresenter = new AutoCompleteCityDataPresenter(CitySearchActivity.this);
                            weatherPresenter.getAutoCompleteCityResults(query);
                        }
                    }, 600);
                }

                mCityContentButton = findViewById(R.id.CityContentButton);
                mCityContentButton.setVisibility(View.INVISIBLE);
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(final String query) {

                mListView = findViewById(R.id.auto_list);

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        TextView cityCoordinates = view.findViewById(R.id.city_zmw);
                        String city = cityCoordinates.getText().toString();

                        final Bundle bundle = new Bundle();
                        bundle.putString("City", city);

                        Intent myIntent = new Intent(CitySearchActivity.this, WeatherDataDisplayActivity.class);
                        CitySearchActivity.this.startActivity(myIntent);
                    }
                });

                AutoCompleteCityDataPresenter weatherPresenter = new AutoCompleteCityDataPresenter(CitySearchActivity.this);
                weatherPresenter.getAutoCompleteCityResults(query);

                mCityContentButton = findViewById(R.id.CityContentButton);
                mCityContentButton.setVisibility(View.INVISIBLE);
                return true;
            }

        });

        return true;
    }

    @Override
    public void getCityList(ArrayList<Result> autoCompleteCityResults) {

        if (autoCompleteCityResults != null) {
            mAdapter = new AutoCompleteAdapter(this, autoCompleteCityResults);
            mListView.setAdapter(mAdapter);
        } else {
            mCityContentButton.setText("No results");
            mCityContentButton.setVisibility(View.VISIBLE);
        }

    }


}

