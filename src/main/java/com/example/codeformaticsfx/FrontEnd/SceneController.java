package com.example.codeformaticsfx.FrontEnd;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

public class SceneController {

    @FXML
    public Button OptionsButton;
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

    //HomeScene
    public void StartButton() {
        System.out.println("Start");
    }   //just seeing if the buttons work
   /* public void OptionButton() {
        System.out.println("Option");
        OptionsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                SettingsStage stage2 = new SettingsStage();
                getScene.setRoot(stage2);
            }
        });
    }*/


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
       root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeStage.fxml")));
       Stage window = (Stage) StartButton.getScene().getWindow();
       window.setScene(new Scene(root, window.getWidth(),window.getHeight()));
   }
    public void switchSettings(ActionEvent event) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SettingsStage.fxml")));
        Stage home = (Stage) StartButton.getScene().getWindow();
        home.setScene(new Scene(root, home.getWidth(),home.getHeight()));
    }

    public void switchGame(ActionEvent event) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Game.fxml")));
        Stage game = (Stage) StartButton.getScene().getWindow();
        game.setScene(new Scene(root, game.getWidth(),game.getHeight()));
    }

}
