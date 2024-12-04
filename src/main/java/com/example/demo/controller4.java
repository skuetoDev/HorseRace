package com.example.demo;

import com.example.demo.helper.CardImageLoader;
import com.example.demo.helper.Pause;
import com.example.demo.helper.RoundMaxException;
import com.example.demo.model.Card;
import com.example.demo.model.CardsDeck;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class controller4 {

    @FXML
    private Label roundNumberLabel;

    @FXML
    private Pane display4;

    @FXML
    private Label actionLabel;

    @FXML
    private ImageView showCard;

    @FXML
    private Label forwardLabel;

    @FXML
    private Label backwardLabel;

    @FXML
    private Button nextButtonDisplay5;

    private static final int row = 4;
    private static final int column = 10;

    protected static int[][] horsePositions;

    int firsPosition = 132;

    boolean winner = false;

    protected static String winHorseSuit;
    CardsDeck cardsDeck;


    private int round = 1;
    String imagePath = "/com/example/demo/images/BARAJA";


    private LinkedHashMap<String, String> logs;
    private int counterExcepcion = 0;


    @FXML
    public void initialize() {
        horsePositions = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (j == 0) {
                    horsePositions[i][j] = 1;
                } else {
                    horsePositions[i][j] = 0;
                }
            }
        }
        logs = new LinkedHashMap<>();

        gameStart();


    }

    private void gameStart() {
        cardsDeck = new CardsDeck();

        gameRound();


    }


    //metodo por cada ronda
    private void gameRound() {

        try {

            if (round % 41 == 0 && counterExcepcion == 0) {
                counterExcepcion++;
                //System.out.println(counterExcepcion +"dentro de la excepcion");
                throw new RoundMaxException(" Suffling is necesary to continue the game");
            }

            if (winner) {
                nextButtonDisplay5.setOpacity(1);

                String fileName = "logs.txt";

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                    for (Map.Entry<String, String> entry : logs.entrySet()) {
                        writer.write(entry.getKey() + entry.getValue());
                        writer.newLine();
                    }
                    System.out.println("Archivo " + fileName + " creado correctamente");
                } catch (IOException e) {
                    System.out.println("Error al escribir el archivo " + fileName);

                }

                return;
            }


            try {
                getCard();
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: imagen no encontrada");


            }


        } catch (RoundMaxException e) {
            showAlertAndPause(e.getMessage());

        }

    }

    private void getCard(){
        Card card = cardsDeck.getCardFromDeck();
        Image cardImage = CardImageLoader.loadCardImage(card, imagePath);

        Pause.updateLabelWithPause(roundNumberLabel, String.valueOf(round), 1, () -> {
            //encender un label u otro
            if (round % 5 == 0) {

                forwardLabel.setOpacity(0);
                backwardLabel.setOpacity(1);
            } else {
                backwardLabel.setOpacity(0);
                forwardLabel.setOpacity(1);

            }


            Pause.updateImageWithPause(showCard, cardImage, 1, () -> {
                Pause.updateLabelWithPause(actionLabel, "Card Taken : " + card.getDescription(), 1, () -> {
                    updateHorsePosition(card);
                    round++;
                    gameRound();


                });
            });

        });
    }


    public void showAlertAndPause(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("PAUSE GAME");
            alert.setHeaderText(message);
            alert.setContentText("Press ACEPTAR to shuffle again or CANCELAR if you want to exit");

            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {


                    getCard();                // Reanuda el juego ya pasada la excepcion
                } else {
                    goToDisplay1();              // Va a la pantalla de inicio si se cancela
                }
            });
        });
    }

    private void goToDisplay1() {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("display1.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) nextButtonDisplay5.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void updateHorsePosition(Card card) {

        String horseSuit = String.valueOf(card.getSuit());
        ImageView horse = (ImageView) display4.lookup(("#KNIGHT_of_" + horseSuit));

        logs.put("Ronda " + round + " : ", card.getDescription());

        /*
        System.out.println("Ronda " + round + " : ");
        System.out.println(card.getDescription());
        */


        //movimiento hacia atras
        if (round % 5 == 0) {
            // elige entre 2 valores siempre el mayor

            double newX = Math.max(horse.getLayoutX() - 100, firsPosition);
            Pause.updateHorsePlaceWithPause(horse, 1, newX, null);

            //movimiento hacia delante
        } else {

            if (isWinner(horse, horseSuit)) winner = true;

            Pause.updateHorsePlaceWithPause(horse, 1, horse.getLayoutX() + 100, null);

        }
    }

    private boolean isWinner(ImageView horse, String horseSuit) {
        //comprueba si el caballo ha llegado a la linea de meta
        if (horse.getLayoutX() >= 931) {
            Pause.updateLabelWithPause(actionLabel, "FINISH " + horseSuit + " WIN THE RACE", 1, null);
            winHorseSuit = horseSuit;
            logs.put("END GAME ", winHorseSuit + " wins ");

            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void goToDisplay5() {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("display5.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) nextButtonDisplay5.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static String getWinHorseSuit() {
        return winHorseSuit;

    }


}



