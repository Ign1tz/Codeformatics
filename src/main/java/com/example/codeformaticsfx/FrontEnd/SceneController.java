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
import java.util.Objects;

public class SceneController extends HomeStage {

    private Stage primaryStage;

    @FXML
    public Button musicButton;
    @FXML
    private Button OptionsButton;
    @FXML
    private Label nameLabel;
    @FXML
    private Button saveButton;
    @FXML
    private TextField myNameText;
    @FXML
    private Button StartButton;              //Implementing and tagging buttons as module to be accessible via SceneController
    @FXML
    private Button Return;
    @FXML
    private Label Questions;

    String name;

    public void setPrimaryStage(Stage stage) {          //Implement the main primary Stage from HomeStage to SceneController
        this.primaryStage = stage;                      //Used for modifying the main Window/Stage
    }

    @FXML
    public void updateQuestion() {
        Questions.setText("this changes the text on button click");
    }

    public Scene getScene(){
        return scene;
    }


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
        Stage home = (Stage) OptionsButton.getScene().getWindow();
        home.setScene(new Scene(root, home.getWidth(),home.getHeight()));
    }

    public void switchGame(ActionEvent event) throws IOException, InterruptedException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Game.fxml")));
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }

    public void Return(ActionEvent event) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeStage.fxml")));
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }

}
