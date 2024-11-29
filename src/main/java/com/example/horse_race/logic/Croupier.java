package com.example.horse_race.logic;

import com.example.horse_race.model.players.*;

import java.util.ArrayList;

import static com.example.horse_race.helper.Pause.*;
import static com.example.horse_race.helper.Prints.*;
import static com.example.horse_race.helper.Reads.*;
import static com.example.horse_race.logic.GameLogic.isWinner;

public class Croupier {

    //fields

    private static ArrayList<Player> players;
    private static int jackpot = 0;
    private static String playerWin = "";


    //constructor

    public Croupier() {
        players = new ArrayList<>();
        configureUsers();

    }

    //methods

    /**
     * method to configure users ( humans and bots)
     */
    private void configureUsers() {
        boolean exit = false;
        printWelcome();
        pauseSelection(2);
        printHorseRace();
        pauseLineBreak(1);
        do {
            printQuestionPlayerHuman();
            int users = getInt(1, 4);
            createHumanPlayers(users);

            createBotPlayers(users);
            printPlayers();
            jackpotSum(players);
            printJackpotMessage();

            // create a game horseRace of GameLogic class
            GameLogic horseRace = new GameLogic();
            if (isWinner()) {
                printWinner();
                exit = true;
            }
        } while (!exit);
    }


    /**
     * Method to create human player and add to array players
     *
     * @param humanPlayer a player human who is added to array players
     */
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

    /**
     * Method to create bot player who not repeated the names of bot and horseSuits chosen and add to array players
     *
     * @param botPlayer who is created and added to array players
     */
    private void createBotPlayers(int botPlayer) {
        for (int i = botPlayer; i < 4; i++) {
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

    /**
     * Method to check if horseSuit was chosen or not
     *
     * @param horseSuit to check whether it has been chosen
     * @return True if is selected
     * False is not selected
     */
    private boolean horseSuiteAssigned(String horseSuit) {
        for (Player p : players) {
            if (p.getHorseSuit().equalsIgnoreCase(horseSuit)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to check if name exist in array players
     *
     * @param name to check whether it has been chosen
     * @return True if is selected
     * False is not selected
     */
    private boolean nameAlreadyExists(String name) {
        for (Player p : players) {
            if (p.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;

    }

    /**
     * Method to add all bets and put in jackpot
     *
     * @param players arrayList to get player´s bet
     */
    private void jackpotSum(ArrayList<Player> players) {
        int jackpotSum = 0;

        for (Player x : players) {
            jackpotSum += x.getBet();
        }
        jackpot = jackpotSum;

    }

    //Getters and Setters

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

