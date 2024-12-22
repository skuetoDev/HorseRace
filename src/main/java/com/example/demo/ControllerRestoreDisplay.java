package com.example.demo;

import com.example.demo.helper.Database.DatabaseManager;
import com.example.demo.helper.SetLight;
import com.example.demo.model.GameLogic;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ControllerRestoreDisplay {

    @FXML
    private Button backToMenuButton;
    @FXML
    private FlowPane buttonsContainer;
    protected static int roundNumber;


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
    @FXML
    public void initialize (){
        loadGameButtons();

    }

    private void loadGameButtons(){

        List<String> games = DatabaseManager.getUnfinishedGames();

        for(String game : games){
            int newIdgame = Integer.parseInt(game.replace("players_game",""));

            Button gameButton = new Button("Game "+newIdgame);
            gameButton.getStyleClass().add("dynamic-restoreButton");
            gameButton.setEffect(SetLight.setLight());

            List<Map<String, Object>> players = DatabaseManager.selectPlayersFromTable(game);


            gameButton.setOnAction(event -> {
                int nextRound = lastRound(Integer.parseInt(game.replace("players_game",""))) + 1;
                goToDisplay4Restored(nextRound, players);

            });
            buttonsContainer.getChildren().add(gameButton);
            DatabaseManager.setIdPartida(newIdgame);
        }
    }

    private void goToDisplay4Restored(int round, List<Map<String, Object>> players){
        roundNumber = round;
        try{
            FXMLLoader  loader = new FXMLLoader(getClass().getResource("display4.fxml"));
            Parent root = loader.load();
            Controller4 controller = loader.getController();
            Controller4.setRestoring(true);
            controller.setGameData(players);
            Scene scene = new Scene(root);
            Stage stage = (Stage) buttonsContainer.getScene().getWindow();
            stage.setScene(scene);
            stage.show();




        }catch (Exception e){
            System.out.println("ERROR in goToDisplay4Restored" + e.getMessage());

        }


    }

    private int lastRound( int gameNumber){
        String tableName = "logs_game" + gameNumber;
        String query = "SELECT id_round FROM "+ tableName + " ORDER BY id_round DESC LIMIT 1";
        int lastId = -1; // if table is empty
        try(PreparedStatement prstm = DatabaseManager.getConnection().prepareStatement(query);
            ResultSet rs = prstm.executeQuery()){
            if(rs.next()) lastId = rs.getInt("id_round");


        }catch (SQLException e ){
            System.out.println("ERROR lastRound() " + e.getMessage());
        }
        return lastId;

    }


}
