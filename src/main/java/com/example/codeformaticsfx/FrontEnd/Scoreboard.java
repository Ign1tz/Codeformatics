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
    private Label place;
    @FXML
    private Label name;
    @FXML
    private Label points;
    public Scoreboard() throws IOException {}
    readWriteScoreboard scoreboard = new readWriteScoreboard();
    EncodeDecode encodeDecode = new EncodeDecode();
    private List<readWriteScoreboard> encodedScoreboard = scoreboard.fromFile("./GameResources/Scoreboards/Scoreboard.json").scoreboard;

    @Override
    public void start(Stage stage) throws IOException {
        Parent parentRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Scoreboard.fxml")));
        Scene scene = new Scene(parentRoot);
        int yPlace = 114, addY = 40;
        for(int count = 0; count < encodedScoreboard.size(); count++){
            String nameCurrent = encodeDecode.decodeSingle(encodedScoreboard.get(count).name);
            String score = encodeDecode.decodeSingle(encodedScoreboard.get(count).score);
            String tis = encodeDecode.decodeSingle(encodedScoreboard.get(count).tis);
            if(count == 0) {
                place = new Label(String.valueOf(count + 1));
                name = new Label(nameCurrent);
                points = new Label(score);
            } else {
                Label placement = new Label(String.valueOf(count + 1));
                Label Name = new Label(nameCurrent);
                Label Score = new Label(score);
                placement.relocate(105.0, yPlace + addY);
                Name.relocate(202.0, yPlace + addY);
                Score.relocate(444, yPlace + addY);
            }
            addY = addY + 40;
        }
        stage.setTitle("Hello!");
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
