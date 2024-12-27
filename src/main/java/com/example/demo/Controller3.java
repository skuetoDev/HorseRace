package com.example.demo;

import com.example.demo.helper.AlertUtil;
import com.example.demo.helper.Database.DatabaseManager;
import com.example.demo.helper.Pause;
import com.example.demo.helper.SetLight;
import com.example.demo.model.Cards.CardSuit;
import com.example.demo.model.GameLogic;
import com.example.demo.model.players.Human;
import com.example.demo.model.players.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Set;

public class Controller3 {

    @FXML
    private Pane display3;

    @FXML
    private TextField playersInput;

    @FXML
    private Button submitButton;

    @FXML
    private Button backButtonDisplay3;

    private static int jackpot = 0;

    private Button playButtonDisplay3;

    private int numPlayers;
    private double startY;

    /**
     * Method to initialize the display and create database and tables of the game
     */
    @FXML
    public void initialize() {
        submitButton.setOnAction(event -> {
            try {
                numPlayers = Integer.parseInt(playersInput.getText());
                createDynamicDisplay3_1(numPlayers);
            } catch (NumberFormatException e) {
                AlertUtil.showError("INPUT ERROR", "Please enter a valid number of players 1-4");

            }
            DatabaseManager.createDatabase();
            DatabaseManager.createTables();
        });
    }

    /**
     * method to create a dynamic display 3.2 where there are 3 labels and 3 Textfields to
     * create a human player, there are 3 buttons also back to go to display2 save players
     * ( which saves them in a LinkedHasMap ) and advance to the dynamic display 3.2
     * @param numPlayers human players who wants to play
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
            nameField.setLayoutY((startY + i * 90) + 40);
            nameField.setId("nameTextField-" + i);
            nameField.getStyleClass().add("dynamic-textField");


            //create label + textField of bet
            Label labelBet = new Label("Player " + (i + 1) + " Bet :");
            labelBet.setLayoutX(535);
            labelBet.setLayoutY(startY + i * 90);
            labelBet.getStyleClass().add("dynamic-label");

            TextField betField = new TextField();
            betField.setLayoutX(520);
            betField.setLayoutY((startY + i * 90) + 40);
            betField.setId("betTextField-" + i);
            betField.getStyleClass().add("dynamic-textField");


            //create label + textField of suit
            Label labelSuit = new Label("Player " + (i + 1) + " Suit:");
            labelSuit.setLayoutX(850);
            labelSuit.setLayoutY(startY + i * 90);
            labelSuit.getStyleClass().add("dynamic-label");

            TextField suitField = new TextField();
            suitField.setLayoutX(835);
            suitField.setId("suitTextField-" + i);
            suitField.setLayoutY((startY + i * 90) + 40);
            suitField.getStyleClass().add("dynamic-textField");


            display3.getChildren().addAll(labelName, nameField, labelBet, betField, labelSuit, suitField);
        }

        backButtonDisplay3 = new Button("Back");
        backButtonDisplay3.setLayoutX(325);
        backButtonDisplay3.setLayoutY(((startY + numPlayers * 90) + 100));
        backButtonDisplay3.getStyleClass().add("dynamic-button");
        backButtonDisplay3.setEffect(SetLight.setLight());
        backButtonDisplay3.setOnAction(event -> goToDisplay2());
        display3.getChildren().add(backButtonDisplay3);

        Button savePlayersButton = new Button("SAVE PLAYERS");
        savePlayersButton.setLayoutX(470);
        savePlayersButton.setLayoutY((startY + numPlayers * 90) + 100);
        savePlayersButton.getStyleClass().add("dynamic-button");
        savePlayersButton.setEffect(SetLight.setLight());
        savePlayersButton.setOnAction(event -> createPlayers());
        display3.getChildren().add(savePlayersButton);


        Button nextButtondisplay3 = new Button("Next");
        nextButtondisplay3.setLayoutX(765);
        nextButtondisplay3.setLayoutY((startY + numPlayers * 90) + 100);
        nextButtondisplay3.getStyleClass().add("dynamic-button");
        nextButtondisplay3.setEffect(SetLight.setLight());
        nextButtondisplay3.setOnMouseClicked(event -> {
            if (GameLogic.getPlayers().isEmpty()) {
                AlertUtil.showError("ERROR SAVED PLAYER", "You must save players before go next display");
            } else {
                DatabaseManager.insertTablePlayersInfo(GameLogic.getPlayers());
                dynamicDisplay3_2();
            }

        });
        display3.getChildren().add(nextButtondisplay3);


    }

    /**
     * method to create human/bot players
     */
    private void createPlayers() {

        try {
            GameLogic.clearPlayers();
            for (int i = 0; i < numPlayers; i++) {
                TextField nameField = (TextField) display3.lookup("#nameTextField-" + i);
                TextField betField = (TextField) display3.lookup("#betTextField-" + i);
                TextField suitField = (TextField) display3.lookup("#suitTextField-" + i);

                if (nameField.getText().isEmpty() || suitField.getText().isEmpty() || betField.getText().isEmpty()) {
                    throw new IllegalArgumentException("Name, Bet and Suit cannot be empty");
                }
                int bet = Integer.parseInt(betField.getText());

                if (bet <= 0 || bet > 100) {
                    throw new IllegalArgumentException("Bet must to be between 1 and 100");

                }

                String suitImput = suitField.getText().toUpperCase();
                try{
                    CardSuit.valueOf(suitImput);
                }catch (IllegalArgumentException e){
                    throw new IllegalArgumentException("Invalid suit: " + suitImput + " Valid suits are: GOLD, SWORDS, CUPS, CLUBS.");
                }

                Human humanPlayer = new Human(nameField.getText(), Integer.parseInt(betField.getText()), suitField.getText());
                if (GameLogic.getPlayers().isEmpty()) {
                    GameLogic.getPlayers().add(humanPlayer);
                } else {
                    if (!GameLogic.horseSuiteAssigned(suitField.getText())) {
                        GameLogic.getPlayers().add(humanPlayer);

                    } else {
                        AlertUtil.showInformation("SUIT ALREADY CHOSEN", "The suits of player " + (i + 1) +
                                " already choose, please choose another suit");
                        return;
                    }
                }
            }
            GameLogic.createBotPlayers(numPlayers);


            AlertUtil.showInformation("SUCCESS", "Players saved successfully");
        } catch (NumberFormatException e) {
            AlertUtil.showError("INPUT ERROR", "Bet must be a valid number!");
        } catch (IllegalArgumentException e) {
            AlertUtil.showError("INPUT ERROR", e.getMessage());
        }


    }

