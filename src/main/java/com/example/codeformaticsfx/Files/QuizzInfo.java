package com.example.codeformaticsfx.Files;

import java.util.List;

public class QuizzInfo { //this class is just so you can create QuizzInfo Objects which are needed to create Quizzes
    public String AuthorName, Quizzname, questionsUsed, logoPath;
    public List<readWriteQuestions> questionsList;

    public QuizzInfo(){}
    public QuizzInfo(String Quizzname, String Authorname, String questionsUsed, String logoPath, List<readWriteQuestions> questionsList){
        this.Quizzname = Quizzname;
        this.AuthorName = Authorname;
        this.logoPath = logoPath;
        this.questionsUsed = questionsUsed;
        this.questionsList = questionsList;
    }
}
