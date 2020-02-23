package com.example.adrianmichalak.myweatherapp.models;

import android.view.View;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Weather {
 @SerializedName("main")
    private String main;
 @SerializedName("description")
    private String description;
 @SerializedName("icon")
    private String icon;

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}