package com.example.codeformaticsfx.FrontEnd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

public class SceneController {
    //HomeScene
    public void StartButton() {
        System.out.println("Start");
    }   //just seeing if the buttons work

    public void OptionButton() {
        System.out.println("Option");
    }

    //SettingsScene
    @FXML
    private Label nameLabel;
    @FXML
    private Button saveButton;
    @FXML
    private TextField myNameText;

    //Start
    @FXML
    public Button StartButton;              //Implementing and tagging button as module to be accessible for fxml loader

    String name;

    public void submit(ActionEvent event) {
        try {
            name = myNameText.getText();
            System.out.println(name);
            if (name.length() >= 9) {
                nameLabel.setText("Max. 8 characters ");    //usernames with more than 8 characters are not allowed
            } else {
                nameLabel.setText("Welcome " + name + "!");
            }
        } catch (Exception e) {
            nameLabel.setText("error");
        }
    }

    //Switching between two Scenes
    private Stage stage;
    private Parent root;
    private Scene scene;
    public void switchStart(ActionEvent event) throws IOException {
       root = FXMLLoader.load(getClass().getResource("HomeStage.fxml"));
       stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchSettings(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("SettingsStage.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchGame(ActionEvent event) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Game.fxml")));
        Stage game = (Stage) StartButton.getScene().getWindow();
        game.setScene(new Scene(root, game.getWidth(),game.getHeight()));
    }

}
