package edu.tsatualdypov.app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("main")
    private String main;

    @JsonProperty("description")
    private String description;

    public Integer getWeatherID() {
        return this.id;
    }

    public String getWeatherName() {
        return this.main;
    }

    public String getWeatherDescription() {
        return this.description;
    }
    
    public Weather() { }
}
