package com.project.simoneconigliaro.weatherapp.di.forecastlist;

import com.project.simoneconigliaro.weatherapp.network.WeatherApi;
import com.project.simoneconigliaro.weatherapp.ui.forecastlist.ForecastAdapter;
import com.project.simoneconigliaro.weatherapp.ui.forecastlist.ForecastListActivity;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ForecastListModule {

    @ForecastListScope
    @Provides
    static ForecastAdapter provideForecastAdapter(ForecastAdapter.OnClickHandler onClickHandler) {
        return new ForecastAdapter(onClickHandler);
    }

    @ForecastListScope
    @Provides
    ForecastAdapter.OnClickHandler provideForecastAdapterOnClickHandler(ForecastListActivity forecastListActivity) {
        return forecastListActivity;
    }

    @ForecastListScope
    @Provides
    static WeatherApi provideWeatherApi(Retrofit retrofit) {
        return retrofit.create(WeatherApi.class);
    }

}
