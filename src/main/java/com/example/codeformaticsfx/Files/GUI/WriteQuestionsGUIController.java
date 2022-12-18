package com.example.codeformaticsfx.Files.GUI;

import com.example.codeformaticsfx.Files.readWriteQuestions;
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
import javafx.scene.image.ImageView;
import javafx.stage.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class WriteQuestionsGUIController implements Initializable {
    @FXML
    private TextField Question, A1, A2, A3, A4;
    @FXML
    private Button finish, newQuestion;
    @FXML
    private Label diff, error;
    @FXML
    private Slider diffSlider;
    @FXML
    private ImageView test;
    @FXML
    private CheckBox one, two, three, four;
    private String[] Diff =  {"Easy", "Medium", "Hard"};
    private String[] right =  {"Answer one", "Answer two", "Answer three", "Answer four"};
    public String question, a1,a2,a3,a4,QuizzName, AuthorName, numberOfQuestions;
    private Stage stage;
    private Scene scene;
    private Parent root;
    public String difficulty, isRight;
    public static readWriteQuestions writeQuestions = new readWriteQuestions();
    private InitQuestionInputController initQuestion = new InitQuestionInputController();

    public void finish(ActionEvent event) throws IOException {
        if(Question.getText() == null||A1.getText()==null||A2.getText()==null||A3.getText()==null||A4.getText()==null||isRight==null){
            error.setOpacity(1);
        }else {
            question = Question.getText();
            a1 = A1.getText();
            a2 = A2.getText();
            a3 = A3.getText();
            a4 = A4.getText();
            writeQuestions.writeQuestions(question,a1,a2,a3,a4,difficulty,isRight);
            System.out.println(question);
            System.out.println(a1);
            System.out.println(a2);
            System.out.println(a3);
            System.out.println(a4);
            System.out.println(difficulty);
            System.out.println(isRight);
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("StartQuizzInput.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader);
            stage.setScene(scene);
            stage.show();
        }
    }
    public void newQuestion(ActionEvent event) throws IOException {
        if(Question.getText() == null||A1.getText()==null||A2.getText()==null||A3.getText()==null||A4.getText()==null||isRight==null){
            error.setOpacity(1);
        }else {
            question = Question.getText();
            a1 = A1.getText();
            a2 = A2.getText();
            a3 = A3.getText();
            a4 = A4.getText();
            writeQuestions.writeQuestions(question,a1,a2,a3,a4,difficulty,isRight);
            System.out.println(question);
            System.out.println(a1);
            System.out.println(a2);
            System.out.println(a3);
            System.out.println(a4);
            System.out.println(difficulty);
            System.out.println(isRight);
            Parent fxmlLoader = FXMLLoader.load(getClass().getResource("Question input.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(fxmlLoader);
            stage.setScene(scene);
            stage.show();
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
        diff.setText(difficulty);

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
                diff.setText(difficulty);
            }
        });
    }
}