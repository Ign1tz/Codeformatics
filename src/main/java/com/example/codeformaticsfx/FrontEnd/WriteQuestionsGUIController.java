package com.example.codeformaticsfx.FrontEnd;

import com.example.codeformaticsfx.Files.Vars;
import com.example.codeformaticsfx.Files.readWriteQuestions;
import javafx.beans.value.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.*;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class WriteQuestionsGUIController implements Initializable {
    @FXML
    private GridPane inputQuestionsGrid;
    @FXML
    private TextField Question, A1, A2, A3, A4;
    @FXML
    private Label diff, error, numberOfQuestions, hardQuestions;
    @FXML
    private Slider diffSlider;
    @FXML
    private CheckBox one, two, three, four;
    public String question, a1,a2,a3,a4;
    public String difficulty, isRight;
    public static readWriteQuestions writeQuestions = new readWriteQuestions();
    private InitQuestionInputController initQuestion = new InitQuestionInputController();
    private static int Questions = 0, hardQuestionsNumber = 0;

    public void finish(ActionEvent event) throws IOException {
        if(Question.getText() == null||A1.getText()==null||A2.getText()==null||A3.getText()==null||A4.getText()==null||isRight==null){ //if not everything is filled show error
            error.setOpacity(1);
        }else if ((!(hardQuestionsNumber == 1 && Objects.equals(difficulty, "Hard")) && (hardQuestionsNumber < 2)) || Questions < 6){ //checks if at least 2 hard questions are put in
        }else { //makes new Question Object and goes to finish quiz
            question = Question.getText();
            a1 = A1.getText();
            a2 = A2.getText();
            a3 = A3.getText();
            a4 = A4.getText();
            writeQuestions.writeQuestions(question,a1,a2,a3,a4,difficulty,isRight);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FinishQuizzInput.fxml"));
            Parent root = loader.load();
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
            Questions = 0; //resets values for next time
            hardQuestionsNumber = 0;
        }
    }
    public void newQuestion(ActionEvent event) throws IOException {
        if(Question.getText() == ""||A1.getText()==""||A2.getText()==""||A3.getText()==""||A4.getText()==""||isRight==""){ //if not everything is filled show error
            error.setOpacity(1);
        }else { //make new question object
            question = Question.getText();
            a1 = A1.getText();
            a2 = A2.getText();
            a3 = A3.getText();
            a4 = A4.getText();
            writeQuestions.writeQuestions(question,a1,a2,a3,a4,difficulty,isRight);
            Questions++;
            if(difficulty == "Hard"){
                hardQuestionsNumber++;
            }
            numberOfQuestions.setText("You have " + Questions + " questions!");
            hardQuestions.setText("You have " + hardQuestionsNumber + " hard questions!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionInput.fxml"));
            Parent root = loader.load();
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
        }
    }
    public void oneIsRight(){
        if(one.isSelected()){//disables checkboxes that arent this one
            two.setDisable(true);
            three.setDisable(true);
            four.setDisable(true);
            isRight = "A1";
        }else{ //enables all checkboxes
            two.setDisable(false);
            three.setDisable(false);
            four.setDisable(false);
            isRight = "";
        }
    }
    public void twoIsRight() {
        if(two.isSelected()){//disables checkboxes that arent this one
            one.setDisable(true);
            three.setDisable(true);
            four.setDisable(true);
            isRight = "A2";
        }else{ //enables all checkboxes
            one.setDisable(false);
            three.setDisable(false);
            four.setDisable(false);
            isRight = "";
        }
    }
    public void threeIsRight() {
        if(three.isSelected()){//disables checkboxes that arent this one
            one.setDisable(true);
            two.setDisable(true);
            four.setDisable(true);
            isRight = "A3";
        }else{ //enables all checkboxes
            one.setDisable(false);
            two.setDisable(false);
            four.setDisable(false);
            isRight = "";
        }
    }
    public void fourIsRight() {
        if(four.isSelected()){//disables checkboxes that arent this one
            one.setDisable(true);
            two.setDisable(true);
            three.setDisable(true);
            isRight = "A4";
        }else{ //enables all checkboxes
            one.setDisable(false);
            two.setDisable(false);
            three.setDisable(false);
            isRight = "";
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberOfQuestions.setText("You have " + Questions + " questions!");
        hardQuestions.setText("You have " + hardQuestionsNumber + " hard questions!");
        if(Vars.currentBackgroundValue <= 33){ //sets background
            inputQuestionsGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground1 +")");
        } else if (Vars.currentBackgroundValue > 33 && Vars.currentBackgroundValue <= 66) {
            inputQuestionsGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground2 +")");
        }else{
            inputQuestionsGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground3 +")");
        }
        switch ((int) diffSlider.getValue()){ //gets difficulty of question
            case 1:
                difficulty = "Easy";
                break;
            case 2:
                difficulty = "Medium";
                break;
            case 3:
                difficulty = "Hard";
                break;
        }
        diff.setText("Difficulty: " + difficulty);
        diffSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                switch ((int) diffSlider.getValue()){
                    case 1:
                        difficulty = "Easy";
                        break;
                    case 2:
                        difficulty = "Medium";
                        break;
                    case 3:
                        difficulty = "Hard";
                        break;
                }
                diff.setText("Difficulty: " + difficulty);
            }
        });
    }
}