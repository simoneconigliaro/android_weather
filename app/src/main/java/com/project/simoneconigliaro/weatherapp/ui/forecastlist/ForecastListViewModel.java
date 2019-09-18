package com.project.simoneconigliaro.weatherapp.ui.forecastlist;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.project.simoneconigliaro.weatherapp.models.Weather;
import com.project.simoneconigliaro.weatherapp.network.WeatherApi;

import javax.inject.Inject;

public class ForecastListViewModel extends ViewModel {

    private static final String TAG = "ForecastListViewModel";

    private final WeatherApi weatherApi;

    @Inject
    public ForecastListViewModel(WeatherApi weatherApi) {

        this.weatherApi = weatherApi;

        Log.d(TAG, "ForecastListViewModel: viewmodel is working...");

        if(this.weatherApi == null) {
            Log.d(TAG, "ForecastListViewModel: weather api is NULL");
        } else {
            Log.d(TAG, "ForecastListViewModel: weather api is NOT NULL");
        }
    }
}
