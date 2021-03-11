package edu.tsatualdypov.app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature {
    @JsonProperty("temp")
    private Double temperature;

    @JsonProperty("temp_max")
    private Double temperatureMax;

    @JsonProperty("temp_min")
    private Double temperatureMin;

    public Double getTemperature() {
        return this.temperature;
    }

    public Double getMaxTemperature() {
        return this.temperatureMax;
    }

    public Double getMinTemperature() {
        return this.temperatureMin;
    }
    
    public Temperature() { }
}
