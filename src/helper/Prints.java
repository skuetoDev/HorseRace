package src.helper;

import src.model.Card;

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
}
