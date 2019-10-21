package com.project.simoneconigliaro.weatherapp.ui.detail;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.project.simoneconigliaro.weatherapp.R;
import com.project.simoneconigliaro.weatherapp.models.WeatherResponse;
import com.project.simoneconigliaro.weatherapp.ui.forecastlist.WeatherResource;
import com.project.simoneconigliaro.weatherapp.util.Constants;
import com.project.simoneconigliaro.weatherapp.util.DateUtils;
import com.project.simoneconigliaro.weatherapp.util.WeatherIcons;
import com.project.simoneconigliaro.weatherapp.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class DetailActivity extends DaggerAppCompatActivity {

    private static final String TAG = "DetailActivity";

    private DetailViewModel viewModel;

    @Inject
    ViewModelProviderFactory providerFactory;

    private TextView locationTextView, dayTextView, descriptionTextView,
            sunriseTextView, sunsetTextView,
            temperatureTextView, tempMinTextView, tempMaxTextView,
            humidityTextView, pressureTextView, windTextView;

    private ImageView weatherIconImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getReferenceWidgets();

        viewModel = ViewModelProviders.of(this, providerFactory).get(DetailViewModel.class);

        subscribeObservers();
    }


    public void subscribeObservers() {
        viewModel.observeWeather().observe(this, new Observer<WeatherResource<WeatherResponse>>() {
            @Override
            public void onChanged(WeatherResource<WeatherResponse> weatherResponseResource) {
                if (weatherResponseResource != null) {
                    switch (weatherResponseResource.status) {
                        case LOADING: {

                            break;
                        }
                        case SUCCESS: {
                            int dayPosition = viewModel.getDayPosition();
                            setValues(weatherResponseResource.data, dayPosition);

                            break;
                        }
                        case ERROR: {
                            Log.d(TAG, "onChanged: " + weatherResponseResource.message);

                            break;
                        }
                    }


                }
                Log.d(TAG, "onChanged: " + weatherResponseResource.data.getCity().getName());
            }
        });
    }

    private void setValues(WeatherResponse weatherResponse, int dayPosition) {

        String location = weatherResponse.getCity().getName();
        String day = DateUtils.getDayOfTheWeek(weatherResponse.getListDays().get(dayPosition).getDate(), false);
        String description = weatherResponse.getListDays().get(dayPosition).getWeathers().get(0).getDescription();
        String sunrise = DateUtils.getHour(weatherResponse.getListDays().get(dayPosition).getSunrise());
        String sunset = DateUtils.getHour(weatherResponse.getListDays().get(dayPosition).getSunset());
        String temperature = ((int) weatherResponse.getListDays().get(dayPosition).getTemperatures().getTempDay()) + Constants.CELSIUS_SYMBOL;
        String tempMin = ((int) weatherResponse.getListDays().get(dayPosition).getTemperatures().getTempMin()) + Constants.CELSIUS_SYMBOL;
        String tempMax = ((int) weatherResponse.getListDays().get(dayPosition).getTemperatures().getTempMax()) + Constants.CELSIUS_SYMBOL;
        String humidity = weatherResponse.getListDays().get(dayPosition).getHumidity() + Constants.HUMIDIY_SYMBOL;
        String pressure = weatherResponse.getListDays().get(dayPosition).getPressure() + Constants.PRESSURE_UNIT;
        String wind = weatherResponse.getListDays().get(dayPosition).getSpeed() + Constants.WIND_SPEED_UNIT;
        String icon = weatherResponse.getListDays().get(dayPosition).getWeathers().get(0).getIcon();

        locationTextView.setText(location);
        dayTextView.setText(day);
        descriptionTextView.setText(description);
        sunriseTextView.setText(sunrise);
        sunsetTextView.setText(sunset);
        temperatureTextView.setText(temperature);
        tempMinTextView.setText(tempMin);
        tempMaxTextView.setText(tempMax);
        humidityTextView.setText(humidity);
        pressureTextView.setText(pressure);
        windTextView.setText(wind);
        weatherIconImageView.setImageResource(WeatherIcons.getIcon(icon));

    }

    private void getReferenceWidgets() {
        locationTextView = findViewById(R.id.tv_detail_location);
        dayTextView = findViewById(R.id.tv_detail_day);
        descriptionTextView = findViewById(R.id.tv_detail_description);
        sunriseTextView = findViewById(R.id.tv_detail_sunrise);
        sunsetTextView = findViewById(R.id.tv_detail_sunset);
        temperatureTextView = findViewById(R.id.tv_detail_temperature);
        tempMinTextView = findViewById(R.id.tv_detail_temp_min);
        tempMaxTextView = findViewById(R.id.tv_detail_temp_max);
        humidityTextView = findViewById(R.id.tv_detail_humidity);
        pressureTextView = findViewById(R.id.tv_detail_pressure);
        windTextView = findViewById(R.id.tv_detail_wind);
        weatherIconImageView = findViewById(R.id.iv_detail_weather_icon);
    }

}
