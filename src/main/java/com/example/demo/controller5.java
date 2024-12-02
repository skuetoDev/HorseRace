package com.example.demo;

import com.example.demo.helper.Pause;
import com.example.demo.model.CardsDeck;
import com.example.demo.model.players.Player;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.example.demo.controller3.getJackpot;
import static com.example.demo.controller3.getPlayers;
import static com.example.demo.controller4.getWinHorseSuit;

public class controller5 {

    @FXML
    private Button backButtonDisplay5;

    @FXML
    private Label jackpotLabel;

    @FXML
    private Label winnerLabel;
    private  String horseSuit = getWinHorseSuit();

    private List<Player> players = new ArrayList<>();
    private String winnerPlayer ;





    @FXML
    public void initialize() {
        showWinner();




    }

    private void showWinner(){
        for (Player player : players = getPlayers()) {

            if(player.getHorseSuit().equalsIgnoreCase(horseSuit)){

                winnerPlayer = player.getName();
            }
        }

        Pause.updateLabelWithPause(winnerLabel,"The winner is " + winnerPlayer,1, ()-> {
            Pause.updateLabelWithPause(jackpotLabel," And the JACKPOT won is...." + (getJackpot() + " €"),1, null);
        });

        //TODO guardado de fichero para los campeones con bote ganado
    }

    @FXML
    private void goToDisplay2(){

        try{

            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("display2.fxml")));
            Scene scene = new Scene(root);
            Stage stage =(Stage) backButtonDisplay5.getScene().getWindow();

            stage.setScene(scene);

        }catch (Exception e){
            e.printStackTrace();
        }
    }






}
