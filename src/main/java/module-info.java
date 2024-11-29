module com.example.horse_race {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.horse_race to javafx.fxml;
    exports com.example.horse_race;
}