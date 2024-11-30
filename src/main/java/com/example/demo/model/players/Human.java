package com.example.demo.model.players;
import com.example.demo.helper.AlertUtil;
import com.example.demo.model.CardSuit;
import static com.example.demo.helper.Prints.printError;
import static java.lang.String.valueOf;


public class Human extends Player {

    //Constructor

    public Human(String name, int bet, String horseSuit) {
        super.name = name;
        super.bet = bet;
        super.horseSuit = chooseSuit(horseSuit,name);
    }

    //Methods

    /**
     * Method to choose one suit from enum CardSuit,
     *
     * @param suit who represent a suit in a printed list
     * @return String suit with the suit are choose
     */
    private String chooseSuit(String suit, String name) {
        String choosenSuit = "";
        suit = suit.toUpperCase();


        switch (suit) {
            case "GOLD":
                choosenSuit = String.valueOf(CardSuit.GOLD);
                break;
            case "CLUBS":
                choosenSuit = String.valueOf(CardSuit.CLUBS);
                break;
            case "CUPS":
                choosenSuit = String.valueOf(CardSuit.CUPS);
                break;
            case "SWORDS":
                choosenSuit = String.valueOf(CardSuit.SWORDS);
                break;
            default:
                AlertUtil.showError("ERROR SUIT",suit +
                        " written by " + name + " is not a suit of Cards, Please change it");
                break;
        }
        return choosenSuit;
    }


    //Setter and Getter

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


}
