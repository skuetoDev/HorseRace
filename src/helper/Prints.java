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
        System.out.println("\u001B[34mWELCOME TO ......\u001B[0m ");
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
        System.out.println("\n ╔═══\u001B[33m♞\u001B[0m═════════\u001B[34mTHE PLAYERS\u001B[0m═════════\u001B[33m♞\u001B[0m═══╗\n");
        for ( int i = 0 ; i <4 ; i ++){
            System.out.println(" Player´s " + (i+1) + " name :" + players.get(i).getName());
            System.out.println(" "+players.get(i).getName() + "´s bet :" + players.get(i).getBet() + " €");
            System.out.println(" "+players.get(i).getName() + " choose  the horse of " + players.get(i).getHorseSuit() + "\n");
        }
        System.out.println("\n╚═══\u001B[33m♞\u001B[0m════════════════════════════\u001B[33m♞\u001B[0m═══╝\n");

    }

    public static void printHorseSuit(){
        System.out.println( "Choose one Horse suit:" +
                            "\u001B[33m \n[1] " + CardSuit.GOLD +"\u001B[0m"+
                            "\u001B[32m \n[2] " + CardSuit.CLUBS +"\u001B[0m"+
                            "\u001B[31m \n[3] " + CardSuit.CUPS +"\u001B[0m"+
                            "\u001B[36m \n[4] " + CardSuit.SWORDS+"\u001B[0m"
                );
    }

}
