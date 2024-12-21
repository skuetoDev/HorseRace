package com.example.demo;

import com.example.demo.helper.Database.DatabaseManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DatabaseManager.createDatabase();
        DatabaseManager.createTables();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("display1.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("HORSE RACE ! \uD83C\uDFC7");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}