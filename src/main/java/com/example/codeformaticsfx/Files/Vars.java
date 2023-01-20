package com.example.codeformaticsfx.Files;

import com.example.codeformaticsfx.FrontEnd.SettingsController;
import javafx.scene.layout.GridPane;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;

public class Vars {
    public static boolean soundIsOn = false;
    public static MediaPlayer mediaPlayer;
    public static int currentBackgroundValue = 50;
    public static String pathScoreboard = "./GameResources/Scoreboards/javaScoreboard.json";
    public static String pathQuestions = "./GameResources/QuestionLibrary/java.json";
    public static GridPane finishQuestionGrid, finishGrid, GameGrid, homeGrid, inputQuestionGrid, scoreboardGrid, SettingsGrid;

}
