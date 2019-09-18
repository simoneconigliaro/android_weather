package com.project.simoneconigliaro.weatherapp.di.forecastlist;

import com.project.simoneconigliaro.weatherapp.network.WeatherApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class ForecastListModule {

    @Provides
    static WeatherApi provideWeatherApi(Retrofit retrofit){
        return retrofit.create(WeatherApi.class);

    }
}
