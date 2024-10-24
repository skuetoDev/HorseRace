package model.players;
import model.CardSuit;
import java.util.Random;

public class Bot extends Player {

    //fields

    private Random random ;
    private BotName[] names = {BotName.DAENERYS, BotName.ARIA, BotName.JOHN};
    private CardSuit[] horseSuit = {CardSuit.CLUBS, CardSuit.CUPS,CardSuit.GOLD,CardSuit.SWORDS};

    //constructor

    public Bot (){
        random = new Random();
        super.name = botRandomName();
        super.bet =  1 +(random.nextInt(100));
        super.horseSuit = botRandomHorseSuit();
    }

    //Methods

    /**
     * Method to choose randomly a horseSuit who are in enum CardSuit
     * @return horseSuit not choose
     */
    private String botRandomHorseSuit(){
        int index = random.nextInt(horseSuit.length);
        return horseSuit[index].name();

    }

    /**
     * Method to choose randomly a name who are in enum BotName
     * @return a name who are in BotName
     */
    private String botRandomName(){
        int index = random.nextInt(names.length);
        return names[index].name();
    }

    // Getter and Setter

    public String getHorseSuit() {
        return super.horseSuit;
    }

    @Override
    public int getBet() {
        return super.bet;
    }

    @Override
    public String getName() {
        return super.name;
    }
}
