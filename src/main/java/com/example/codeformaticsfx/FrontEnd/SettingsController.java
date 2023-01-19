package com.example.codeformaticsfx.FrontEnd;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    public GridPane SettingsGrid;
    @FXML
    private Button musicButton, saveButton, newQuiz;
    @FXML
    private CheckBox CheckSound;
    @FXML
    private Slider sliderSettings;
    @FXML
    private Label Label1;
    @FXML
    private TextField myNameText;
    private static String name;
    public boolean nameGiven = false;

    public void SoundOnOFF(ActionEvent event) {
        if(CheckSound.isSelected()) {
            CheckSound.setText("ON");
        } else {
            CheckSound.setText("OFF");
        }
    }
    public void BackgroundChange(ActionEvent event) {
        if (sliderSettings.getValue() == 0) {
        }
    }
    public void createQuizz(ActionEvent event) throws IOException { //Working on (moritz)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionInput.fxml"));
        Parent root = loader.load();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }

    public void submit(ActionEvent event) {
        try {
            name = myNameText.getText();                //name can be typed into the field
            if (name.length() >= 9) {
                Label1.setText("Max. 8 characters ");    //usernames with more than 8 characters are not allowed
            } else {
                Label1.setText("Welcome " + name + "!");
                nameGiven = true;
            }
        } catch (Exception e) {
            Label1.setText("error");
        }
    }

    public void switchStart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeStage.fxml"));
        Parent root = loader.load();
        SceneController controller = loader.getController();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        switch ((int) sliderSettings.getValue()){
            case 1:
                SettingsGrid.setStyle("-fx-background-image: url('com/example/codeformaticsfx/FrontEnd/Background2.jpg')");
                break;
            case 2:
                SettingsGrid.setStyle("-fx-background-image: url('com/example/codeformaticsfx/FrontEnd/Background.jpg')");
                break;
        }
        sliderSettings.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                switch ((int) sliderSettings.getValue()){
                    case 1:
                        SettingsGrid.setStyle("-fx-background-image: url('com/example/codeformaticsfx/FrontEnd/Background2.jpg')");
                        break;
                    case 2:
                        SettingsGrid.setStyle("-fx-background-image: url('com/example/codeformaticsfx/FrontEnd/Background.jpg')");
                        break;
                }
            }
        });
    }
}
