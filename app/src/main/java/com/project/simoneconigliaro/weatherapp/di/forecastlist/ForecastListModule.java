package com.project.simoneconigliaro.weatherapp.di.forecastlist;

import android.app.Application;
import android.view.View;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.project.simoneconigliaro.weatherapp.network.WeatherApi;
import com.project.simoneconigliaro.weatherapp.ui.forecastlist.ForecastAdapter;
import com.project.simoneconigliaro.weatherapp.ui.forecastlist.ForecastListActivity;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ForecastListModule {

    @Provides
    static ForecastAdapter provideForecastAdapter(ForecastAdapter.OnClickHandler onClickHandler) {
        return new ForecastAdapter(onClickHandler);
    }

    @Provides
    ForecastAdapter.OnClickHandler provideForecastAdapterOnClickHandler(ForecastListActivity forecastListActivity) {
        return forecastListActivity;
    }

    @Provides
    static WeatherApi provideWeatherApi(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }
}
