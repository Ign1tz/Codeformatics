package com.example.codeformaticsfx.FrontEnd;

import com.example.codeformaticsfx.Files.EncodeDecode;
import com.example.codeformaticsfx.Files.readWriteScoreboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ScoreboardController {
    public ScoreboardController() throws IOException {
    }
    @FXML
    private Label place;
    @FXML
    private Label name;
    @FXML
    private Label points;
    @FXML
    private Button test;
    @FXML
    private Scene scene;

    readWriteScoreboard scoreboard = new readWriteScoreboard();
    EncodeDecode encodeDecode = new EncodeDecode();
    private List<readWriteScoreboard> encodedScoreboard = scoreboard.fromFile("./GameResources/Scoreboards/Scoreboard.json").scoreboard;

    private void startup(){
        int yPlace = 114, addY = 40;
        for(int place = 0; place < encodedScoreboard.size(); place++){
            String nameCurrent = encodeDecode.decodeSingle(encodedScoreboard.get(place).name);
            String score = encodeDecode.decodeSingle(encodedScoreboard.get(place).score);
            String tis = encodeDecode.decodeSingle(encodedScoreboard.get(place).tis);
            name.setText(nameCurrent);
            Label placement = new Label(String.valueOf(place + 1));
            Label Name = new Label(nameCurrent);
            Label Score = new Label(score);
            placement.relocate(105.0, yPlace + addY);
            Name.relocate(202.0, yPlace + addY);
            Score.relocate(444, yPlace + addY);
            addY = addY + 40;
        }
    }
    @FXML
    private void newScoreboard(ActionEvent event) throws IOException {
        int yPlace = 114, addY = 40;
        for(int place = 0; place < encodedScoreboard.size(); place++){
            String nameCurrent = encodeDecode.decodeSingle(encodedScoreboard.get(place).name);
            String score = encodeDecode.decodeSingle(encodedScoreboard.get(place).score);
            String tis = encodeDecode.decodeSingle(encodedScoreboard.get(place).tis);
            name.setText(nameCurrent);
            Label placement = new Label(String.valueOf(place + 1));
            Label Name = new Label(nameCurrent);
            Label Score = new Label(score);
            placement.setTranslateX(105);
            placement.setTranslateY(yPlace + addY);
            Name.relocate(202.0, yPlace + addY);
            Score.relocate(444, yPlace + addY);
            addY = addY + 40;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
        Parent root = loader.load();
        ScoreboardController controller = loader.getController();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}
