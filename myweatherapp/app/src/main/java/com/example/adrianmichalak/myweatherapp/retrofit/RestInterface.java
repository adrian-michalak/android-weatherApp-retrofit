package com.example.adrianmichalak.myweatherapp.retrofit;

import com.example.adrianmichalak.myweatherapp.models.Weather;
import com.example.adrianmichalak.myweatherapp.models.WeatherResult;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestInterface {
    //@GET("weather") Observable<WeatherResult> getWeatherByLatLng(@Query("lat") String lat, @Query("lon") String lon,
                                                             //  @Query("appid") String appid, @Query("units") String units );
    @GET("/data/2.5/weather")
    Call<WeatherResult> getWeatherByCity(@Query("q") String city,
                                         @Query("appid") String appid,
                                         @Query("units") String units );
    @GET("/data/2.5/weather")
    Call<List<WeatherResult>> getWeather(@Query("q") String city,
                                   @Query("appid") String appid,
                                   @Query("units") String units );

}
