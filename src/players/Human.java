package players;

import model.CardSuit;

import static helper.Prints.printError;
import static java.lang.String.valueOf;


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
                printError();
                break;
        }
        return suit;
    }


}
