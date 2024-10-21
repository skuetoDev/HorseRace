package src.helper;

import java.util.Scanner;

import static src.helper.Prints.printValidNumber;
import static src.helper.Prints.printValidText;

public class Reads {
    static Scanner input = new Scanner(System.in);

    public static String getText (){
        String text = "";
        boolean exit = false;
        do {
            if(input.hasNextLine()) {
                text = input.nextLine();
                exit = true;
            }else{
                printValidText();
                input.nextLine();
            }
        }while(!exit);
        return text;
    }

    public static int getInt (){
        int number = 0;
        boolean exit = false;
        do {
            if(input.hasNextInt()) {
                number = input.nextInt();
                input.nextLine();
                exit = true;
            }else{
                printValidNumber();
                input.nextLine();
            }
        }while(!exit);
        return number;
    }
}
