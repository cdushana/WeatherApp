package com.example.weatherapp.models

import com.squareup.moshi.Json

data class WeatherData(
    @Json(name = "list") val list: List<WeatherItem>? = null,
    @Json(name = "city") val city: City? = null
)

data class City(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "coord") val coord: Coordinates? = null,
    @Json(name = "country") val country: String? = null,
    @Json(name = "population") val population: Int? = null,
    @Json(name = "timezone") val timezone: Int? = null,
    @Json(name = "sunrise") val sunrise: Int? = null,
    @Json(name = "sunset") val sunset: Int? = null
)

data class Coordinates(
    @Json(name = "lat") val lat: Float? = null,
    @Json(name = "lon") val lon: Float? = null
)

data class WeatherItem(
    @Json(name = "dt") val dt: Int? = null,
    @Json(name = "main") val main: MainWeatherObject? = null,
    @Json(name = "weather") val weather: List<Weather>? = null,
    @Json(name = "clouds") val clouds: Cloud? = null,
    @Json(name = "wind") val wind: Wind? = null,
    @Json(name = "sys") val sys: Sys? = null,
    @Json(name = "dt_txt") val dt_txt: String? = null
)

data class MainWeatherObject(
    @Json(name = "temp") val temp: Float? = null,
    @Json(name = "feels_like") val feels_like: Float? = null,
    @Json(name = "temp_min") val temp_min: Float? = null,
    @Json(name = "temp_max") val temp_max: Float? = null,
    @Json(name = "pressure") val pressure: Int? = null,
    @Json(name = "sea_level") val sea_level: Int? = null,
    @Json(name = "grid_level") val grid_level: Int? = null,
    @Json(name = "humidity") val humidity: Int? = null,
    @Json(name = "temp_kf") val temp_kf: Float? = null
)

data class Weather(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "main") val main: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "icon") val icon: String? = null
)

data class Cloud(
    @Json(name = "all") val all: Int? = null
)

data class Wind(
    @Json(name = "speed") val speed: Float? = null,
    @Json(name = "deg") val deg: Int? = null
)

data class Sys(
    @Json(name = "pod") val pod: String? = null
)