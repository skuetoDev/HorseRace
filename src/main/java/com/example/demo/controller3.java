package com.example.demo;
import com.example.demo.helper.AlertUtil;
import com.example.demo.model.players.Bot;
import com.example.demo.model.players.Human;
import com.example.demo.model.players.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class controller3 {

    @FXML
    private Pane display3;


    @FXML
    private TextField playersInput;


    @FXML
    private Button submitButton;

    private List<Player> players = new ArrayList<>();

    @FXML
    private Button backButtonDisplay3;

    private Button savePlayersButton;

    private Button display3NextButton;

    private Button playButtonDisplay3;

    int numPlayers;
    double startY;
    List<TextField> names = new ArrayList<>();
    List<TextField> bets = new ArrayList<>();
    List<TextField> suits = new ArrayList<>();






    @FXML
    public void initialize() {
        submitButton.setOnAction(event ->{

            try {
                numPlayers = Integer.parseInt(playersInput.getText());
                createDynamicDisplay3_1(numPlayers);
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
    private void createDynamicDisplay3_1(int numPlayers) {

        //clean display3
        display3.getChildren().removeIf(node -> node instanceof TextField || node instanceof Button || node instanceof Label);

        startY = 140;

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


        backButtonDisplay3 = new Button("BACK");
        backButtonDisplay3.setLayoutX(350);
        backButtonDisplay3.setLayoutY(((startY + numPlayers  * 90) + 60));
        backButtonDisplay3.getStyleClass().add("dynamic-button");
        backButtonDisplay3.setEffect(setLight());
        backButtonDisplay3.setOnAction(event -> goToDisplay2());
        display3.getChildren().add(backButtonDisplay3);

        savePlayersButton = new Button("Save Players");
        savePlayersButton.setLayoutX(550);
        savePlayersButton.setLayoutY((startY + numPlayers  * 90) + 60);
        savePlayersButton.getStyleClass().add("dynamic-button");
        savePlayersButton.setEffect(setLight());
        savePlayersButton.setOnAction(event -> createPlayers());
        display3.getChildren().add(savePlayersButton);


    }



    private  void createPlayers(){
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
            createBotPlayers(numPlayers);
            dynamicDisplay3_2();

    }

    private void createBotPlayers(int botPlayer) {

        for (int i = botPlayer; i < 4; i++) {
            boolean exit = false;
            while (!exit) {
                Bot playerBot = new Bot();
                if (!horseSuiteAssigned(playerBot.getHorseSuit()) && !nameAlreadyExists(playerBot.getName())) {
                    players.add(playerBot);
                    exit = true;
                }
            }
        }

    }

    private void dynamicDisplay3_2(){
        display3.getChildren().removeIf(node -> node instanceof TextField || node instanceof Button || node instanceof Label);

        for(int i = 0 ; i < players.size() ; i++ ){
            String information;


            Label playersTitleLabel = new Label(" \uD83C\uDFC7      Players     \uD83C\uDFC7");
            playersTitleLabel.setLayoutX(400);
            playersTitleLabel.setLayoutY(70);
            playersTitleLabel.getStyleClass().add("dynamic-labelTitle-players");
            display3.getChildren().add(playersTitleLabel);



            information = "➤ Player " + (i+1) + " Name :  " + players.get(i).getName() +
                        "\n        Bet :  " + players.get(i).getBet() + " €" +
                        "\n        Horse suit :  " + players.get(i).getHorseSuit();

            Label playersNameLabel = new Label(information);
            playersNameLabel.setLayoutX(400);
            playersNameLabel.setLayoutY(120 + i * 100) ;
            playersNameLabel.getStyleClass().add("dynamic-label-players");
            display3.getChildren().add(playersNameLabel);


        }


        playButtonDisplay3 = new Button("PLAY");
        playButtonDisplay3.setLayoutX(500);
        playButtonDisplay3.setLayoutY((startY + players.size()  * 90) + 60);
        playButtonDisplay3.getStyleClass().add("dynamic-button");
        playButtonDisplay3.setEffect(setLight());
        playButtonDisplay3.setOnAction(event -> goToDisplay4());
        display3.getChildren().add(playButtonDisplay3);


    }


    private boolean horseSuiteAssigned(String horseSuit) {
        for (Player p : players) {
            if (p.getHorseSuit().equalsIgnoreCase(horseSuit)) {
                return true;
            }
        }
        return false;
    }


    private void goToDisplay2(){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("display2.fxml")));
            Scene scene = new Scene(root);
            Stage stage =(Stage) backButtonDisplay3.getScene().getWindow();
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private boolean nameAlreadyExists(String name) {
        for (Player p : players) {
            if (p.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;

    }

    private Effect setLight(){
        Lighting lightingEffect = new Lighting();
        Light.Distant light = new Light.Distant();

        light.setColor(Color.WHITE);
        light.setAzimuth(45);
        light.setElevation(45);
        lightingEffect.setLight(light);

        lightingEffect.setDiffuseConstant(1.37);
        lightingEffect.setSurfaceScale(3.22);

        return lightingEffect;

    }

    private void goToDisplay4(){
        try{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("display4.fxml")));
            Scene scene = new Scene(root);
            Stage stage =(Stage) playButtonDisplay3.getScene().getWindow();
            stage.setScene(scene);
        }catch (Exception e){
            e.printStackTrace();
        }

    }




}
