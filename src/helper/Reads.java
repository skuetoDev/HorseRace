package helper;

import java.util.Scanner;

import static helper.Prints.printValidNumber;
import static helper.Prints.printValidText;

public class Reads {
    static Scanner input = new Scanner(System.in);

    /**
     * Static method to get one String until is not empty
     *
     * @return String text
     */
    public static String getText() {
        String text = "";
        boolean exit = false;
        do {
            if (input.hasNextLine()) {
                text = input.nextLine();
                exit = true;
            } else {
                printValidText();
                input.nextLine();
            }
        } while (!exit);
        return text;
    }

    /**
     * Static method to get one number between one range
     *
     * @param min int number range accepted
     * @param max int number range accepted
     * @return int between min and max numbers
     */
    public static int getInt(int min, int max) {
        int number = 0;
        boolean exit = false;
        do {
            if (input.hasNextInt()) {
                number = input.nextInt();
                if (number >= min && number <= max) {
                    input.nextLine();
                    exit = true;
                } else {
                    printValidNumber();
                }

            } else {
                printValidNumber();
                input.nextLine();
            }
        } while (!exit);
        return number;
    }
}
