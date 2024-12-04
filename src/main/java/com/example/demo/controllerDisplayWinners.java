package com.example.demo;

import com.example.demo.helper.AlertUtil;
import com.example.demo.helper.Pause;
import javafx.application.Application;
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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Objects;

public class controllerDisplayWinners {

    @FXML
    public Label winnersLabel;


    @FXML
    public Button backButtonWinnersDisplay;

    private LinkedList<String> winners;

    @FXML
    public void initialize() {
        winners = new LinkedList<>();
        File winnersFile = new File("winners.txt");
        if(winnersFile.exists()) readWinners(winners);


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
    private void readWinners(LinkedList<String> winners){

        String fileName = "winners.txt";

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))){
            String line;
            while((line = reader.readLine()) != null) {
                winners.add(line);
            }
        }catch (IOException e ){
            System.out.println("error to read file : " + e.getMessage());
        }


    }




}
