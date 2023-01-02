package com.example.codeformaticsfx.FrontEnd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Game extends Application {

    @Override
    public void start(Stage Game) {

        try {
            Parent parentRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Game.fxml"))); //connect fxml file with root
            Scene scene = new Scene(parentRoot);        //set root to scene
            String css = Objects.requireNonNull(this.getClass().getResource("Game.css")).toExternalForm(); //connect css file to scene
            scene.getStylesheets().add(css);
            Game.setTitle("Game");     //title of window
            Game.setResizable(false);       //not resiazable
            Game.setMaximized(true);       //fullscreen
            Game.setScene(scene);           //setscene to stage


            Game.show();                    //show stage
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
