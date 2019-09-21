package com.project.simoneconigliaro.weatherapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    private transient int info; //if -1 error

    private City city;

    @SerializedName("list")
    private List<Day> listDays;

    public City getCity() {
        return city;
    }

    public List<Day> getListDays() {
        return listDays;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }
}