    /**
     * Method to create a dinamic display3.2 where there is one label with all players and his information
     * (name,bet,horsesuit chosen), and one buton (play) to start the game.
     */
    private void dynamicDisplay3_2() {
        display3.getChildren().removeIf(node -> node instanceof TextField || node instanceof Button || node instanceof Label);

        for (int i = 0; i < GameLogic.getPlayers().size(); i++) {
            String information;


            Label playersTitleLabel = new Label(" \uD83C\uDFC7      Players     \uD83C\uDFC7");
            playersTitleLabel.setLayoutX(180);
            playersTitleLabel.setLayoutY(70);
            playersTitleLabel.getStyleClass().add("dynamic-labelTitle-players");
            display3.getChildren().add(playersTitleLabel);


            information = "Player " + (i + 1) + " Name :  " + GameLogic.getPlayers().get(i).getName() +
                    "\nBet :  " + GameLogic.getPlayers().get(i).getBet() + " €" +
                    "\nHorse suit :  " + GameLogic.getPlayers().get(i).getHorseSuit();

            Label playersNameLabel = new Label(information);
            playersNameLabel.setLayoutX(200);
            playersNameLabel.setLayoutY(120 + i * 100);
            playersNameLabel.getStyleClass().add("dynamic-label-players");


            //to make pause

            Pause.slowShow(i, display3, playersNameLabel).play();


        }

        Label jackpotLabel = new Label(" $    The Jackpot       $");
        jackpotLabel.setLayoutX(650);
        jackpotLabel.setLayoutY(380);
        jackpotLabel.getStyleClass().add("dynamic-labelTitle-players");
        display3.getChildren().add(jackpotLabel);


        for (Player p : GameLogic.getPlayers()) {
            jackpot +=  p.getBet();

        }

        Label jackpotAmount = new Label("The total jackpot is " + jackpot + " €" + "\n" +
                "\nand the winner of the race  " + "\n" +
                "\n will WIN it !");
        jackpotAmount.setLayoutX(700);
        jackpotAmount.setLayoutY(440);
        jackpotAmount.getStyleClass().add("dynamic-label-players");
        display3.getChildren().add(jackpotAmount);

        Image moneyBag = new Image(Objects.requireNonNull(getClass().getResource("images/bolsa.png")).toExternalForm());

        ImageView imageView = new ImageView(moneyBag);
        imageView.setLayoutX(700);
        imageView.setLayoutY(70);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        display3.getChildren().add(imageView);


        playButtonDisplay3 = new Button("PLAY");
        playButtonDisplay3.setLayoutX(500);
        playButtonDisplay3.setLayoutY((startY + GameLogic.getPlayers().size() * 90) + 100);
        playButtonDisplay3.getStyleClass().add("dynamic-button");
        playButtonDisplay3.setEffect(SetLight.setLight());
        playButtonDisplay3.setOnAction(event -> goToDisplay4());
        display3.getChildren().add(playButtonDisplay3);


    }

    /**
     * Method to back display2
     */
    private void goToDisplay2() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("display2.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) backButtonDisplay3.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("ERROR in goToDisplay2() " + e.getMessage());
        }


    }

    /**
     * Method to go next display display4
     */
    public void goToDisplay4() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("display4.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) playButtonDisplay3.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int getJackpot() {
        return jackpot;
    }

    public static void setJackpot(int jackpot) {
        Controller3.jackpot = jackpot;
    }
}
