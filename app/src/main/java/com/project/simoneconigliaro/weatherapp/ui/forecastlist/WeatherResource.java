package com.project.simoneconigliaro.weatherapp.ui.forecastlist;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WeatherResource<T> {

    @NonNull
    public final WeatherStatus status;

    @Nullable
    public final T data;

    @Nullable
    public final String message;


    public WeatherResource(@NonNull WeatherStatus status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> WeatherResource<T> success(@Nullable T data) {
        return new WeatherResource<>(WeatherStatus.SUCCESS, data, null);
    }

    public static <T> WeatherResource<T> error(@NonNull String msg, @Nullable T data) {
        return new WeatherResource<>(WeatherStatus.ERROR, data, msg);
    }

    public static <T> WeatherResource<T> loading(@Nullable T data) {
        return new WeatherResource<>(WeatherStatus.LOADING, data, null);
    }

    public enum WeatherStatus {SUCCESS, ERROR, LOADING}

}
