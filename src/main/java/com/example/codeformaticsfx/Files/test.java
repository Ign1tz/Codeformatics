package com.example.codeformaticsfx.Files;

import com.example.codeformaticsfx.pickQuestions;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class test {
    public void testing() throws IOException {
        pickQuestions temp = new pickQuestions();
        readWriteQuestions rwq = new readWriteQuestions();
        EncodeDecode eD = new EncodeDecode();
        readWriteQuestions test = new readWriteQuestions();
        temp.loadQuestionDifficulty();
        List<Integer> easy = temp.easy;
        List<Integer> medium = temp.medium;
        List<Integer> hard = temp.hard;
        temp.testQuestion();
        int idk;
        List<Integer> difficulty = temp.questionListDiff;
        QuizzInfo thisQuizz = rwq.Quizz("./GameResources/QuestionLibrary/Java.json");
        for(int i = 1; i <= Integer.parseInt(eD.decodeSingle(thisQuizz.questionsUsed)); i++){
            int thisQuestion = difficulty.get(i - 1);
            switch (thisQuestion){
                case 1:
                    idk = randomNumber(easy.size() - 1);
                    test = thisQuizz.questionsList.get(easy.get(idk));
                    easy.remove(idk);
                    break;
                case 2:
                    idk = randomNumber(medium.size() - 1);
                    test = thisQuizz.questionsList.get(medium.get(idk));
                    medium.remove(idk);
                    break;
                case 3:
                    idk = randomNumber(hard.size() - 1);
                    test = thisQuizz.questionsList.get(hard.get(idk));
                    hard.remove(idk);
                    break;
            }
            eD.decodeQuestion(test);
            System.out.println(eD.decodedQuestion);
            System.out.println(eD.decodedA1);
            System.out.println(eD.decodedA2);
            System.out.println(eD.decodedA3);
            System.out.println(eD.decodedA4);
            System.out.println();
        }
    }

    private int randomNumber(int high){
        int low = 1, random;
        Random rand = new Random();
        random =  rand.nextInt(high) + low;
        return random;
    }
    public static void main(String[] args) throws IOException {
        test test = new test();
        test.testing();
    }
}
