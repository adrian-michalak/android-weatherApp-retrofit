package com.example.adrianmichalak.myweatherapp;

import android.app.Application;

import com.example.adrianmichalak.myweatherapp.retrofit.Rest;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Rest.init();
        FlowManager.init(new FlowConfig.Builder(this).build());

    }
}
