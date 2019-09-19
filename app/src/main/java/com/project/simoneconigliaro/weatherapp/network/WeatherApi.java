package com.project.simoneconigliaro.weatherapp.network;

import com.project.simoneconigliaro.weatherapp.models.WeatherResponse;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("forecast/daily")
    Flowable<WeatherResponse> getWeather(@Query("q") String location,
                                         @Query("cnt") String countDays,
                                         @Query("appid") String appId
    );

}
