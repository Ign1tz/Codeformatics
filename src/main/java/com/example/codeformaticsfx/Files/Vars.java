package com.example.codeformaticsfx.Files;

import javafx.scene.media.MediaPlayer;

import java.util.List;

public class Vars { //keeps overarching Variables
    public static String currentQuiz = "java";
    public static boolean soundIsOn = false;
    public static boolean isHome = true;
    public static MediaPlayer mediaPlayer;
    public static int currentBackgroundValue = 50; //keeps value of the background slider
    public static String pathScoreboard = "src/main/resources/com/example/codeformaticsfx/GameResources/Scoreboards/"+currentQuiz+"Scoreboard.json";
    public static String pathQuestions = "src/main/resources/com/example/codeformaticsfx/GameResources/QuestionLibrary/"+currentQuiz+".json";
    public static String pathBackground1 = "com/example/codeformaticsfx/FrontEnd/back1.jpg";
    public static String pathBackground2 = "com/example/codeformaticsfx/FrontEnd/Background.jpg";
    public static String pathBackground3 = "com/example/codeformaticsfx/FrontEnd/back2.jpg";
    public static String logoPath = "com/example/codeformaticsfx/GameResources/Logos/java.png";
    public static List<String> listOfTopics;
}
