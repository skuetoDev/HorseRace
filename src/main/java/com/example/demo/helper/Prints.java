package com.example.demo.helper;

import com.example.demo.model.Card;
import com.example.demo.model.CardSuit;
import com.example.demo.model.players.Player;

import java.util.ArrayList;

import static com.example.demo.logic.GameBoard.*;
import static com.example.demo.helper.Colour.*;
import static com.example.demo.helper.Pause.*;
import static com.example.demo.logic.Croupier.*;
import static com.example.demo.logic.GameLogic.*;

public abstract class Prints {


    /*
     * // method to print only the array horses position to check
     * public static void printHorsesPosition(){
     * for (int i = 0; i < getRow(); i++) {
     * for (int j = 0; j < getColumn(); j++) {
     * System.out.print(getHorsePositions()[i][j]);
     * }
     * System.out.println();
     * }
     * <p>
     * }
     */

    /**
     * Static method to print only the array that represent the race
     */
    public static void printViewBoard() {
        for (int i = 0; i < getRowGameBoard(); i++) {
            for (int j = 0; j < getColumnGameBoard(); j++) {
                System.out.print(getBoard()[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Static method to print object Card
     *
     * @param card to print
     */
    public static void printCard(Card card) {
        System.out.println(card.getDescription());
        dotsLineBreak();
    }

    //especifics methods prints

    /**
     * Static method to print the entry
     */
    public static void printWelcome() {
        System.out.print(croupierVoice() + "WELCOME TO " + restore());
        dotsLineBreak();
    }

    /**
     * Static method to print the entry
     */
    public static void printHorseRace() {
        System.out.print("""                                                                      
                  _    _                                          
                 | |  | |                                 
                 | |__| |  ___   _ __  ___   ___         
                 |  __  | / _ \\ | '__|/ __| / _ \\        
                 | |  | || (_) || |   \\__ \\|  __/      
                 |_|  |_| \\___/ |_|   |___/ \\___|                 
                """);
        halfPause(1);
        System.out.println("""
                \u001B[34m           _____                  \s            ,_|\\_/|_,
                          |  __ \\                 \s          ,((\\\\`   `-\\_
                          | |__) | __ _   ___  ___\s        ,(())      `))\\
                          |  _  / / _` | / __|/ _ \\      ,(()))        ,_ \\
                          | | \\ \\| (_| || (__|  __/      ((())'    |        \\
                          |_|  \\_\\__,_| \\___|\\___|       )))))     >.__      \\
                                                         ((('     /    `-.  .c|
                                                                 /        `-`'\u001B[0m
                         """);
    }

    // question prints

    public static void printQuestionPlayerHuman() {
        System.out.println(croupierVoice() + "HOW MANY PLAYERS ARE HUMAN? (1-4)" + restore());
    }

    public static void printQuestionPlayerName() {
        System.out.println(croupierVoice() + "PLAYER´s NAME " + restore());
    }

    public static void printQuestionPlayerBet() {
        System.out.println(croupierVoice() + "HOW MUCH DO YOU WANT TO  (1-100)" + restore());

    }

    /**
     * Static method to print a table to choose one horse suit
     */
    public static void printHorseSuit() {
        System.out.println("CHOOSE ONE HORSE SUIT " +
                goldYellow() + " \n[1] " + CardSuit.GOLD + restore() +
                clubGreen() + "\n[2] " + CardSuit.CLUBS + restore() +
                cupRed() + "\n[3] " + CardSuit.CUPS + restore() +
                swordBlue() + "\n[4] " + CardSuit.SWORDS + restore()
        );
    }

    /**
     * Static method to print a table with all the players who play game ( humans and bots)
     */
    public static void printPlayers() {
        ArrayList<Player> players = getPlayers();
        System.out.println("\n ╔═══\u001B[33m♞\u001B[0m═════════\u001B[34mTHE PLAYERS\u001B[0m═════════\u001B[33m♞\u001B[0m═══╗");
        pauseLineBreak(1);
        for (int i = 0; i < 4; i++) {
            System.out.println("  Player´s " + (i + 1) + " name : " + players.get(i).getName());
            System.out.println("  " + players.get(i).getName() + "´s bet :" + players.get(i).getBet() + " €");
            System.out.println("  " + players.get(i).getName() + " choose  the horse of " + players.get(i).getHorseSuit());
            pauseLineBreak(1);
        }
        System.out.println("╚═══\u001B[33m♞\u001B[0m════════════════════════════\u001B[33m♞\u001B[0m═══╝");
        pauseLineBreak(1);

    }

    /**
     * Static method to print a croupier voice, saying that the game starts and how much is in the jackpot
     */
    public static void printJackpotMessage() {
        int jackpotSum = getJackpot();
        System.out.print(croupierVoice() + "ACUMULATED " + goldYellow() + "JACKPOT" + croupierVoice() + " IS " + restore());
        dotsPause();
        System.out.println(jackpotSum + " €");
        pauseLineBreak(1);
        System.out.println(croupierVoice() + "THE WINNER WILL WIN " + goldYellow() + "THE JACKPOT " + restore());
        dotsLineBreak();
        System.out.print(croupierVoice() + "GAME  IS ABOUT TO START" + restore());
        dotsLineBreak();
    }

    /**
     * Static method to  print when game is finished
     */
    public static void printFinish(){
        System.out.println(croupierVoice() + "\nFINISH ");
        dotsLineBreak();
    }

    /**
     * Static method to print a croupier voice, saying that the game is finish, and the player who wins
     */
    public static void printWinner() {
        System.out.print("THE WINNER IS  ");
        dotsPause();
        System.out.println(goldYellow() + "♞ " + croupierVoice() + "THE HORSE OF " + restore() + getChampionSuit() + goldYellow() + " ♞");
        pauseLineBreak(1);
        System.out.println(croupierVoice() + "THAT MEANS THAT " + restore());
        dotsPause();
        System.out.print(getPlayerWin());
        System.out.print(croupierVoice() + " WILL TAKE " + goldYellow() + "THE JACKPOT" + croupierVoice() + " AND GET " + restore() + getJackpot() + " €");

    }

    /**
     * Static method to print rounds
     */
    public static void printRoundNumber() {
        System.out.println(croupierVoice() + "ROUND " + numbers() + getRound() + restore());
        dotsLineBreak();


    }

    //Methods to print backwards, fordwards and no movemnts

    public static void printBackward() {
        System.out.println(croupierVoice() + "JUMP BACKWARD" + restore());
        pauseLineBreak(1);

    }

    public static void printFordward() {
        System.out.println(croupierVoice() + "JUMP FORDWARD" + restore());
        pauseLineBreak(1);

    }

    public static void printNoMovement() {
        System.out.println(croupierVoice() + "NO MOVEMENT" + restore());
    }

    /**
     * Static method to print when the croupier when pull a card from deck
     */
    public static void printPullCard() {
        System.out.print(croupierVoice() + "PULLING A CARD" + restore());
        dotsPause();
        printCard(getCard());

    }

    /**
     * Static method to print when de deck is empty and needs to be shuffling.
     */
    public static void printShuffling() {
        System.out.println(croupierVoice() + "SHUFFLING AGAIN" + restore());
        dotsLineBreak();
    }

    // Error prints
    public static void printValidText() {
        System.out.println("Please introduce a valid text");
    }

    public static void printValidNumber() {
        System.out.println("Please introduce a valid Number");
    }


    public static void printSuitChoosen() {
        System.out.println("This suit is already chosen, please choose another.");
    }

    public static void printError() {
        System.out.println("ERROR");
    }


}
