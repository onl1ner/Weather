package edu.tsatualdypov.app.ui;

import java.io.File;

import javafx.scene.image.Image;

public class WeatherImage extends Image {
    
    private static File getFileImage(WeatherImageType type) {
        String path = "app/src/main/resources/images/weather/";

        switch (type) {
            case CLEAR: path += "clear.png"; break;
            case PATRLYCLOUDY: path += "partlyCloudy.png"; break;
            case CLOUDY: path += "cloudy.png"; break;
            case RAIN: path += "rain.png"; break;
            case THUNDER: path += "thunderstorm.png"; break;
            case SNOW: path += "snow.png"; break;
        }

        return new File(path);
    }

    private static File getFileImage(Integer weatherID) {
        WeatherImageType type = WeatherImageType.CLEAR;

        switch (weatherID / 100) {
            case 2: type = WeatherImageType.THUNDER; break;
            case 3: case 5: type = WeatherImageType.RAIN; break;
            case 6: type = WeatherImageType.SNOW; break;
            case 8: type = WeatherImageType.CLEAR; break;
        }

        // Because "Clouds" group is in 80x code :(
        if (weatherID / 100 == 8 && weatherID % 100 != 0) {
            type = WeatherImageType.CLOUDY;
        }

        return WeatherImage.getFileImage(type);
    }

    public WeatherImage(Integer weatherID) {
        super(WeatherImage.getFileImage(weatherID).toURI().toString());
    }

    public WeatherImage(WeatherImageType type) {
        super(WeatherImage.getFileImage(type).toURI().toString());
    }
}
