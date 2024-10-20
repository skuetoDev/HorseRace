package src.players;
import java.util.Random;

public class Bot extends Player{
    private Random random;

    //Constructor
    public Bot(String name, int balance){
        super.name = name;
        super.bet = balance;
    }

    //Methods
    @Override
    public double getBet() {
        return super.bet;
    }

    @Override
    public double setBet() {
        return bet = 1 +(random.nextInt(50));
    }

}
