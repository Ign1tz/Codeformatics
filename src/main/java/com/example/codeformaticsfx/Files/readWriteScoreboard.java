package com.example.codeformaticsfx.Files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class readWriteScoreboard {
    public String name;
    public String score;
    public String tis; //Time in Seconds
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public readWriteScoreboard() {
    }
    public readWriteScoreboard(String name, String score, String timeInSeconds){
        this.name = name;
        this.score = score;
        this.tis = timeInSeconds;
    }
    public ScoreboardInfo scoreboard(String path) throws IOException {
        ScoreboardInfo scoreboardListTemp = fromFile(path);
        if(scoreboardListTemp == null){
            scoreboardListTemp = new ScoreboardInfo(Vars.currentQuiz, null);
        }
        return scoreboardListTemp;
    }
    public void writeToScoreboard(String currentName, String currentScore, String currentTIS, String path) throws IOException {
        EncodeDecode encodeDecode = new EncodeDecode();
        boolean wasAdded = false;
        List<readWriteScoreboard> tempScoreboard = new ArrayList<>();
        if(scoreboard(path).scoreboard != null){
            tempScoreboard = scoreboard(path).scoreboard;
        }else {
            readWriteScoreboard newPlayer = new readWriteScoreboard(encodeDecode.encodeSinge(currentName), encodeDecode.encodeSinge(currentScore), encodeDecode.encodeSinge(currentTIS));
            tempScoreboard.add(newPlayer);
            ScoreboardInfo sI = new ScoreboardInfo(Vars.currentQuiz, tempScoreboard);
            Writer writer = Files.newBufferedWriter(Paths.get(path));
            gson.toJson(sI, writer);
            writer.close();
            return;
        }
        System.out.println(tempScoreboard);
        readWriteScoreboard newPlayer = new readWriteScoreboard(encodeDecode.encodeSinge(currentName), encodeDecode.encodeSinge(currentScore), encodeDecode.encodeSinge(currentTIS));
        for(int place = 0; place < tempScoreboard.size(); place++){
            String tempScore = encodeDecode.decodeSingle(tempScoreboard.get(place).score);
            String tempTISS = encodeDecode.decodeSingle(tempScoreboard.get(place).tis);
            if(Double.valueOf(tempScore)  < Double.valueOf(encodeDecode.decodeSingle(newPlayer.score))){
                tempScoreboard.add(place, newPlayer);
                wasAdded = true;
                break;
            } else if (Double.valueOf(tempScore).equals(Double.valueOf(encodeDecode.decodeSingle(newPlayer.score)))) {
                if(Double.valueOf(tempTISS) > Double.valueOf(encodeDecode.decodeSingle(newPlayer.tis))) {
                    tempScoreboard.add(place, newPlayer);
                    wasAdded = true;
                    break;
                }
            }
        }
        if(!wasAdded){
            tempScoreboard.add(newPlayer);
        }
        ScoreboardInfo sI = new ScoreboardInfo(Vars.currentQuiz, tempScoreboard);
        Writer writer = Files.newBufferedWriter(Paths.get(path));
        gson.toJson(sI, writer);
        writer.close();
    }
    public ScoreboardInfo fromFile(String filePath) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        ScoreboardInfo tempList = new Gson().fromJson(reader, new TypeToken<ScoreboardInfo>() {}.getType());
        reader.close();
        return tempList;
    }
}
