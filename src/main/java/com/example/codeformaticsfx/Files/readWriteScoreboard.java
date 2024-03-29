package com.example.codeformaticsfx.Files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.*;
import java.util.*;

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
            scoreboardListTemp = new ScoreboardInfo(Vars.currentQuiz, null); //adds Info if not existant
        }
        return scoreboardListTemp;
    }
    public void writeToScoreboard(String currentName, String currentScore, String currentTIS, String path) throws IOException {
        EncodeDecode encodeDecode = new EncodeDecode();
        boolean wasAdded = false;
        List<readWriteScoreboard> tempScoreboard = new ArrayList<>();
        if(scoreboard(path).scoreboard != null){
            tempScoreboard = scoreboard(path).scoreboard; //add if exists already saved scoreboard
        }else {
            readWriteScoreboard newPlayer = new readWriteScoreboard(encodeDecode.encodeSinge(currentName), encodeDecode.encodeSinge(currentScore), encodeDecode.encodeSinge(currentTIS)); //makes new player entry
            tempScoreboard.add(newPlayer);
            ScoreboardInfo sI = new ScoreboardInfo(Vars.currentQuiz, tempScoreboard); //makes new ScoreboardInfo
            Writer writer = Files.newBufferedWriter(Paths.get(path)); //saves it
            gson.toJson(sI, writer);
            writer.close();
            return;
        }
        readWriteScoreboard newPlayer = new readWriteScoreboard(encodeDecode.encodeSinge(currentName), encodeDecode.encodeSinge(currentScore), encodeDecode.encodeSinge(currentTIS)); //makes new player entry
        for(int place = 0; place < tempScoreboard.size(); place++){ //goes through scoreboard to find place of current entry
            String tempScore = encodeDecode.decodeSingle(tempScoreboard.get(place).score);
            String tempTISS = encodeDecode.decodeSingle(tempScoreboard.get(place).tis);
            if(Double.valueOf(tempScore)  < Double.valueOf(encodeDecode.decodeSingle(newPlayer.score))){ //sees if score is higher
                tempScoreboard.add(place, newPlayer);
                wasAdded = true;
                break;
            } else if (Double.valueOf(tempScore).equals(Double.valueOf(encodeDecode.decodeSingle(newPlayer.score)))) { //looks if player was faster
                if(Double.valueOf(tempTISS) > Double.valueOf(encodeDecode.decodeSingle(newPlayer.tis))) {
                    tempScoreboard.add(place, newPlayer);
                    wasAdded = true;
                    break;
                }
            }
        }
        if(!wasAdded){ //if entry wasnt added till now it goes in last place
            tempScoreboard.add(newPlayer);
        }
        ScoreboardInfo sI = new ScoreboardInfo(Vars.currentQuiz, tempScoreboard); //makes new Scoreboard info
        Writer writer = Files.newBufferedWriter(Paths.get(path));
        gson.toJson(sI, writer);
        writer.close();
    }
    public ScoreboardInfo fromFile(String filePath) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath)); //opens Scoreboard file to get scoreboard and info
        ScoreboardInfo tempList = new Gson().fromJson(reader, new TypeToken<ScoreboardInfo>() {}.getType()); //saves ScoreboardInfo
        reader.close();
        return tempList;
    }
}
