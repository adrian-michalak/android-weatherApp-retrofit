package com.example.adrianmichalak.myweatherapp.DataBase;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name=DataBase.NAME,version = DataBase.VERSION)
public class DataBase {

    public static final String NAME="AppDataBase";
    public static final int VERSION = 1; //gdy zmiana to podnosimy o 1
}
