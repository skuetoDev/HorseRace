package com.example.demo;

import com.example.demo.model.CardSuit;
import com.example.demo.model.players.Human;
import com.example.demo.model.players.Player;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static com.example.demo.helper.Colour.*;
import static javafx.application.Application.launch;

public class controller3 {

    @FXML
    private Pane display3;


    @FXML
    private TextField playersInput;


    @FXML
    private Button submitButton;

    private List<Player> players = new ArrayList<>();


    @FXML
    public void initialize() {
        submitButton.setOnAction(event ->{

            try {
                int numPlayers = Integer.parseInt(playersInput.getText());
                createFormularyPlayers(numPlayers);
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Please enter a valid number of players");

            }
        });
    }

    /**
     * method to create dinamic players with numPlayers
     *
     * @param numPlayers
     */
    protected void createFormularyPlayers(int numPlayers) {



        //clean display3
        display3.getChildren().removeIf(node -> node instanceof TextField || node instanceof Button || node instanceof Label);

        double startY = 90;

        List<TextField> names = new ArrayList<>();
        List<TextField> bets = new ArrayList<>();
        List<TextField> suits = new ArrayList<>();

        for (int i = 0; i < numPlayers; i++) {

            //create label + textField of Name
            Label labelName = new Label(" Player " + (i + 1) + " Name : ");
            labelName.setLayoutX(190);
            labelName.setLayoutY(startY + (i+1) * 50);
            labelName.getStyleClass().add("dynamic-label");

            TextField nameField = new TextField();
            nameField.setLayoutX(195);
            nameField.setLayoutY(startY + (i+1) * 90);


            //create label + textfield of bit
            Label labelBet = new Label("Bet of player " + (i + 1));
            labelBet.setLayoutX(450);
            labelBet.setLayoutY(startY + (i + 1) * 50);
            labelBet.getStyleClass().add("dynamic-label");

            TextField betField = new TextField();
            betField.setLayoutX(450);
            betField.setLayoutY(startY + (i + 1) * 90);

            //create label + textfield of suit
            Label labelSuit = new Label("Suit ");
            labelSuit.setLayoutX(750);
            labelSuit.setLayoutY(startY + i + 50);
            labelSuit.getStyleClass().add("dynamic-label");

            TextField suitField = new TextField();
            suitField.setLayoutX(750);
            suitField.setLayoutY(startY + (i + 1) * 90);

            names.add(nameField);
            bets.add(betField);
            suits.add(suitField);

            display3.getChildren().addAll(labelName, nameField, labelBet, betField, labelSuit, suitField);


        }

        Button savePlayersButton = new Button("Save Players");
        savePlayersButton.setLayoutX(180);
        savePlayersButton.setLayoutY(startY + numPlayers + 50 + 20);
        savePlayersButton.getStyleClass().add("dynamic-button");

        savePlayersButton.setOnAction(event -> {
            players.clear();
            for (int i = 0; i < numPlayers; i++) {

                String name = names.get(i).getText();

                int suit, bet;


                try {

                    bet = Integer.parseInt(bets.get(i).getText());

                } catch (NumberFormatException e) {
                    bet = 1;
                    suit = 1;
                }
                boolean validSuit = false;
                do {
                    try {
                        suit = Integer.parseInt(suits.get(i).getText());
                        if (horseSuiteAssigned(CardSuit.values()[suit - 1].toString())) {
                            showAlert("Suit asigned", "Please choose another one");
                        } else {
                            validSuit = true;
                        }

                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        showAlert(" Error suit", "Number suit must be number between 1-4");
                        suit = 1;
                    }
                } while (!validSuit);
                Human player = new Human(name, suit, bet);
                players.add(player);

            }
        });


    }

    private boolean horseSuiteAssigned(String horseSuit) {
        for (Player p : players) {
            if (p.getHorseSuit().equalsIgnoreCase(horseSuit)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Muestra un cuadro de diálogo de alerta
     * @param title Título de la alerta
     * @param message Mensaje del cuerpo
     */
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
