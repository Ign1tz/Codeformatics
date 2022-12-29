package com.example.codeformaticsfx.Quizz.GUI;

import com.example.codeformaticsfx.Files.EncodeDecode;
import com.example.codeformaticsfx.Files.readWriteQuestions;
import com.example.codeformaticsfx.Quizz.QuestionJoker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class AnswerQuestionGUIController implements Initializable, Serializable {

    @FXML
    public Button nextQuestion;
    @FXML
    public Button jokerSeventy;
    @FXML
    private Label FrageNummer;
    @FXML
    private Button a1, a2, a3, a4;
    @FXML
    private Label solution;
    @FXML
    private Button jokerFifty;
    @FXML
    private Label score;
    @FXML
    private Button jokerNew;
    private String A1,A2,A3,A4,rightAnswer;
    private int range;
    private int counterF = 0;
    private int counterS = 0;
    private int counterN = 0;
    private int correct = 0;

    public static readWriteQuestions readQuestions = new readWriteQuestions();

    public List<EncodeDecode> loaded = new ArrayList<>();

    Random random = new Random();
    private EncodeDecode encodeDecode = new EncodeDecode();
    private QuestionJoker questionJoker = new QuestionJoker();

    public void checkOne(ActionEvent actionEvent) {
        if (encodeDecode.decodedRightAnswer.equals("A1")){
            solution.setText("true");
            correct++;
            score.setText("Score: "+correct);
            solution.setTextFill(Color.valueOf("green"));
            nextQuestion.setDisable(false);
        }else {
            solution.setText("false");
            solution.setTextFill(Color.valueOf("red"));
        }
        a1.setDisable(true);
        a2.setDisable(true);
        a3.setDisable(true);
        a4.setDisable(true);
        jokerFifty.setDisable(true);
        jokerSeventy.setDisable(true);
        jokerNew.setDisable(true);
    }

    public void checkTwo(ActionEvent actionEvent) {
        if (encodeDecode.decodedRightAnswer.equals("A2")){
            solution.setText("true");
            correct++;
            score.setText("Score: "+correct);
            solution.setTextFill(Color.valueOf("green"));
            nextQuestion.setDisable(false);
        }else {
            solution.setText("false");
            solution.setTextFill(Color.valueOf("red"));
        }
        a1.setDisable(true);
        a2.setDisable(true);
        a3.setDisable(true);
        a4.setDisable(true);
        jokerFifty.setDisable(true);
        jokerSeventy.setDisable(true);
        jokerNew.setDisable(true);
    }

    public void checkThree(ActionEvent actionEvent) {
        if (encodeDecode.decodedRightAnswer.equals("A3")){
            solution.setText("true");
            correct++;
            score.setText("Score: "+correct);
            solution.setTextFill(Color.valueOf("green"));
            nextQuestion.setDisable(false);
        }else {
            solution.setText("false");
            solution.setTextFill(Color.valueOf("red"));
        }
        a1.setDisable(true);
        a2.setDisable(true);
        a3.setDisable(true);
        a4.setDisable(true);
        jokerFifty.setDisable(true);
        jokerSeventy.setDisable(true);
        jokerNew.setDisable(true);
    }

    public void checkFour(ActionEvent actionEvent) {
        if (encodeDecode.decodedRightAnswer.equals("A4")){
            solution.setText("true");
            correct++;
            score.setText("Score: "+correct);
            solution.setTextFill(Color.valueOf("green"));
            nextQuestion.setDisable(false);
        }else {
            solution.setText("false");
            solution.setTextFill(Color.valueOf("red"));
        }
        a1.setDisable(true);
        a2.setDisable(true);
        a3.setDisable(true);
        a4.setDisable(true);
        jokerFifty.setDisable(true);
        jokerSeventy.setDisable(true);
        jokerNew.setDisable(true);
    }

    public void jokerF(ActionEvent actionEvent) {
        if (counterF == 0) {

            A1=a1.getText();
            A2=a2.getText();
            A3=a3.getText();
            A4=a4.getText();
            rightAnswer = encodeDecode.decodedRightAnswer;
            range=4;
            questionJoker.jokerFiftyFifty(A1,A2,A3,A4,rightAnswer,range);


            a1.setDisable(true);
            a2.setDisable(true);
            a3.setDisable(true);
            a4.setDisable(true);

            switch (questionJoker.getRightAnswer()){
                case "A1":
                    a1.setDisable(false);
                    break;
                case "A2":
                    a2.setDisable(false);
                    break;
                case "A3":
                    a3.setDisable(false);
                    break;
                case "A4":
                    a4.setDisable(false);
                    break;
            }

            switch (questionJoker.getRandomAnswer()){
                case 1:
                    a1.setDisable(false);
                    break;
                case 2:
                    a2.setDisable(false);
                    break;
                case 3:
                    a3.setDisable(false);
                    break;
                case 4:
                    a4.setDisable(false);
                    break;
            }
            counterF = 1;
        }
    }

    public void jokerS(ActionEvent actionEvent) {
        if (counterS == 0) {

            a1.setDisable(true);
            a2.setDisable(true);
            a3.setDisable(true);
            a4.setDisable(true);

            A1=a1.getText();
            A2=a2.getText();
            A3=a3.getText();
            A4=a4.getText();
            rightAnswer = encodeDecode.decodedRightAnswer;
            range=4;
            int border = questionJoker.jokerSeventyThirty(A1,A2,A3,A4,rightAnswer,range);

            if (border <= 7){
                switch (questionJoker.getRightAnswer()){
                    case "A1":
                        a1.setDisable(false);
                        a1.setTextFill(Color.valueOf("green"));
                        break;
                    case "A2":
                        a2.setDisable(false);
                        a2.setTextFill(Color.valueOf("green"));
                        break;
                    case "A3":
                        a3.setDisable(false);
                        a3.setTextFill(Color.valueOf("green"));
                        break;
                    case "A4":
                        a4.setDisable(false);
                        a4.setTextFill(Color.valueOf("green"));
                        break;
                }
                switch (questionJoker.getRandomAnswer()){
                    case 1:
                        a1.setDisable(false);
                        a1.setTextFill(Color.valueOf("yellow"));
                        break;
                    case 2:
                        a2.setDisable(false);
                        a2.setTextFill(Color.valueOf("yellow"));
                        break;
                    case 3:
                        a3.setDisable(false);
                        a3.setTextFill(Color.valueOf("yellow"));
                        break;
                    case 4:
                        a4.setDisable(false);
                        a4.setTextFill(Color.valueOf("yellow"));
                        break;
                }
            }else {
                switch (questionJoker.getRightAnswer()){
                    case "A1":
                        a1.setDisable(false);
                        a1.setTextFill(Color.valueOf("yellow"));
                        break;
                    case "A2":
                        a2.setDisable(false);
                        a2.setTextFill(Color.valueOf("yellow"));
                        break;
                    case "A3":
                        a3.setDisable(false);
                        a3.setTextFill(Color.valueOf("yellow"));
                        break;
                    case "A4":
                        a4.setDisable(false);
                        a4.setTextFill(Color.valueOf("yellow"));
                        break;
                }
                switch (questionJoker.getRandomAnswer()){
                    case 1:
                        a1.setDisable(false);
                        a1.setTextFill(Color.valueOf("green"));
                        break;
                    case 2:
                        a2.setDisable(false);
                        a2.setTextFill(Color.valueOf("green"));
                        break;
                    case 3:
                        a3.setDisable(false);
                        a3.setTextFill(Color.valueOf("green"));
                        break;
                    case 4:
                        a4.setDisable(false);
                        a4.setTextFill(Color.valueOf("green"));
                        break;
                }
            }
            counterS = 1;
        }
    }

    public void jokerN(ActionEvent actionEvent) {
        if (counterN == 0){
            counterN = 1;
            nextQuest(new ActionEvent());
        }
    }

    public void nextQuest(ActionEvent actionEvent) {
        a1.setDisable(false);
        a2.setDisable(false);
        a3.setDisable(false);
        a4.setDisable(false);
        jokerSeventy.setDisable(false);
        jokerFifty.setDisable(false);
        jokerNew.setDisable(false);
        nextQuestion.setDisable(true);

        int randomint = random.nextInt(4);
        String questionpath = "./GameResources/QuestionLibrary/test.json";
        try {
            encodeDecode.decodeQuestion(readQuestions.questionList(questionpath).get(randomint));
           /* while(loaded.contains(encodeDecode)){
                randomint = random.nextInt(4);
                encodeDecode.decodeQuestion(readQuestions.questionList(questionpath).get(randomint));
            }*/
        } catch (Exception e) {
            e.getStackTrace();
        }

        FrageNummer.setText(encodeDecode.decodedQuestion);
        a1.setText(encodeDecode.decodedA1);
        a2.setText(encodeDecode.decodedA2);
        a3.setText(encodeDecode.decodedA3);
        a4.setText(encodeDecode.decodedA4);

        a1.setTextFill(Color.valueOf("black"));
        a2.setTextFill(Color.valueOf("black"));
        a3.setTextFill(Color.valueOf("black"));
        a4.setTextFill(Color.valueOf("black"));
        if (counterF == 1){
            jokerFifty.setDisable(true);
        }
        if (counterS == 1){
            jokerSeventy.setDisable(true);
        }
        if (counterN == 1){
            jokerNew.setDisable(true);
        }
        solution.setText("");
        //loaded.add(encodeDecode);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        a1.setDisable(false);
        a2.setDisable(false);
        a3.setDisable(false);
        a4.setDisable(false);
        jokerFifty.setDisable(false);
        jokerSeventy.setDisable(false);
        jokerNew.setDisable(false);
        nextQuestion.setDisable(true);

        int randomint = random.nextInt(4);
        String questionpath = "./GameResources/QuestionLibrary/test.json";
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

        //loaded.add(encodeDecode);
    }
}