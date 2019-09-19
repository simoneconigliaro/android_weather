package com.project.simoneconigliaro.weatherapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Day {

    @SerializedName("weather")
    private List<Weather> weathers;

    private double sunrise;

    private double sunset;

    @SerializedName("temp")
    private Temperature temperatures;

    private double pressure;

    private double humidity;

    private double speed;

    @SerializedName("dt")
    private long date;

}
