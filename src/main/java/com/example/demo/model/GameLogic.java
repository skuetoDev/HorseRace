package com.example.demo.model;

import com.example.demo.helper.RoundMaxException;
import com.example.demo.model.Cards.CardsDeck;
import com.example.demo.model.players.Bot;

import com.example.demo.model.players.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class GameLogic {

    private static CardsDeck cardsDeck;

    private static List<Player> players = new ArrayList<>();
    private static List<String> names = new ArrayList<>();
    private static List<Integer> bets = new ArrayList<>();
    private static List<String> suits = new ArrayList<>();

    private static final String winersFileString = "winner.json";

    private static int jackpot;

    /**
     * Comprueba si debe lanzarse la excepción RoundMaxException.
     *
     * @param round            El número de ronda actual.
     * @param counterExcepcion Contador de excepciones.
     * @throws RoundMaxException si la ronda es múltiplo de 41 y counterExcepcion es 0.
     */
    public static void checkRound(int round, int counterExcepcion) throws RoundMaxException {
        if (round % 41 == 0 && counterExcepcion == 0) {
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


    public static StringBuilder readWinners() {
        StringBuilder stringWinners = new StringBuilder();
        Gson gson = new Gson();


        try (BufferedReader reader = new BufferedReader(new FileReader(winersFileString))) {
           JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

            if (jsonObject != null) {
                String player = jsonObject.get("player").getAsString();
                String jackpot = jsonObject.get("jackpot").getAsString();
                stringWinners.append(player).append("....").append(jackpot).append(" €\n");

            }

        } catch (IOException e) {
            System.out.println("Error to read file : " + e.getMessage());
        }
        return stringWinners;


    }

    public static String getWinersFileString() {
        return winersFileString;
    }

    public static void writeWinners(String winnerName, int jackpot ) {
        JsonObject winnerData = new JsonObject();
        winnerData.addProperty("player",winnerName);
        winnerData.addProperty("jackpot",jackpot);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(winersFileString))) {
            String json = gson.toJson(winnerData);
            writer.write(json);
            System.out.println("File " + winersFileString + " overwrite");
        } catch (IOException e) {
            System.out.println("Error writing file " + winersFileString);
        }

    }


    public static List<Player> getPlayers() {
        return players;
    }


}