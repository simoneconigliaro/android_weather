package com.project.simoneconigliaro.weatherapp.ui.forecastlist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.project.simoneconigliaro.weatherapp.CheckPermissionActivity;
import com.project.simoneconigliaro.weatherapp.R;
import com.project.simoneconigliaro.weatherapp.models.WeatherResponse;
import com.project.simoneconigliaro.weatherapp.ui.detail.DetailActivity;
import com.project.simoneconigliaro.weatherapp.ui.settings.SettingsActivity;
import com.project.simoneconigliaro.weatherapp.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

public class ForecastListActivity extends CheckPermissionActivity implements ForecastAdapter.OnClickHandler, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = "ForecastListActivity";

    private ForecastListViewModel viewModel;

    private ProgressBar progressBar;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    ForecastAdapter forecastAdapter;

    RecyclerView recyclerView;

    SearchView searchView;

    @Inject
    SharedPreferences sharedPreferences;

    boolean isCurrentLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        progressBar = findViewById(R.id.progress_bar);
        recyclerView = findViewById(R.id.recycler_view);
        searchView = findViewById(R.id.search_view);

        viewModel = ViewModelProviders.of(this, providerFactory).get(ForecastListViewModel.class);

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        subscribeObservers();
        initRecyclerView();
        initSearchView();
        getLastLocation();
        isCurrentLocation = true;

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    private void subscribeObservers() {
        viewModel.observeWeather().observe(this, new Observer<WeatherResource<WeatherResponse>>() {
            @Override
            public void onChanged(WeatherResource<WeatherResponse> weatherResourceResponse) {
                if (weatherResourceResponse != null) {
                    switch (weatherResourceResponse.status) {
                        case LOADING: {
                            showProgressBar(true);

                            break;
                        }
                        case SUCCESS: {
                            showProgressBar(false);
                            forecastAdapter.setForecast(weatherResourceResponse.data);
                            break;
                        }
                        case ERROR: {
                            showProgressBar(false);
                            Toast.makeText(ForecastListActivity.this, weatherResourceResponse.message, Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }
            }
        });
    }


    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(forecastAdapter);
    }

    private void showProgressBar(boolean isVisible) {
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    private void initSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String location) {
                viewModel.getWeatherFromLocation(location);
                isCurrentLocation = false;
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    @Override
    public void onItemClick(int position) {
        viewModel.setDayPosition(position);
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.forecast_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;

            case R.id.current_location:
                getLastLocation();
                isCurrentLocation = true;
                searchView.setQuery("", false);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.pref_temp_units_key))) {
            forecastAdapter.notifyDataSetChanged();
            String location = viewModel.getLocation();
            if (!isCurrentLocation) {
                if (viewModel.getLocation() != null) {
                    searchView.setQuery(location, false);
                    searchView.clearFocus();
                }
            }
        }
    }

}
