package com.example.weatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.models.WeatherData
import com.example.weatherapp.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO add dependency injection here, make this a Singleton
class WeatherViewModel : ViewModel() {

    private val _forecast = MutableLiveData<WeatherData>()
    val forecast: LiveData<WeatherData> get() = _forecast

    fun retrieveWeatherForCity(cityName: String) {
        // TODO could create repository
        RetrofitClient()
            .createWeatherService()
            .getWeatherForCity(cityName)
            .enqueue(object : Callback<WeatherData> {
                override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                    // TODO error handling, internal server error
                }

                override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                    if (response.isSuccessful) {
                        _forecast.postValue(response.body())
                    } else {
                        _forecast.postValue(null)
                       // TODO error handling, response comes back successful, but code shows error
                    }
                }
            })
    }
}