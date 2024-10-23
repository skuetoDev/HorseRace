package helper;


import model.Card;
import model.CardSuit;
import players.Player;

import java.util.ArrayList;
import java.util.Arrays;

import static helper.Colour.*;
import static helper.Pause.*;
import static logic.Croupier.*;
import static logic.GameLogic.*;

public abstract class Prints {

    public static void printPositions(String[][] board, int row, int column) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    // basic methods prints
    public static void printCard(Card card) {
        System.out.println(card.getDescription());
        dotsLineBreak();
    }


    public static void printTextLineBreak(String text) {
        System.out.println(text);
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
    //especifics methods prints

    public static void printWelcome() {
        System.out.print(croupierVoice() + "WELCOME TO " + restore());
        dotsLineBreak();
    }

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

    public static void printHorseSuit() {
        System.out.println("CHOOSE ONE HORSE SUIT " +
                goldYellow() + " \n[1] " + CardSuit.GOLD + restore() +
                clubGreen() + "\n[2] " + CardSuit.CLUBS + restore() +
                cupRed() + "\n[3] " + CardSuit.CUPS + restore() +
                swordBlue() + "\n[4] " + CardSuit.SWORDS + restore()
        );
    }

    public static void printJackpotMessage() {
        int jackpotSum = getJackpot();
        System.out.print(croupierVoice() + "ACUMULATED "+goldYellow()+"JACKPOT"+croupierVoice() + "IS" + restore());
        dotsPause();
        System.out.println(jackpotSum+" €");
        System.out.println(croupierVoice() + "THE WINNER WILL WIN "+goldYellow()+"THE CUP ♞"+restore() );
        pauseSelection(1);
        System.out.print(croupierVoice()+"AND "+goldYellow()+"THE JACKPOT " + restore());
        pauseLineBreak(1);
        System.out.print(croupierVoice() + "GAME  IS ABOUT TO START" + restore());
        dotsLineBreak();
    }

    public static void printWinner() {
        System.out.println(croupierVoice() + "\nFINISH ");
        dotsLineBreak();
        System.out.println("THE WINNER OF THE "+goldYellow()+"THE CUP ♞ " + croupierVoice() + " IS");
        dotsLineBreak();
        System.out.println(croupierVoice() + "THE HORSE OF " + restore() + getChampionSuit());
        pauseSelection(1);
        System.out.print(croupierVoice() + "THAT MEANS THAT THE WINNER OF "+goldYellow()+"THE JACKPOT "+croupierVoice()+"IS" + restore());
        dotsPause();
        System.out.print(getPlayerWin());
        System.out.println(croupierVoice()+" AND THE GAIN IS : "+ restore()+getJackpot()+" €");
    }

    public static void printRoundNumber() {
        System.out.print(croupierVoice() + "ROUND " + numbers() + getRound()+restore());
        dotsLineBreak();


    }

    public static void printBackward() {
        System.out.println(croupierVoice()+"JUMP BACKWARD" + restore());
        pauseLineBreak(1);

    }
    public static void printFordward() {
        System.out.println(croupierVoice()+"JUMP FORDWARD" + restore());
        pauseLineBreak(1);

    }

    public static void printNoMovement(){
        System.out.println(croupierVoice()+"NO MOVEMENT"+restore());
    }

    public static void printPullCard() {
        System.out.print(croupierVoice() + "PULLING A CARD" + restore());
        dotsPause();
        printCard(getCard());

    }

    // Error prints
    public static void printValidText() {
        System.out.println("Please introduce a valid text");
    }

    public static void printValidNumber() {
        System.out.println("Please introduce a valid Number");
    }

    public static void printOutOfRange() {
        System.out.println("Please, enter a valid Range");
    }

    public static void printSuitChoosen() {
        System.out.println("This suit is already chosen, please choose another.");
    }

    public static void printError() {
        System.out.println("ERROR");
    }


}
