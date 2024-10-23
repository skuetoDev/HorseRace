package logic;

import model.Card;
import model.CardsDeck;
import players.Player;

import static helper.Colour.*;
import static helper.Pause.*;
import static helper.Prints.*;
import static logic.Croupier.*;
import static logic.GameBoard.placeHorseOnBoard;


public class GameLogic {

    //fields
    private static int row = 4;
    private static int column = 10;
    protected static int [][] horsePositions;
    private static int round = 0;
    private CardsDeck cardsDeck;
    private static Card card;
    private static boolean winner = false;
    private static String championSuit = "";
    private GameBoard gameBoard;

    //constructor
    public GameLogic() {
        horsePositions = new int[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (j == 0){
                    horsePositions[i][j] = 1;
                }else{
                    horsePositions[i][j] = 0;
                }
            }
        }
        cardsDeck = new CardsDeck();
        gameBoard = new GameBoard();
        gameMovement();
    }

    private void gameMovement(){
        do {
            card = cardsDeck.getCardFromDeck();
            round++;
            printRoundNumber();
            printPullCard();
            String suit = String.valueOf(card.getSuit());
            switch (suit){
                case "GOLD":
                    if ((round % 5 == 0)) {
                        bakckward(0);
                    } else {
                        fordward(0);
                    }
                    break;
                case "CLUBS":
                    if ((round % 5 == 0)) {
                        bakckward(1);
                    } else {
                        fordward(1);
                    }
                    break;
                case "CUPS":
                    if ((round % 5 == 0)) {
                        bakckward(2);
                    } else {
                        fordward(2);
                    }
                    break;
                case "SWORDS":
                    if ((round % 5 == 0)) {
                        bakckward(3);
                    } else {
                        fordward(3);
                    }
                    break;

            }
        }while(!winner);
    }


    /**
     * Method to add one position in horsesPosition array, no matter what suit it was
     *
     * @param road from the deck to get the suit
     */
    private void fordward(int road) {


        if (horsePositions[road][9] == 1) {
            winner = true;
            championSuit = championSuit(road);
            setPlayerWin(playerSuit(championSuit));
            return;
        }
        for (int j = 0; j < horsePositions[road].length; j++) {
            if (horsePositions[road][j] == 1) {
                horsePositions[road][j + 1] = 1;
                horsePositions[road][j] = 0;
                break;
            }
        }
        printFordward();
        placeHorseOnBoard(horsePositions);
        printViewBoard();
        //printHorsesPosition();
        pauseLineBreak(1);
    }

    private void bakckward(int road) {


        if (horsePositions[road][0] == 1) {
            printNoMovement();
            placeHorseOnBoard(horsePositions);
            printViewBoard();
            //printHorsesPosition();
            return;
        }
        for (int j = 0; j < getHorsePositions().length; j++) {
            if (horsePositions[road][j] == 1) {
                horsePositions[road][j - 1] = 1;
                horsePositions[road][j] = 0;
                break;
            }
        }
        printBackward();
        placeHorseOnBoard(horsePositions);
        printViewBoard();
        //printHorsesPosition();
        pauseLineBreak(1);
    }


    private String championSuit(int road) {
        return switch (road) {
            case 0 -> "GOLD";
            case 1-> "CLUBS";
            case 2 -> "CUPS";
            case 3 -> "SWORDS";
            default -> "ERROR: 3";
        };
    }

    private String playerSuit(String suit) {
         String winnerName= "";
        for (Player p : getPlayers()) {
            if (p.getHorseSuit().equalsIgnoreCase(suit)) {
                winnerName = p.getName();
            }
        }
        return winnerName;
    }

    public static String getChampionSuit() {
        return championSuit;
    }

    public static boolean isWinner() {
        return winner;
    }

    public static int getRound() {
        return round;
    }

    public static Card getCard() {
        return card;
    }

    public static int getRow() {
        return row;
    }

    public static int getColumn() {
        return column;
    }

    public static int[][] getHorsePositions() {
        return horsePositions;
    }
}


