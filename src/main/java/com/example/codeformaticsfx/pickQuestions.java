package com.example.codeformaticsfx;

import com.example.codeformaticsfx.Files.EncodeDecode;
import com.example.codeformaticsfx.Files.readWriteQuestions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;

public class pickQuestions {
    public List<Integer> easy = new ArrayList<>();
    public List<Integer> medium = new ArrayList<>();
    public List<Integer> hard = new ArrayList<>();
    private int numberOfEasyQuestions, numberOfMediumQuestions, numberOfHardQuestions;
    private readWriteQuestions temp = new readWriteQuestions();
    public List<readWriteQuestions> questionList = temp.questionList("./GameResources/QuestionLibrary/Java.json");
    public List<Integer> questionListDiff = new ArrayList<>();

    public pickQuestions() throws IOException {
    }

    public void loadQuestionDifficulty(){
        readWriteQuestions question;
        String difficulty;
        for(int place = 0; place < questionList.size(); place++){
            question = questionList.get(place);
            byte[] eDifficulty = Base64.getDecoder().decode(question.DIFFICULTY);
            difficulty = new String(eDifficulty);
            switch (difficulty){
                case "Easy":
                    easy.add(place);
                    break;
                case "Medium":
                    medium.add(place);
                    break;
                case  "Hard":
                    hard.add(place);
                    break;
            }
        }
    }

    /*public readWriteQuestions activeQuestion(List<Integer> probList, int lastQuestion, int numberOfQuestions, int currentQuestion){
        List<Integer> probListTemp = updateProb(probList, lastQuestion, numberOfQuestions,currentQuestion);
        int probEasy = probListTemp.get(0), probMedium = probListTemp.get(1), probHard = probListTemp.get(2);
        int questionIndex = randomNumber(probEasy+probMedium+probHard);
        int thisQuestion, questionNumber;
        if(currentQuestion == numberOfQuestions - 1){
            thisQuestion = randomNumber(hard.size());
            thisQuestion--;
            questionNumber = hard.get(thisQuestion);
            hard.remove(thisQuestion);
            return questionList.get(questionNumber);
        }
        if(questionIndex <= probEasy){
            if(easyQuestionLeft()){
                thisQuestion = randomNumber(easy.size());
                thisQuestion--;
                questionNumber = easy.get(thisQuestion);
                easy.remove(thisQuestion);
                return questionList.get(questionNumber);
            }
        }
        if (questionIndex <= probMedium + probEasy){
            if(mediumQuestionLeft()){
                thisQuestion = randomNumber(medium.size());
                thisQuestion--;
                questionNumber = medium.get(thisQuestion);
                medium.remove(thisQuestion);
                return questionList.get(questionNumber);
            }
        }
        if(questionIndex <= probEasy + probMedium + probHard){
            if(hardQuestionLeft()){
                thisQuestion = randomNumber(hard.size());
                thisQuestion--;
                questionNumber = hard.get(thisQuestion);
                hard.remove(thisQuestion);
                return questionList.get(questionNumber);
            }
        }
        if(easyQuestionLeft()){
            thisQuestion = randomNumber(easy.size());
            thisQuestion--;
            questionNumber = easy.get(thisQuestion);
            easy.remove(thisQuestion);
            return questionList.get(questionNumber);
        }
        return null;
    }*/
    public void activeQuestion2(List<Integer> probList, int lastQuestion, int numberOfQuestions, int currentQuestion){
        List<Integer> probListTemp = updateProb(probList, lastQuestion, numberOfQuestions,currentQuestion);
        int probEasy = probListTemp.get(0), probMedium = probListTemp.get(1), probHard = probListTemp.get(2);
        int questionIndex = randomNumber(probEasy + probMedium + probHard);
        if(currentQuestion == numberOfQuestions - 1){
            numberOfHardQuestions--;
            questionListDiff.add(3);
            return;
        }
        if(questionIndex <= probEasy){
            if(numberOfEasyQuestions > 1){
                numberOfEasyQuestions--;
                questionListDiff.add(1);
                return;
            }
        }
        if (questionIndex <= probMedium + probEasy){
            if(numberOfMediumQuestions > 1){
                numberOfMediumQuestions--;
                questionListDiff.add(2);
                return;
            }
        }
        if(questionIndex <= probEasy + probMedium + probHard){
            if(numberOfHardQuestions > 2){
                numberOfHardQuestions--;
                questionListDiff.add(3);
                return;
            }
        }
        if(numberOfEasyQuestions > 1){
            numberOfEasyQuestions--;
            questionListDiff.add(1);
        }
    }
    private boolean easyQuestionLeft(){
        boolean hasQuestionLeft = false;
        if(easy.size() > 1){
            hasQuestionLeft = true;
        }
        return hasQuestionLeft;
    }
    private boolean mediumQuestionLeft(){
        boolean hasQuestionLeft = false;
        if(medium.size() > 1){
            hasQuestionLeft = true;
        }
        return hasQuestionLeft;
    }
    private boolean hardQuestionLeft(){
        boolean hasQuestionLeft = false;
        if(hard.size() > 2){
            hasQuestionLeft = true;
        }
        return hasQuestionLeft;
    }
    private List<Integer> updateProb(List<Integer> probList, int lastQuestion, int numberOfQuestions, int currentQuestion){
        int probEasy = probList.get(0), probMedium = probList.get(1), probHard = probList.get(2);
        if (currentQuestion == Math.ceil(numberOfQuestions/2)){
            probEasy = 12;
            probMedium = 66;
            probHard = 22;
        }
        if(lastQuestion == 0){
            return probList;
        }
        probList.clear();
        if(currentQuestion < Math.ceil(numberOfQuestions/2)) {
            switch (lastQuestion) {
                case 1:
                    if (probEasy >= 6) {
                        probEasy = (probEasy - 5);
                        probMedium = (probMedium + 3);
                        probHard = (probHard + 2);
                    }
                    break;
                case 2:
                    if (probMedium >= 4) {
                        probEasy = (probEasy + 3);
                        probMedium = (probMedium - 3);
                    }
                    break;
                case 3:
                    if (probHard >= 4) {
                        probEasy = (probEasy + 3);
                        probHard = (probHard - 3);
                    }
                    break;
            }
        }else{
            switch (lastQuestion){
                case 1:
                    if(probEasy >= 6){
                        probEasy = (probEasy - 5);
                        probMedium = (probMedium + 2);
                        probHard = (probHard + 3);
                    }
                    break;
                case 2:
                    if(probMedium >= 4) {
                        probHard = (probHard + 3);
                        probMedium = (probMedium - 3);
                    }
                    break;
                case 3:
                    if(probHard >= 4) {
                        probMedium = (probMedium + 3);
                        probHard = (probHard - 3);
                    }
                    break;
            }
        }
        if (hard.size() == 1) {
            probMedium += probHard;
            probHard = 0;
        }
        if(currentQuestion == numberOfQuestions - 1){
            probHard = 100;
            probMedium = 0;
            probEasy = 0;
        }

        probList.add(probEasy);
        probList.add(probMedium);
        probList.add(probHard);
        return probList;
    }

