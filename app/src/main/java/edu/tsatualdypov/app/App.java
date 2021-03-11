package edu.tsatualdypov.app;

import edu.tsatualdypov.app.services.network.WeatherService;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("/view/Mainframe.fxml"));
        
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        File imageFile = new File("src/main/resources/images/icon/weather.png");
        Image logo = new Image(imageFile.toURI().toString());
        
        primaryStage.setMinWidth(1024.0);
        primaryStage.setMinHeight(768.0);
        
        primaryStage.getIcons().add(logo);
        primaryStage.setTitle("Weather");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        WeatherService.shared.closeConnection();
    }

    public static void main(String[] args) {
        Application.launch();
    }

}