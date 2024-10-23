package logic;

import players.*;

import java.util.ArrayList;

import static helper.Colour.*;
import static helper.Pause.*;
import static helper.Prints.*;
import static helper.Reads.*;
import static logic.GameLogic.isWinner;


public class Croupier {


    //fields

    private static ArrayList<Player> players;
    private static int jackpot = 0;
    private static int users = 0;
    private static String playerWin = "";


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
            printQuestionPlayerHuman();

            users = getInt(1, 4);

            createHumanPlayers(users);
            createBotPlayers(users);
            printPlayers();
            jackpotSum(players);
            printJackpotMessage();

            GameLogic horseRace = new GameLogic();


            if (isWinner()) {
                printWinner();
                exit = true;
            }
        } while (!exit);
    }


    private void createHumanPlayers(int humanPlayer) {

        for (int i = 0; i < humanPlayer; i++) {
            printQuestionPlayerName();
            String namePlayer = getText();
            printQuestionPlayerBet();
            pauseSelection(1);
            int bet = getInt(1, 100);
            boolean exit = false;
            while (!exit) {
                printHorseSuit();
                int suit = getInt(1, 4);
                Human human = new Human(namePlayer, bet, suit);
                if (!horseSuiteAssigned(human.getHorseSuit())) {
                    players.add(human);
                    exit = true;
                } else {
                    printSuitChoosen();
                }
            }

        }

    }

    private void createBotPlayers(int humanPlayer) {
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

    private boolean horseSuiteAssigned(String horseSuit) {
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

    private void jackpotSum(ArrayList<Player> players) {
        int jackpotSum = 0;

        for (Player x : players) {
            jackpotSum += x.getBet();
        }
        jackpot = jackpotSum;

    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static int getJackpot() {
        return jackpot;
    }

    public static void setPlayerWin(String playerWin) {
        Croupier.playerWin = playerWin;
    }

    public static String getPlayerWin() {
        return playerWin;
    }

}

