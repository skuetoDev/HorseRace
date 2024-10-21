package src.helper;

import src.model.Card;
import src.model.CardSuit;
import src.players.Player;

import java.util.ArrayList;
import java.util.Arrays;

public  abstract class Prints {

    public static void printPositions(int [][] array) {
        StringBuilder x = new StringBuilder();

        for (int[] row : array) {
            x.append(Arrays.toString(row)).append("\n");
        }
        System.out.println(x);
    }

    public static void printCard (Card card){
        System.out.println(card.getDescription());
    }

    public static void printTextNumber(String text, int number){
        System.out.println(text+number);
    }
    public static void printText(String text){
        System.out.println(text);
    }
    public static void printWelcome(){
        System.out.println("""
                  
                  
                  WELCOME TO ......
               
                  _    _                           _____                  \s
                 | |  | |                         |  __ \\                 \s
                 | |__| |  ___   _ __  ___   ___  | |__) | __ _   ___  ___\s
                 |  __  | / _ \\ | '__|/ __| / _ \\ |  _  / / _` | / __|/ _ \\
                 | |  | || (_) || |   \\__ \\|  __/ | | \\ \\| (_| || (__|  __/
                 |_|  |_| \\___/ |_|   |___/ \\___| |_|  \\_\\\\__,_| \\___|\\___|
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
        for ( int i = 0 ; i <4 ; i ++){
            System.out.println("Player´s " + (i+1) + " name :" + players.get(i).getName());
            System.out.println(players.get(i).getName() + "´s bet :" + players.get(i).getBet() + " €");
            System.out.println(players.get(i).getName() + " choose  the horse of " + players.get(i).getHorseSuit() + "\n");
        }

    }

    public static void printHorseSuit(){
        System.out.println( "Choose one Horse suit:" +
                            "\n[1] " + CardSuit.GOLD +
                            "\n[2] " + CardSuit.CLUBS +
                            "\n[3] " + CardSuit.CUPS +
                            "\n[4] " + CardSuit.SWORDS
                );
    }
}
