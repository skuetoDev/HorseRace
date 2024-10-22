package logic;

import model.Card;
import model.CardsDeck;
import players.Player;

import static helper.Colour.*;
import static helper.Pause.*;
import static helper.Prints.*;


public class GameLogic {

    //fields
    private int row = 9;
    private int column = 81;
    protected String[][] board;
    private int round = 0;
    private CardsDeck cardsDeck;
    private Card card;
    boolean winner = false;
    String championSuit = "";



    //constructor
    public GameLogic() {
        board = new String[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i % 2 == 0) {
                    // Filas impares para asteriscos y guiones
                    if (j == 2) {
                        board[i][j] = "     ";
                    } else if (j % 8 == 0 & j > 0) {
                        board[i][j] = "\u001B[95m*\u001B[0m";
                    } else if (j > 8) {
                        board[i][j] = "\u001B[94m─\u001B[0m";
                    } else {
                        board[i][j] = " ";
                    }
                } else {
                    // Filas de celdas para nombres y
                    if (i == 1 && j == 3) {
                        board[i][j] = goldYellow() + "GOLD " + restore();
                    } else if (i == 1 && j == 12) {
                        board[i][j] = goldYellow() + "█" + restore();
                    } else if (i == 3 && j == 3) {
                        board[i][j] = clubGreen() + "CLUBS" + restore();
                    } else if (i == 3 && j == 12) {
                        board[3][12] = clubGreen() + "█" + restore();
                    } else if (i == 5 && j == 3) {
                        board[i][j] = cupRed() + "CUPS " + restore();
                    } else if (i == 5 && j == 12) {
                        board[5][12] = cupRed() + "█" + restore();
                    } else if (i == 7 && j == 3) {
                        board[i][j] = swordBlue() + "SWORD" + restore();
                    } else if (i == 7 && j == 12) {
                        board[7][12] = swordBlue() + "█" + restore();
                    } else if (j % 8 == 0 && j > 0) {
                        board[i][j] = "\u001B[34m│\u001B[0m";
                    } else {
                        board[i][j] = " ";
                    }
                }
            }

        }
        printPositions(board, row, column);
        cardsDeck = new CardsDeck();
    }

    public void horseMovemnt() {
        do {
            card = cardsDeck.getCardFromDeck();
            round++;
            if (round % 5 == 0) {
                printTextNumber(croupierVoice() + "ROUND " + restore(), round);
                pauseSelection(1);
                bakckward(card);
                halfPause(1);
                printTextLineBreak(croupierVoice() + "JUMP BACKWARD" + restore());
            } else {
                printTextNumber("ROUND : ", round);
                pauseSelection(1);
                fordward(card);
                halfPause(1);
                printTextLineBreak(croupierVoice() + "JUMP FORDWARD" + restore());
            }
        } while (!winner);
    }


    /**
     * Method to add one position in horsesPosition array, no matter what suit it was
     *
     * @param card from the deck to get the suit
     */
    private void fordward(Card card) {
        String suit;
        printText("PULLING A CARD");
        dotsLineBreak();
        printCard(card);
        pauseLineBreak(1);
        suit = String.valueOf(card.getSuit());
        switch (suit) {
            case "GOLD":
                addPosition(1, goldYellow());
                break;
            case "CLUBS":
                addPosition(3, clubGreen());
                break;
            case "CUPS":
                addPosition(5, cupRed());
                break;
            case "SWORDS":
                addPosition(7, swordBlue());
                break;
            default:
                printTextNumber("ERROR: ", 1);
                break;
        }
        printPositions(board, row, column);
        pauseLineBreak(1);
    }

    private void bakckward(Card card) {
        String suit;
        printText("PULLING A CARD");
        dotsLineBreak();
        printCard(card);
        pauseLineBreak(1);
        suit = String.valueOf(card.getSuit());
        switch (suit) {
            case "GOLD":
                subPosition(1, goldYellow());
                break;
            case "CLUBS":
                subPosition(3, clubGreen());
                break;
            case "CUPS":
                subPosition(5, cupRed());
                break;
            case "SWORDS":
                subPosition(7, swordBlue());
                break;
            default:
                printTextNumber("ERROR: ", 2);
                break;
        }
        printPositions(board, row, column);
        pauseLineBreak(1);
    }


    private void addPosition(int road, String color) {
        String simbol = "█";

        if (board[road][76].contains(simbol)) {
            winner = true;
            championSuit = championSuit(road);

            return;
        }
        for (int j = 0; j < board[road].length; j++) {
            if (board[road][j].contains(simbol)) {
                board[road][j + 8] = color + simbol + restore();
                board[road][j] = " ";
                break;
            }
        }
    }

    private void subPosition(int road, String color) {
        String simbol = "█";

        if (board[road][12].contains(simbol)) {
            return;
        }
        for (int j = 0; j < board[road].length; j++) {
            if (board[road][j].contains(simbol)) {
                board[road][j - 8] = color + simbol + restore();
                board[road][j] = " ";
                break;
            }
        }
    }

    private String championSuit(int road) {
        return switch (road) {
            case 1 -> "GOLD";
            case 3 -> "CLUBS";
            case 5 -> "CUPS";
            case 7 -> "SWORDS";
            default -> "ERROR: 3";
        };
    }
}

