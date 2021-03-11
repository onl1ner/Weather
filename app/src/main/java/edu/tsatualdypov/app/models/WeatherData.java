package edu.tsatualdypov.app.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    @JsonProperty("name")
    private String city;

    @JsonProperty("dt")
    private Integer dt;

    @JsonProperty("main")
    private Temperature main;

    @JsonProperty("weather")
    private List<Weather> weather;

    public String getCity() {
        return this.city;
    }

    public Double getTemperature() {
        return this.main.getTemperature();
    }

    public Integer getWeatherID() {
        return this.weather.get(0).getWeatherID();
    }

    public String getWeatherName() {
        return this.weather.get(0).getWeatherName();
    }

    public WeatherData() { }
}
