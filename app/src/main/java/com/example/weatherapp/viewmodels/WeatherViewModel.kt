package com.example.weatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.models.WeatherData
import com.example.weatherapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherViewModel : ViewModel() {

    private val _forecast = MutableLiveData<WeatherData>()
    val forecast: LiveData<WeatherData> get() = _forecast

    fun retrieveWeatherForCity(cityName: String) {
        RetrofitClient()
            .createWeatherService()
            .getWeatherForCity(cityName)
            .enqueue(object : Callback<WeatherData> {
                override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                    println("city not found")
                }

                override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                    if (response.isSuccessful) {
                        _forecast.postValue(response.body())
                    } else {
                        println("Failure")
                        // error case
//                        when (response.code()) {
//                            404 ->
//                            500 ->
//                            else ->

//                        }
                    }
                }
            })
    }
}