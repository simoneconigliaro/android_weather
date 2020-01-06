package com.project.simoneconigliaro.weatherapp.util;

import com.project.simoneconigliaro.weatherapp.R;

public class WeatherBackground {

    public static int getGradientBackground(String code) {
        switch (code) {
            case "01d":
                return R.drawable.gradient_clear_sky_sun;

            case "01n":
                return R.drawable.gradient_clear_sky_night;

            case "02d":
                return R.drawable.gradient_few_clouds_sun;

            case "02n":
                return R.drawable.gradient_few_clouds_night;

            case "03d":
            case "03n":
            case "04d":
            case "04n":
                return R.drawable.gradient_scattered_clouds;

            case "09d":
            case "09n":
                return R.drawable.gradient_shower_rain;

            case "10d":
                return R.drawable.gradient_rain_sun;

            case "10n":
                return R.drawable.gradient_rain_night;

            case "11d":
                return R.drawable.gradient_thunderstorm_sun;

            case "11n":
                return R.drawable.gradient_thunderstorm_night;

            case "13d":
                return R.drawable.gradient_snow_sun;

            case "13n":
                return R.drawable.gradient_snow_night;

            case "50d":
                return R.drawable.gradient_mist_sun;

            case "50n":
                return R.drawable.gradient_mist_night;

            default:
                return R.drawable.gradient_unknown_weather;
        }
    }
}


