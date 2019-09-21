package com.project.simoneconigliaro.weatherapp.ui.forecastlist;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.project.simoneconigliaro.weatherapp.models.Weather;
import com.project.simoneconigliaro.weatherapp.models.WeatherResponse;
import com.project.simoneconigliaro.weatherapp.network.WeatherApi;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ForecastListViewModel extends ViewModel {

    private static final String TAG = "ForecastListViewModel";

    private final WeatherApi weatherApi;

    private MediatorLiveData<WeatherResource<WeatherResponse>> weather = new MediatorLiveData<>();

    @Inject
    public ForecastListViewModel(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
        Log.d(TAG, "ForecastListViewModel: viewmodel is working...");
    }

    public void getWeather() {
        weather.setValue(WeatherResource.loading((WeatherResponse)null));
        final LiveData<WeatherResource<WeatherResponse>> source =  LiveDataReactiveStreams.fromPublisher(
                weatherApi.getWeather("london", "6", "994a22ffdd7639fa94b55ec8a12f7106")
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
                                if(weatherResponse.getInfo() == -1) {
                                    return WeatherResource.error("Error retrieving data", (WeatherResponse)null);
                                }
                                return WeatherResource.success(weatherResponse);
                            }
                        })

                .subscribeOn(Schedulers.io())
        );

        weather.addSource(source, new Observer<WeatherResource<WeatherResponse>>() {
            @Override
            public void onChanged(WeatherResource<WeatherResponse> weatherResponse) {
                weather.setValue(weatherResponse);
                weather.removeSource(source);
            }
        });
    }

    public LiveData<WeatherResource<WeatherResponse>> observeWeather() {
        return weather;
    }




}
