package com.project.simoneconigliaro.weatherapp.network;

import com.project.simoneconigliaro.weatherapp.models.WeatherResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("forecast/daily")
    Flowable<WeatherResponse> getWeatherFromLocation(@Query("q") String location,
                                                     @Query("cnt") String countDays,
                                                     @Query("units") String unitsType,
                                                     @Query("appid") String appId
    );

    @GET("forecast/daily")
    Flowable<WeatherResponse> getWeatherFromLatLon(@Query("lat") String lat,
                                                   @Query("lon") String lon,
                                                   @Query("cnt") String countDays,
                                                   @Query("units") String unitsType,
                                                   @Query("appid") String appId
    );

}
