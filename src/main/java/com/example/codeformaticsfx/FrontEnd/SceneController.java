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
import javafx.stage.Stage;


import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SceneController extends HomeStage {

    private Stage primaryStage;

    @FXML
    public Button musicButton;
    @FXML
    private Button OptionsButton;
    @FXML
    private Label nameLabel;
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
    private CheckBox Answer1 = new CheckBox("test");

    String name;


    static List<Integer> diffList;
    static List<Integer> easy;
    static List<Integer> medium;
    static List<Integer> hard;
    static int question = 0;
    static List<readWriteQuestions> questionsList;



    public void setPrimaryStage(Stage stage) {          //Implement the main primary Stage from HomeStage to SceneController
        this.primaryStage = stage;                      //Used for modifying the main Window/Stage
    }

    @FXML
    public void updateQuestion() throws IOException {
        if(question >= diffList.size()){
            return;
        }
        EncodeDecode eD = new EncodeDecode();
        int random;
        String thisQuestion = null;
        readWriteQuestions temp = null;
        switch (diffList.get(question)){
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
        question++;
    }

    public Scene getScene(){
        return scene;
    }


    public void submit(ActionEvent event) {
        try {
            name = myNameText.getText();
            System.out.println(name);
            if (name.length() >= 9) {
                nameLabel.setText("Max. 8 characters ");    //usernames with more than 8 characters are not allowed
            } else {
                nameLabel.setText("Welcome " + name + "!");
            }
        } catch (Exception e) {
            nameLabel.setText("error");
        }
    }


    //Switching between two Scenes
    private Stage stage;
    private Parent root;
    private Scene scene;

   public void switchStart(ActionEvent event) throws IOException {
       root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeStage.fxml")));
       Stage window = (Stage) StartButton.getScene().getWindow();
       window.setScene(new Scene(root, window.getWidth(),window.getHeight()));
   }
    public void switchSettings(ActionEvent event) throws IOException{
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("SettingsStage.fxml")));
        Stage home = (Stage) OptionsButton.getScene().getWindow();
        home.setScene(new Scene(root, home.getWidth(),home.getHeight()));
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
        readWriteQuestions temp = null;
        int random;
        String thisQuestion = null;
        switch (diffList.get(question)){
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

        System.out.println(eD.decodeSingle(temp.A1));

        //This part initialises the Design representation of the new Scene.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Game.fxml"));
        Parent root = loader.load();
        SceneController controller = loader.getController();
        controller.Answer1.setText(eD.decodeSingle(temp.A1));
        controller.Answer2.setText(eD.decodeSingle(temp.A2));
        controller.Answer3.setText(eD.decodeSingle(temp.A3));
        controller.Answer4.setText(eD.decodeSingle(temp.A4));
        controller.Questions.setText(thisQuestion);
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
        question++;
    }

    public void Return(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeStage.fxml"));
        Parent root = loader.load();
        SceneController controller = loader.getController();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }

    private int randomNumber(int high){
        int low = 1, random;
        Random rand = new Random();
        random =  rand.nextInt(high) + low;
        return random;
    }

}
