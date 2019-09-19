package com.project.simoneconigliaro.weatherapp.ui.forecastlist;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.project.simoneconigliaro.weatherapp.models.Weather;
import com.project.simoneconigliaro.weatherapp.models.WeatherResponse;
import com.project.simoneconigliaro.weatherapp.network.WeatherApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ForecastListViewModel extends ViewModel {

    private static final String TAG = "ForecastListViewModel";

    private final WeatherApi weatherApi;

    @Inject
    public ForecastListViewModel(WeatherApi weatherApi) {

        this.weatherApi = weatherApi;

        Log.d(TAG, "ForecastListViewModel: viewmodel is working...");

        weatherApi.getWeather("london", "6", "994a22ffdd7639fa94b55ec8a12f7106")
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<WeatherResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WeatherResponse weatherResponse) {
                        Log.d(TAG, "onNext: " + weatherResponse.getCity().getName());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: ", e);

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
