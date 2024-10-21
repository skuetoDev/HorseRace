package src.players;

import src.model.CardSuit;

import static java.lang.String.valueOf;
import static src.helper.Prints.printText;

public class Human extends Player {


    //Constructor
    public Human(String name, int bet, int horseSuit) {
        super.name = name;
        super.bet = bet;
        super.horseSuit = chooseSuit(horseSuit);
    }

    //Methods

    public int getBet() {
        return super.bet;
    }

    @Override
    public String getName() {
        return super.name;
    }

    public String getHorseSuit() {
        return super.horseSuit;
    }

    public String chooseSuit(int number) {
        String suit = "";

        switch (number) {
            case 1:
                suit = valueOf(CardSuit.GOLD);
                break;
            case 2:
                suit = valueOf(CardSuit.CLUBS);
                break;
            case 3:
                suit = valueOf(CardSuit.CUPS);
                break;
            case 4:
                suit = valueOf(CardSuit.SWORDS);
                break;
            default:
                printText("Error: 3");
                break;
        }
        return suit;
    }


}
