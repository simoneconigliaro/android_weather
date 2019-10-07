package com.project.simoneconigliaro.weatherapp.di.detail;

import androidx.lifecycle.ViewModel;

import com.project.simoneconigliaro.weatherapp.di.ViewModelKey;
import com.project.simoneconigliaro.weatherapp.ui.detail.DetailViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class DetailViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel.class)
    public abstract ViewModel bindForecastListViewModel(DetailViewModel viewModel);
}
