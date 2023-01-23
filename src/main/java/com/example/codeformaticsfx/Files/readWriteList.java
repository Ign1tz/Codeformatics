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

public class readWriteList { //keeps a list of all topics that exist
    private List<readWriteList> listOfTopics = new ArrayList<>();
    public String QUESTIONS;
    public String SCOREBOARD;
    public String NAME;
    public readWriteList(String name){
        this.QUESTIONS = "src/main/resources/com/example/codeformaticsfx/GameResources/QuestionLibrary/" + name + ".json";
        this.SCOREBOARD = "src/main/resources/com/example/codeformaticsfx/GameResources/Scoreboards/" + name + "Scoreboard.json";
        this.NAME = name;
    }
    public readWriteList(){}
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void writeList(String name) throws IOException {
        List<String> temp = fromFile(); //gets entrys from file
        if(temp == null){ //null save
            temp = new ArrayList<>();
        }
        Writer writer = Files.newBufferedWriter(Paths.get("src/main/resources/com/example/codeformaticsfx/GameResources/QuestionList.json")); //opens file to write in
        temp.add(name); //adds new topic to list
        gson.toJson(temp, writer); // writes list to file
        writer.close();
    }
    public List<String> fromFile() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/com/example/codeformaticsfx/GameResources/QuestionList.json")); //opens file to read from
        List<String> tempList = new Gson().fromJson(reader, new TypeToken<List<String>>() {}.getType()); //saves json content from file in a List
        reader.close();
        return tempList;
    }
}
