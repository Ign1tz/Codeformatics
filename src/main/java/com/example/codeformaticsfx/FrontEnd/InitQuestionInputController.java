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
        if(QuizzName.getText() == "" || AuthorName.getText() == "") {
            error.setOpacity(1);
        }else{
            if(!existsIn(QuizzName.getText().toLowerCase(), QuizList)) {
                nameNoSpace = QuizzName.getText().replace(" ", "").toLowerCase();
                if(file == null && directory.getText() != null){
                    Vars.logoPath = "default";
                }else{
                    Files.copy(Path.of(file.getAbsolutePath()), Path.of("src/main/resources/com/example/codeformaticsfx/GameResources/Logos/" + nameNoSpace + file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf('.'))));
                    Vars.logoPath = "com/example/codeformaticsfx/GameResources/Logos/" + nameNoSpace + file.getAbsolutePath().substring(file.getAbsolutePath().lastIndexOf('.'));
                }
                try {
                    writeQuestions.finish(QuizzName.getText(), AuthorName.getText(), questionNumber, nameNoSpace, Vars.logoPath);
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
    private boolean existsIn(String string, List<String> List){
        for(int i = 0; i <= List.size() - 1; i++){
            System.out.println(List.get(i));
            if(Objects.equals(string, List.get(i))){
                return true;
            }
        }
        return false;
    }
    public void initialize(URL location, ResourceBundle resources) {
        if(Vars.currentBackgroundValue <= 33){
            finishQuestionsGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground1 +")");
        } else if (Vars.currentBackgroundValue > 33 && Vars.currentBackgroundValue <= 66) {
            finishQuestionsGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground2 +")");
        }else{
            finishQuestionsGrid.setStyle("-fx-background-image: url("+ Vars.pathBackground3 +")");
        }
        QuizzName.getCharacters().toString();
        AuthorName.getCharacters().toString();
        numberOfQuestions.setMax(writeQuestions.getQuestionList().size() - 4);
        numberOfQuestions.setMin(3);
        numberOfQuestions.setValue(Math.ceil((writeQuestions.getQuestionList().size() - 4)/2));
        questionNumber = String.valueOf((int) numberOfQuestions.getValue());
        Number.setText(String.valueOf((int) numberOfQuestions.getValue()));
        if(numberOfQuestions.getMax() == numberOfQuestions.getMin()){
            numberOfQuestions.setDisable(true);
        }
        numberOfQuestions.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                questionNumber = String.valueOf((int) numberOfQuestions.getValue());
                Number.setText(String.valueOf((int) numberOfQuestions.getValue()));
            }
        });
    }
    @FXML
    private void Browse(){
        FileChooser dirCh = new FileChooser();
        dirCh.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image FIles", "*.png", "*.jpg", "*.gif")
        );
        Stage stage = (Stage) finishQuestionsGrid.getScene().getWindow();
        file = dirCh.showOpenDialog(stage);
        if(file != null){
            directory.setText(file.getAbsolutePath());
            Vars.logoPathAbsolut = file.getAbsolutePath();
        }
    }
}
