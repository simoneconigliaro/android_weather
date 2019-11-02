package com.project.simoneconigliaro.weatherapp.ui.forecastlist;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.ViewModel;

import com.project.simoneconigliaro.weatherapp.BuildConfig;
import com.project.simoneconigliaro.weatherapp.SessionManager;
import com.project.simoneconigliaro.weatherapp.models.WeatherResponse;
import com.project.simoneconigliaro.weatherapp.network.WeatherApi;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ForecastListViewModel extends ViewModel {

    private static final String TAG = "ForecastListViewModel";

    public static final String API_KEY = BuildConfig.OPEN_WEATHER_API_KEY;

    private final WeatherApi weatherApi;
    private SessionManager sessionManager;
    private String location;

    public static final String CELSIUS_UNITS = "metric";



    @Inject
    public ForecastListViewModel(WeatherApi weatherApi, SessionManager sessionManager) {
        this.weatherApi = weatherApi;
        this.sessionManager = sessionManager;
        Log.d(TAG, "ForecastListViewModel: viewmodel is working...");
    }

    public void getWeatherFromLocation(String location) {
        setLocation(location);
        sessionManager.getWeather(getWeatherResponseFromLocation(location));
    }

    public void getWeatherFromLatLon(String lat, String lon) {
        sessionManager.getWeather(getWeatherResponseFromLatLon(lat, lon));
    }

    private LiveData<WeatherResource<WeatherResponse>> getWeatherResponseFromLocation(String location) {
        return LiveDataReactiveStreams.fromPublisher(
                weatherApi.getWeatherFromLocation(location, "10", CELSIUS_UNITS, API_KEY)
                        .onErrorReturn(new Function<Throwable, WeatherResponse>() {
                            @Override
                            public WeatherResponse apply(Throwable throwable) throws Exception {
                                WeatherResponse weatherResponse = new WeatherResponse();
                                weatherResponse.setInfo(-1);
                                return weatherResponse;
                            }
                        })
                        .map(new Function<WeatherResponse, WeatherResource<WeatherResponse>>() {
                            @Override
                            public WeatherResource<WeatherResponse> apply(WeatherResponse weatherResponse) throws Exception {
                                if (weatherResponse.getInfo() == -1) {
                                    return WeatherResource.error("Error retrieving data", (WeatherResponse) null);
                                }
                                Log.d(TAG, "getWeatherResponse: DATA SUCCESS");
                                return WeatherResource.success(weatherResponse);
                            }
                        })

                        .subscribeOn(Schedulers.io())
        );

    }

    private LiveData<WeatherResource<WeatherResponse>> getWeatherResponseFromLatLon(String lat, String lon) {
        return LiveDataReactiveStreams.fromPublisher(
                weatherApi.getWeatherFromLatLon(lat, lon, "10", CELSIUS_UNITS, API_KEY)
                        .onErrorReturn(new Function<Throwable, WeatherResponse>() {
                            @Override
                            public WeatherResponse apply(Throwable throwable) throws Exception {
                                WeatherResponse weatherResponse = new WeatherResponse();
                                weatherResponse.setInfo(-1);
                                return weatherResponse;
                            }
                        })
                        .map(new Function<WeatherResponse, WeatherResource<WeatherResponse>>() {
                            @Override
                            public WeatherResource<WeatherResponse> apply(WeatherResponse weatherResponse) throws Exception {
                                if (weatherResponse.getInfo() == -1) {
                                    return WeatherResource.error("Error retrieving data", (WeatherResponse) null);
                                }
                                Log.d(TAG, "getWeatherResponse: DATA SUCCESS");
                                return WeatherResource.success(weatherResponse);
                            }
                        })

                        .subscribeOn(Schedulers.io())
        );

    }

    public LiveData<WeatherResource<WeatherResponse>> observeWeather() {
        return sessionManager.observeWeather();
    }

    public void setDayPosition(int dayPosition) {
        sessionManager.setDayPosition(dayPosition);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
