package com.project.simoneconigliaro.weatherapp.di;

import com.project.simoneconigliaro.weatherapp.ui.ForecastListActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract ForecastListActivity contributeForecastListActivity();


}
