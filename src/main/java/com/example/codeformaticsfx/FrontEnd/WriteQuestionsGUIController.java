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
    private String[] Diff =  {"Easy", "Medium", "Hard"};
    private String[] right =  {"Answer one", "Answer two", "Answer three", "Answer four"};
    public String question, a1,a2,a3,a4;
    private Stage stage;
    private Scene scene;
    public String difficulty, isRight;
    public static readWriteQuestions writeQuestions = new readWriteQuestions();
    private InitQuestionInputController initQuestion = new InitQuestionInputController();
    private static int Questions = 0, hardQuestionsNumber = 0;

    public void finish(ActionEvent event) throws IOException {
        if(Question.getText() == null||A1.getText()==null||A2.getText()==null||A3.getText()==null||A4.getText()==null||isRight==null){
            error.setOpacity(1);
        }else if ((!(hardQuestionsNumber == 1 && Objects.equals(difficulty, "Hard")) && (hardQuestionsNumber < 2)) || Questions < 6){
            System.out.println("ehhhh");
        }else {
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
            Questions = 0;
            hardQuestionsNumber = 0;
        }
    }
    public void newQuestion(ActionEvent event) throws IOException {
        if(Question.getText() == ""||A1.getText()==""||A2.getText()==""||A3.getText()==""||A4.getText()==""||isRight==""){
            error.setOpacity(1);
        }else {
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
    public void oneIsRight(ActionEvent event){
        if(one.isSelected()){
            two.setDisable(true);
            three.setDisable(true);
            four.setDisable(true);
            isRight = "A1";
        }else{
            two.setDisable(false);
            three.setDisable(false);
            four.setDisable(false);
        }
    }
    public void twoIsRight(ActionEvent event) {
        if(two.isSelected()){
            one.setDisable(true);
            three.setDisable(true);
            four.setDisable(true);
            isRight = "A2";
        }else{
            one.setDisable(false);
            three.setDisable(false);
            four.setDisable(false);
        }
    }
    public void threeIsRight(ActionEvent event) {
        if(three.isSelected()){
            one.setDisable(true);
            two.setDisable(true);
            four.setDisable(true);
            isRight = "A3";
        }else{
            one.setDisable(false);
            two.setDisable(false);
            four.setDisable(false);
        }
    }
    public void fourIsRight(ActionEvent event) {
        if(four.isSelected()){
            one.setDisable(true);
            two.setDisable(true);
            three.setDisable(true);
            isRight = "A4";
        }else{
            one.setDisable(false);
            two.setDisable(false);
            three.setDisable(false);
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        numberOfQuestions.setText("You have " + Questions + " questions!");
        hardQuestions.setText("You have " + hardQuestionsNumber + " hard questions!");
        if(Vars.currentBackgroundValue <= 33){
            inputQuestionsGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground1 +")");
        } else if (Vars.currentBackgroundValue > 33 && Vars.currentBackgroundValue <= 66) {
            inputQuestionsGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground2 +")");
        }else{
            inputQuestionsGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground3 +")");
        }
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