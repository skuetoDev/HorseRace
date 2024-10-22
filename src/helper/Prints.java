package helper;

import model.Card;
import model.CardSuit;
import players.Player;

import java.util.ArrayList;
import java.util.Arrays;

import static helper.Colour.*;
import static helper.Pause.*;

public  abstract class Prints {

    public static void printPositions(String [][] board,int row, int column) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    // basic methods prints
    public static void printCard (Card card){
        System.out.println(card.getDescription());
    }

    public static void printTextNumber(String text, int number){
        System.out.println(text+number);
    }
    public static void printTextLineBreak(String text){
        System.out.println(text);
    }
    public static void printText(String text){
        System.out.print(text);
    }

    public static void printE(int number){
        System.out.println(number+" €");
    }
    //especifics methods prints
    public static void printWelcome(){
        System.out.print(croupierVoice()+"WELCOME TO "+restore());
        dotsPause();
    }
    public static void printHorseRace() {
        System.out.println("""                                                                             
                                                                                         
                                                                                         
                                                                                         ,_|\\ _/|_,
                                                                                       ,((\\\\``-\\\\\\\\_
                  _    _                             _____                  \s        ,(())      `))\\       
                 | |  | |                           |  __ \\                 \s      ,(()))       ,_ \\
                 | |__| |  ___   _ __  ___   ___    | |__) | __ _   ___  ___\s     ((())'   |        \\
                 |  __  | / _ \\ | '__|/ __| / _ \\   |  _  / / _` | / __|/ _ \\     )))))     >.__     \\
                 | |  | || (_) || |   \\__ \\|  __/   | | \\ \\| (_| || (__|  __/    ((('     /    `-. .c|
                 |_|  |_| \\___/ |_|   |___/ \\___|   |_|  \\_\\\\__,_| \\___|\\___|            /        `-`'   
                """);
    }

    public static void printValidText(){
        System.out.println("Please introduce a valid text");
    }
    public static void printValidNumber(){
        System.out.println("Please introduce a valid Number");
    }

    public static void printOutOfRange(){
        System.out.println("Please, enter a valid Range");
    }

    public static void printPlayers(ArrayList<Player> players){
        System.out.println("\n ╔═══\u001B[33m♞\u001B[0m═════════\u001B[34mTHE PLAYERS\u001B[0m═════════\u001B[33m♞\u001B[0m═══╗");
        pauseLineBreak(1);
        for ( int i = 0 ; i <4 ; i ++){
            System.out.println("  Player´s " + (i+1) + " name : " + players.get(i).getName());
            System.out.println("  "+players.get(i).getName() + "´s bet :" + players.get(i).getBet() + " €");
            System.out.println("  "+players.get(i).getName() + " choose  the horse of " + players.get(i).getHorseSuit());
            pauseLineBreak(1);
        }
        System.out.println("╚═══\u001B[33m♞\u001B[0m════════════════════════════\u001B[33m♞\u001B[0m═══╝");
        pauseLineBreak(1);

    }

    public static void printHorseSuit(){
        System.out.println( "CHOOSE ONE HORSE SUIT " +
                            goldYellow()+" \n[1] " + CardSuit.GOLD +restore()+
                            clubGreen()+"\n[2] " + CardSuit.CLUBS +restore()+
                            cupRed()+"\n[3] " + CardSuit.CUPS +restore()+
                            swordBlue()+"\n[4] " + CardSuit.SWORDS+restore()
                );
    }
    public static void printJackpotMessage(int jackpotSum) {
        printText(croupierVoice()+"ACUMULATED JACKPOT IS"+restore());
        dotsPause();
        printE(jackpotSum);
        printText(croupierVoice()+"mTHE WINNER WILL WIN \u001B[33mTHE CUP ♞\u001B[34m AND ALL JACKPOT "+restore());
        printE(jackpotSum);
        pauseSelection(1);
        printText(croupierVoice()+"[34m GAME  IS ABOUT TO START"+restore());
        dotsLineBreak();
    }



}
