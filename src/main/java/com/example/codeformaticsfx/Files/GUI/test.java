package com.example.codeformaticsfx.Files.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class test {
    @FXML
    private TextField QuizzName, AuthorName, numberOfQuestions;
    @FXML
    private Label error, inputANumber;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public String qName, aName;


    public void startQuestion(ActionEvent event) throws IOException {
        if(QuizzName.getText() == "" && AuthorName.getText() == "" && numberOfQuestions.getText() == "") {
            error.setOpacity(1);
        }else{
            try{
                Double.parseDouble(numberOfQuestions.getText());
                Parent fxmlLoader = FXMLLoader.load(getClass().getResource("Question input.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(fxmlLoader);
                stage.setScene(scene);
                stage.show();
            } catch (NumberFormatException nfe){
                inputANumber.setOpacity(1);
            }
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        QuizzName.getCharacters().toString();
        AuthorName.getCharacters().toString();
        numberOfQuestions.getCharacters().toString();
    }
}
