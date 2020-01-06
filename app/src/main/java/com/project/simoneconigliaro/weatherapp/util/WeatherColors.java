package com.project.simoneconigliaro.weatherapp.util;

import android.graphics.Color;

public class WeatherColors {

    public static int getColor(String code) {
        switch (code) {
            case "01d":
                return Color.parseColor("#f6366d");

            case "01n":
                return Color.parseColor("#000e33");

            case "02d":
                return Color.parseColor("#00a2ff");

            case "02n":
                return Color.parseColor("#00195b");

            case "03d":
            case "03n":
            case "04d":
            case "04n":
                return Color.parseColor("#0b436f");

            case "09d":
            case "09n":
                return Color.parseColor("#00aed3");

            case "10d":
                return Color.parseColor("#00a2ff");

            case "10n":
                return Color.parseColor("#003165");

            case "11d":
                return Color.parseColor("#890063");

            case "11n":
                return Color.parseColor("#890063");

            case "13d":
                return Color.parseColor("#49a7ff");

            case "13n":
                return Color.parseColor("#002587");

            case "50d":
                return Color.parseColor("#ff9600");

            case "50n":
                return Color.parseColor("#d8d8d8");

            default:
                return Color.parseColor("#a8a8a8");
        }
    }
}


