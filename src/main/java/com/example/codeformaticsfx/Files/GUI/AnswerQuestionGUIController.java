package com.example.codeformaticsfx.Files.GUI;

import com.example.codeformaticsfx.Files.EncodeDecode;
import com.example.codeformaticsfx.Files.readWriteQuestions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class AnswerQuestionGUIController implements Initializable {

    @FXML
    public Button nextQuestion;
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
    @FXML
    private Label score;

    private int counter = 0;

    private int correct = 0;

    public static readWriteQuestions readQuestions = new readWriteQuestions();

    public List<EncodeDecode> loaded = new ArrayList<>();

    Random random = new Random();
    EncodeDecode encodeDecode = new EncodeDecode();

    public void checkOne(ActionEvent actionEvent) {
        hint.setText("");
        if (encodeDecode.decodedRightAnswer.equals("A1")){
            solution.setText("true");
            correct++;
            score.setText("Score: "+correct);
            solution.setTextFill(Color.valueOf("green"));
        }else {
            solution.setText("false");
            solution.setTextFill(Color.valueOf("red"));
        }
    }

    public void checkTwo(ActionEvent actionEvent) {
        hint.setText("");
        if (encodeDecode.decodedRightAnswer.equals("A2")){
            solution.setText("true");
            correct++;
            score.setText("Score: "+correct);
            solution.setTextFill(Color.valueOf("green"));
        }else {
            solution.setText("false");
            solution.setTextFill(Color.valueOf("red"));
        }
    }

    public void checkThree(ActionEvent actionEvent) {
        hint.setText("");
        if (encodeDecode.decodedRightAnswer.equals("A3")){
            solution.setText("true");
            correct++;
            score.setText("Score: "+correct);
            solution.setTextFill(Color.valueOf("green"));
        }else {
            solution.setText("false");
            solution.setTextFill(Color.valueOf("red"));
        }
    }

    public void checkFour(ActionEvent actionEvent) {
        hint.setText("");
        if (encodeDecode.decodedRightAnswer.equals("A4")){
            solution.setText("true");
            correct++;
            score.setText("Score: "+correct);
            solution.setTextFill(Color.valueOf("green"));
        }else {
            solution.setText("false");
            solution.setTextFill(Color.valueOf("red"));
        }
    }

    public void jokerF(ActionEvent actionEvent) {
        if (counter == 0) {
            int randomanswer = random.nextInt(4);
            randomanswer = randomanswer+1;
            while (randomanswer == Integer.valueOf(encodeDecode.decodedRightAnswer.replaceAll("A", ""))){
                randomanswer = random.nextInt(4);
                randomanswer = randomanswer+1;
            }

            switch (encodeDecode.decodedRightAnswer){
                case "A1":
                    a1.setTextFill(Color.valueOf("green"));
                    a2.setTextFill(Color.valueOf("red"));
                    a3.setTextFill(Color.valueOf("red"));
                    a4.setTextFill(Color.valueOf("red"));
                    break;
                case "A2":
                    a1.setTextFill(Color.valueOf("red"));
                    a2.setTextFill(Color.valueOf("green"));
                    a3.setTextFill(Color.valueOf("red"));
                    a4.setTextFill(Color.valueOf("red"));
                    break;
                case "A3":
                    a1.setTextFill(Color.valueOf("red"));
                    a2.setTextFill(Color.valueOf("red"));
                    a3.setTextFill(Color.valueOf("green"));
                    a4.setTextFill(Color.valueOf("red"));
                    break;
                case "A4":
                    a1.setTextFill(Color.valueOf("red"));
                    a2.setTextFill(Color.valueOf("red"));
                    a3.setTextFill(Color.valueOf("red"));
                    a4.setTextFill(Color.valueOf("green"));
                    break;
            }

            switch (randomanswer){
                case 1:
                    a1.setTextFill(Color.valueOf("green"));
                    break;
                case 2:
                    a2.setTextFill(Color.valueOf("green"));
                    break;
                case 3:
                    a3.setTextFill(Color.valueOf("green"));
                    break;
                case 4:
                    a4.setTextFill(Color.valueOf("green"));
                    break;
            }
            counter = 1;
        }
    }

    public void nextQuest(ActionEvent actionEvent) {
        if (!solution.getText().equals("true")){
            solution.setTextFill(Color.valueOf("black"));
            solution.setText("Next question can be loaded when you answer correct :)");
            return;
        }
        int randomint = random.nextInt(4);
        randomint = randomint+1;
        String questionpath = "./GameResources/QuestionLibrary/test.json";
        try {
            encodeDecode.decodeQuestion(readQuestions.questionList(questionpath).get(randomint));
            /*while(loaded.contains(encodeDecode)){
                randomint = random.nextInt(5);
                randomint = randomint+1;
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
        hint.setText("Hint");
        if (counter == 1){
            hint.setText("JOKER ALREADY USED");
        }
        solution.setText("");
        //loaded.add(encodeDecode);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int randomint = random.nextInt(5);
        randomint = randomint+1;
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