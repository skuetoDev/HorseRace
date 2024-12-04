package com.example.demo;

import com.example.demo.helper.CardImageLoader;
import com.example.demo.helper.Pause;
import com.example.demo.model.Card;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

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
    private ImageView winnerHorseImageview;

    private Image winnerHorse;

    private LinkedList<String> winners;



    @FXML
    public void initialize() {
        winners = new LinkedList<>();
        showWinner();




    }

    private void showWinner(){

        for (Player player : players = getPlayers()) {

            if(player.getHorseSuit().equalsIgnoreCase(horseSuit)){

                winnerPlayer = player.getName();
            }
        }
        String winnerHorsePath = "/com/example/demo/images/BARAJA/KNIGHT_of_" + horseSuit + ".png";
        URL resource= CardImageLoader.class.getResource(winnerHorsePath);

        if (resource!= null){
              winnerHorse = new Image(resource.toExternalForm());
        }else {
            throw new IllegalArgumentException(" image " + winnerHorsePath + " not found");

        }

            Pause.updateLabelWithPause(winnerLabel,winnerPlayer + " win with the horse of "+horseSuit,1, ()-> {
            Pause.updateLabelWithPause(jackpotLabel," And the JACKPOT won is...." + (getJackpot() + " €"),1, () ->{
                Pause.updateImageWithPause(winnerHorseImageview,winnerHorse,1,null);
            });
        });
        String winersFileString = "winners.txt";
        File winnersFile = new File(winersFileString);

        if(winnersFile.exists()) readWinners(winners,winersFileString);
        winners.add(winnerPlayer + "..." + getJackpot() + " €");
        writeWinners(winners,winersFileString);



    }

    private void writeWinners(LinkedList<String> winners,String filetoWrite){


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filetoWrite))) {
            for (String winner : winners) {
                writer.write(winner+"\n");

            }
            System.out.println("File " + filetoWrite + " overwrite  ");
        } catch (IOException e) {
            System.out.println("Error writing file " + filetoWrite);

        }


    }

    private void readWinners(LinkedList<String> winners,String fileToRead){

        try(BufferedReader reader = new BufferedReader(new FileReader(fileToRead))){
            String line;
            while((line = reader.readLine()) != null){
                    winners.add(line);
            }
        }catch (IOException e ){
            System.out.println("error to read file : " + e.getMessage());
        }


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
