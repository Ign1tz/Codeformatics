package com.example.codeformaticsfx.Files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class readWriteQuestions { //this class handles creating the questions, writing them as a json to a file and reading them from said file
    private static List<readWriteQuestions> QuestionList = new ArrayList<>();
    public List<readWriteQuestions> getQuestionList(){
        return this.QuestionList;
    }
    public String QUESTION, A1, A2, A3, A4, DIFFICULTY, RIGHTAWNSER;
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public readWriteQuestions(String eQuestion, String eA1, String eA2, String eA3, String eA4, String eDifficulty, String eAnswer) {
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
    public List<readWriteQuestions> questionList(String filePath) throws IOException {
        QuizzInfo Qtemp = readQuizz(filePath);
        List<readWriteQuestions> temp = new ArrayList<>();
        if(Qtemp != null){
            temp = Qtemp.questionsList;
        }
        return temp;
    }
    public void writeQuestions(String Question, String A1, String A2, String A3, String A4, String Difficulty, String Answer) throws IOException {
        EncodeDecode encodeDecode = new EncodeDecode();
        encodeDecode.encodeQuestionBase64(Question, A1, A2, A3, A4, Difficulty, Answer);
        readWriteQuestions newQuestion = new readWriteQuestions(encodeDecode.encodedQuestion, encodeDecode.encodedA1, encodeDecode.encodedA2, encodeDecode.encodedA3, encodeDecode.encodedA4, encodeDecode.encodedDifficulty, encodeDecode.encodedRightAnswer);
        QuestionList.add(newQuestion);
    }
    public void finish(String quizzname, String authorname, String questionsUsed, String QuizName, String logoPath) throws IOException {
        String quizFilePath = "src/main/resources/com/example/codeformaticsfx/GameResources/QuestionLibrary/" + QuizName + ".json";
        String scoreboardFilePath = "src/main/resources/com/example/codeformaticsfx/GameResources/Scoreboards/" + QuizName + "Scoreboard.json";
        readWriteList rwL = new readWriteList(QuizName);
        Writer writer = Files.newBufferedWriter(Paths.get(quizFilePath));;
        Writer writer2 = Files.newBufferedWriter(Paths.get(scoreboardFilePath));
        File quizFile = new File(quizFilePath);
        if(quizFile.createNewFile()){
            System.out.println("ehhh");
        }
        EncodeDecode encodeDecode = new EncodeDecode();
        encodeDecode.encodeQuizzInfo(quizzname, authorname, questionsUsed);
        QuizzInfo thisQuizz = new QuizzInfo(encodeDecode.encodedQuizzName, encodeDecode.encodedAuthorname, encodeDecode.encodedQuestionsUsed, encodeDecode.encodeSinge(logoPath), getQuestionList());
        gson.toJson(thisQuizz, writer);
        writer.close();
        writer2.close();
        rwL.writeList(QuizName);
        QuestionList = new ArrayList<>();
    }
    public QuizzInfo readQuizz(String filePath) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        QuizzInfo temp = new Gson().fromJson(reader, new TypeToken<QuizzInfo>() {}.getType());
        reader.close();
        return temp;
    }
}
