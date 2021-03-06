package com.example.weatherapp.network

import com.example.weatherapp.helpers.Constants
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

// TODO could make this a Singleton too, add dependency injection
class RetrofitClient {
    fun createWeatherService(): WeatherService {
        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retrofit.create(WeatherService::class.java)
    }
}