package com.example.demo;

import com.example.demo.helper.Database.DatabaseManager;
import com.example.demo.helper.SetLight;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ControllerRestoreDisplay {

    @FXML
    private Button backToMenuButton;
    @FXML
    private FlowPane buttonsContainer;
    protected static int roundNumber;

    private static boolean isRestoring = false;
    public static boolean getIsRestoring() {
        return isRestoring;
    }


    /**
     * Method to go to display2
     */
    @FXML
    protected void goToDisplay2() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("display2.fxml")));
            Scene scene = new Scene(root);
            Stage stage = (Stage) backToMenuButton.getScene().getWindow();
            stage.setScene(scene);
        } catch (Exception e) {
            System.out.println("ERROR goToDisplay2 " + e.getMessage());
        }
    }

    /**
     * Method to initialize the display
     */
    @FXML
    public void initialize (){
            loadGameButtons();
    }

    /**
     * Method to create dynamic buttons depending on the games with non-wining games that have
     */
    private void loadGameButtons(){

        List<String> games = DatabaseManager.getUnfinishedGames();

        for(String game : games){
            int newIdgame = Integer.parseInt(game.replace("players_game",""));

            Button gameButton = new Button("Game "+newIdgame);
            gameButton.getStyleClass().add("dynamic-restoreButton");
            gameButton.setEffect(SetLight.setLight());

            List<Map<String, Object>> players = DatabaseManager.selectPlayersFromTable(game);


            gameButton.setOnAction(event -> {
                int nextRound = DatabaseManager.lastRound(Integer.parseInt(game.replace("players_game",""))) + 1;
                goToDisplay4Restored(nextRound, players);

            });
            buttonsContainer.getChildren().add(gameButton);
            DatabaseManager.setIdPartida(newIdgame);
        }
    }

    /**
     * Method to go to display4 (board display) with specific parameters
     * @param round round to update the display (load from database)
     * @param players arrayList of Maps with String and objects that contains the information to restore specific display
     */
    private void goToDisplay4Restored(int round, List<Map<String, Object>> players){
        roundNumber = round;
        try{
            isRestoring = true;
            FXMLLoader  loader = new FXMLLoader(getClass().getResource("display4.fxml"));
            Parent root = loader.load();
            Controller4 controller = loader.getController();

            controller.setGameData(players);
            Scene scene = new Scene(root);
            Stage stage = (Stage) buttonsContainer.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

        }catch (Exception e){
            System.out.println("ERROR in goToDisplay4Restored" + e.getMessage());
        }
    }



}
