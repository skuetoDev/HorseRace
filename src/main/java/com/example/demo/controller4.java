package com.example.demo;

import com.example.demo.helper.CardImageLoader;
import com.example.demo.helper.Pause;
import com.example.demo.logic.GameLogic;
import com.example.demo.model.Card;
import com.example.demo.model.CardSuit;
import com.example.demo.model.CardsDeck;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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

        gameMovemnt();


    }

    private void gameMovemnt() {
        String imagePath = "/com/example/demo/images/BARAJA";
        int round = 0;

        CardsDeck cardsDeck = new CardsDeck();
        int maxRounds = 40; // Número máximo de rondas
        startGameRound(1, imagePath, cardsDeck, maxRounds);
    }

    private void startGameRound(int round, String imagePath, CardsDeck cardsDeck, int maxRounds) {
        int firsPosition = 132;

        if (round > maxRounds) {
            System.out.println("Juego terminado");
            return; // Finaliza el juego
        }

        // Actualizar número de ronda
        Pause.updateLabelWithPause(display4, roundNumber, String.valueOf(round), 1, () -> {
            // Acción después de actualizar el número de ronda
            Card card = cardsDeck.getCardFromDeck();
            Pause.updateLabelWithPause(display4, actionLabel, "Carta jugada: " + card.getDescription(), 1, () -> {
                try {
                    Image cardImage = CardImageLoader.loadCardImage(card, imagePath);
                    Pause.updateImageWithPause(display4, showCard, cardImage, 1, () -> {
                        String horseSuit = String.valueOf(card.getSuit());
                        ImageView imageView = (ImageView) display4.lookup(("#KNIGHT_of_"+horseSuit));
                        System.out.println(card.getDescription());

                        if(round != 0 ){
                            if (round == 1){
                                imageView.setLayoutX(firsPosition);
                            }else{
                                imageView.setLayoutX(imageView.getLayoutX() + 100 );
                            }

                        }



                        // Llamar recursivamente para la siguiente ronda
                        startGameRound(round + 1, imagePath, cardsDeck, maxRounds);
                    });
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: No se encontró la imagen: " + e.getMessage());

                    // Llamar a la siguiente ronda aunque falte la imagen
                    startGameRound(round + 1, imagePath, cardsDeck, maxRounds);
                }
            });
        });
    }
}
