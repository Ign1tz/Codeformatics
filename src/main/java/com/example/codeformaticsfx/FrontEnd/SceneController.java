package com.example.codeformaticsfx.FrontEnd;

import com.example.codeformaticsfx.Files.*;
import com.example.codeformaticsfx.Quizz.QuestionJoker;
import com.example.codeformaticsfx.pickQuestions;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SceneController implements Initializable{

    //FXML USAGE START
    @FXML
    public GridPane Warning, homeGrid, finishGrid, GameGrid;
    @FXML
    public Button musicButton, exit, stay, OptionsButton, saveButton, newQuizz, StartButton, Return;
    @FXML
    private Label Label1, scoreboardLabel, Difficulty, Score, FinalScore, Questions, LabelCharacters;
    @FXML
    private CheckBox Answer1, Answer2, Answer3, Answer4;
    @FXML
    private CheckBox CheckSound;
    @FXML
    private Slider sliderSettings;
    @FXML
    private TextField nameScoreboard, myNameText;
    //FXML USAGE END

    //VARIABLES START
    static String name;
    private static Stage primaryStage;
    static List<Integer> diffList, easy, medium, hard;
    static int question = 0;
    static List<readWriteQuestions> questionsList;
    static String selected, right, numberOfQuestions, score = "00000";
    static String pathScoreboard = "./GameResources/Scoreboards/JavaScoreboard.json";
    static boolean nameGiven = false;
    private Stage stage;
    private Parent root;
    private static Scene scene;
    int counterF, counterS;
    private ArrayList<File> songs;
    private File directory;
    private File[] files;
    private int songNumber;
    private Media media;
    private MediaPlayer mediaPlayer;
    private String[] arrStr;
    QuestionJoker questionJoker = new QuestionJoker();
    //VARIABLES END

    //METHODS START
    public void setPrimaryStage(Stage stage, Scene scene) {          //Implement the main primary Stage from HomeStage to SceneController
        this.primaryStage = stage;                                  //Used for modifying the main Window/Stage
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    private int randomNumber(int high) {
        int low = 1, random;
        Random rand = new Random();
        random = rand.nextInt(high) + low;
        return random;
    }

    public void switchStart(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeStage.fxml"));
        Parent root = loader.load();
        SceneController controller = loader.getController();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }

    public void switchSettings(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsStage.fxml"));
        Parent root = loader.load();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
    //SWITCH SCENES END

    //HOMESTAGE START
    public void switchGame(ActionEvent event) throws IOException, InterruptedException {
        question = 0;
        pickQuestions pQ = new pickQuestions();
        EncodeDecode eD = new EncodeDecode();
        readWriteQuestions rwq = new readWriteQuestions();
        diffList = pQ.testQuestion();
        easy = pQ.easy;
        medium = pQ.medium;
        hard = pQ.hard;
        QuizzInfo thisQuizz = rwq.readQuizz(Vars.pathQuestions);
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
        controller.Score.setText("Score: 00000");
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
        question++;
    }

    //END GAME SCENE
    public void Return(ActionEvent event){
        Warning.setOpacity(1);
        Warning.setDisable(false);
        exit.setDisable(false);
        stay.setDisable(false);
    }
    public void Stay(ActionEvent event){
        Warning.setOpacity(0);
        Warning.setDisable(true);
        exit.setDisable(true);
        stay.setDisable(true);
    }

    public void Exit(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeStage.fxml"));
        Parent root = loader.load();
        SceneController controller = loader.getController();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
        score = "0";
    }

    public void toScoreboard(ActionEvent event) throws IOException {
        readWriteScoreboard rwS = new readWriteScoreboard();
        if(!Objects.equals(name,null)) {
            if(myNameText != null){
                nameScoreboard.setText(myNameText.getText());
            }
            if(!nameGiven) {
                name = nameScoreboard.getText();
            }
            rwS.writeToScoreboard(name, score, String.valueOf(1), Vars.pathScoreboard);
            ScoreboardController SC = new ScoreboardController();
            //SC.setPath(Vars.pathScoreboard);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
            Parent root = loader.load();
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
        } else{
            nameScoreboard.setOpacity(1);
            nameScoreboard.setDisable(false);
            scoreboardLabel.setOpacity(1);
            try {
                name = nameScoreboard.getText();
                if (name.length() >= 9) {
                    scoreboardLabel.setText("Username not accepted");
                }
            } catch (Exception e) {
                scoreboardLabel.setText("error");
            }
        }
    }

    //QUESTIONS LOGIC
    @FXML
    public void updateQuestion(ActionEvent event) throws IOException {
        readWriteQuestions temp = null;
        if(Objects.equals(selected, right)){
            score = String.valueOf(Integer.parseInt(score) + 100);
        }
        if(selected == null){

        }else {
            if (counterS == 1){
                Answer1.setTextFill(Color.valueOf("#e91e63"));
                Answer2.setTextFill(Color.valueOf("#e91e63"));
                Answer3.setTextFill(Color.valueOf("#e91e63"));
                Answer4.setTextFill(Color.valueOf("#e91e63"));
            }
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
            selected = null;
            Questions.setText(thisQuestion);
            Answer1.setText(eD.decodeSingle(temp.A1));
            Answer2.setText(eD.decodeSingle(temp.A2));
            Answer3.setText(eD.decodeSingle(temp.A3));
            Answer4.setText(eD.decodeSingle(temp.A4));
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

    //Questions - Joker

    public void jokerF(ActionEvent actionEvent) {
        if (counterF == 0) {

            String A1=Answer1.getText();
            String A2=Answer2.getText();
            String A3=Answer3.getText();
            String A4=Answer4.getText();
            String rightAnswer = right;
            int range=4;
            int rightnumber = 0;

            if ("A1".equals(rightAnswer)){
                rightnumber = 1;
            }
            if ("A2".equals(rightAnswer)){
                rightnumber = 2;
            }
            if ("A3".equals(rightAnswer)){
                rightnumber = 3;
            }
            if ("A4".equals(rightAnswer)){
                rightnumber = 4;
            }

            questionJoker.jokerFiftyFifty(A1,A2,A3,A4,rightAnswer,range,rightnumber);

            Answer1.setDisable(true);
            Answer2.setDisable(true);
            Answer3.setDisable(true);
            Answer4.setDisable(true);
            Answer1.setSelected(false); //removes Selection of (possible) wrong awnser resulting in the possible selection of multiple awnsers
            Answer2.setSelected(false);
            Answer3.setSelected(false);
            Answer4.setSelected(false);

            switch (questionJoker.getRightAnswer()){
                case "A1":
                    Answer1.setDisable(false);
                    break;
                case "A2":
                    Answer2.setDisable(false);
                    break;
                case "A3":
                    Answer3.setDisable(false);
                    break;
                case "A4":
                    Answer4.setDisable(false);
                    break;
            }

            switch (questionJoker.getRandomAnswer()){
                case 1:
                    Answer1.setDisable(false);
                    break;
                case 2:
                    Answer2.setDisable(false);
                    break;
                case 3:
                    Answer3.setDisable(false);
                    break;
                case 4:
                    Answer4.setDisable(false);
                    break;
            }
            counterF = 1;
        }
    }


    public void jokerS(ActionEvent actionEvent) {
        if (counterS == 0) {

            Answer1.setDisable(true);
            Answer2.setDisable(true);
            Answer3.setDisable(true);
            Answer4.setDisable(true);
            Answer1.setSelected(false); //removes Selection of (possible) wrong answer resulting in the possible selection of multiple answers
            Answer2.setSelected(false);
            Answer3.setSelected(false);
            Answer4.setSelected(false);

            String A1=Answer1.getText();
            String A2=Answer2.getText();
            String A3=Answer3.getText();
            String A4=Answer4.getText();
            String rightAnswer = right;
            int range=4;
            int rightnumber = 0;

            if ("A1".equals(rightAnswer)){
                rightnumber = 1;
            }
            if ("A2".equals(rightAnswer)){
                rightnumber = 2;
            }
            if ("A3".equals(rightAnswer)){
                rightnumber = 3;
            }
            if ("A4".equals(rightAnswer)){
                rightnumber = 4;
            }


            int border = questionJoker.jokerSeventyThirty(A1,A2,A3,A4,rightAnswer,range, rightnumber);

            if (border <= 7){
                switch (questionJoker.getRightAnswer()){
                    case "A1":
                        Answer1.setDisable(false);
                        Answer1.setTextFill(Color.valueOf("green"));
                        break;
                    case "A2":
                        Answer2.setDisable(false);
                        Answer2.setTextFill(Color.valueOf("green"));
                        break;
                    case "A3":
                        Answer3.setDisable(false);
                        Answer3.setTextFill(Color.valueOf("green"));
                        break;
                    case "A4":
                        Answer4.setDisable(false);
                        Answer4.setTextFill(Color.valueOf("green"));
                        break;
                }
                switch (questionJoker.getRandomAnswer()){
                    case 1:
                        Answer1.setDisable(false);
                        Answer1.setTextFill(Color.valueOf("yellow"));
                        break;
                    case 2:
                        Answer2.setDisable(false);
                        Answer2.setTextFill(Color.valueOf("yellow"));
                        break;
                    case 3:
                        Answer3.setDisable(false);
                        Answer3.setTextFill(Color.valueOf("yellow"));
                        break;
                    case 4:
                        Answer4.setDisable(false);
                        Answer4.setTextFill(Color.valueOf("yellow"));
                        break;
                }
            }else {
                switch (questionJoker.getRightAnswer()){
                    case "A1":
                        Answer1.setDisable(false);
                        Answer1.setTextFill(Color.valueOf("yellow"));
                        break;
                    case "A2":
                        Answer2.setDisable(false);
                        Answer2.setTextFill(Color.valueOf("yellow"));
                        break;
                    case "A3":
                        Answer3.setDisable(false);
                        Answer3.setTextFill(Color.valueOf("yellow"));
                        break;
                    case "A4":
                        Answer4.setDisable(false);
                        Answer4.setTextFill(Color.valueOf("yellow"));
                        break;
                }
                switch (questionJoker.getRandomAnswer()){
                    case 1:
                        Answer1.setDisable(false);
                        Answer1.setTextFill(Color.valueOf("green"));
                        break;
                    case 2:
                        Answer2.setDisable(false);
                        Answer2.setTextFill(Color.valueOf("green"));
                        break;
                    case 3:
                        Answer3.setDisable(false);
                        Answer3.setTextFill(Color.valueOf("green"));
                        break;
                    case 4:
                        Answer4.setDisable(false);
                        Answer4.setTextFill(Color.valueOf("green"));
                        break;
                }
            }
            counterS = 1;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Vars.currentBackgroundValue <= 33){
            if(homeGrid != null) homeGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground1 +")");
            if(finishGrid != null) finishGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground1 +")");
            if(GameGrid != null) GameGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground1 +")");
        } else if (Vars.currentBackgroundValue > 33 && Vars.currentBackgroundValue <= 66) {
            if(homeGrid != null) homeGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground2 +")");
            if(finishGrid != null) finishGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground2 +")");
            if(GameGrid != null) GameGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground2 +")");
        }else{
            if(homeGrid != null) homeGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground3 +")");
            if(finishGrid != null) finishGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground3 +")");
            if(GameGrid != null) GameGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground3 +")");
        }
    }
    //HOMESTAGE END

    //Method for Layout
    public String layoutString (String a) {
       final int middle = a.length() / 2;   //getting middle of String
        if (a.length() > 13) {
            arrStr = new String[]{a.substring(0, middle), a.substring(middle)}; //splitting String in two sections
        }
       a = arrStr[0] + "-" + "\n" + arrStr[1]; //filling a with new layout String
    return a;
    }
}
