module com.example.codeformaticsfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.codeformaticsfx to javafx.fxml;
    exports com.example.codeformaticsfx;
}