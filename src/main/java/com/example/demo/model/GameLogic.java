package com.example.demo.model;
import com.example.demo.helper.RoundMaxException;
import com.example.demo.model.Cards.CardsDeck;
import com.example.demo.model.players.Bot;
import com.example.demo.model.players.Player;
import java.util.*;

public class GameLogic {

    private static CardsDeck cardsDeck;

    private static final List<Player> players = new ArrayList<>();

    private static final List<String> names = new ArrayList<>();
    private static final List<Integer> bets = new ArrayList<>();
    private static final List<String> suits = new ArrayList<>();


    public static List<Player> getPlayers() {
        return players;
    }


    /**
     * Comprueba si debe lanzarse la excepción RoundMaxException.
     *
     * @param round            El número de ronda actual.
     * @param counterExcepcion Contador de excepciones.
     * @throws RoundMaxException si la ronda es múltiplo de 41 y counterExcepcion es 0.
     */
    public static void checkRound(int round, int counterExcepcion) throws RoundMaxException {
        if ((round % 41 == 0 && round != 0 ) && counterExcepcion == 0) {
            throw new RoundMaxException("Suffling is necessary to continue the game");
        }
    }

    public static void createCardsDeck() {
        cardsDeck = new CardsDeck();

    }

    /**
     * Method to get a cardsDeck
     *
     * @return cardsDeck
     */
    public static CardsDeck getCardsDeck() {
        return cardsDeck;
    }

    /**
     * Clear all players fields
     */
    public static void clearPlayers() {
        players.clear();
        names.clear();
        bets.clear();
        suits.clear();
    }

    /**
     * method to check if one horseSuit is assigned
     *
     * @param horseSuit to check
     * @return true if assigned, false if not assigned
     */
    public static boolean horseSuiteAssigned(String horseSuit) {
        for (Player p : players) {
            if (p.getHorseSuit().equalsIgnoreCase(horseSuit)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to create bot random players with a dynamic numbers
     *
     * @param botPlayer int to create bots
     */
    public static void createBotPlayers(int botPlayer) {

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
     * Method to check if one name of players(bots) are choosen.
     *
     * @param name name to check
     * @return if exists true false if not
     */
    private static boolean nameAlreadyExists(String name) {
        for (Player p : players) {
            if (p.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;

    }















}