package com.project.simoneconigliaro.weatherapp.di;

import com.project.simoneconigliaro.weatherapp.di.forecastlist.ForecastListModule;
import com.project.simoneconigliaro.weatherapp.di.forecastlist.ForecastListViewModelsModule;
import com.project.simoneconigliaro.weatherapp.ui.forecastlist.ForecastListActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector(
            modules = {ForecastListViewModelsModule.class, ForecastListModule.class})
    abstract ForecastListActivity contributeForecastListActivity();


}
