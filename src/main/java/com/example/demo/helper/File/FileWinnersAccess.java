package com.example.demo.helper.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWinnersAccess {
    private static final String winnersFilePath = "./winners.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static ObjectNode rootNode;
    private ArrayNode playersNode;

    /**
     * Constructor that creates empty file json
     */
    public FileWinnersAccess() {
        createEmptyWinnersJson();

    }

    /**
     * Method to create a first node in json
     */
    private void createEmptyWinnersJson() {
        rootNode = objectMapper.createObjectNode();
        playersNode = objectMapper.createArrayNode();
        rootNode.set("Winners", playersNode);

    }

    /**
     * Method to update a node in json in a specific file: winners.json
     */
    public void loadWinnersFromJson() {
        try {
            File file = new File(winnersFilePath);

            if (file.exists()) {
                rootNode = (ObjectNode) objectMapper.readTree(file);
                playersNode = (ArrayNode) rootNode.get("Winners");
            } else {


            }
        } catch (IOException e) {
            System.out.println("ERROR " + e.getMessage());
        }

    }

    /**
     * Method to get an arrayList with all winners that are written in winners.json
     *
     * @return arrayList of strings array with all winners
     */
    public List<String[]> getWinnersList() {
        List<String[]> winners = new ArrayList<>();
        if (playersNode.isEmpty() || playersNode == null) {
            return winners;

        } else {
            for (int i = 0; i < playersNode.size(); i++) {
                ObjectNode winnerNode = (ObjectNode) playersNode.get(i);
                String name = winnerNode.get("Winner").asText();
                String bet = winnerNode.get("jackpot").asText();
                winners.add(new String[]{name, bet});

            }
            return winners;
        }

    }

    /**
     * Method to write in specific json file (winners.json)
     */
    public void saveWinnersToJson() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(winnersFilePath), rootNode);
        } catch (IOException e) {
            System.out.println("ERROR " + e.getMessage());
        }

    }

    /**
     * Method to add a winner in node of json file
     *
     * @param name       of winner to add
     * @param jackpotWon in a game by winner to add
     */
    public void addWinner(String name, int jackpotWon) {
        ObjectNode winnerNode = objectMapper.createObjectNode();
        winnerNode.put("Winner", name);
        winnerNode.put("Jackpot", jackpotWon);
        playersNode.add(winnerNode);
    }

}
