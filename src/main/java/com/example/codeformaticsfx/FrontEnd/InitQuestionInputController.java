package com.example.codeformaticsfx.FrontEnd;

import com.example.codeformaticsfx.Files.readWriteList;
import com.example.codeformaticsfx.Files.readWriteQuestions;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class InitQuestionInputController implements Initializable{
    private readWriteQuestions writeQuestions = WriteQuestionsGUIController.writeQuestions;
    private readWriteList rwL = new readWriteList();
    @FXML
    private TextField QuizzName, AuthorName;
    @FXML
    private Label error, Number, NameError;
    @FXML
    private Slider numberOfQuestions;
    private Stage stage;
    private Scene scene;
    private String questionNumber;
    private List<String> QuizList;

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
        if(QuizzName.getText() == "" && AuthorName.getText() == "") {
            error.setOpacity(1);
        }else{
            if(!existsIn(QuizzName.getText().toLowerCase(), QuizList)) {
                try {
                    String filePath = "./GameResources/QuestionLibrary/" + QuizzName.getText().toLowerCase() + ".json";
                    writeQuestions.finish(filePath, QuizzName.getText(), AuthorName.getText(), questionNumber, QuizzName.getText().toLowerCase());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("SettingsStage.fxml"));
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
}
