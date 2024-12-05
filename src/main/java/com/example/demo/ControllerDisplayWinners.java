package com.example.demo;

import com.example.demo.helper.Pause;
import com.example.demo.model.GameLogic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

public class ControllerDisplayWinners {

    @FXML
    public Label winnersLabel;

    @FXML
    public Button backButtonWinnersDisplay;

    private LinkedList<String> winners;

    /**
     * Method to show all winners in saved in file txt.
     */
    @FXML
    public void initialize() {
        winners = new LinkedList<>();

        File winnersFile = new File("winners.txt");
        if(winnersFile.exists()) GameLogic.readWinners(winners);


        StringBuilder champions = new StringBuilder();
        for (String winner : winners) {
            champions.append(winner).append("\n");

        }
        String winners = champions.toString();
        Pause.updateLabelWithPause(winnersLabel,winners,1,null);


    }

    @FXML
    protected  void goToDisplay2(){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("display2.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) backButtonWinnersDisplay.getScene().getWindow();

            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }

    }





}
