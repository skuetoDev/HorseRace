package logic;

import players.*;

import java.util.ArrayList;

import static helper.Colour.croupierVoice;
import static helper.Colour.restore;
import static helper.Pause.*;
import static helper.Prints.*;
import static helper.Reads.*;


public class Croupier {


    //fields
    private int balance;
    private ArrayList<Player> players;
    private int jackpot ;


    //constructor
    public Croupier() {
        players = new ArrayList<>();
        configureUsers();


    }

    //methods

    public void configureUsers() {
        boolean exit = false;
        printWelcome();
        pauseSelection(2);
        printHorseRace();
        pauseLineBreak(1);
        do {
        printTextLineBreak("\u001B[34m HOW MANY PLAYERS ARE HUMAN? (1-4)\u001B[0m");
        int users = getInt();

            if (users >= 1 && users <= 4) {
                createHumanPlayers(users);

                createBotPlayers(users);

                printPlayers(players);

                printJackpotMessage(jackpotSum(players));

                GameLogic horseRace = new GameLogic();
                horseRace.horseMovemnt();

                if(horseRace.winner){
                    printTextLineBreak(croupierVoice() + "FINISH");
                    printTextLineBreak("THE \u001B[33mTHE CUP ♞ " + croupierVoice() + "´S CHAMPION IS");
                    dotsLineBreak();
                    printTextLineBreak(croupierVoice() + "THE HORSE OF "+restore() + horseRace.championSuit);
                    printTextLineBreak(croupierVoice()+"THEREFORE THE WINNER OF THE JACKPOT IS"+restore());
                    dotsLineBreak();
                    printText(playerSuit(horseRace.championSuit));
                }
                exit = true;

            } else {
                printOutOfRange();
            }
        }while(!exit);
    }



    private void createHumanPlayers(int humanPlayer){
        for (int i = 0; i < humanPlayer; i++) {
            printTextNumber(croupierVoice()+"NAME OF PLAYER "+restore(), (i + 1));
            String namePlayer = getText();
            printTextLineBreak(croupierVoice()+namePlayer + "´S BET"+restore());
            pauseSelection(1);
            int bet = getInt();
            boolean exit = false;
            while (!exit) {
                printHorseSuit();
                int suit = getInt();
                Human human = new Human(namePlayer, bet, suit);
                if (!horseSuiteAssigned(human.getHorseSuit())) {
                    players.add(human);
                    exit = true;
                }else{
                    printTextLineBreak("This suit is already chosen, please choose another.");
                }
            }

        }

    }
    private void createBotPlayers(int humanPlayer){
        for (int i = humanPlayer; i < 4; i++) {
            boolean exit = false;
            while (!exit) {
                Bot playerBot = new Bot();
                if (!horseSuiteAssigned(playerBot.getHorseSuit()) && !nameAlreadyExists(playerBot.getName())) {
                    players.add(playerBot);
                    exit = true;
                }
            }
        }
    }

    private boolean horseSuiteAssigned(String horseSuit ){
        for (Player p : players) {
            if (p.getHorseSuit().equalsIgnoreCase(horseSuit)) {
                return true;
            }
        }
        return false;
    }

    private boolean nameAlreadyExists(String name) {
        for (Player p : players) {
            if (p.getName().equalsIgnoreCase(name)) {
               return true;
            }
        }
        return false;

    }

    private int jackpotSum (ArrayList<Player> players){
        int jackpotSum = 0;

        for( Player x : players){
            jackpotSum += x.getBet();
        }

        return jackpotSum;
    }
    private String playerSuit(String suit){
        String name= "";
        for (Player p : players) {
            if (p.getHorseSuit().equalsIgnoreCase(suit)) {
                name= p.getName();
            }
        }

        return name;
    }
}
