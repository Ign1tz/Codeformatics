module com.example.codeformaticsfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.codeformaticsfx to javafx.fxml;
    exports com.example.codeformaticsfx;
    exports com.example.codeformaticsfx.Files;
    opens com.example.codeformaticsfx.Files to javafx.fxml;
    exports com.example.codeformaticsfx.Files.GUI;
    opens com.example.codeformaticsfx.Files.GUI to javafx.fxml;
    exports com.example.codeformaticsfx.Quizz.GUI;
    opens com.example.codeformaticsfx.Quizz.GUI to javafx.fxml;
    exports com.example.codeformaticsfx.Quizz;
    opens com.example.codeformaticsfx.Quizz to javafx.fxml;
}