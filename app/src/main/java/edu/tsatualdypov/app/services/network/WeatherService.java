package edu.tsatualdypov.app.services.network;

import edu.tsatualdypov.app.common.Constants;
import edu.tsatualdypov.app.common.Future;
import edu.tsatualdypov.app.models.ForecastData;
import edu.tsatualdypov.app.models.WeatherData;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;

public class WeatherService implements IWeatherService {
    
    public static final IWeatherService shared = new WeatherService();

    private OkHttpClient client;

    public ForecastData getForecastWeather(String city) throws InterruptedException, ExecutionException, JsonMappingException, JsonProcessingException, IOException {
        HttpUrl.Builder builder = HttpUrl.parse(Constants.baseURL + "/forecast/daily").newBuilder();

        builder.addQueryParameter("q", city);
        builder.addQueryParameter("cnt", "8");
        builder.addQueryParameter("units", "metric");
        builder.addQueryParameter("appid", Constants.apiKey);

        String url = builder.build().toString();

        Future future = new Future();

        Request request = new Request
            .Builder()
            .url(url)
            .build();

        this.client.newCall(request).enqueue(future);

        ObjectMapper mapper = new ObjectMapper();
        ResponseBody body = future.get().body();

        return mapper.readValue(body.string(), ForecastData.class);
    }
    
    public WeatherData getCurrentWeather(String city) throws InterruptedException, ExecutionException, JsonMappingException, JsonProcessingException, IOException {
        HttpUrl.Builder builder = HttpUrl.parse(Constants.baseURL + "/weather").newBuilder();
        
        builder.addQueryParameter("q", city);
        builder.addQueryParameter("units", "metric");
        builder.addQueryParameter("appid", Constants.apiKey);

        String url = builder.build().toString();

        Future future = new Future();

        Request request = new Request
            .Builder()
            .url(url)
            .build();

        this.client.newCall(request).enqueue(future);

        ObjectMapper mapper = new ObjectMapper();
        ResponseBody body = future.get().body();
        
        return mapper.readValue(body.string(), WeatherData.class);
    }

    public void closeConnection() {
        this.client.connectionPool().evictAll();
        this.client.dispatcher().executorService().shutdown();
    }
    
    private WeatherService() { 
        this.client = new OkHttpClient();
    }
}
