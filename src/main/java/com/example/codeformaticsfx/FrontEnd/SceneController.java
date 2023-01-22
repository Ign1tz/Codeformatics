package com.example.codeformaticsfx.FrontEnd;

import com.example.codeformaticsfx.Files.*;
import com.example.codeformaticsfx.Quizz.QuestionJoker;
import com.example.codeformaticsfx.pickQuestions;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class SceneController implements Initializable {

    //FXML USAGE START
    @FXML
    public GridPane Warning, homeGrid, finishGrid, GameGrid;
    @FXML
    public Button exit, stay, OptionsButton, StartButton, Return, twentyfive, jokerSeventy, jokerFifty, newQuestion, nextQuestion;
    @FXML
    private Label scoreboardLabel, Score, FinalScore, Questions;
    @FXML
    private CheckBox Answer1, Answer2, Answer3, Answer4;
    @FXML
    private TextField nameScoreboard, myNameText;
    @FXML
    private ImageView myImageView;
    @FXML
    private Button exitGame;
    //FXML USAGE END

    //VARIABLES START
    static String name;
    private static Stage primaryStage;
    static List<Integer> diffList, easy, medium, hard;
    static int question = 0;
    static List<readWriteQuestions> questionsList;
    static String selected, right, numberOfQuestions, score = "00000";
    static boolean nameGiven = false, firstTime = true;
    private static Scene scene;
    int counterF, counterS, counterTF, counterN;
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
        Vars.isHome = true;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeStage.fxml"));
        Parent root = loader.load();
        SceneController controller = loader.getController();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }

    public void switchSettings(ActionEvent event) throws IOException {
        Vars.isHome = false;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsStage.fxml"));
        Parent root = loader.load();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }
    //SWITCH SCENES END

    //HOMESTAGE START
    public void switchGame(ActionEvent event) throws IOException, InterruptedException {
        Vars.isHome = false;
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
        controller.Answer1.setText(layoutString(eD.decodeSingle(temp.A1)));
        controller.Answer2.setText(layoutString(eD.decodeSingle(temp.A2)));
        controller.Answer3.setText(layoutString(eD.decodeSingle(temp.A3)));
        controller.Answer4.setText(layoutString(eD.decodeSingle(temp.A4)));
        assert thisQuestion != null;
        controller.Questions.setText(layoutString(thisQuestion));
        controller.Score.setText("Score: 00000");
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
        question++;
    }

    //END GAME SCENE
    public void Return() {  //return button
        Warning.setOpacity(1);
        Warning.setDisable(false);  //GridPane is now visible and can be used
        exit.setDisable(false);
        stay.setDisable(false);     ////exit and stay button are usable
        Answer1.setDisable(true);
        Answer2.setDisable(true);
        Answer3.setDisable(true);
        Answer4.setDisable(true);   //every answer in game cannot be used
        twentyfive.setDisable(true);
        jokerSeventy.setDisable(true);
        newQuestion.setDisable(true);
        jokerFifty.setDisable(true);
        nextQuestion.setDisable(true);  //every button in game cannot be used
    }

    public void Stay() {
        Warning.setOpacity(0);
        Warning.setDisable(true);       //GridPane cannot be used and seen
        exit.setDisable(true);
        stay.setDisable(true);      //exit and stay button cannot be used as well
        Answer1.setDisable(false);
        Answer2.setDisable(false);
        Answer3.setDisable(false);
        Answer4.setDisable(false);
        twentyfive.setDisable(false);
        jokerSeventy.setDisable(false);
        newQuestion.setDisable(false);
        jokerFifty.setDisable(false);
        nextQuestion.setDisable(false); //every button and answer can be used
    }

    public void Exit(ActionEvent event) throws IOException {
        Vars.isHome = true;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeStage.fxml"));
        Parent root = loader.load();
        SceneController controller = loader.getController();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
        score = "0";
    }

    public void toScoreboard(ActionEvent event) throws IOException {
        readWriteScoreboard rwS = new readWriteScoreboard();
        if (!Objects.equals(name, null) && !name.equals(" ")) {
            if (myNameText != null) {
                nameScoreboard.setText(myNameText.getText());
            }
            if (!nameGiven) {
                name = nameScoreboard.getText();
            }
            System.out.println(Vars.pathScoreboard);
            rwS.writeToScoreboard(name, score, String.valueOf(1), Vars.pathScoreboard);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Scoreboard.fxml"));
            Parent root = loader.load();
            Scene scene = ((Node) event.getSource()).getScene();
            scene.setRoot(root);
            score = "00000";
            name = null;
        } else {
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
        if (Objects.equals(selected, right)) {
            score = String.valueOf(Integer.parseInt(score) + 100);
        }
        if (selected == null) {

        } else {
            if (counterS == 1) {
                Answer1.setTextFill(Color.valueOf("#e91e63"));
                Answer2.setTextFill(Color.valueOf("#e91e63"));
                Answer3.setTextFill(Color.valueOf("#e91e63"));
                Answer4.setTextFill(Color.valueOf("#e91e63"));
            }
            StringBuilder finalScore = new StringBuilder(score);
            for (int nulls = 5; nulls > score.length(); nulls--) {
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
            Questions.setText(layoutString(thisQuestion));
            Answer1.setText(layoutString(eD.decodeSingle(temp.A1)));
            Answer2.setText(layoutString(eD.decodeSingle(temp.A2)));
            Answer3.setText(layoutString(eD.decodeSingle(temp.A3)));
            Answer4.setText(layoutString(eD.decodeSingle(temp.A4)));
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
    // 50/50 Joker
    public void jokerF() {
        // check if joker was used
        if (counterF == 0) {
            String A1 = Answer1.getText();
            String A2 = Answer2.getText();
            String A3 = Answer3.getText();
            String A4 = Answer4.getText();
            String rightAnswer = right;
            int range = 4;
            int rightnumber = 0;

            // get right answer
            if ("A1".equals(rightAnswer)) {
                rightnumber = 1;
            }
            if ("A2".equals(rightAnswer)) {
                rightnumber = 2;
            }
            if ("A3".equals(rightAnswer)) {
                rightnumber = 3;
            }
            if ("A4".equals(rightAnswer)) {
                rightnumber = 4;
            }

            questionJoker.jokerFiftyFifty(A1, A2, A3, A4, rightAnswer, range, rightnumber);

            Answer1.setDisable(true);
            Answer2.setDisable(true);
            Answer3.setDisable(true);
            Answer4.setDisable(true);
            Answer1.setSelected(false); //removes Selection of (possible) wrong awnser resulting in the possible selection of multiple awnsers
            Answer2.setSelected(false);
            Answer3.setSelected(false);
            Answer4.setSelected(false);

            // show right answer
            switch (questionJoker.getRightAnswer()) {
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

            // show random answer
            switch (questionJoker.getRandomAnswer()) {
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
            jokerFifty.setDisable(true);
        }
    }

    public void jokerTF(ActionEvent actionEvent) {
        // check if joker was used
        if (counterTF == 0) {
            Answer1.setDisable(true);
            Answer2.setDisable(true);
            Answer3.setDisable(true);
            Answer4.setDisable(true);
            Answer1.setSelected(false); //removes Selection of (possible) wrong answer resulting in the possible selection of multiple answers
            Answer2.setSelected(false);
            Answer3.setSelected(false);
            Answer4.setSelected(false);

            String A1 = Answer1.getText();
            String A2 = Answer2.getText();
            String A3 = Answer3.getText();
            String A4 = Answer4.getText();
            String rightAnswer = right;
            int range = 4;
            int rightnumber = 0;

            // get right answer
            if ("A1".equals(rightAnswer)) {
                rightnumber = 1;
            }
            if ("A2".equals(rightAnswer)) {
                rightnumber = 2;
            }
            if ("A3".equals(rightAnswer)) {
                rightnumber = 3;
            }
            if ("A4".equals(rightAnswer)) {
                rightnumber = 4;
            }


            questionJoker.jokerTwentyFive(A1, A2, A3, A4, rightAnswer, range, rightnumber);

            Answer1.setDisable(false);
            Answer2.setDisable(false);
            Answer3.setDisable(false);
            Answer4.setDisable(false);
            Answer1.setSelected(false); //removes Selection of (possible) wrong awnser resulting in the possible selection of multiple awnsers
            Answer2.setSelected(false);
            Answer3.setSelected(false);
            Answer4.setSelected(false);

            // disable random answer
            switch (questionJoker.getRandomAnswer()) {
                case 1:
                    Answer1.setDisable(true);
                    break;
                case 2:
                    Answer2.setDisable(true);
                    break;
                case 3:
                    Answer3.setDisable(true);
                    break;
                case 4:
                    Answer4.setDisable(true);
                    break;
            }
            counterTF = 1;
            twentyfive.setDisable(true);
        }
    }


    public void jokerNew(ActionEvent actionEvent) {
    }


    public void jokerS() {
        // check if joker was used
        if (counterS == 0) {
            Answer1.setDisable(true);
            Answer2.setDisable(true);
            Answer3.setDisable(true);
            Answer4.setDisable(true);
            Answer1.setSelected(false); //removes Selection of (possible) wrong answer resulting in the possible selection of multiple answers
            Answer2.setSelected(false);
            Answer3.setSelected(false);
            Answer4.setSelected(false);

            String A1 = Answer1.getText();
            String A2 = Answer2.getText();
            String A3 = Answer3.getText();
            String A4 = Answer4.getText();
            String rightAnswer = right;
            int range = 4;
            int rightnumber = 0;

            // get right answer
            if ("A1".equals(rightAnswer)) {
                rightnumber = 1;
            }
            if ("A2".equals(rightAnswer)) {
                rightnumber = 2;
            }
            if ("A3".equals(rightAnswer)) {
                rightnumber = 3;
            }
            if ("A4".equals(rightAnswer)) {
                rightnumber = 4;
            }


            int border = questionJoker.jokerSeventyThirty(A1, A2, A3, A4, rightAnswer, range, rightnumber);

            // see if 70% has right answer
            if (border <= 7) {
                switch (questionJoker.getRightAnswer()) {
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
                switch (questionJoker.getRandomAnswer()) {
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
            } else {
                // if 30% has right answer
                switch (questionJoker.getRightAnswer()) {
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
                switch (questionJoker.getRandomAnswer()) {
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
            jokerSeventy.setDisable(true);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        readWriteList rwl = new readWriteList();
        if (firstTime) {
            try {
                Vars.listOfQuestions = rwl.fromFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            firstTime = false;
        }
        if (Vars.isHome) {
            Image image = new Image(Vars.logoPath);
            myImageView.setImage(image);
        }
        if (Vars.currentBackgroundValue <= 33) {
            if (homeGrid != null) homeGrid.setStyle("-fx-background-image: url(" + Vars.pathBackground1 + ")");
            if (finishGrid != null) finishGrid.setStyle("-fx-background-image: url(" + Vars.pathBackground1 + ")");
            if (GameGrid != null) GameGrid.setStyle("-fx-background-image: url(" + Vars.pathBackground1 + ")");
        } else if (Vars.currentBackgroundValue > 33 && Vars.currentBackgroundValue <= 66) {
            if (homeGrid != null) homeGrid.setStyle("-fx-background-image: url(" + Vars.pathBackground2 + ")");
            if (finishGrid != null) finishGrid.setStyle("-fx-background-image: url(" + Vars.pathBackground2 + ")");
            if (GameGrid != null) GameGrid.setStyle("-fx-background-image: url(" + Vars.pathBackground2 + ")");
        } else {
            if (homeGrid != null) homeGrid.setStyle("-fx-background-image: url(" + Vars.pathBackground3 + ")");
            if (finishGrid != null) finishGrid.setStyle("-fx-background-image: url(" + Vars.pathBackground3 + ")");
            if (GameGrid != null) GameGrid.setStyle("-fx-background-image: url(" + Vars.pathBackground3 + ")");
        }
    }

    public void endAll(ActionEvent event) {
       primaryStage.close();    //closes stages, game and window
    }
    //HOMESTAGE END

    //Method for Layout
    public String layoutString(String string) {
        boolean newLine = false;
        String[] arrStr = {"", ""};     //String is defined
        char[] check = string.toCharArray(); //create new Array with string as chars
        final int middle = string.length() / 2;   //getting middle of String
        if (string.length() > 13) {         //if string is bigger than 13 characters it should be splitted
            if (check[middle] == ' ') { //if middle of array is a space, split string
                arrStr = new String[]{string.substring(0, middle), string.substring(middle)}; //splitting String in two sections
            } else {
                for (int x = middle + 1; x < check.length; x++) {
                    if (check[x] == ' ') {
                        arrStr = new String[]{string.substring(0, x), string.substring(x)}; //if middle isn't a space search for the closest
                        newLine = true;
                        break;
                    }
                }
            }
            string = arrStr[0] + "\n" + arrStr[1].stripLeading(); //filling a with new layout String
        }
        if(!newLine){
            string = String.valueOf(check);
        }
        return string;
    }
}
