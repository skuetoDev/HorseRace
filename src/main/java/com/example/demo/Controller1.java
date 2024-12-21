package com.example.demo;
import com.example.demo.helper.Database.DatabaseManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller1 {


    public Button exitButton;
    @FXML
    private Button enterButton;


    @FXML
    protected void goNextScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("display2.fxml"));
        Scene scene2 = new Scene(loader.load());
        Stage stage = (Stage) enterButton.getScene().getWindow();
        stage.setScene(scene2);


    }

    @FXML
    protected void exit() {
        DatabaseManager.closeConnection();
        Platform.exit();
        System.exit(0);
    }


}