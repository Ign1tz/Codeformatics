package com.example.codeformaticsfx.FrontEnd;

import com.example.codeformaticsfx.Files.readWriteQuestions;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InitQuestionInputController {
    @FXML
    private TextField QuizzName, AuthorName, numberOfQuestions;
    @FXML
    private Label error, inputANumber;
    private Stage stage;
    private Scene scene;
    private readWriteQuestions writeQuestions = WriteQuestionsGUIController.writeQuestions;


    public void startQuestion(ActionEvent event) throws IOException {
        WriteQuestionsGUIController WQGC = new WriteQuestionsGUIController();
        if(QuizzName.getText() == "" && AuthorName.getText() == "" && numberOfQuestions.getText() == "") {
            error.setOpacity(1);
        }else{
            try{
                Double.parseDouble(numberOfQuestions.getText());
                String filePath = "./GameResources/QuestionLibrary/" + QuizzName.getText() + ".json";
                writeQuestions.finish(filePath, QuizzName.getText(), AuthorName.getText(), numberOfQuestions.getText());
                Parent fxmlLoader = FXMLLoader.load(getClass().getResource("FinishedQuizz.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(fxmlLoader);
                stage.setTitle("Question Info");
                stage.setMaximized(true);
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
