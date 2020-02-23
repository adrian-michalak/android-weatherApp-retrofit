package com.example.adrianmichalak.myweatherapp;

import android.annotation.SuppressLint;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adrianmichalak.myweatherapp.models.WeatherResult;
import com.example.adrianmichalak.myweatherapp.retrofit.Rest;
import java.text.DateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private ImageButton imageButtonSearch;
    private ImageView imageViewTemperature;
    private TextView textViewTemp,textViewDate, textViewDescription , textViewCity , textViewHumidity, textViewPressure,textViewTempMax;
    private static final String UNITS = "metric";
    private EditText mEditText;
    private String APIKEY = "74763830b85baa33bb344e85833390a4";

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

       // imageViewPressure = findViewById(R.id.pressure_icon);
        textViewPressure = findViewById(R.id.pressure_tv);

       // imageViewHumidity = findViewById(R.id.humidity_icon);
        textViewHumidity = findViewById(R.id.humidity_tv);

        textViewCity = findViewById(R.id.city_name_tv);

      //  imageViewTempMax = findViewById(R.id.temp_max_icon);
        textViewTempMax = findViewById(R.id.temp_max__tv);
        imageButtonSearch = findViewById(R.id.image_button_search);
        textViewTemp = findViewById(R.id.text_weather_temp);
        imageViewTemperature = findViewById(R.id.imageViewTemperature);

        mEditText = findViewById(R.id.set_city);
        textViewDate = findViewById(R.id.date_tv);
        textViewDescription = findViewById(R.id.description_tv);

        imageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDate();
                fetchData();
            }
        });
    }

    private void fetchData() {

        Rest.getRest().getWeatherByCity(mEditText.getText().toString(),APIKEY,UNITS).enqueue(new Callback<WeatherResult>() {
            @SuppressLint("SetTextI18n")
            public void onResponse(@NonNull Call<WeatherResult> call, @NonNull retrofit2.Response<WeatherResult> response) {

                if(response.body() != null){

                    WeatherResult weatherResult = response.body();
                    String description = weatherResult.getWeatherList().get(0).getDescription();
                    String pressure = weatherResult.getMain().getPressure().toString();
                    String humidity = weatherResult.getMain().getHumidity();
                    String temp_max = weatherResult.getMain().getTemp_max();
                    String icon = weatherResult.getWeatherList().get(0).getIcon();
                    String main = weatherResult.getWeatherList().get(0).getMain();

                    textViewPressure.setText("Pressure"+"\n\n"+pressure + " hpa");
                    textViewHumidity.setText("Humidity"+"\n\n"+humidity + " % ");
                    textViewTempMax.setText("Max "+"\n\n"+temp_max +"  \u2103" );
                    textViewDescription.setText(main);
                    textViewCity.setText(mEditText.getText());
                    textViewTemp.setText(weatherResult.getMain().getTemp() + "  \u2103");
                    setIcon(icon);
                }
                mEditText.setText("");
            }
            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {
                Log.e("ERROR", t.toString());

            }
        });
    }


    private void setIcon(String s){
        if (s.equalsIgnoreCase("01d") || s.equalsIgnoreCase("01n") ){ imageViewTemperature.setImageResource(R.drawable.clear_sky); }
        if (s.equalsIgnoreCase("02d") || s.equalsIgnoreCase("02n") ){ imageViewTemperature.setImageResource(R.drawable.few_clouds); }
        if (s.equalsIgnoreCase("03d") || s.equalsIgnoreCase("03n") ){ imageViewTemperature.setImageResource(R.drawable.scattered_clouds); }
        if (s.equalsIgnoreCase("04d") || s.equalsIgnoreCase("04n") ){ imageViewTemperature.setImageResource(R.drawable.broken_clouds); }
        if (s.equalsIgnoreCase("09d") || s.equalsIgnoreCase("09n") ){ imageViewTemperature.setImageResource(R.drawable.shower_rain); }
        if (s.equalsIgnoreCase("10d") || s.equalsIgnoreCase("10n") ){ imageViewTemperature.setImageResource(R.drawable.rain); }
        if (s.equalsIgnoreCase("11d") || s.equalsIgnoreCase("11n") ){ imageViewTemperature.setImageResource(R.drawable.thunderstorm); }
        if (s.equalsIgnoreCase("13d") || s.equalsIgnoreCase("13n") ){ imageViewTemperature.setImageResource(R.drawable.snow); }
        if (s.equalsIgnoreCase("50d") || s.equalsIgnoreCase("50n") ){ imageViewTemperature.setImageResource(R.drawable.mist); }
    }

    private void getDate(){
        Calendar calendar = Calendar.getInstance();
        String date = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        textViewDate.setText(date);
    }

    private String translateDescription(String s){
        String temp = "";
        if (s.equals("clear sky")){ temp = s.replace(s,"Słonecznie"); }
        if (s.equals("few clouds")) { temp = s.replace(s,"Pochmurnie");}
        if (s.equals("scattered clouds")) { temp = s.replace(s,"Zachmurzenie"); }
        if (s.equals("broken clouds")) { temp = s.replace(s,"Zachmurzenie"); }
        if (s.equals("shower rain")) { temp = s.replace(s,"Lekki deszcz"); }
        if (s.equals("rain")) {temp = s.replace(s,"Deszcz"); }
        if (s.equals("thunderstorm")) {temp = s.replace(s,"Burza"); }
        if (s.equals("snow")) { temp = s.replace(s,"Opady śniegu"); }
        if (s.equals("mist")) { temp = s.replace(s,"Mgła"); }
        System.out.print(temp + "!!!!!!!!!!\n\n ");
        return temp;
    }
}
