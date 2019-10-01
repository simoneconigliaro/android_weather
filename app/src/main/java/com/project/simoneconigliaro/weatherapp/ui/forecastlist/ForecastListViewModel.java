package com.project.simoneconigliaro.weatherapp.ui.forecastlist;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.project.simoneconigliaro.weatherapp.SessionManager;
import com.project.simoneconigliaro.weatherapp.models.WeatherResponse;
import com.project.simoneconigliaro.weatherapp.network.WeatherApi;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ForecastListViewModel extends ViewModel {

    private static final String TAG = "ForecastListViewModel";

    private final WeatherApi weatherApi;
    private SessionManager sessionManager;

    @Inject
    public ForecastListViewModel(WeatherApi weatherApi, SessionManager sessionManager) {
        this.weatherApi = weatherApi;
        this.sessionManager = sessionManager;
        Log.d(TAG, "ForecastListViewModel: viewmodel is working...");
    }

    public void getWeather() {
        if(observeWeather().getValue() == null) {
            Log.d(TAG, "getWeather: weather is null");
            sessionManager.getWeather(getWeatherResponse());
        }
    }

    private LiveData<WeatherResource<WeatherResponse>> getWeatherResponse(){
        return LiveDataReactiveStreams.fromPublisher(
                weatherApi.getWeather("london", "10", "metric", "994a22ffdd7639fa94b55ec8a12f7106")
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

    public void setWeatherPosition(int position){
        sessionManager.setWeatherPosition(position);
    }

}
