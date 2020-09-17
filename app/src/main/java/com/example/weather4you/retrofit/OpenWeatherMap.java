package com.example.weather4you.retrofit;

import com.example.weather4you.model.WeatherForecastResponse;
import com.example.weather4you.model.WeatherResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMap {
    @GET("weather")
    Observable<WeatherResponse> getWeatherByLtLong(@Query("lat") String latitude,
                                                   @Query("lon") String longitude,
                                                   @Query("appid") String apiId,
                                                   @Query("units") String units
    );

    @GET("forecast")
    Observable<WeatherForecastResponse> getWeatherForecastByLtLong(@Query("lat") String latitude,
                                                           @Query("lon") String longitude,
                                                           @Query("appid") String apiId,
                                                           @Query("units") String units
    );
}
