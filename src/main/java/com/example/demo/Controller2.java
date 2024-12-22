package com.example.demo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.util.Objects;

public class Controller2 {
    @FXML
    public Button playGameButton;
    @FXML
    public Button display2Winners;
    @FXML
    private Button display2BackButton;
    @FXML
    private Button restoreGame;


    @FXML
    protected void goToDisplay1() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("display1.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) display2BackButton.getScene().getWindow();

            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    protected void goToDisplay3() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("display3.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) playGameButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    protected void goToDisplayWinners() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("displayWinners.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) display2Winners.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @FXML
    protected void goToRestoreDisplay() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("restoreDisplay.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) restoreGame.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
