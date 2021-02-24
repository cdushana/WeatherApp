package com.example.weatherapp

import com.example.weatherapp.network.RetrofitClient
import com.example.weatherapp.network.WeatherService
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class ServiceTest {
    @Mock
    lateinit var weatherService: WeatherService

    @Before
    fun setUp() {
        weatherService = RetrofitClient().createWeatherService()
    }

    @Test
    fun test_successfulResponse() {
        val response = weatherService.getWeatherForCity("San Diego").execute()
        assertEquals(response.code(), 200)
        assertEquals(true, response.isSuccessful)
    }

    @Test
    fun test_errorResponse() {
        val response = weatherService.getWeatherForCity("not_a_city").execute()
        assertEquals(response.code(), 404)
    }
}