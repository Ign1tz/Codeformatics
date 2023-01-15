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
    private Label place1, place2, place3, place4, place5;
    @FXML
    private Label name1, name2, name3, name4, name5;
    @FXML
    private Label points1, points2, points3, points4, points5;
    @FXML
    private Button prev, next;
    private int count = 0;

    readWriteScoreboard scoreboard = new readWriteScoreboard();
    EncodeDecode encodeDecode = new EncodeDecode();
    private List<readWriteScoreboard> encodedScoreboard = scoreboard.fromFile("./GameResources/Scoreboards/Scoreboard.json").scoreboard;

    private void startup() {

    }

    @FXML
    private void next(ActionEvent event) throws IOException {
            name1.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
            place1.setText(String.valueOf(count + 1));
            points1.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        count++;
        if (encodedScoreboard.size() > count) {
            name2.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
            place2.setText(String.valueOf(count + 1));
            points2.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        } else {
            name2.setText("");
            place2.setText("");
            points2.setText("");
        }
        count++;
        if (encodedScoreboard.size() > count) {
            name3.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
            place3.setText(String.valueOf(count + 1));
            points3.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        } else {
            name3.setText("");
            place3.setText("");
            points3.setText("");
        }
        count++;
        if (encodedScoreboard.size() > count) {
            name4.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
            place4.setText(String.valueOf(count + 1));
            points4.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        } else {
            name4.setText("");
            place4.setText("");
            points4.setText("");
        }
        count++;
        if (encodedScoreboard.size() > count) {
            name5.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
            place5.setText(String.valueOf(count + 1));
            points5.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        } else {
            name5.setText("");
            place5.setText("");
            points5.setText("");
        }
        count++;
        prev.setOpacity(1);
        prev.setDisable(false);
        if (count >= encodedScoreboard.size()) {
            next.setOpacity(0);
            next.setDisable(true);
        }
    }

    public void previous(ActionEvent event) {
        count -= 10;
        name1.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
        place1.setText(String.valueOf(count + 1));
        points1.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        count++;
        name2.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
        place2.setText(String.valueOf(count + 1));
        points2.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        count++;
        name3.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
        place3.setText(String.valueOf(count + 1));
        points3.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        count++;
        name4.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
        place4.setText(String.valueOf(count + 1));
        points4.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        count++;
        name5.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
        place5.setText(String.valueOf(count + 1));
        points5.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        count++;
        next.setOpacity(1);
        next.setDisable(false);
        if (count == 5) {
            prev.setOpacity(0);
            prev.setDisable(true);
        }
    }

    public void initialize() {
        name1.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
        place1.setText(String.valueOf(count + 1));
        points1.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        count++;
        if (encodedScoreboard.size() > count) {
            name2.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
            place2.setText(String.valueOf(count + 1));
            points2.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        } else {
            name2.setText("");
            place2.setText("");
            points2.setText("");
        }
        count++;
        if (encodedScoreboard.size() > count) {
            name3.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
            place3.setText(String.valueOf(count + 1));
            points3.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        } else {
            name3.setText("");
            place3.setText("");
            points3.setText("");
        }
        count++;
        if (encodedScoreboard.size() > count) {
            name4.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
            place4.setText(String.valueOf(count + 1));
            points4.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        } else {
            name4.setText("");
            place4.setText("");
            points4.setText("");
        }
        count++;
        if (encodedScoreboard.size() > count) {
            name5.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
            place5.setText(String.valueOf(count + 1));
            points5.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        } else {
            name5.setText("");
            place5.setText("");
            points5.setText("");
        }
        prev.setOpacity(0);
        prev.setDisable(true);
        if (count >= encodedScoreboard.size()) {
            next.setOpacity(0);
            next.setDisable(true);
        }
    }
}
