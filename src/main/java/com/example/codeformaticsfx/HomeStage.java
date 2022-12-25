package com.example.codeformaticsfx;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeStage extends Application {

    @Override
    public void start(Stage primaryStage) {
    try {
       Parent parentRoot = FXMLLoader.load(getClass().getResource("HomeStage.fxml")); //connect fxml file with root
        Scene scene = new Scene(parentRoot);        //set root to scene
        String css = this.getClass().getResource("HomeStage.css").toExternalForm(); //connect css file to scene
        scene.getStylesheets().add(css);
        primaryStage.setTitle("HomeStage");     //title of window
        primaryStage.setResizable(false);       //not resiazable
        primaryStage.setFullScreen(true);       //fullscreen
        primaryStage.setScene(scene);           //setscene to stage

        primaryStage.show();                    //show stage
    }
    catch (Exception e) {
        e.printStackTrace();
    }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
