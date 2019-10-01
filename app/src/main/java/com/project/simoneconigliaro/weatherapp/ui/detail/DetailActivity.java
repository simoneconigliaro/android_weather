package com.project.simoneconigliaro.weatherapp.ui.detail;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.project.simoneconigliaro.weatherapp.R;
import com.project.simoneconigliaro.weatherapp.models.WeatherResponse;
import com.project.simoneconigliaro.weatherapp.ui.forecastlist.ForecastListActivity;
import com.project.simoneconigliaro.weatherapp.ui.forecastlist.ForecastListViewModel;
import com.project.simoneconigliaro.weatherapp.ui.forecastlist.WeatherResource;
import com.project.simoneconigliaro.weatherapp.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class DetailActivity extends DaggerAppCompatActivity {

    private static final String TAG = "DetailActivity";

    private DetailViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        viewModel = ViewModelProviders.of(this, providerFactory).get(DetailViewModel.class);

        int position = viewModel.getPosition();
        Toast.makeText(this, "" + position, Toast.LENGTH_SHORT).show();
        subscribeObservers();
    }

    public void subscribeObservers(){
        viewModel.observeWeather().observe(this, new Observer<WeatherResource<WeatherResponse>>() {
            @Override
            public void onChanged(WeatherResource<WeatherResponse> weatherResponseWeatherResource) {
                Log.d(TAG, "onChanged: " + weatherResponseWeatherResource.data.getCity().getName());
            }
        });
    }

}
