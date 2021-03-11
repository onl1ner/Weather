package edu.tsatualdypov.app.controllers;

import edu.tsatualdypov.app.models.ForecastData;
import edu.tsatualdypov.app.models.ForecastDay;
import edu.tsatualdypov.app.models.WeatherData;
import edu.tsatualdypov.app.services.network.WeatherService;
import edu.tsatualdypov.app.ui.WeatherImage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

public class WeatherController {
    
    @FXML private HBox weatherContentHBox;
    @FXML private HBox loadingStateHBox;

    @FXML private Label cityLabel;

    @FXML private Label currentWeatherLabel;
    @FXML private Label currentTemperatureLabel;

    @FXML private Label firstWeekDayLabel;
    @FXML private Label firstWeekDayMaxTemperatureLabel;
    @FXML private Label firstWeekDayMinTemperatureLabel;

    @FXML private Label secondWeekDayLabel;
    @FXML private Label secondWeekDayMaxTemperatureLabel;
    @FXML private Label secondWeekDayMinTemperatureLabel;

    @FXML private Label thirdWeekDayLabel;
    @FXML private Label thirdWeekDayMaxTemperatureLabel;
    @FXML private Label thirdWeekDayMinTemperatureLabel;

    @FXML private Label fourthWeekDayLabel;
    @FXML private Label fourthWeekDayMaxTemperatureLabel;
    @FXML private Label fourthWeekDayMinTemperatureLabel;

    @FXML private Label fifthWeekDayLabel;
    @FXML private Label fifthWeekDayMaxTemperatureLabel;
    @FXML private Label fifthWeekDayMinTemperatureLabel;

    @FXML private Label sixthWeekDayLabel;
    @FXML private Label sixthWeekDayMaxTemperatureLabel;
    @FXML private Label sixthWeekDayMinTemperatureLabel;

    @FXML private Label seventhWeekDayLabel;
    @FXML private Label seventhWeekDayMaxTemperatureLabel;
    @FXML private Label seventhWeekDayMinTemperatureLabel;

    @FXML private ImageView weatherImageView;

    @FXML private TextField searchCityField;

    private Label[] weekDayLabels;
    private Label[] weekDayMaxTemperatureLabels;
    private Label[] weekDayMinTemperatureLabels;

    private void getData(String city) {
        this.showLoading();

        try {
            WeatherData weather = WeatherService.shared.getCurrentWeather(city);
            ForecastData forecast = WeatherService.shared.getForecastWeather(city);

            if (weather != null) {
                this.setCurrentData(weather);
                this.setForecastData(forecast);
                this.hideLoading();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private void setCurrentData(WeatherData data) {
        this.weatherImageView.setImage(new WeatherImage(data.getWeatherID()));

        this.cityLabel.setText(data.getCity());
        this.currentWeatherLabel.setText(data.getWeatherName());
        this.currentTemperatureLabel.setText(data.getTemperature().intValue() + "°");
    }

    private void setWeekDayData(Integer index, ForecastDay data) {
        Label weekDayLabel = this.weekDayLabels[index];
        Label weekDayMaxTemperatureLabel = this.weekDayMaxTemperatureLabels[index];
        Label weekDayMinTemperatureLabel = this.weekDayMinTemperatureLabels[index];
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        
        Date date = new Date((long) data.getDate() * 1000);
        
        String weekDay = dateFormat.format(date);
        String maxTemperature = data.getTemperature().getMaxTemperature().intValue() + "°";
        String minTemperature = data.getTemperature().getMinTemperature().intValue() + "°";

        weekDayLabel.setText(weekDay);
        weekDayMaxTemperatureLabel.setText(maxTemperature);
        weekDayMinTemperatureLabel.setText(minTemperature);
    }

    private void setForecastData(ForecastData data) {
        for (Integer index = 1; index < data.getList().length; ++index) {
            ForecastDay day = data.getList()[index];
            this.setWeekDayData(index - 1, day);
        }
    }

    private FadeTransition fade(Node object, Duration duration, double fromValue, double toValue) {
        FadeTransition transition = new FadeTransition(duration, object);

        transition.setFromValue(fromValue);
        transition.setToValue(toValue);

        return transition;
    }

    private void showLoading() {
        FadeTransition showLoading = this.fade(this.loadingStateHBox, Duration.seconds(0.2), 0.0, 1.0);
        FadeTransition hideWeather = this.fade(this.weatherContentHBox, Duration.seconds(0.2), 1.0, 0.0);

        hideWeather.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showLoading.play();
            }
            
        });

        hideWeather.play();
    }

    private void hideLoading() {
        FadeTransition showWeather = this.fade(this.weatherContentHBox, Duration.seconds(0.2), 0.0, 1.0);
        FadeTransition hideLoading = this.fade(this.loadingStateHBox, Duration.seconds(0.2), 1.0, 0.0);

        hideLoading.setDelay(Duration.seconds(1.0));
        hideLoading.setOnFinished(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                showWeather.play();
            }
            
        });
        
        hideLoading.play();
    }
    
    @FXML public void searchCityFieldAction(ActionEvent event) {
        String city = this.searchCityField.getText();

        this.getData(city);

        this.searchCityField.setText("");
        this.loadingStateHBox.requestFocus();
    }

    @FXML public void initialize() {
        // Settings the nodes to the default state 
        // (in case if something were changed in SceneBuilder).
        this.weatherContentHBox.setOpacity(0.0);
        this.loadingStateHBox.setOpacity(1.0);
        
        this.weekDayLabels = new Label[] { this.firstWeekDayLabel, this.secondWeekDayLabel,
                                           this.thirdWeekDayLabel, this.fourthWeekDayLabel,
                                           this.fifthWeekDayLabel, this.sixthWeekDayLabel,
                                           this.seventhWeekDayLabel };

        this.weekDayMaxTemperatureLabels = new Label[] { this.firstWeekDayMaxTemperatureLabel, this.secondWeekDayMaxTemperatureLabel,
                                                         this.thirdWeekDayMaxTemperatureLabel, this.fourthWeekDayMaxTemperatureLabel,
                                                         this.fifthWeekDayMaxTemperatureLabel, this.sixthWeekDayMaxTemperatureLabel,
                                                         this.seventhWeekDayMaxTemperatureLabel };

        this.weekDayMinTemperatureLabels = new Label[] { this.firstWeekDayMinTemperatureLabel, this.secondWeekDayMinTemperatureLabel,
                                                         this.thirdWeekDayMinTemperatureLabel, this.fourthWeekDayMinTemperatureLabel,
                                                         this.fifthWeekDayMinTemperatureLabel, this.sixthWeekDayMinTemperatureLabel,
                                                         this.seventhWeekDayMinTemperatureLabel};

        this.getData("Nur-Sultan");
    }
}
