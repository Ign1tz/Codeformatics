package com.example.codeformaticsfx.Files;

import com.example.codeformaticsfx.Files.GUI.WriteQuestionsGUI;
import com.example.codeformaticsfx.Files.GUI.WriteQuestionsGUIController;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class readWriteQuestions {
    private static readWriteQuestions newQuestion;
    private static List<readWriteQuestions> QuestionList = new ArrayList<>();
    public List<readWriteQuestions> getQuestionList(){
        return this.QuestionList;
    }
    public String QUESTION, A1, A2, A3, A4, DIFFICULTY, RIGHTAWNSER;

    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public readWriteQuestions(String eQuestion, String eA1, String eA2, String eA3, String eA4, String eDifficulty, String eAnswer) throws IOException {
        this.QUESTION = eQuestion;
        this.A1 = eA1;
        this.A2 = eA2;
        this.A3 = eA3;
        this.A4 = eA4;
        this.DIFFICULTY = eDifficulty;
        this.RIGHTAWNSER = eAnswer;
    }
    public readWriteQuestions(){

    }
    public QuizzInfo Quizz(String filePath) throws IOException {
        QuizzInfo temp = readQuizz(filePath);
        return temp;
    }

    public List<readWriteQuestions> questionList(String filePath) throws IOException {
        QuizzInfo Qtemp = Quizz(filePath);
        List<readWriteQuestions> temp = new ArrayList<>();
        if(Qtemp != null){

            temp = Qtemp.questionsList;
        }
        return temp;
    }

    public void writeQuestions(String Question, String A1, String A2, String A3, String A4, String Difficulty, String Answer) throws IOException {
        WriteQuestionsGUIController GUIInput = new WriteQuestionsGUIController();
        EncodeDecode encodeDecode = new EncodeDecode();
        /*Scanner scan = new Scanner(System.in);
        String unnessary, authorname, quizzname, questionsUsed;
        List<readWriteQuestions> tempList = new ArrayList<>();
        if(questionList(filePath) != null) {
            QuizzInfo temp = Quizz(filePath);
            unnessary = scan.nextLine();
            if(temp != null) {
                if(temp.questionsList != null){
                    tempList = temp.questionsList;
                }
                if (temp.AuthorName == null) {
                    System.out.print("Author Name: ");
                    authorname = scan.nextLine();
                } else {
                    authorname = encodeDecode.decodeSingle(temp.AuthorName);
                }
                if (temp.Quizzname == null) {
                    System.out.print("Quizzname: ");
                    quizzname = scan.nextLine();
                } else {
                    quizzname = encodeDecode.decodeSingle(temp.Quizzname);
                }
                if (temp.questionsUsed == null) {
                    System.out.print("How many Questions will be used?: ");
                    questionsUsed = scan.nextLine();
                } else {
                    questionsUsed = encodeDecode.decodeSingle(temp.questionsUsed);
                }
            } else{
                unnessary = scan.nextLine();
                System.out.print("Author Name: ");
                authorname = scan.nextLine();
                System.out.print("Quizzname: ");
                quizzname = scan.nextLine();
                System.out.print("How many Questions will be used?: ");
                questionsUsed = scan.nextLine();
            }
        }else{
            unnessary = scan.nextLine();
            System.out.print("Author Name: ");
            authorname = scan.nextLine();
            System.out.print("Quizzname: ");
            quizzname = scan.nextLine();
            System.out.print("How many Questions will be used?: ");
            questionsUsed = scan.nextLine();
        }
        int difficulty = 0;
        boolean stop = false, isInside = false;
        String difficultyS = "";
        //while (!stop) {
            //unnessary = scan.nextLine();*/
            System.out.print("Question: ");
            String question = Question;
            System.out.print("A1: ");
            String a1 = A1;
            System.out.print("A2: ");
            String a2 = A2;
            System.out.print("A3: ");
            String a3 = A3;
            System.out.print("A4: ");
            String a4 = A4;
            System.out.print("Correct answer: ");
            String answer = Answer;
            /*while (!isInside) {
                System.out.print("Difficulty Level [1-3]: ");
                difficulty = scan.nextInt();
                if (difficulty < 4 && difficulty > 0) {
                    isInside = true;
                }
            }
            isInside = false;
            switch (difficulty) {
                case 1:
                    difficultyS = "Easy";
                    break;
                case 2:
                    difficultyS = "Medium";
                    break;
                case 3:
                    difficultyS = "Hard";
                    break;
            }*/
            encodeDecode.encodeQuestionBase64(question, a1, a2, a3, a4, Difficulty, answer);
            newQuestion = new readWriteQuestions(encodeDecode.encodedQuestion, encodeDecode.encodedA1, encodeDecode.encodedA2, encodeDecode.encodedA3, encodeDecode.encodedA4, encodeDecode.encodedDifficulty, encodeDecode.encodedRightAnswer);
            QuestionList.add(newQuestion);
            //System.out.print("Another Question? ");
        //}
    }
    public void finish(String filePath, String quizzname, String authorname, String questionsUsed) throws IOException {
        Writer writer = Files.newBufferedWriter(Paths.get(filePath));
        File file = new File(filePath);
        if(file.createNewFile()){
            System.out.println("ehhh");
        }
        EncodeDecode encodeDecode = new EncodeDecode();
        encodeDecode.encodeQuizzInfo(quizzname, authorname, questionsUsed);
        QuizzInfo thisQuizz = new QuizzInfo(encodeDecode.encodedQuizzName, encodeDecode.encodedAuthorname, encodeDecode.encodedQuestionsUsed, getQuestionList());
        gson.toJson(thisQuizz, writer);
        writer.close();
    }

    private List<readWriteQuestions> readQuestion(String filePath) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        List<readWriteQuestions> tempList = new Gson().fromJson(reader, new TypeToken<List<readWriteQuestions>>() {}.getType());
        reader.close();
        return tempList;
    }
    private QuizzInfo readQuizz(String filePath) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        QuizzInfo temp = new Gson().fromJson(reader, new TypeToken<QuizzInfo>() {}.getType());
        reader.close();
        return temp;
    }

    public static void main(String[] args) throws IOException {
        File theDir = new File("./QuestionLibrary");
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        File newFile = new File("./QuestionLibrary/test.json");
        if (!newFile.exists()){
            Files.createFile(Path.of("./QuestionLibrary/test.json"));
        }
        readWriteQuestions test = new readWriteQuestions();
        //test.writeQuestions("./QuestionLibrary/test.json");
    }
}
