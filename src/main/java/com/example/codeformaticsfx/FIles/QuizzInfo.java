package com.example.codeformaticsfx.FIles;

import java.util.List;

public class QuizzInfo {
    public String AuthorName, Quizzname, questionsUsed;
    public List<readWriteQuestions> questionsList;

    public QuizzInfo(String Quizzname, String Authorname, String questionsUsed, List<readWriteQuestions> questionsList){
        this.Quizzname = Quizzname;
        this.AuthorName = Authorname;
        this.questionsUsed = questionsUsed;
        this.questionsList = questionsList;
    }
}
