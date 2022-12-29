package com.example.codeformaticsfx.Quizz;

import com.example.codeformaticsfx.Files.EncodeDecode;

import java.util.Random;

public class QuestionJoker {

    private String a1,a2,a3,a4,rightAnswer;
    private int range;
    private int randomAnswer;
    Random random = new Random();

    public void jokerFiftyFifty(String a1,String a2,String a3,String a4,String rightAnswer,int range){
        int randomanswer = random.nextInt(range);
        while (randomanswer == Integer.valueOf(rightAnswer.replaceAll("A",""))){
            randomanswer = random.nextInt(range);
        }

        this.a1=a1;
        this.a2=a2;
        this.a3=a3;
        this.a4=a4;
        this.rightAnswer=rightAnswer;
        this.range=range;
        this.randomAnswer=randomanswer;
    }

    public int jokerSeventyThirty(String a1,String a2,String a3,String a4,String rightAnswer,int range){
        int randomanswer = random.nextInt(range);
        while (randomanswer == Integer.valueOf(rightAnswer.replaceAll("A",""))){
            randomanswer = random.nextInt(range);
        }

        this.a1=a1;
        this.a2=a2;
        this.a3=a3;
        this.a4=a4;
        this.rightAnswer=rightAnswer;
        this.range=range;
        this.randomAnswer=randomanswer;

        return random.nextInt(10 - 1 + 1) + 1;
    }

    public int getRange() {
        return range;
    }


    public int getRandomAnswer() {
        return randomAnswer;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public String getA1() {
        return a1;
    }

    public String getA2() {
        return a2;
    }

    public String getA3() {
        return a3;
    }

    public String getA4() {
        return a4;
    }
}