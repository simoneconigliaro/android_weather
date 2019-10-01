package com.project.simoneconigliaro.weatherapp;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.project.simoneconigliaro.weatherapp.models.WeatherResponse;
import com.project.simoneconigliaro.weatherapp.ui.forecastlist.WeatherResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {

    private static final String TAG = "SessionManager";

    MediatorLiveData<WeatherResource<WeatherResponse>> cachedWeather = new MediatorLiveData<>();

    int weatherPosition;

    @Inject
    public SessionManager() {
    }

    public void getWeather(final LiveData<WeatherResource<WeatherResponse>> source){
        if(cachedWeather != null){
            cachedWeather.setValue(WeatherResource.loading((WeatherResponse)null));
            cachedWeather.addSource(source, new Observer<WeatherResource<WeatherResponse>>() {
                @Override
                public void onChanged(WeatherResource<WeatherResponse> weatherResponseWeatherResource) {
                    cachedWeather.setValue(weatherResponseWeatherResource);
                    cachedWeather.removeSource(source);
                }
            });
          }
    }

    public int getWeatherPosition() {
        return weatherPosition;
    }

    public void setWeatherPosition(int weatherPosition) {
        this.weatherPosition = weatherPosition;
    }

    public LiveData<WeatherResource<WeatherResponse>> observeWeather(){
        return cachedWeather;
    }


}
