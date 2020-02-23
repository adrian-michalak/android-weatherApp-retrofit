package com.example.adrianmichalak.myweatherapp.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rest {
    private static RestInterface serviceRest;
    private static OkHttpClient okHttpClient; // zapewnia ze dla kazdego zapytania do API bedziemy miec jednego klienta
    private static Gson gson;
    public static final String BASE_URL = "http://api.openweathermap.org";

    private Rest(){}

    public static RestInterface getRest() {return serviceRest;}

    public static void init()
    {

        gson = new GsonBuilder().create();
        okHttpClient=new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
        serviceRest = retrofit.create(RestInterface.class);
    }
}
