package com.project.simoneconigliaro.weatherapp.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.project.simoneconigliaro.weatherapp.SessionManager;
import com.project.simoneconigliaro.weatherapp.models.WeatherResponse;
import com.project.simoneconigliaro.weatherapp.ui.forecastlist.WeatherResource;

import javax.inject.Inject;

public class DetailViewModel extends ViewModel {

    private SessionManager sessionManager;

    @Inject
    public DetailViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public LiveData<WeatherResource<WeatherResponse>> observeWeather() {
        return sessionManager.observeWeather();
    }

    public int getDayPosition(){
        return sessionManager.getDayPosition();
    }
}
