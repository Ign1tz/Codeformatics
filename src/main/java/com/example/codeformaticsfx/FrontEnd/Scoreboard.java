package com.example.codeformaticsfx.FrontEnd;

import com.example.codeformaticsfx.Files.EncodeDecode;
import com.example.codeformaticsfx.Files.readWriteScoreboard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import static javafx.application.Application.launch;

public class Scoreboard /*extends Application*/{
    /*public Scoreboard() throws IOException {}
    readWriteScoreboard scoreboard = new readWriteScoreboard();
    EncodeDecode encodeDecode = new EncodeDecode();
    private List<readWriteScoreboard> encodedScoreboard = scoreboard.fromFile("./Scoreboards/Scoreboard.json");

    @Override
    public void start(Stage stage) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("Scoreboard.fxml"));
        Scene scene = new Scene(fxmlLoader);
        int yPlace = 114, addY = 0;
        for(int place = 0; place < encodedScoreboard.size(); place++){
            String name = encodeDecode.decodeSingle(encodedScoreboard.get(place).name);
            String score = encodeDecode.decodeSingle(encodedScoreboard.get(place).score);
            String tis = encodeDecode.decodeSingle(encodedScoreboard.get(place).tis);
            Label placement = new Label(String.valueOf(place + 1));
            Label Name = new Label(name);
            Label Score = new Label(score);
            placement.relocate(105.0, yPlace + addY);
            Name.relocate(202.0, yPlace + addY);
            Score.relocate(444, yPlace + addY);
            addY = addY + 40;
        }
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }*/
}
