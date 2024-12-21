package com.example.demo;

import com.example.demo.helper.CardImageLoader;
import com.example.demo.helper.FileLogsAccess;
import com.example.demo.helper.Pause;
import com.example.demo.helper.RoundMaxException;
import com.example.demo.model.Cards.Card;
import com.example.demo.model.GameLogic;
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
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Objects;


public class Controller4 {

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


    private boolean winner = false;

    protected static String winHorseSuit;

    protected int round = 1;

    protected int counterExcepcion = 0;

    private LinkedHashMap<String, String> logs;
    private FileLogsAccess fileAccess;



    @FXML
    public void initialize() {

        gameStart();


    }

    /**
     * Method to start the game, with import createCardsDeck from Logic, then star rounds with gameRound
     */
    private void gameStart() {

        fileAccess = new FileLogsAccess();
        fileAccess.deleteJSON();
        GameLogic.createCardsDeck();
        gameRound();


    }


    /**
     * Method for each round in game, first check if winner its true create a .txt with
     * all movements in the game. if its false, get a card from deck with method getCard().
     */
    protected void gameRound() {
        try {
            GameLogic.checkRound(round, counterExcepcion);
            if (winner) {
                nextButtonDisplay5.setOpacity(1);
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

    /**
     * Method to get a card from deck show corresponding image from BARAJA, and with methods from helper.Pause
     * to make pause with all interactions in this display.
     */
    private void getCard() {
        Card card = GameLogic.getCardsDeck().getCardFromDeck();
        String imagePath = "/com/example/demo/images/BARAJA";
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

    /**
     * Method to create a window to explain the deck is empty. if you want to play more to shuffling again or
     * you want to exit, to display1 ( menu)
     *
     * @param message custom to communicate user something
     */
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

    /**
     * Method to go display1
     */
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

    /**
     * Method to move image of horse move one position forward or backward
     *
     * @param card to extract the suit to move horse
     */
    private void updateHorsePosition(Card card) {
        String horseSuit = String.valueOf(card.getSuit());
        ImageView horse = (ImageView) display4.lookup(("#KNIGHT_of_" + horseSuit));
        //todo acceso y guardado  bbdd
        try{
            fileAccess.loadFromJSON();
            fileAccess.addRound(round, card.getDescription());
            fileAccess.saveToJSON();
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }

        /*
        System.out.println("Ronda " + round + " : ");
        System.out.println(card.getDescription());
        */

        //back
        if (round % 5 == 0) {
            // elige entre 2 valores siempre el mayor

            int firsPosition = 132;
            double newX = Math.max(horse.getLayoutX() - 100, firsPosition);
            Pause.updateHorsePlaceWithPause(horse, 1, newX, null);

            //next
        } else {

            if (isWinner(horse, horseSuit)) winner = true;

            Pause.updateHorsePlaceWithPause(horse, 1, horse.getLayoutX() + 100, null);

        }
    }

    /**
     * Method to check if one horse cross finish line
     * @param horse     image to check if is cross finish line
     * @param horseSuit to check
     * @return true or false if this horse pass finish line
     */
    private boolean isWinner(ImageView horse, String horseSuit) {

        if (horse.getLayoutX() >= 931) {
            Pause.updateLabelWithPause(actionLabel, "FINISH " + horseSuit + " WIN THE RACE", 1, null);
            winHorseSuit = horseSuit;


            try{
                fileAccess.loadFromJSON();
                fileAccess.addRound(round," END GAME " + winHorseSuit + " WINS");
                fileAccess.saveToJSON();

            }catch (IOException ex){
                System.out.println(ex.getMessage());
            }

            return true;
        } else {
            return false;
        }
    }

    /**
     * Method to go display5
     */
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



