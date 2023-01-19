module com.example.codeformaticsfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.media;

    opens com.example.codeformaticsfx to javafx.fxml;
    exports com.example.codeformaticsfx;
    exports com.example.codeformaticsfx.Files;
    opens com.example.codeformaticsfx.Files to javafx.fxml;
    exports com.example.codeformaticsfx.FrontEnd;
    opens com.example.codeformaticsfx.FrontEnd to javafx.fxml;
}