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
    private readWriteQuestions temp = new readWriteQuestions();
    private EncodeDecode ed = new EncodeDecode();
    private int numberOfQuestions = Integer.parseInt(ed.decodeSingle(temp.readQuizz(Vars.pathQuestions).questionsUsed));
    private int numberOfEasyQuestions, numberOfMediumQuestions, numberOfHardQuestions;
    public List<readWriteQuestions> questionList = temp.questionList(Vars.pathQuestions);
    public List<Integer> questionListDiff = new ArrayList<>();

    public pickQuestions() throws IOException {
    }

    public void loadQuestionDifficulty(){ //creates three lists
        readWriteQuestions question;
        String difficulty;
        for(int place = 0; place < questionList.size(); place++){
            question = questionList.get(place);
            byte[] eDifficulty = Base64.getDecoder().decode(question.DIFFICULTY);
            difficulty = new String(eDifficulty);
            switch (difficulty){
                case "Easy":
                    easy.add(place); // holds the places of all easy questions
                    break;
                case "Medium":
                    medium.add(place);// holds the places of all medium questions
                    break;
                case  "Hard":
                    hard.add(place); // holds the places of all hard questions
                    break;
            }
        }
    }

    public void activeQuestion(List<Integer> probList, int lastQuestion, int numberOfQuestions, int currentQuestion){
        List<Integer> probListTemp = updateProb(probList, lastQuestion, numberOfQuestions,currentQuestion);
        int probEasy = probListTemp.get(0), probMedium = probListTemp.get(1), probHard = probListTemp.get(2);
        int questionIndex = randomNumber(probEasy + probMedium + probHard); //gets one value between 1 and 100
        if(currentQuestion == numberOfQuestions - 1){ //last question will always be a difficult question
            numberOfHardQuestions--;
            questionListDiff.add(3);
            return;
        }
        if(questionIndex <= probEasy){ // checks if in range for easy
            if(numberOfEasyQuestions > 1){
                numberOfEasyQuestions--;
                questionListDiff.add(1);
                return;
            }
        }
        if (questionIndex <= probMedium + probEasy){// checks if in range for medium
            if(numberOfMediumQuestions > 1){
                numberOfMediumQuestions--;
                questionListDiff.add(2);
                return;
            }
        }
        if(questionIndex <= probEasy + probMedium + probHard){// checks if in range for hard
            if(numberOfHardQuestions > 2){
                numberOfHardQuestions--;
                questionListDiff.add(3);
                return;
            }
        }
        if(numberOfMediumQuestions > 1){ //if no hard questions left try medium
            numberOfMediumQuestions--;
            questionListDiff.add(2);
        }
        if(numberOfEasyQuestions > 1){ //if even no medium question is left
            numberOfEasyQuestions--;
            questionListDiff.add(1);
        }
    }
    private List<Integer> updateProb(List<Integer> probList, int lastQuestionDiff, int numberOfQuestions, int currentQuestion){
        int probEasy = probList.get(0), probMedium = probList.get(1), probHard = probList.get(2);
        if (currentQuestion == Math.ceil(numberOfQuestions/2)){ // drasticaly alters probability for the second half of the game
            probEasy = 12;
            probMedium = 66;
            probHard = 22;
        }
        if(lastQuestionDiff == 0){ // fist question does not need a probability change
            return probList;
        }
        probList.clear();
        if(currentQuestion < Math.ceil(numberOfQuestions/2)) { //first half
            switch (lastQuestionDiff) { //checks last questions Difficulty and changes probability
                case 1:
                    if (probEasy >= 6) { // prevents probability from being negative
                        probEasy = (probEasy - 5);
                        probMedium = (probMedium + 3);
                        probHard = (probHard + 2);
                    }
                    break;
                case 2:
                    if (probMedium >= 4) {// prevents probability from being negative
                        probEasy = (probEasy + 3);
                        probMedium = (probMedium - 3);
                    }
                    break;
                case 3:
                    if (probHard >= 4) {// prevents probability from being negative
                        probEasy = (probEasy + 3);
                        probHard = (probHard - 3);
                    }
                    break;
            }
        }else{ //second half
            switch (lastQuestionDiff){//checks last questions Difficulty and changes probability
                case 1:
                    if(probEasy >= 6){// prevents probability from being negative
                        probEasy = (probEasy - 5);
                        probMedium = (probMedium + 2);
                        probHard = (probHard + 3);
                    }
                    break;
                case 2:
                    if(probMedium >= 4) {// prevents probability from being negative
                        probHard = (probHard + 3);
                        probMedium = (probMedium - 3);
                    }
                    break;
                case 3:
                    if(probHard >= 4) {// prevents probability from being negative
                        probMedium = (probMedium + 3);
                        probHard = (probHard - 3);
                    }
                    break;
            }
        }
        if (hard.size() == 2) { //if only 2 hard questions are left, there wont be hard questions till the end, instead medium will be more probable
            probMedium += probHard;
            probHard = 0;
        }
        if(currentQuestion == numberOfQuestions - 1){ // last question is guaranteed to be a hard question
            probHard = 100;
            probMedium = 0;
            probEasy = 0;
        }

        probList.add(probEasy);
        probList.add(probMedium);
        probList.add(probHard);
        return probList;
    }

    private int randomNumber(int high){ // does not need further explanation
        int low = 1, random;
        Random rand = new Random();
        random =  rand.nextInt(high) + low;
        return random;
    }
    public List<Integer> testQuestion(){
        loadQuestionDifficulty();
        numberOfEasyQuestions = easy.size();
        numberOfMediumQuestions = medium.size();
        numberOfHardQuestions = hard.size();
        List<Integer> probList = new ArrayList<>();
        probList.add(66);// probability for easy
        probList.add(22);// probability for medium
        probList.add(12);// probability for hard
        int difficultyOfLastQuestion = 0;
        for(int i = 0; i < numberOfQuestions; i++){ //generates a list of what difficulty of questions get chosen in which place
            activeQuestion(probList, difficultyOfLastQuestion, numberOfQuestions, i);
            difficultyOfLastQuestion = questionListDiff.get(questionListDiff.size() - 1); //updates the difficulty of the last question
        }
        return questionListDiff;
    }
}