    private int randomNumber(int high){
        int low = 1, random;
        Random rand = new Random();
        random =  rand.nextInt(high) + low;
        return random;
    }
    /*private void printQuestion(){
        EncodeDecode encodeDecode = new EncodeDecode();
        loadQuestionDifficulty();
        readWriteQuestions activeQuestion;
        List<Integer> probList = new ArrayList<>();
        probList.add(66);
        probList.add(22);
        probList.add(12);
        int difficultyOfLastQuestion = 0;
        for(int i = 0; i < questionList.size(); i++){
            activeQuestion = activeQuestion(probList, difficultyOfLastQuestion, questionList.size(), i);
            encodeDecode.decodeQuestion(activeQuestion);
            switch (encodeDecode.decodedDifficulty){
                case "Easy":
                    difficultyOfLastQuestion = 1;
                    break;
                case "Medium":
                    difficultyOfLastQuestion = 2;
                    break;
                case "Hard":
                    difficultyOfLastQuestion = 3;
                    break;
            }
            justKeepPrintingPrintingPrinting(i, encodeDecode);
        }
    }*/
    public List<Integer> testQuestion(){
        loadQuestionDifficulty();
        EncodeDecode t = new EncodeDecode();
        numberOfEasyQuestions = easy.size();
        numberOfMediumQuestions = medium.size();
        numberOfHardQuestions = hard.size();
        List<Integer> probList = new ArrayList<>();
        probList.add(66);
        probList.add(22);
        probList.add(12);
        int difficultyOfLastQuestion = 0;
        for(int i = 0; i < 12; i++){
            activeQuestion2(probList, difficultyOfLastQuestion, 12, i);
            difficultyOfLastQuestion = questionListDiff.get(questionListDiff.size() - 1);
        }
        return questionListDiff;
    }
    private void justKeepPrintingPrintingPrinting(int questionNumber, EncodeDecode encodeDecode){
        System.out.println("q: " + encodeDecode.decodedQuestion);
        System.out.println("d: " + encodeDecode.decodedDifficulty);
        System.out.println("r: " + encodeDecode.decodedRightAnswer);
        System.out.println("a1: "+ encodeDecode.decodedA1);
        System.out.println("a2: " + encodeDecode.decodedA2);
        System.out.println("a3: "+ encodeDecode.decodedA3);
        System.out.println("a4: " + encodeDecode.decodedA4);
        System.out.println();
        System.out.println(questionNumber);
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        pickQuestions temp = new pickQuestions();
        temp.testQuestion();
        /*for(int i = 0; i < 100000; i++){
            pickQuestions temp = new pickQuestions();
            temp.testQuestion();
            if(temp.questionListDiff.size() != 12){
                System.out.println("mistake");
            }
            temp.questionListDiff.clear();
        }
        String test = "test";
        byte[] encodedBytes = Base64.getEncoder().encode(test.getBytes());
        System.out.println(new String(encodedBytes));
        byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
        System.out.println(new String(decodedBytes));*/
    }
}
