package com.project.simoneconigliaro.weatherapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Day {

    @SerializedName("temp")
    private List<Weather> weathers;

    @SerializedName("dt")
    private long date;

    private double sunrise;

    private double sunset;

    @SerializedName("temp")
    private List<Temperature> temperatures;

    private double pressure;

    private double humidity;

    private double speed;

}
