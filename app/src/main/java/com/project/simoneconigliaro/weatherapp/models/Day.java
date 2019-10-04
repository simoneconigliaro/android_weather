package com.project.simoneconigliaro.weatherapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Day {

    @SerializedName("weather")
    private List<Weather> weathers;

    private long sunrise;

    private long sunset;

    @SerializedName("temp")
    private Temperature temperatures;

    private double pressure;

    private double humidity;

    private double speed;

    @SerializedName("dt")
    private long date;

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }

    public Temperature getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(Temperature temperatures) {
        this.temperatures = temperatures;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
