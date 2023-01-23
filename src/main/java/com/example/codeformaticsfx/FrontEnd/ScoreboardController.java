package com.example.codeformaticsfx.FrontEnd;

import com.example.codeformaticsfx.Files.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.List;

public class ScoreboardController {
    public ScoreboardController() throws IOException {
    }
    @FXML
    private GridPane scoreboardGrid;
    @FXML
    private Label place1, place2, place3, place4, place5, place6, place7, place8, place9, place10, winnerPlace, secondPlace,
    thirdPlace;
    @FXML
    private Label name1, name2, name3, name4, name5, name6, name7, name8, name9, name10, winnerName, secondName, thirdName;
    @FXML
    private Label points1, points2, points3, points4, points5, points6, points7, points8, points9, points10, winnerPoints,
    secondPoints, thirdPoints;
    @FXML
    private Button prev, next;
    private int count = 0;
    private String path = Vars.pathScoreboard;

    readWriteScoreboard scoreboard = new readWriteScoreboard();
    EncodeDecode encodeDecode = new EncodeDecode();
    private List<readWriteScoreboard> encodedScoreboard = scoreboard.fromFile(path).scoreboard;

    @FXML
    private void next() { //shows next page of scoreboard
        setScoreboard();
        prev.setOpacity(1);
        prev.setDisable(false);
        if (count >= encodedScoreboard.size()) {
            next.setOpacity(0);
            next.setDisable(true);
        }
    }


    public void previous() { //showes previous page of scoreboard
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

        if(Vars.currentBackgroundValue <= 33){ //sets background
            scoreboardGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground1 +")");
        } else if (Vars.currentBackgroundValue > 33 && Vars.currentBackgroundValue <= 66) {
            scoreboardGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground2 +")");
        }else{
            scoreboardGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground3 +")");
        }
    }
    public void setScoreboard(){
       if (encodedScoreboard.size() > count) { //checks if this place exists in in scoreboard list
           if (count == 0) { //if its the first place change how it looks
               winnerName.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
               winnerPlace.setText(String.valueOf(count + 1));
               winnerPoints.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
               name1.setText("");
               place1.setText("");
               points1.setText("");
           } else {
               name1.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
               place1.setText(String.valueOf(count + 1));
               points1.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
               winnerName.setText("");
               winnerPlace.setText("");
               winnerPoints.setText("");
           }
       } else {
           winnerName.setText("");
           winnerPlace.setText("");
           winnerPoints.setText("");
           name1.setText("");
           place1.setText("");
           points1.setText("");
       }
       count++;
       if (encodedScoreboard.size() > count) {
           if (count == 1) {
               secondName.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
               secondPlace.setText(String.valueOf(count + 1));
               secondPoints.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
               name2.setText("");
               place2.setText("");
               points2.setText("");
           } else {
               name2.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
               place2.setText(String.valueOf(count + 1));
               points2.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
               secondName.setText("");
               secondPlace.setText("");
               secondPoints.setText("");
           }
       } else {
           secondName.setText("");
           secondPlace.setText("");
           secondPoints.setText("");
           name2.setText("");
           place2.setText("");
           points2.setText("");
       }
       count++;
       if (encodedScoreboard.size() > count){
           if (count == 2) {
               thirdName.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
               thirdPlace.setText(String.valueOf(count + 1));
               thirdPoints.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
               name3.setText("");
               place3.setText("");
               points3.setText("");
           } else {
               name3.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).name));
               place3.setText(String.valueOf(count + 1));
               points3.setText(encodeDecode.decodeSingle(encodedScoreboard.get(count).score));
               thirdName.setText("");
               thirdPlace.setText("");
               thirdPoints.setText("");
           }
       } else {
           thirdName.setText("");
           thirdPlace.setText("");
           thirdPoints.setText("");
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
