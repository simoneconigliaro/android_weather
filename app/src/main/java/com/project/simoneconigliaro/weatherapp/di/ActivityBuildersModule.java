package com.project.simoneconigliaro.weatherapp.di;

import com.project.simoneconigliaro.weatherapp.di.detail.DetailScope;
import com.project.simoneconigliaro.weatherapp.di.detail.DetailViewModelsModule;
import com.project.simoneconigliaro.weatherapp.di.forecastlist.ForecastListModule;
import com.project.simoneconigliaro.weatherapp.di.forecastlist.ForecastListScope;
import com.project.simoneconigliaro.weatherapp.di.forecastlist.ForecastListViewModelsModule;
import com.project.simoneconigliaro.weatherapp.ui.detail.DetailActivity;
import com.project.simoneconigliaro.weatherapp.ui.detail.DetailViewModel;
import com.project.simoneconigliaro.weatherapp.ui.forecastlist.ForecastListActivity;
import com.project.simoneconigliaro.weatherapp.ui.settings.SettingsActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {


    @ForecastListScope
    @ContributesAndroidInjector(
            modules = {ForecastListViewModelsModule.class, ForecastListModule.class})
    abstract ForecastListActivity contributeForecastListActivity();

    @DetailScope
    @ContributesAndroidInjector(
            modules = {DetailViewModelsModule.class})
    abstract DetailActivity contributeDetailActivity();

    @ContributesAndroidInjector
    abstract SettingsActivity contributeSettingsActivity();


}
