package com.example.demo;

import com.example.demo.helper.CardImageLoader;
import com.example.demo.helper.Pause;
import com.example.demo.model.GameLogic;
import com.example.demo.model.players.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.*;
import java.net.URL;
import java.util.*;

import static com.example.demo.Controller4.getWinHorseSuit;

public class Controller5 {

    @FXML
    private Button backButtonDisplay5;

    @FXML
    private Label jackpotLabel;

    @FXML
    private Label winnerLabel;

    @FXML
    private ImageView winnerHorseImageview;

    private Image winnerHorse;

    private LinkedList<String> winners;






    @FXML
    public void initialize() {
        winners = new LinkedList<>();
        showWinner();




    }

    /**
     * Method to show the name through the horse suit and read and write through methods writeWinners and readWinners
     */
    private void showWinner(){
        String winnerPlayer = "";

        for (Player player : GameLogic.getPlayers()) {

            if(player.getHorseSuit().equalsIgnoreCase(getWinHorseSuit())){

                 winnerPlayer = player.getName();
            }
        }
        String winnerHorsePath = "/com/example/demo/images/BARAJA/KNIGHT_of_" + getWinHorseSuit() + ".png";
        URL resource= CardImageLoader.class.getResource(winnerHorsePath);

        if (resource!= null){
              winnerHorse = new Image(resource.toExternalForm());
        }else {
            throw new IllegalArgumentException(" image " + winnerHorsePath + " not found");

        }

            Pause.updateLabelWithPause(winnerLabel,winnerPlayer + " win with the horse of "+getWinHorseSuit(),1, ()-> {
            Pause.updateLabelWithPause(jackpotLabel," And the JACKPOT won is...." + (Controller3.getJackpot() + " €"),1, () ->{
                Pause.updateImageWithPause(winnerHorseImageview,winnerHorse,1,null);
            });
        });

        File winnersFile = new File(GameLogic.getWinersFileString());
        if(winnersFile.exists()) GameLogic.readWinners(winners);
        winners.add(winnerPlayer + "..." + Controller3.getJackpot() + " €");
        GameLogic.writeWinners(winners);

    }


    /**
     * method to go display2
     */
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
