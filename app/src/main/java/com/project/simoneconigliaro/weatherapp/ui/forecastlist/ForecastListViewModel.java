package com.project.simoneconigliaro.weatherapp.ui.forecastlist;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class ForecastListViewModel extends ViewModel {

    private static final String TAG = "ForecastListViewModel";

    @Inject
    public ForecastListViewModel() {

        Log.d(TAG, "ForecastListViewModel: viewmodel is working...");
    }
}
