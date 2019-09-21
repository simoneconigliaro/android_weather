package com.project.simoneconigliaro.weatherapp.ui.forecastlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.project.simoneconigliaro.weatherapp.R;
import com.project.simoneconigliaro.weatherapp.di.DaggerAppComponent;
import com.project.simoneconigliaro.weatherapp.models.WeatherResponse;
import com.project.simoneconigliaro.weatherapp.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class ForecastListActivity extends DaggerAppCompatActivity {

    private static final String TAG = "ForecastListActivity";

    private ForecastListViewModel viewModel;

    private ProgressBar progressBar;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress_bar);

        viewModel = ViewModelProviders.of(this, providerFactory).get(ForecastListViewModel.class);

        subscribeObservers();

        viewModel.getWeather();
    }

    private void subscribeObservers() {
        viewModel.observeWeather().observe(this, new Observer<WeatherResource<WeatherResponse>>() {
            @Override
            public void onChanged(WeatherResource<WeatherResponse> weatherResourceResponse) {
                if (weatherResourceResponse != null) {
                    switch (weatherResourceResponse.status) {
                        case LOADING: {
                            showProgressBar(true);

                            break;
                        }
                        case SUCCESS: {
                            showProgressBar(false);
                            Log.d(TAG, "onChanged: SUCCESS " + weatherResourceResponse.data.getCity().getName());

                            break;
                        }
                        case ERROR: {
                            showProgressBar(false);
                            Toast.makeText(ForecastListActivity.this, weatherResourceResponse.message, Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }

                }
            }
        });


    }

    private void showProgressBar(boolean isVisible) {
        if (isVisible) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }


}
