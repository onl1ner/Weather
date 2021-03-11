package edu.tsatualdypov.app.services.network;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import edu.tsatualdypov.app.models.ForecastData;
import edu.tsatualdypov.app.models.WeatherData;

public interface IWeatherService {
    ForecastData getForecastWeather(String city) throws InterruptedException, ExecutionException, JsonMappingException, JsonProcessingException, IOException;
    WeatherData getCurrentWeather(String city) throws InterruptedException, ExecutionException, JsonMappingException, JsonProcessingException, IOException;
    
    void closeConnection();
}