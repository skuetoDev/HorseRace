package com.example.demo.helper;
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


    public FileLogsAccess(){
        createEmptyJSON();

    }
    private void createEmptyJSON() {
        rootNode =objectMapper.createObjectNode();
        roundsNode = objectMapper.createArrayNode();
        rootNode.set("Game",roundsNode);
    }

    public void addRound (int roundNumber, String card){
        ObjectNode roundNode = objectMapper.createObjectNode();
        roundNode.put("round",roundNumber);
        roundNode.put("Card ",card);
        roundsNode.add(roundNode);

    }


    public  void loadFromJSON() throws IOException{
        File file = new File(filePath);
        if(file.exists()){
            rootNode = (ObjectNode) objectMapper.readTree(file);
            roundsNode = (ArrayNode) rootNode.get("Game");
        }else{
            createEmptyJSON();
        }
    }

    public void  deleteJSON(){
        File file = new File(filePath);

        if(file.exists()) file.delete();
    }

    public void saveToJSON() throws IOException{
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath),rootNode);
    }


}
