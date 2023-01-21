package com.example.codeformaticsfx;

import com.example.codeformaticsfx.Files.EncodeDecode;
import com.example.codeformaticsfx.Files.Vars;
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
    public List<readWriteQuestions> questionList = temp.questionList(Vars.pathQuestions);
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

    public void activeQuestion(List<Integer> probList, int lastQuestion, int numberOfQuestions, int currentQuestion){
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
            activeQuestion(probList, difficultyOfLastQuestion, 12, i);
            difficultyOfLastQuestion = questionListDiff.get(questionListDiff.size() - 1);
        }
        return questionListDiff;
    }
}
