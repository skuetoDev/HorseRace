module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.model;
    opens com.example.demo.model to javafx.fxml;
    exports com.example.demo.model.Cards;
    opens com.example.demo.model.Cards to javafx.fxml;
}