package com.project.simoneconigliaro.weatherapp.di.forecastlist;

import androidx.lifecycle.ViewModel;

import com.project.simoneconigliaro.weatherapp.di.ViewModelKey;
import com.project.simoneconigliaro.weatherapp.ui.forecastlist.ForecastListViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ForecastListViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ForecastListViewModel.class)
    public abstract ViewModel bindForecastListViewModel(ForecastListViewModel viewModel);
}
