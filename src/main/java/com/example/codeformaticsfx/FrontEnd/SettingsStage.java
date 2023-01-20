package com.example.codeformaticsfx.FrontEnd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class SettingsStage extends Application {

    @Override
    public void start(Stage SettingsStage) {

        try {
            Parent parentRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SettingsStage.fxml"))); //connect fxml file with root
            Scene scene = new Scene(parentRoot);        //set root to scene
            String css = Objects.requireNonNull(this.getClass().getResource("SettingsStage.css")).toExternalForm(); //connect css file to scene
            scene.getStylesheets().add(css);
            SettingsStage.setTitle("SettingsStage");     //title of window
            SettingsStage.setResizable(false);       //not resiazable
            SettingsStage.setMaximized(true);       //fullscreen
            SettingsStage.setScene(scene);           //setscene to stage
            SettingsStage.show();                    //show stage
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
