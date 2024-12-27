package com.example.demo.helper.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;

public class FileLogsAccess {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private ObjectNode rootNode;
    private ArrayNode roundsNode;
    private static final String filePath = "./logs.json";

    /**
     * constructor that create empty json structure and file
     */
    public FileLogsAccess() {
        createEmptyLogsJson();

    }

    /**
     * Method to create empty json structure and file
     */
    private void createEmptyLogsJson() {
        rootNode = objectMapper.createObjectNode();
        roundsNode = objectMapper.createArrayNode();
        rootNode.set("Game", roundsNode);
    }

    /**
     * Method to add round and card in json file throw node
     *
     * @param roundNumber to adding in json file
     * @param card        to add in json
     */
    public void addRound(int roundNumber, String card) {
        ObjectNode roundNode = objectMapper.createObjectNode();
        roundNode.put("round", roundNumber);
        roundNode.put("Card ", card);
        roundsNode.add(roundNode);

    }

    /**
     * Method to load all game round from json file in node Game
     *
     * @throws IOException specific of this type of Object
     */
    public void loadLogsFromJSON() throws IOException {
        File file = new File(filePath);
        if (file.exists()) {
            rootNode = (ObjectNode) objectMapper.readTree(file);
            roundsNode = (ArrayNode) rootNode.get("Game");
        } else {
            createEmptyLogsJson();
        }
    }

    /**
     * Method to delete a json file with in a specific path
     */
    public void deleteJSON() {
        File file = new File(filePath);

        if (file.exists()) file.delete();
    }

    /**
     * Method to save a node in a json file in specific path
     *
     * @throws IOException specific Error of this type Object
     */
    public void saveToJSON() throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), rootNode);
    }


}
