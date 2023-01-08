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
    public ScoreboardInfo scoreboard() throws IOException {
        ScoreboardInfo scoreboardListTemp = fromFile("./GameResources/Scoreboards/Scoreboard.json");
        return scoreboardListTemp;
    }
    public void writeToScoreboard() throws IOException {
        EncodeDecode encodeDecode = new EncodeDecode();
        boolean wasAdded = false;
        Scanner scan = new Scanner(System.in);
        System.out.print("name: ");
        String currentName = scan.nextLine();
        System.out.print("Score: ");
        String currentScore = scan.nextLine();
        System.out.print("Time: ");
        String  currentTIS = scan.nextLine();
        List<readWriteScoreboard> tempScoreboard = new ArrayList<>();
        List<readWriteScoreboard> tempList = scoreboard().scoreboard;
        if(tempList != null){
            tempScoreboard = scoreboard().scoreboard;
        }
        System.out.println(tempScoreboard);
        readWriteScoreboard newPlayer = new readWriteScoreboard(currentName, currentScore, currentTIS);
        for(int place = 0; place < tempScoreboard.size(); place++){
            encodeDecode.encodeScoreboardBase64(currentName, currentScore, currentTIS);
            newPlayer = new readWriteScoreboard(encodeDecode.encodedName, encodeDecode.encodedScore, encodeDecode.encodedTIS);
            String tempScore = encodeDecode.decodeSingle(tempScoreboard.get(place).score);
            String tempTISS = encodeDecode.decodeSingle(tempScoreboard.get(place).tis);
            if(Double.valueOf(tempScore)  < Double.valueOf(newPlayer.score)){
                tempScoreboard.add(place, newPlayer);
                wasAdded = true;
                break;
            } else if (Double.valueOf(tempScore).equals(Double.valueOf(newPlayer.score))) {
                if(Double.valueOf(tempTISS) > Double.valueOf(newPlayer.tis)) {
                    tempScoreboard.add(place, newPlayer);
                    wasAdded = true;
                    break;
                }
            }
        }
        if(!wasAdded){
            tempScoreboard.add(newPlayer);
        }
        ScoreboardInfo sI = new ScoreboardInfo("java", tempScoreboard);
        Writer writer = Files.newBufferedWriter(Paths.get("./GameResources/Scoreboards/Scoreboard.json"));
        gson.toJson(sI, writer);
        writer.close();
    }
    public ScoreboardInfo fromFile(String filePath) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        ScoreboardInfo tempList = new Gson().fromJson(reader, new TypeToken<ScoreboardInfo>() {}.getType());
        reader.close();
        return tempList;
    }

    public static void main(String[] args) throws IOException {
        File theDir = new File("./GameResources/Scoreboards");
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        File file = new File("./GameResources/Scoreboards/Scoreboard.json");
        if(!file.exists()){
            Files.createFile(Path.of("./GameResources/Scoreboards/Scoreboard.json"));
        }
        readWriteScoreboard temp = new readWriteScoreboard();
        System.out.print(temp.scoreboard());
        temp.writeToScoreboard();
    }
}
