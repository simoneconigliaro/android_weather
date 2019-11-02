package com.project.simoneconigliaro.weatherapp.di.forecastlist;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

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
    static ForecastAdapter provideForecastAdapter(ForecastListActivity forecastListActivity, ForecastAdapter.OnClickHandler onClickHandler) {
        return new ForecastAdapter(forecastListActivity.getApplicationContext(), onClickHandler);
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

    @ForecastListScope
    @Provides
    static SharedPreferences provideSharedPreferences(ForecastListActivity forecastListActivity) {
        return PreferenceManager.getDefaultSharedPreferences(forecastListActivity.getApplicationContext());
    }

    @ForecastListScope
    @Provides
    static String providePrefTempUnit(SharedPreferences sharedPreferences){
        return sharedPreferences.getString("temperature", "metric");
    }

}
