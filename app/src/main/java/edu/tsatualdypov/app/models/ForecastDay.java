package edu.tsatualdypov.app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastDay {
    @JsonProperty("dt")
    private Integer dt;

    @JsonProperty("temp")
    private ForecastTemperature temp;

    public Integer getDate() {
        return this.dt;
    }

    public ForecastTemperature getTemperature() {
        return this.temp;
    }

    public ForecastDay() { }
}
