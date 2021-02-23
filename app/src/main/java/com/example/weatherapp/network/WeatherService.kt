package com.example.weatherapp.network

import com.example.weatherapp.models.WeatherData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("forecast?&appid=65d00499677e59496ca2f318eb68c049&units=imperial")
    fun getWeatherForCity(@Query("q") cityName: String): Call<WeatherData>
}