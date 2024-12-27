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

    /**
     * Method to go to display 1 (back)
     */
    @FXML
    protected void goToDisplay1() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("display1.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) display2BackButton.getScene().getWindow();

            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("ERROR goToDisplay1 " + e.getMessage());
        }

    }

    /**
     * Method to go to display 3 (next)
     */
    @FXML
    protected void goToDisplay3() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("display3.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) playGameButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("ERROR in goToDisplay3 " + e.getMessage());
        }


    }

    /**
     * Method to go winners display
     */
    @FXML
    protected void goToDisplayWinners() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("displayWinners.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) display2Winners.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("ERROR in goToDisplayWinners" + e.getMessage());
        }


    }

    /**
     * Method to go display of restored games
     */
    @FXML
    protected void goToRestoreDisplay() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("restoreDisplay.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) restoreGame.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("ERROR in goToRestoreDisplay " + e.getMessage());
        }


    }


}
