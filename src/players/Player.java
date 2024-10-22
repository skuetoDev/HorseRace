package players;

import model.CardSuit;

public abstract class Player  {

    //fields
    protected int bet;

    protected String name;
    protected String horseSuit;




    //methods
    public abstract int getBet();

    public abstract String getName();

    public abstract String getHorseSuit();
}
