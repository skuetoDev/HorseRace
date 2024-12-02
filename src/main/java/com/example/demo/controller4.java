package com.example.demo;

import com.example.demo.helper.CardImageLoader;
import com.example.demo.helper.Pause;
import com.example.demo.model.Card;
import com.example.demo.model.CardsDeck;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class controller4 {


    private static final int row = 4;
    private static final int column = 10;

    protected static int[][] horsePositions;

    @FXML
    private Label roundNumber;

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

    int firsPosition = 132;

    boolean winner = false;

    protected static String winHorseSuit;

    @FXML
    private Button nextButtonDisplay5;


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

        CardsDeck cardsDeck = new CardsDeck();
        gameMovemnt(cardsDeck);


    }

    private void gameMovemnt(CardsDeck cardsDeck) {
        String imagePath = "/com/example/demo/images/BARAJA";


        gameRound(1, imagePath, cardsDeck);
    }

    //metodo por cada ronda
    private void gameRound(int round, String imagePath, CardsDeck cardsDeck) {
        if (winner) {
            nextButtonDisplay5.setOpacity(1);


            return;
        }


        // Actualizar número de ronda
        Pause.updateLabelWithPause(roundNumber, String.valueOf(round), 1, () -> {
            try {
                Card card = cardsDeck.getCardFromDeck();
                Image cardImage = CardImageLoader.loadCardImage(card, imagePath);
                Pause.updateImageWithPause(showCard, cardImage, 1, () -> {
                    Pause.updateLabelWithPause(actionLabel, "Card Taken : " + card.getDescription(), 1, () -> {
                        updateHorsePosition(card, round);
                        gameRound(round + 1, imagePath, cardsDeck);


                    });


                });
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: imagen no encontrada");


            }

        });

    }

    private void updateHorsePosition(Card card, int round) {
        String horseSuit = String.valueOf(card.getSuit());
        ImageView horse = (ImageView) display4.lookup(("#KNIGHT_of_" + horseSuit));
        System.out.println("Ronda " + round + " : ");
        System.out.println(card.getDescription());

        //TODO crear aqui para guardar fichero con la carta sacada y la ronda


        //movimiento hacia atras
        if (round % 5 == 0) {
            // elige entre 2 valores siempre el mayor

            double newX = Math.max(horse.getLayoutX() - 100, firsPosition);
            Pause.updateHorsePlaceWithPause( horse, 0.5, newX, null);
            forwardLabel.setOpacity(0);
            backwardLabel.setOpacity(1);




            //movimiento hacia delante
        } else {

            if (isWinner(horse, horseSuit)) winner = true;

            Pause.updateHorsePlaceWithPause( horse, 0.5, horse.getLayoutX() + 100, null);
            backwardLabel.setOpacity(0);
            forwardLabel.setOpacity(1);







        }


    }

    private boolean isWinner(ImageView horse, String horseSuit) {
        //comprueba si el caballo ha llegado a la linea de meta
        if (horse.getLayoutX() >= 931) {
            Pause.updateLabelWithPause(actionLabel, "FINISH "+ horseSuit + " WIN THE RACE", 1, null);
            winHorseSuit = horseSuit;
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

    public  static String getWinHorseSuit(){
        return winHorseSuit;

    }

}



