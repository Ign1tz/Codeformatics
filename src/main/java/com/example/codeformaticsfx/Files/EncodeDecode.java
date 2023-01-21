package com.example.codeformaticsfx.Files;

import java.util.Base64;

public class EncodeDecode { //this class is just for encoding and decoding strings with Base64
    public String encodedQuestion, encodedA1, encodedA2, encodedA3, encodedA4, encodedDifficulty, encodedRightAnswer;
    public String encodedQuizzName, encodedAuthorname, encodedQuestionsUsed;

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
    public void encodeQuizzInfo(String quizzName, String authorName, String questionsUsed){
        byte[] eQuizzname = Base64.getEncoder().encode(quizzName.getBytes());
        encodedQuizzName = new String(eQuizzname);
        byte[] eAuthorName = Base64.getEncoder().encode(authorName.getBytes());
        encodedAuthorname = new String(eAuthorName);
        byte[] eQuestionsUsed = Base64.getEncoder().encode(questionsUsed.getBytes());
        encodedQuestionsUsed = new String(eQuestionsUsed);
    }
    public String decodeSingle(String temp){
        byte[] dTemp = Base64.getDecoder().decode(temp);
        return new String(dTemp);
    }
    public String encodeSinge(String temp){
        byte[] eTemp = Base64.getEncoder().encode(temp.getBytes());
        return new String(eTemp);
    }
}
