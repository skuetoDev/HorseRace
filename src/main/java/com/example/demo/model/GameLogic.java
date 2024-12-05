package com.example.demo.model;
import com.example.demo.helper.RoundMaxException;
import com.example.demo.model.Cards.CardsDeck;
import com.example.demo.model.players.Bot;

import com.example.demo.model.players.Player;


import java.io.*;
import java.util.*;

public class GameLogic {

    private static CardsDeck cardsDeck;

    private static List<Player> players = new ArrayList<>();
    private static List<String> names = new ArrayList<>();
    private static List<Integer> bets = new ArrayList<>();
    private static List<String> suits = new ArrayList<>();

    private static  String winersFileString = "winners.txt";

    private static int jackpot ;

    /**
     * Comprueba si debe lanzarse la excepción RoundMaxException.
     *
     * @param round El número de ronda actual.
     * @param counterExcepcion Contador de excepciones.
     * @throws RoundMaxException si la ronda es múltiplo de 41 y counterExcepcion es 0.
     */
    public static void checkRound(int round, int counterExcepcion) throws RoundMaxException {
        if (round % 41 == 0 && counterExcepcion == 0) {
            throw new RoundMaxException("Suffling is necessary to continue the game");
        }
    }

    public static void createCardsDeck(){
        cardsDeck = new CardsDeck();

    }



    public static void createFileLogs(LinkedHashMap<String, String> logs) {


        String fileName = "logs.txt";

        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : logs.entrySet()) {
                writer.write(entry.getKey() + entry.getValue());
                writer.newLine();
            }
            System.out.println("Archivo " + fileName + " creado correctamente");
        } catch (
                IOException e) {
            System.out.println("Error al escribir el archivo " + fileName);

        }
    }

    public static CardsDeck getCardsDeck() {
        return cardsDeck;
    }

    public static void clearPlayers() {
        players.clear();
        names.clear();
        bets.clear();
        suits.clear();
    }

    public static boolean horseSuiteAssigned(String horseSuit) {
        for (Player p : players) {
            if (p.getHorseSuit().equalsIgnoreCase(horseSuit)) {
                return true;
            }
        }
        return false;
    }

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

    private static boolean nameAlreadyExists(String name) {
        for (Player p : players) {
            if (p.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;

    }

    public static void readWinners(LinkedList<String> winners){


        try(BufferedReader reader = new BufferedReader(new FileReader(winersFileString))){
            String line;
            while((line = reader.readLine()) != null){
                winners.add(line);
            }
        }catch (IOException e ){
            System.out.println("error to read file : " + e.getMessage());
        }


    }

    public static String getWinersFileString() {
        return winersFileString;
    }

    public static void writeWinners(LinkedList<String> winners){


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(winersFileString))) {
            for (String winner : winners) {
                writer.write(winner+"\n");

            }
            System.out.println("File " + winersFileString + " overwrite  ");
        } catch (IOException e) {
            System.out.println("Error writing file " + winersFileString);

        }


    }

    public static List<Player> getPlayers() {
        return players;
    }

    public static void setJackpot(int jackpot) {
        GameLogic.jackpot = jackpot;
    }

    public static int getJackpot() {
        return jackpot;
    }
}