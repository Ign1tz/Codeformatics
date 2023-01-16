package com.example.codeformaticsfx.FrontEnd;

import com.example.codeformaticsfx.Files.EncodeDecode;
import com.example.codeformaticsfx.Files.readWriteScoreboard;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static javafx.application.Application.launch;

public class Scoreboard extends Application{

    @FXML
    private Label place1, place2, place3, place4, place5;
    @FXML
    private Label name1, name2, name3, name4, name5;
    @FXML
    private Label points1, points2, points3, points4, points5;
    public Scoreboard() throws IOException {}
    readWriteScoreboard scoreboard = new readWriteScoreboard();
    EncodeDecode encodeDecode = new EncodeDecode();
    private List<readWriteScoreboard> encodedScoreboard = scoreboard.fromFile("./GameResources/Scoreboards/Scoreboard.json").scoreboard;

    @Override
    public void start(Stage stage) throws IOException {
        Parent parentRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scoreboard.fxml")));
        Scene scene = new Scene(parentRoot);
        stage.setTitle("Scoreboard");
        stage.setResizable(false);
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
    }
    /*public void start(Stage stage) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("Scoreboard.fxml"));
        Scene scene = new Scene(fxmlLoader);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }*/

    public static void main(String[] args) {
        launch();
    }
}
