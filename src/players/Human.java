package src.players;

public class Human extends Player{


    //Constructor
    public Human(String name, int balance){
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
        return super.bet;
    }
}
