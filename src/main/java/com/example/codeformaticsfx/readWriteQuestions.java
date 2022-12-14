package com.example.codeformaticsfx;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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

    public List<readWriteQuestions> questionList(String filePath) throws IOException {
        List<readWriteQuestions> temp = readQuestion(filePath);
        return temp;
    }

    public void writeQuestions(String filePath) throws IOException {
        EncodeDecode encodeDecode = new EncodeDecode();
        List<readWriteQuestions> tempList = new ArrayList<>();
        if(questionList(filePath) != null){
            tempList = questionList(filePath);
        }
        Writer writer = Files.newBufferedWriter(Paths.get(filePath));
        Scanner scan = new Scanner(System.in);
        int count = 1, difficulty = 0;
        boolean stop = false, isInside = false;
        String difficultyS = "";
        while (!stop) {
            String unnessary = scan.nextLine();
            System.out.print("Question: ");
            String question = scan.nextLine();
            System.out.print("A1: ");
            String a1 = scan.nextLine();
            System.out.print("A2: ");
            String a2 = scan.nextLine();
            System.out.print("A3: ");
            String a3 = scan.nextLine();
            System.out.print("A4: ");
            String a4 = scan.nextLine();
            System.out.print("Correct answer: ");
            String answer = scan.nextLine();
            while (!isInside) {
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
            }
            encodeDecode.encodeQuestionBase64(question, a1, a2, a3, a4, difficultyS, answer);
            newQuestion = new readWriteQuestions(encodeDecode.encodedQuestion, encodeDecode.encodedA1, encodeDecode.encodedA2, encodeDecode.encodedA3, encodeDecode.encodedA4, encodeDecode.encodedDifficulty, encodeDecode.encodedRightAnswer);
            tempList.add(newQuestion);
            System.out.print("Another Question? ");
            String choice = scan.next();
            count++;
            if(Objects.equals(choice, "n")){
                stop = true;
            }
        }
        gson.toJson(tempList, writer);
        writer.close();
    }

    private List<readWriteQuestions> readQuestion(String filePath) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        List<readWriteQuestions> tempList = new Gson().fromJson(reader, new TypeToken<List<readWriteQuestions>>() {}.getType());
        reader.close();
        return tempList;
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
        test.writeQuestions("./QuestionLibrary/test.json");
    }
}
