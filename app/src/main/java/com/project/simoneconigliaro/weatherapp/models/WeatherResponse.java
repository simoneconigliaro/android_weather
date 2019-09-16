package com.project.simoneconigliaro.weatherapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    private City city;

    @SerializedName("list")
    private List<Day> listDays;

    public City getCity() {
        return city;
    }

    public List<Day> getListDays() {
        return listDays;
    }
}
