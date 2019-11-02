package com.project.simoneconigliaro.weatherapp.util;

import android.content.Context;

public class TemperatureUtils {

    private static int celsiusToFahrenheit(double temperatureInCelsius) {
        int temperatureInFahrenheit = (int) ((temperatureInCelsius * 1.8) + 32);
        return temperatureInFahrenheit;
    }

    public static String formatTemperature(Context context, int temperature) {
        if (!PreferencesUtils.isMetric(context)) {
            temperature = celsiusToFahrenheit(temperature);
            return temperature + Constants.FAHRENHEIT_SYMBOL;
        } else {
            return temperature + Constants.CELSIUS_SYMBOL;
        }
    }
}
