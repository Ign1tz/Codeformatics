package com.example.codeformaticsfx.Files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class readWriteList {
    private List<readWriteList> listOfTopics = new ArrayList<>();
    public String QUESTIONS;
    public String SCOREBOARD;
    public String NAME;
    public readWriteList(String questions){
        this.QUESTIONS = "./GameResources/QuestionLibrary/" + questions + ".json";
        this.SCOREBOARD = "./GameResources/Scoreboards/" + questions + "Scoreboard.json";
        this.NAME = questions;
    }
    public readWriteList(){}
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private void writeList(String questions) throws IOException {
        List<String> temp = fromFile();
        if(temp == null){
            temp = new ArrayList<>();
        }
        Writer writer = Files.newBufferedWriter(Paths.get("./GameResources/QuestionList.json"));
        temp.add(questions);
        gson.toJson(temp, writer);
        writer.close();
        System.out.println(temp);
    }
    public List<String> fromFile() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("./GameResources/QuestionList.json"));
        List<String> tempList = new Gson().fromJson(reader, new TypeToken<List<String>>() {}.getType());
        reader.close();
        return tempList;
    }
}
