package com.project.simoneconigliaro.weatherapp.network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WeatherApi {

    @GET()
    Call<ResponseBody> getFakeStuff();

}
