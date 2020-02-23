package com.example.adrianmichalak.myweatherapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
//====================================
public class WeatherResult {
   @SerializedName("main")
    private Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    //====================================
    @SerializedName("weather")
    private List<Weather> weatherList;

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    //====================================


}
