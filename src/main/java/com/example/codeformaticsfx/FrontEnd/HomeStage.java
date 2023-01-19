package com.example.codeformaticsfx.FrontEnd;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

public class HomeStage extends Application {

    @Override
    public void start(Stage primaryStage) {
    try {
       Parent parentRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeStage.fxml"))); //connect fxml file with root
        Scene scene = new Scene(parentRoot);    //set root to scene
        String css = Objects.requireNonNull(this.getClass().getResource("HomeStage.css")).toExternalForm(); //connect css file to scene
        scene.getStylesheets().add(css);
        primaryStage.setTitle("HomeStage");     //title of window
        primaryStage.setResizable(false);     //not resiazable
        primaryStage.setMaximized(true);        //fullscreen
        primaryStage.setScene(scene);           //setscene to stage

        primaryStage.show();                    //show stage

        SceneController sceneController = new SceneController();
        sceneController.setPrimaryStage(primaryStage, scene);         //Passing the primaryStage to SceneController to switch scenes without creating a new Stage avoiding window size issues

    }
    catch (Exception e) {
        e.printStackTrace();
    }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
