package com.example.codeformaticsfx.FrontEnd;

import com.example.codeformaticsfx.Files.EncodeDecode;
import com.example.codeformaticsfx.Files.readWriteScoreboard;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ScoreboardController {
    public ScoreboardController() throws IOException {
    }
    /*@FXML
    private Label place;
    @FXML
    private Label name;
    @FXML
    private Label points;

    readWriteScoreboard scoreboard = new readWriteScoreboard();
    EncodeDecode encodeDecode = new EncodeDecode();
    private List<readWriteScoreboard> encodedScoreboard = scoreboard.fromFile("Scoreboard.json");

    private void startup(){
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
    }*/
}
