package com.example.codeformaticsfx.FrontEnd;

import com.example.codeformaticsfx.Files.EncodeDecode;
import com.example.codeformaticsfx.Files.Vars;
import com.example.codeformaticsfx.Files.readWriteList;
import com.example.codeformaticsfx.Files.readWriteQuestions;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    @FXML
    private ChoiceBox chooseQuiz;
    @FXML
    public GridPane SettingsGrid;
    @FXML
    private Button musicButton, saveButton, newQuiz;
    @FXML
    private CheckBox CheckSound;
    @FXML
    private Slider sliderSettings;
    @FXML
    private Label Label1;
    @FXML
    private TextField myNameText;
    private ArrayList<File> songs;
    private File directory;
    private File[] files;
    private int songNumber;
    private Media media;
    private static String name;
    public boolean nameGiven = false;
    private static boolean soundOn = false;

    public void createQuizz(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("QuestionInput.fxml"));
        Parent root = loader.load();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);
    }

    public void submit(ActionEvent event) {
        try {
            name = myNameText.getText();                //name can be typed into the field
            if (name.length() >= 9) {
                Label1.setText("Max. 8 characters ");    //usernames with more than 8 characters are not allowed
            } else {
                Label1.setText("Welcome " + name + "!");
                nameGiven = true;
            }
        } catch (Exception e) {
            Label1.setText("error");
        }
    }

    public void switchStart(ActionEvent event) throws IOException {
        Vars.currentQuiz = chooseQuiz.getValue().toString();
        Vars.currentQuiz = (String) chooseQuiz.getValue();
        Vars.pathQuestions = "src/main/resources/com/example/codeformaticsfx/GameResources/QuestionLibrary/" + Vars.currentQuiz + ".json";
        Vars.pathScoreboard = "src/main/resources/com/example/codeformaticsfx/GameResources/Scoreboards/" + Vars.currentQuiz + "Scoreboard.json";
        readWriteQuestions rwq = new readWriteQuestions();
        EncodeDecode ed = new EncodeDecode();
        if (ed.decodeSingle(rwq.readQuizz(Vars.pathQuestions).logoPath).equals("default")) {
            Vars.logoPath = "com/example/codeformaticsfx/GameResources/Logos/default.jpeg";
        } else {
            Vars.logoPath = ed.decodeSingle(rwq.readQuizz(Vars.pathQuestions).logoPath);
        }
        Vars.isHome = true;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeStage.fxml"));
        Parent root = loader.load();
        SceneController controller = loader.getController();
        Scene scene = ((Node) event.getSource()).getScene();
        scene.setRoot(root);

    }

    public void playSong() {
        if (CheckSound.isSelected()) {  //if checkbox is selected play song, if not pause
            Vars.mediaPlayer.play();
            CheckSound.setText("ON");
            Vars.mediaPlayer.setAutoPlay(true);
            Vars.mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            Vars.soundIsOn = true;
            //mediaPlayer.getOnPlaying();
        } else {
            Vars.mediaPlayer.pause();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> quizNameList = FXCollections.observableList(Vars.listOfQuestions);
        chooseQuiz.setItems(quizNameList);
        chooseQuiz.setValue(Vars.currentQuiz);
        if (Vars.soundIsOn) {
            CheckSound.setSelected(true);
        }
        songs = new ArrayList<>();
        directory = new File("src/main/resources/com/example/codeformaticsfx/FrontEnd/music"); //path to file
        files = directory.listFiles();      //stores music in file
        if (files != null && !Vars.soundIsOn) {
            //add music in arraylist
            songs.addAll(Arrays.asList(files));
            media = new Media(songs.get(songNumber).toURI().toString());
            Vars.mediaPlayer = new MediaPlayer(media);
        }
        sliderSettings.setValue(Vars.currentBackgroundValue);
        if (sliderSettings.getValue() <= 33) {
            SettingsGrid.setStyle("-fx-background-image: url(" + Vars.pathBackground1 + ")");
        } else if (sliderSettings.getValue() > 33 && sliderSettings.getValue() <= 66) {
            SettingsGrid.setStyle("-fx-background-image: url(" + Vars.pathBackground2 + ")");
        } else {
            SettingsGrid.setStyle("-fx-background-image: url(" + Vars.pathBackground3 + ")");
        }
        sliderSettings.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (sliderSettings.getValue() <= 33) {
                    SettingsGrid.setStyle("-fx-background-image: url(" + Vars.pathBackground1 + ")");
                } else if (sliderSettings.getValue() > 33 && sliderSettings.getValue() <= 66) {
                    SettingsGrid.setStyle("-fx-background-image: url(" + Vars.pathBackground2 + ")");
                } else {
                    SettingsGrid.setStyle("-fx-background-image: url(" + Vars.pathBackground3 + ")");
                }
                Vars.currentBackgroundValue = (int) sliderSettings.getValue();
            }
        });
    }
}
