package com.example.horse_race.logic;

import static com.example.horse_race.helper.Colour.*;
import static com.example.horse_race.helper.Colour.restore;

import static com.example.horse_race.logic.GameLogic.*;

public class GameBoard {

    //fields

    private static final int row = 9;
    private static final int column = 81;
    private static String[][] board;

    //constructor

    public GameBoard() {
        board = new String[row][column];
        initializeBoard();
        placeHorseOnBoard(horsePositions);
    }

    /**
     * Method to create an array double of Strings (board) with the view of the game
     */
    private void initializeBoard() {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i % 2 == 0) { //Borders
                    if (j == 2) {
                        board[i][j] = "     ";
                    } else if (j % 8 == 0 & j > 0) {
                        board[i][j] = "\u001B[95m*\u001B[0m";
                    } else if (j > 8) {
                        board[i][j] = "\u001B[94m─\u001B[0m";
                    } else {
                        board[i][j] = " ";
                    }
                } else { // suits in start game
                    if (i == 1 && j == 2) {
                        board[i][j] = goldYellow() + "GOLD " + restore();
                    } else if (i == 3 && j == 2) {
                        board[i][j] = clubGreen() + "CLUBS" + restore();
                    } else if (i == 5 && j == 2) {
                        board[i][j] = cupRed() + "CUPS " + restore();
                    } else if (i == 7 && j == 2) {
                        board[i][j] = swordBlue() + "SWORD" + restore();
                        //vertical separator
                    } else if (j % 8 == 0 && j > 0) {
                        board[i][j] = croupierVoice() + "│" + restore();
                    } else {
                        board[i][j] = " ";
                    }

                }
            }
        }
    }

    /**
     * Static method to clean old positions on array board and put new positions
     *
     * @param horsePositions array to update the positions of Board
     */
    protected static void placeHorseOnBoard(int[][] horsePositions) {
        //to clean old positions
        for (int i = 1; i < row; i += 2) {
            for (int j = 4; j < column; j += 8) {
                board[i][j] = " ";
            }
        }
        //put new positions
        for (int i = 0; i < horsePositions.length; i++) {
            for (int j = 0; j < horsePositions[i].length; j++) {
                if (horsePositions[i][j] == 1) {
                    int boardRow = getSuitRow(i);
                    int boardColumn = 4 + j * 8;
                    if (i == 0) {
                        board[boardRow][boardColumn] = goldYellow() + "█" + restore();
                    } else if (i == 1) {
                        board[boardRow][boardColumn] = clubGreen() + "█" + restore();
                    } else if (i == 2) {
                        board[boardRow][boardColumn] = cupRed() + "█" + restore();
                    } else if (i == 3) {
                        board[boardRow][boardColumn] = swordBlue() + "█" + restore();
                    }

                }
            }
        }
    }

    /**
     * Static method to  get the suit of horse in array of view board, from the array of int horsePositions
     *
     * @param horseRow the row of each horse from horsePositions
     * @return de row that belongs in the board
     */
    private static int getSuitRow(int horseRow) {
        return switch (horseRow) {
            case 0 -> 1;
            case 1 -> 3;
            case 2 -> 5;
            case 3 -> 7;
            default -> -1;
        };
    }

    //Getter and Setter

    public static int getRowGameBoard() {
        return row;
    }

    public static String[][] getBoard() {
        return board;
    }

    public static int getColumnGameBoard() {
        return column;
    }
}


