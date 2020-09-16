package com.example.weather4you.api;

import android.location.Location;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherApi {

    public static final String API_KEY = "get your api key from https://openweathermap.org";
    public static Location current_Location = null;

    public static String convertUnixToDate(long dt) {
        Date date = new Date(dt * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm yyyy-MM-dd");
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }

    public static String convertUnixToHour(long time) {
        Date date = new Date(time * 1000L);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String formattedTime = dateFormat.format(date);
        return formattedTime;
    }
}
