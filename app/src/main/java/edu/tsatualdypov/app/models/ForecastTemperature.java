package edu.tsatualdypov.app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastTemperature {
    @JsonProperty("min")
    private Double tempMin;

    @JsonProperty("max")
    private Double tempMax;

    public Double getMaxTemperature() {
        return this.tempMax;
    }

    public Double getMinTemperature() {
        return this.tempMin;
    }
    
    public ForecastTemperature() { }
}
