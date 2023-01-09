package com.example.codeformaticsfx.FrontEnd;

import com.example.codeformaticsfx.Files.EncodeDecode;
import com.example.codeformaticsfx.Files.QuizzInfo;
import com.example.codeformaticsfx.Files.readWriteQuestions;
import com.example.codeformaticsfx.pickQuestions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SceneController extends HomeStage {

    private Stage primaryStage;

    @FXML
    public AnchorPane Warning;
    @FXML
    public GridPane WarningGrid;
    @FXML
    public Button musicButton, exit, stay;
    @FXML
    private Button OptionsButton;
    @FXML
    private Label Label1;
    @FXML
    private Label Difficulty;
    @FXML
    private Label Score;
    @FXML
    private Label FinalScore;
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
    @FXML
    private CheckBox Answer4;
    @FXML
    private CheckBox Answer3;
    @FXML
    private CheckBox Answer2;
    @FXML
    private CheckBox Answer1;
    @FXML
    private Label LabelCharacters;
    @FXML
    private CheckBox CheckSound;


    String name;


    static List<Integer> diffList;
    static List<Integer> easy;
    static List<Integer> medium;
    static List<Integer> hard;
    static int question = 0;
    static List<readWriteQuestions> questionsList;
    static String selected, right, numberOfQuestions;
    static String score = "00000";


    public void setPrimaryStage(Stage stage) {          //Implement the main primary Stage from HomeStage to SceneController
        this.primaryStage = stage;                      //Used for modifying the main Window/Stage
    }

    @FXML
    public void updateQuestion(ActionEvent event) throws IOException {
        readWriteQuestions temp = null;
        if(Objects.equals(selected, right)){
            score = String.valueOf(Integer.parseInt(score) + 100);
        }
        if(selected == null){

        }else {
            StringBuilder finalScore = new StringBuilder(score);
            for(int nulls = 5; nulls > score.length(); nulls--){
                finalScore.insert(0, "0");
            }
            Score.setText("Score: " + finalScore);
            if (question >= diffList.size()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("finishScreens.fxml"));
                Parent root = loader.load();
                SceneController controller = loader.getController();
                controller.FinalScore.setText("" + finalScore);
                Scene scene = ((Node) event.getSource()).getScene();
                scene.setRoot(root);
                return;
            }
            EncodeDecode eD = new EncodeDecode();
            int random;
            String thisQuestion = null;
            switch (diffList.get(question)) {
                case 1:
                    random = randomNumber(easy.size() - 1);
                    temp = questionsList.get(easy.get(random));
                    thisQuestion = eD.decodeSingle(temp.QUESTION);
                    easy.remove(random);
                    break;
                case 2:
                    random = randomNumber(medium.size() - 1);
                    temp = questionsList.get(medium.get(random));
                    thisQuestion = eD.decodeSingle(temp.QUESTION);
                    medium.remove(random);
                    break;
                case 3:
                    random = randomNumber(hard.size() - 1);
                    temp = questionsList.get(hard.get(random));
                    thisQuestion = eD.decodeSingle(temp.QUESTION);
                    hard.remove(random);
                    break;
            }
            Questions.setText(thisQuestion);
            Answer1.setText(eD.decodeSingle(temp.A1));
            Answer2.setText(eD.decodeSingle(temp.A2));
            Answer3.setText(eD.decodeSingle(temp.A3));
            Answer4.setText(eD.decodeSingle(temp.A4));
            Difficulty.setText("Difficulty: " + eD.decodeSingle(temp.DIFFICULTY));
            right = eD.decodeSingle(temp.RIGHTAWNSER);
            question++;
            Answer1.setSelected(false);
            Answer2.setSelected(false);
            Answer3.setSelected(false);
            Answer4.setSelected(false);
            Answer1.setDisable(false);
            Answer2.setDisable(false);
            Answer3.setDisable(false);
            Answer4.setDisable(false);
        }
    }

    public void AnswerOne() {
        if (Answer1.isSelected()) {
            Answer2.setDisable(true);
            Answer3.setDisable(true);
            Answer4.setDisable(true);
            selected = "A1";
        } else {
            Answer2.setDisable(false);
            Answer3.setDisable(false);
            Answer4.setDisable(false);
            selected = null;
        }
    }

    public void AnswerTwo() {
        if (Answer2.isSelected()) {
            Answer1.setDisable(true);
            Answer3.setDisable(true);
            Answer4.setDisable(true);
            selected = "A2";
        } else {
            Answer1.setDisable(false);
            Answer3.setDisable(false);
            Answer4.setDisable(false);
            selected = null;
        }
    }

    public void AnswerThree() {
        if (Answer3.isSelected()) {
            Answer1.setDisable(true);
            Answer2.setDisable(true);
            Answer4.setDisable(true);
            selected = "A3";
        } else {
            Answer1.setDisable(false);
            Answer2.setDisable(false);
            Answer4.setDisable(false);
            selected = null;
        }
    }

    public void AnswerFour() {
        if (Answer4.isSelected()) {
            Answer1.setDisable(true);
            Answer2.setDisable(true);
            Answer3.setDisable(true);
            selected = "A4";
        } else {
            Answer1.setDisable(false);
            Answer2.setDisable(false);
            Answer3.setDisable(false);
            selected = null;
        }
    }
    public Scene getScene() {
        return scene;
    }


    public void submit(ActionEvent event) throws IOException{
        try {
            name = myNameText.getText();
            System.out.println(name);
            if (name.length() >= 9) {
                Label1.setText("Max. 8 characters ");    //usernames with more than 8 characters are not allowed
            } else {
                Label1.setText("Welcome " + name + "!");
            }
        } catch (Exception e) {
            Label1.setText("error");
        }
    }


    //Switching between two Scenes
    private Stage stage;
    private Parent root;
    private Scene scene;

    public void switchStart(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeStage.fxml")));
        Stage window = (Stage) StartButton.getScene().getWindow();
        window.setScene(new Scene(root, window.getWidth(), window.getHeight()));
    }

    public void switchSettings(ActionEvent event) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SettingsStage.fxml")));
        Stage home = (Stage) OptionsButton.getScene().getWindow();
        home.setScene(new Scene(root, home.getWidth(), home.getHeight()));
    }

    public void switchGame(ActionEvent event) throws IOException, InterruptedException {
        question = 0;
        pickQuestions pQ = new pickQuestions();
        EncodeDecode eD = new EncodeDecode();
        readWriteQuestions rwq = new readWriteQuestions();
        diffList = pQ.testQuestion();
        easy = pQ.easy;
        medium = pQ.medium;
        hard = pQ.hard;
        QuizzInfo thisQuizz = rwq.readQuizz("./GameResources/QuestionLibrary/Java.json");
        questionsList = thisQuizz.questionsList;
        numberOfQuestions = eD.decodeSingle(thisQuizz.questionsUsed);
        readWriteQuestions temp = null;
        int random;
        String thisQuestion = null;
        switch (diffList.get(question)) {
            case 1:
                random = randomNumber(easy.size() - 1);
                temp = questionsList.get(easy.get(random));
                thisQuestion = eD.decodeSingle(temp.QUESTION);
                easy.remove(random);
                break;
            case 2:
                random = randomNumber(medium.size() - 1);
                temp = questionsList.get(medium.get(random));
                thisQuestion = eD.decodeSingle(temp.QUESTION);
                medium.remove(random);
                break;
            case 3:
                random = randomNumber(hard.size() - 1);
                temp = questionsList.get(hard.get(random));
                thisQuestion = eD.decodeSingle(temp.QUESTION);
                hard.remove(random);
                break;
        }
        right = eD.decodeSingle(temp.RIGHTAWNSER);
        //This part initialises the Design representation of the new Scene.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        Parent root = loader.load();
        SceneController controller = loader.getController();
        controller.Answer1.setText(eD.decodeSingle(temp.A1));
        controller.Answer2.setText(eD.decodeSingle(temp.A2));
        controller.Answer3.setText(eD.decodeSingle(temp.A3));
        controller.Answer4.setText(eD.decodeSingle(temp.A4));
        controller.Questions.setText(thisQuestion);
        controller.Difficulty.setText("Difficulty: " + eD.decodeSingle(temp.DIFFICULTY));
        controller.Score.setText("Score: 00000");
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
        question++;
    }
    public void Return(ActionEvent event){
        Warning.setOpacity(1);
        Warning.setDisable(false);
        WarningGrid.setOpacity(1);
        exit.setDisable(false);
        stay.setDisable(false);
    }
    public void Stay(ActionEvent event){
        Warning.setOpacity(0);
        Warning.setDisable(true);
        WarningGrid.setOpacity(0);
        exit.setDisable(true);
        stay.setDisable(true);
    }

    public void Exit(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeStage.fxml"));
        Parent root = loader.load();
        SceneController controller = loader.getController();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }

    private int randomNumber(int high) {
        int low = 1, random;
        Random rand = new Random();
        random = rand.nextInt(high) + low;
        return random;
    }
    public void SoundOnOFF(ActionEvent event) {
    if(CheckSound.isSelected()) {
        CheckSound.setText("ON");
    } else {
        CheckSound.setText("OFF");
    }
    }
}
