package com.example.codeformaticsfx;

import java.util.Base64;

public class EncodeDecode {
    public String encodedQuestion, encodedA1, encodedA2, encodedA3, encodedA4, encodedDifficulty, encodedRightAnswer;
    public String decodedQuestion, decodedA1, decodedA2, decodedA3, decodedA4, decodedDifficulty, decodedRightAnswer;
    public String encodedName, encodedScore, encodedTIS;
    public String decodedName, decodedScore, decodedTIS;

    public void encodeQuestionBase64(String question, String a1, String a2, String a3, String a4, String difficulty, String answer){
        byte[] eQuestion = Base64.getEncoder().encode(question.getBytes());
        encodedQuestion = new String(eQuestion);
        byte[] eA1 = Base64.getEncoder().encode(a1.getBytes());
        encodedA1 = new String(eA1);
        byte[] eA2 = Base64.getEncoder().encode(a2.getBytes());
        encodedA2 = new String(eA2);
        byte[] eA3 = Base64.getEncoder().encode(a3.getBytes());
        encodedA3 = new String(eA3);
        byte[] eA4 = Base64.getEncoder().encode(a4.getBytes());
        encodedA4 = new String(eA4);
        byte[] eDifficulty = Base64.getEncoder().encode(difficulty.getBytes());
        encodedDifficulty = new String(eDifficulty);
        byte[] eAnswer = Base64.getEncoder().encode(answer.getBytes());
        encodedRightAnswer = new String(eAnswer);
    }
    public void decodeQuestion(readWriteQuestions activeQuestion){
        byte[] eQuestion = Base64.getDecoder().decode(activeQuestion.QUESTION);
        decodedQuestion = new String(eQuestion);
        byte[] eDifficulty = Base64.getDecoder().decode(activeQuestion.DIFFICULTY);
        decodedDifficulty = new String(eDifficulty);
        byte[] eRightAnswer = Base64.getDecoder().decode(activeQuestion.RIGHTAWNSER);
        decodedRightAnswer = new String(eRightAnswer);
        byte[] eA1 = Base64.getDecoder().decode(activeQuestion.A1);
        decodedA1 = new String(eA1);
        byte[] eA2 = Base64.getDecoder().decode(activeQuestion.A2);
        decodedA2 = new String(eA2);
        byte[] eA3 = Base64.getDecoder().decode(activeQuestion.A3);
        decodedA3 = new String(eA3);
        byte[] eA4 = Base64.getDecoder().decode(activeQuestion.A4);
        decodedA4 = new String(eA4);
    }
    public void encodeScoreboardBase64(String name, String Score, String timeInSeconds){
        byte[] eName = Base64.getEncoder().encode(name.getBytes());
        encodedName = new String(eName);
        byte[] eScore = Base64.getEncoder().encode(Score.getBytes());
        encodedScore = new String(eScore);
        byte[] eTimeInSeconds = Base64.getEncoder().encode(timeInSeconds.getBytes());
        encodedTIS = new String(eTimeInSeconds);
    }
    public void decodeScoreboardBase64(String name, String score, String timeInSeconds){ //will be used in the future
        byte[] dName = Base64.getDecoder().decode(name);
        decodedName = new String(dName);
        byte[] dScore = Base64.getDecoder().decode(score);
        decodedScore = new String(dScore);
        byte[] dTIS = Base64.getDecoder().decode(timeInSeconds);
        decodedTIS = new String(dTIS);
    }
    public String decodeSingle(String temp){
        byte[] dTemp = Base64.getDecoder().decode(temp);
        return new String(dTemp);
    }
}
