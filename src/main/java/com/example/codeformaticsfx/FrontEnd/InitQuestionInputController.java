package com.example.codeformaticsfx.FrontEnd;

import com.example.codeformaticsfx.Files.*;
import javafx.beans.value.*;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.nio.file.*;
import java.util.*;

public class InitQuestionInputController implements Initializable{
    @FXML
    private GridPane finishQuestionsGrid;
    private readWriteQuestions writeQuestions = WriteQuestionsGUIController.writeQuestions;
    private readWriteList rwL = new readWriteList();
    @FXML
    private TextField QuizzName, AuthorName, directory;
    @FXML
    private Label error, Number, NameError;
    @FXML
    private Slider numberOfQuestions;
    private String questionNumber;
    private File file;
    private List<String> QuizList;
    private String nameNoSpace;

    {
        try {
            QuizList = setQuiz();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> setQuiz() throws IOException {
        return rwL.fromFile();
    }
    public void startQuestion(ActionEvent event) throws IOException {
        if(QuizzName.getText() == "" || AuthorName.getText() == "") { //there has to be a QuizName and AuthorName
            error.setOpacity(1);
        }else{
            if(!existsIn(QuizzName.getText().replace(" ", "").toLowerCase(), QuizList)) { // looks if QuizName already exists
                nameNoSpace = QuizzName.getText().replace(" ", "").toLowerCase(); // removes Spaces
                if(file == null && directory.getText() != null){ //sets Logo if not chosen to default
                    Vars.logoPath = "default";
                }else{
                    Files.copy(Path.of(file.getAbsolutePath()), Path.of("src/main/resources/com/example/codeformaticsfx/GameResources/Logos/" + nameNoSpace + file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf('.')))); //copies Logo to logo folder
                    Vars.logoPath = "com/example/codeformaticsfx/GameResources/Logos/" + nameNoSpace + file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf('.')); // sets logo path for logo folder
                }
                try {
                    writeQuestions.finish(QuizzName.getText(), AuthorName.getText(), questionNumber, nameNoSpace, Vars.logoPath); //actually saves quiz
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeStage.fxml"));
                    Parent root = loader.load();
                    Scene scene = ((Node) event.getSource()).getScene();
                    scene.setRoot(root);
                } catch (NumberFormatException nfe) {
                }
            }else{
                NameError.setOpacity(1);
            }
        }
    }
    private boolean existsIn(String string, List<String> List){ //tests if String is in List
        for(int i = 0; i <= List.size() - 1; i++){
            System.out.println(List.get(i));
            if(Objects.equals(string, List.get(i))){
                return true;
            }
        }
        return false;
    }
    public void initialize(URL location, ResourceBundle resources) {
        if(Vars.currentBackgroundValue <= 33){ //deffines the background
            finishQuestionsGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground1 +")");
        } else if (Vars.currentBackgroundValue > 33 && Vars.currentBackgroundValue <= 66) {
            finishQuestionsGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground2 +")");
        }else{
            finishQuestionsGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground3 +")");
        }
        QuizzName.getCharacters().toString();
        AuthorName.getCharacters().toString();
        numberOfQuestions.setMax(writeQuestions.getQuestionList().size() - 4); // defines bounds of slider below
        numberOfQuestions.setMin(3);
        numberOfQuestions.setValue(Math.ceil((writeQuestions.getQuestionList().size() - 4)/2));

        questionNumber = String.valueOf((int) numberOfQuestions.getValue());
        Number.setText(String.valueOf((int) numberOfQuestions.getValue()));
        if(numberOfQuestions.getMax() == numberOfQuestions.getMin()){
            numberOfQuestions.setDisable(true);
        }
        numberOfQuestions.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) { //updatest when slider changes
                questionNumber = String.valueOf((int) numberOfQuestions.getValue());
                Number.setText(String.valueOf((int) numberOfQuestions.getValue()));
            }
        });
    }
    @FXML
    private void Browse(){
        FileChooser dirCh = new FileChooser();
        dirCh.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image FIles", "*.png", "*.jpg", "*.gif")); //only allows these types of files
        Stage stage = (Stage) finishQuestionsGrid.getScene().getWindow();
        file = dirCh.showOpenDialog(stage);
        if(file != null){
            directory.setText(file.getAbsolutePath());
        }
    }
}
