package edu.tsatualdypov.app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForecastData {
    @JsonProperty("list")
    private ForecastDay[] list;

    public ForecastDay[] getList() {
        return this.list;
    }

    public ForecastData() { }
}
