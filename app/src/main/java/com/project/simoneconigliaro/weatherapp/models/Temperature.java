package com.project.simoneconigliaro.weatherapp.models;

import com.google.gson.annotations.SerializedName;

public class Temperature {

    @SerializedName("day")
    private double tempDay;

    @SerializedName("min")
    private double tempMin;

    @SerializedName("max")
    private double tempMax;

    @SerializedName("night")
    private double tempNight;

    @SerializedName("eve")
    private double tempEve;

    @SerializedName("morn")
    private double tempMorn;

    public double getTempDay() {
        return tempDay;
    }

    public void setTempDay(double tempDay) {
        this.tempDay = tempDay;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getTempNight() {
        return tempNight;
    }

    public void setTempNight(double tempNight) {
        this.tempNight = tempNight;
    }

    public double getTempEve() {
        return tempEve;
    }

    public void setTempEve(double tempEve) {
        this.tempEve = tempEve;
    }

    public double getTempMorn() {
        return tempMorn;
    }

    public void setTempMorn(double tempMorn) {
        this.tempMorn = tempMorn;
    }
}
