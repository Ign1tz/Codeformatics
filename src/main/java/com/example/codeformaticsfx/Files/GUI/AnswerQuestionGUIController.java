package com.example.codeformaticsfx.Files.GUI;

import com.example.codeformaticsfx.Files.EncodeDecode;
import com.example.codeformaticsfx.Files.readWriteQuestions;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class AnswerQuestionGUIController implements Initializable {

    @FXML
    private Label FrageNummer;
    @FXML
    private Button a1, a2, a3, a4;

    @FXML
    private Label hint;
    @FXML
    private Label solution;
    @FXML
    private Button jokerFifty;

    private int counter = 0;

    private int correct = 0;

    public static readWriteQuestions readQuestions = new readWriteQuestions();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Random random = new Random();
        int randomint = random.nextInt(5);
        randomint = randomint+1;
        String questionpath = "./GameResources/QuestionLibrary/test.json";
        EncodeDecode encodeDecode = new EncodeDecode();
        try {
            encodeDecode.decodeQuestion(readQuestions.questionList(questionpath).get(randomint));
        } catch (Exception e) {
            e.getStackTrace();
        }

        FrageNummer.setText(encodeDecode.decodedQuestion);
        a1.setText(encodeDecode.decodedA1);
        a2.setText(encodeDecode.decodedA2);
        a3.setText(encodeDecode.decodedA3);
        a4.setText(encodeDecode.decodedA4);

        jokerFifty.setOnMouseClicked(event -> {
            if (counter == 0) {
                hint.setText(encodeDecode.decodedRightAnswer);
                counter = 1;
            }else {
                hint.setText("joker already used");
            }
        });

        a1.setOnMouseClicked(event -> {
            hint.setText("");
            if (encodeDecode.decodedRightAnswer.equals("A1")){
                solution.setText("true");
                correct++;
                solution.setTextFill(Color.valueOf("green"));
            }else {
                solution.setText("false");
                solution.setTextFill(Color.valueOf("red"));
            }
        });

        a2.setOnMouseClicked(event -> {
            hint.setText("");
            if (encodeDecode.decodedRightAnswer.equals("A2")){
                solution.setText("true");
                correct++;
                solution.setTextFill(Color.valueOf("green"));
            }else {
                solution.setText("false");
                solution.setTextFill(Color.valueOf("red"));
            }
        });

        a3.setOnMouseClicked(event -> {
            hint.setText("");
            if (encodeDecode.decodedRightAnswer.equals("A3")){
                solution.setText("true");
                correct++;
                solution.setTextFill(Color.valueOf("green"));
            }else {
                solution.setText("false");
                solution.setTextFill(Color.valueOf("red"));
            }
        });

        a4.setOnMouseClicked(event -> {
            hint.setText("");
            if (encodeDecode.decodedRightAnswer.equals("A4")){
                solution.setText("true");
                correct++;
                solution.setTextFill(Color.valueOf("green"));
            }else {
                solution.setText("false");
                solution.setTextFill(Color.valueOf("red"));
            }
        });
    }
}