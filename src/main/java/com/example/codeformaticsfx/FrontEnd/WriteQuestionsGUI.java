package com.example.codeformaticsfx.FrontEnd;

import com.example.codeformaticsfx.FrontEnd.SceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class WriteQuestionsGUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(getClass().getResource("QuestionInput.fxml"));
        Scene scene = new Scene(fxmlLoader);
        stage.setTitle("Input Questions");
        stage.setMaximized(true);
        stage.setScene(scene);
        stage.show();
   }

    public static void main(String[] args) {
        launch();
    }
}