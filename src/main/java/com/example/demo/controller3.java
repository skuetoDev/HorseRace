package com.example.demo;
import com.example.demo.helper.AlertUtil;
import com.example.demo.model.players.Human;
import com.example.demo.model.players.Player;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;


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
                AlertUtil.showError("INPUT ERROR", "Please enter a valid number of players 1-4");

            }
        });
    }

    /**
     * method to create dinamic players with numPlayers and one button to add in arraylist players.
     * and check that each horseSuit is already chosen
     * @param numPlayers human players who want play
     */
    protected void createFormularyPlayers(int numPlayers) {

        //clean display3
        display3.getChildren().removeIf(node -> node instanceof TextField || node instanceof Button || node instanceof Label);

         final double startY = 140;

        List<TextField> names = new ArrayList<>();
        List<TextField> bets = new ArrayList<>();
        List<TextField> suits = new ArrayList<>();

        for (int i = 0; i < numPlayers; i++) {

            //create label + textField of Name
            Label labelName = new Label(" Player " + (i + 1) + " Name :");
            labelName.setLayoutX(190);
            labelName.setLayoutY(startY + i * 90);
            labelName.getStyleClass().add("dynamic-label");

            TextField nameField = new TextField();
            nameField.setLayoutX(185);
            nameField.setLayoutY((startY + i  * 90) + 40);
            nameField.getStyleClass().add("dynamic-textField");


            //create label + textField of bet
            Label labelBet = new Label("Player " + (i + 1) + " Bet :");
            labelBet.setLayoutX(495);
            labelBet.setLayoutY(startY + i  * 90);
            labelBet.getStyleClass().add("dynamic-label");

            TextField betField = new TextField();
            betField.setLayoutX(475);
            betField.setLayoutY((startY + i  * 90) + 40);
            betField.getStyleClass().add("dynamic-textField");


            //create label + textField of suit
            Label labelSuit = new Label("Player " + (i + 1) + " Suit:" );
            labelSuit.setLayoutX(770);
            labelSuit.setLayoutY(startY + i  * 90);
            labelSuit.getStyleClass().add("dynamic-label");

            TextField suitField = new TextField();
            suitField.setLayoutX(750);
            suitField.setPromptText("");
            suitField.setLayoutY((startY + i  * 90) + 40);
            suitField.getStyleClass().add("dynamic-textField");

            names.add(nameField);
            bets.add(betField);
            suits.add(suitField);

            display3.getChildren().addAll(labelName, nameField, labelBet, betField, labelSuit, suitField);


        }

        Button savePlayersButton = new Button("Save Players");
        savePlayersButton.setLayoutX(450);
        savePlayersButton.setLayoutY((startY + numPlayers  * 90) + 60);
        savePlayersButton.getStyleClass().add("dynamic-button");
        display3.getChildren().add(savePlayersButton);


        savePlayersButton.setOnAction(event -> {
            players.clear();
            for (int i = 0; i < numPlayers; i++) {

                String name = names.get(i).getText();

                int bet;

                try {

                    bet = Integer.parseInt(bets.get(i).getText());

                } catch (NumberFormatException e) {
                    bet = 1;
                }

                String suit = suits.get(i).getText();
                Human humanPlayer = new Human(name, bet, suit);
                if (players.isEmpty()){
                    players.add(humanPlayer);
                } else {
                    if(!horseSuiteAssigned(suit))
                        players.add(humanPlayer);
                    else
                        AlertUtil.showWarning("SUIT ALREADY CHOSEN","The suits of player "+ (i+1) +
                                " already choose, please choose another suit");

                }



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



}
