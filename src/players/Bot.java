package players;
import model.CardSuit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot extends Player {
    private Random random ;
    private BotName[] names = {BotName.DAENERYS, BotName.ARIA, BotName.JOHN};
    private CardSuit[] horseSuit = {CardSuit.CLUBS, CardSuit.CUPS,CardSuit.GOLD,CardSuit.SWORDS};





    //constructor
    public Bot (){
        random = new Random();
        super.name = botRandomName();
        super.bet =  1 +(random.nextInt(50));
        super.horseSuit = botRandomeHorseSuit();
    }
    //Methods
    @Override
    public int getBet() {
        return super.bet;
    }

    @Override
    public String getName() {
        return super.name;
    }

    private String botRandomName(){
        int index = random.nextInt(names.length);
        return names[index].name();
    }

    private String botRandomeHorseSuit(){
        int index = random.nextInt(horseSuit.length);
        return horseSuit[index].name();

    }

    public String getHorseSuit() {
        return super.horseSuit;
    }



}
