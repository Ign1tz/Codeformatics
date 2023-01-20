package com.example.codeformaticsfx.FrontEnd;

import com.example.codeformaticsfx.Files.EncodeDecode;
import com.example.codeformaticsfx.Files.Vars;
import com.example.codeformaticsfx.Files.readWriteScoreboard;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;

public class ScoreboardController {
    public ScoreboardController() throws IOException {
    }
    @FXML
    private GridPane scoreboardGrid;
    @FXML
    private Label place1, place2, place3, place4, place5, place6, place7, place8, place9, place10;
    @FXML
    private Label name1, name2, name3, name4, name5, name6, name7, name8, name9, name10;
    @FXML
    private Label points1, points2, points3, points4, points5, points6, points7, points8, points9, points10;
    @FXML
    private Button prev, next;
    @FXML
    private Button exit;
    private int count = 0;
    private String path = Vars.pathScoreboard;

    readWriteScoreboard scoreboard = new readWriteScoreboard();
    EncodeDecode encodeDecode = new EncodeDecode();
    private List<readWriteScoreboard> encodedScoreboard = scoreboard.fromFile(path).scoreboard;


    public void setPath(String newPath){
        path = newPath;
    }
    @FXML
    private void next(ActionEvent event) {
        setScoreboard();
        prev.setOpacity(1);
        prev.setDisable(false);
        if (count >= encodedScoreboard.size()) {
            next.setOpacity(0);
            next.setDisable(true);
        }
    }


    public void previous(ActionEvent event) {
        //count -= 10; //--- Caused by: java.lang.IndexOutOfBoundsException: Index -21 out of bounds for length 5
        count -= 20;
        setScoreboard();
        next.setOpacity(1);
        next.setDisable(false);
        if (count == 10) {
            prev.setOpacity(0);
            prev.setDisable(true);
        }
    }

    public void initialize() {
        setScoreboard();
        prev.setOpacity(0);
        prev.setDisable(true);
        if (count >= encodedScoreboard.size()) {
            next.setOpacity(0);
            next.setDisable(true);
        }

        if(Vars.currentBackgroundValue <= 33){
            scoreboardGrid.setStyle("-fx-background-image: url('com/example/codeformaticsfx/FrontEnd/Background2.jpg')");
        } else if (Vars.currentBackgroundValue > 33 && Vars.currentBackgroundValue <= 66) {
            scoreboardGrid.setStyle("-fx-background-image: url('com/example/codeformaticsfx/FrontEnd/Background.jpg')");
        }else{
            scoreboardGrid.setStyle("-fx-background-image: url('com/example/codeformaticsfx/FrontEnd/Background2.jpg')");
        }
    }
    public void setScoreboard(){
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
        if (encodedScoreboard.size() > count) {
            name6.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
            place6.setText(String.valueOf(count + 1));
            points6.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        } else {
            name6.setText("");
            place6.setText("");
            points6.setText("");
        }
        count++;
        if (encodedScoreboard.size() > count) {
            name7.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
            place7.setText(String.valueOf(count + 1));
            points7.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        } else {
            name7.setText("");
            place7.setText("");
            points7.setText("");
        }
        count++;
        if (encodedScoreboard.size() > count) {
            name8.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
            place8.setText(String.valueOf(count + 1));
            points8.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        } else {
            name8.setText("");
            place8.setText("");
            points8.setText("");
        }
        count++;
        if (encodedScoreboard.size() > count) {
            name9.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
            place9.setText(String.valueOf(count + 1));
            points9.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        } else {
            name9.setText("");
            place9.setText("");
            points9.setText("");
        }
        count++;
        if (encodedScoreboard.size() > count) {
            name10.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
            place10.setText(String.valueOf(count + 1));
            points10.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
        } else {
            name10.setText("");
            place10.setText("");
            points10.setText("");
        }
        count++;
    }
    public void Exit(ActionEvent event) throws IOException {
        count = 0;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeStage.fxml"));
        Parent root = loader.load();
        SceneController controller = loader.getController();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
}
