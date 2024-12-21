package com.example.demo.helper.File;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWinnersAcess {
    private static final String winersFilePath = "./winners.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static ObjectNode rootNode ;
    private ArrayNode playersNode;


    public FileWinnersAcess(){
        createEmptyWinnersJson();

    }

    private void createEmptyWinnersJson(){
        rootNode = objectMapper.createObjectNode();
        playersNode = objectMapper.createArrayNode();
        rootNode.set("Winners",playersNode);

    }



    public void loadWinnersFromJson() {
        try {
            File file = new File(winersFilePath);

            if (file.exists()) {
                rootNode = (ObjectNode) objectMapper.readTree(file);
                playersNode = (ArrayNode) rootNode.get("Winners");
            } else {


            }
        }catch (IOException e){
            System.out.println("ERROR " + e.getMessage());
        }

    }
    public List<String[]> getWinnersList(){
        List<String[]> winners = new ArrayList<>();
        if( playersNode.isEmpty() || playersNode == null){
            return winners;

        }else{
            for( int i = 0 ; i<playersNode.size() ; i++){
                ObjectNode winnerNode = (ObjectNode) playersNode.get(i);
                String name = winnerNode.get("Winner").asText();
                String bet = winnerNode.get("jackpot").asText();
                winners.add(new String[]{name,bet});

            }
            return winners;
        }

    }

    public void saveWinnersToJson() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(winersFilePath),rootNode);
        }catch (IOException e){
            System.out.println("ERROR " + e.getMessage());
        }

    }

    public void addWinner (String name, int Bet){
        ObjectNode winnerNode = objectMapper.createObjectNode();
        winnerNode.put("Winner",name);
        winnerNode.put("jackpot",Bet );
        playersNode.add(winnerNode);
    }

    public static String getWinersFilePath() {
        return winersFilePath;
    }
}
