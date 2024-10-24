package logic;

import model.Card;
import model.CardsDeck;
import model.players.Player;
import static helper.Pause.*;
import static helper.Prints.*;
import static logic.Croupier.*;
import static logic.GameBoard.placeHorseOnBoard;


public class GameLogic {

    //fields

    private static final int row = 4;
    private static final int column = 10;
    protected static int[][] horsePositions;
    private static int round = 0;
    private final CardsDeck cardsDeck;
    private static Card card;
    private static boolean winner = false;
    private static String championSuit = "";
    private GameBoard gameBoard;

    //constructor

    public GameLogic() {
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
        cardsDeck = new CardsDeck();
        gameBoard = new GameBoard();
        placeHorseOnBoard(horsePositions);
        printViewBoard();
        gameMovement();

    }

    /**
     * method to get a card from deck and depends on suit make horse move in the array of numbers horsePositions
     */
    private void gameMovement() {
        do {
            card = cardsDeck.getCardFromDeck();
            round++;
            printRoundNumber();
            printPullCard();
            String suit = String.valueOf(card.getSuit());
            switch (suit) {
                case "GOLD":
                    if ((round % 5 == 0)) {
                        backward(0);
                    } else {
                        fordward(0);
                    }
                    break;
                case "CLUBS":
                    if ((round % 5 == 0)) {
                        backward(1);
                    } else {
                        fordward(1);
                    }
                    break;
                case "CUPS":
                    if ((round % 5 == 0)) {
                        backward(2);
                    } else {
                        fordward(2);
                    }
                    break;
                case "SWORDS":
                    if ((round % 5 == 0)) {
                        backward(3);
                    } else {
                        fordward(3);
                    }
                    break;

            }
        } while (!winner);
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
            printFinish();
            placeHorseOnBoard(horsePositions);
            printViewBoard();
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
        pauseLineBreak(1);
    }


    /**
     * Method to move back a horse in array of numbers horsePositions
     *
     * @param road the number who represent a horseSuit
     */
    private void backward(int road) {
        if (horsePositions[road][0] == 1) {
            printNoMovement();
            placeHorseOnBoard(horsePositions);
            printViewBoard();
        } else {
            for (int j = 0; j < column; j++) {
                if (horsePositions[road][j] == 1) {
                    if (j > 0) {
                        horsePositions[road][j - 1] = 1;
                        horsePositions[road][j] = 0;
                        break;
                    }
                }
            }
            printBackward();
            placeHorseOnBoard(horsePositions);
            printViewBoard();
            pauseLineBreak(1);
        }
    }

    /**
     * Method to get the horseSuit in a String, depends on road number
     *
     * @param road that represents the suit of horse
     * @return String with the suit of horse
     */
    private String championSuit(int road) {
        String suit = "";
        switch (road) {
            case 0:
                suit = "GOLD";
                break;
            case 1:
                suit = "CLUBS";
                break;
            case 2:
                suit = "CUPS";
                break;
            case 3:
                suit = "SWORDS";
                break;
            default:
                suit = "ERROR: 3";
                break;
        }

        return suit;

    }

    /**
     * Method to get de player name who is in array players and choose the winner horse
     *
     * @param suit of horse that wins
     * @return a name of player whose horse has won
     */
    private String playerSuit(String suit) {
        String winnerName = "";
        for (Player p : getPlayers()) {
            if (p.getHorseSuit().equalsIgnoreCase(suit)) {
                winnerName = p.getName();
            }
        }
        return winnerName;
    }


    //Setter and Getter
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
}


