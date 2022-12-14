package com.example.codeformaticsfx;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
    public List<readWriteScoreboard> scoreboard() throws IOException {
        List<readWriteScoreboard> scoreboardListTemp = fromFile("./Scoreboards/Scoreboard.json");
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
        List<readWriteScoreboard> tempList = scoreboard();
        if(tempList != null){
            tempScoreboard = scoreboard();
        }
        readWriteScoreboard newPlayer = new readWriteScoreboard(currentName, currentScore, currentTIS);
        for(int place = 0; place < tempScoreboard.size(); place++){
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
            encodeDecode.encodeScoreboardBase64(newPlayer.name, newPlayer.score, newPlayer.tis);
            newPlayer = new readWriteScoreboard(encodeDecode.encodedName, encodeDecode.encodedScore, encodeDecode.encodedTIS);
            tempScoreboard.add(newPlayer);
        }
        Writer writer = Files.newBufferedWriter(Paths.get("./Scoreboards/Scoreboard.json"));
        gson.toJson(tempScoreboard, writer);
        writer.close();
    }
    public List<readWriteScoreboard> fromFile(String filePath) throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(filePath));
        List<readWriteScoreboard> tempList = new Gson().fromJson(reader, new TypeToken<List<readWriteScoreboard>>() {}.getType());
        reader.close();
        return tempList;
    }

    public static void main(String[] args) throws IOException {
        File theDir = new File("./Scoreboards");
        if (!theDir.exists()){
            theDir.mkdirs();
        }
        File file = new File("./Scoreboards/Scoreboard.json");
        if(!file.exists()){
            Files.createFile(Path.of("./Scoreboards/Scoreboard.json"));
        }
        readWriteScoreboard temp = new readWriteScoreboard();
        temp.writeToScoreboard();
    }
}
