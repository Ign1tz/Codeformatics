package com.example.codeformaticsfx.Files;

import java.util.List;

public class ScoreboardInfo { //adds info to scoreboard what quiz it belogngs to
    public String Quizzname;
    public List<readWriteScoreboard> scoreboard;

    public ScoreboardInfo(String Quizzname, List<readWriteScoreboard> scoreboard){
        this.Quizzname = Quizzname;
        this.scoreboard = scoreboard;
    }
    public ScoreboardInfo(){}
}
