package src.logic;

import src.helper.Prints;
import src.players.*;

import java.util.ArrayList;

import static src.helper.Prints.*;
import static src.helper.Reads.*;


public class Croupier {


    //fields
    private int balance;
    private ArrayList<Player> players;


    //constructor
    public Croupier() {
        players = new ArrayList<>();
        configureUsers();


    }

    //methods

    public void configureUsers() {
        boolean exit = false;
        printWelcome();
        do {
        printText("How many users are Human? (1-4)");
        int users = getInt();

            if (users >= 1 && users <= 4) {
                createHumanPlayers(users);
                createBotPlayers(users);
                printPlayers(players);
                //continuar
                exit = true;

            } else {
                printOutOfRange();
            }
        }while(!exit);
    }



    private void createHumanPlayers(int humanPlayer){
        for (int i = 0; i < humanPlayer; i++) {
            printTextNumber("Name of Player ", (i + 1));
            String namePlayer = getText();
            printText(namePlayer + "´s bet");
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
                    printText("This suit is already chosen, please choose another.");
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
}
