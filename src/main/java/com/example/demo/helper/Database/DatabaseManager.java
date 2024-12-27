package com.example.demo.helper.Database;

import com.example.demo.model.Cards.Card;
import com.example.demo.model.players.Player;

import java.sql.*;
import java.util.*;


public class DatabaseManager {

    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String user = "root";
    private static final String password = "";
    private static int idGame = 1;
    private static final String useDatabase = "USE horse_race;";
    private static final String databaseName = "horse_race";
    private static Connection connection;


    /**
     * Method to change idGame to a new IdGame
     *
     * @param newIdGame new id to change old id
     */
    public static void setIdPartida(int newIdGame) {
        DatabaseManager.idGame = newIdGame;
    }

    /**
     * Constructor with pattern singleton
     */
    private DatabaseManager() {
    }

    /**
     * Method to get one connection with specific data (url, user and password).
     *
     * @return a Object Connection with a connection done
     * @throws SQLException error typical of this Object type
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

    /**
     * Method to close  Object Connection
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connection closed");
            } catch (SQLException ex) {
                System.out.println("ERROR closing connection" + ex.getMessage());
            }
        }
    }

    /**
     * Method to create Database  with a specific name
     */
    public static void createDatabase() {

        try (Statement stmt = getConnection().createStatement()) {

            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + databaseName;
            stmt.executeUpdate(createDatabaseQuery);
            System.out.println("Database " + databaseName + " Created!");

        } catch (SQLException e) {

            System.out.println("Error in connection or creation of database" + e.getMessage());
        }
    }

    /**
     * Method to create tables one, for games, other for specific game logs and another for specific gameplayers
     */
    public static void createTables() {
        //creacion de tablas
        String configTable = """
                CREATE TABLE IF NOT EXISTS config(
                    id INT PRIMARY KEY,
                    game INT,
                    winner BOOLEAN DEFAULT false
                );
                """;
        try (Statement stmt = getConnection().createStatement()) {
            stmt.execute(useDatabase);
            stmt.execute(configTable);

            String checkEmpty = "SELECT COUNT(*) AS count FROM config";
            try (ResultSet rs = stmt.executeQuery(checkEmpty)) {
                if (rs.next() && rs.getInt("count") == 0) {
                    String firstINSERT = "INSERT INTO config VALUES (1,1,0);";
                    stmt.executeUpdate(firstINSERT);
                    idGame = 1;
                    System.out.println("partida inicial en config");
                } else {
                    idGame = selectLastGame();
                    String newGame = "INSERT INTO config (id, game) VALUES (" + idGame + "," + idGame + ");";
                    stmt.execute(newGame);
                }
            }

            String playersTable = "players_game" + idGame;
            String logsTable = "logs_game" + idGame;
            String createPlayersTable = """
                    CREATE TABLE IF NOT EXISTS %s(
                        id_player INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(30),
                        bet INT,
                        suit VARCHAR(8),
                        layoutX_position DECIMAL DEFAULT 31,
                        isWinner BOOLEAN DEFAULT false
                    );
                    """.formatted(playersTable);

            String createLogsTable = """
                    CREATE TABLE IF NOT EXISTS %s(
                        id_round    INT PRIMARY KEY,
                        value_card  VARCHAR(8),
                        card_suit   VARCHAR(8)
                    );
                    """.formatted(logsTable);
            stmt.execute(createPlayersTable);
            stmt.execute(createLogsTable);
            System.out.println("TABLE " + playersTable + " created");

        } catch (SQLException e) {
            System.out.println("ERROR in tables creation " + e.getMessage());
        }

    }

    /**
     * Method to select the las game for games table (config)
     *
     * @return the id of last game
     */
    private static int selectLastGame() {
        String queryLastConfig = "SELECT game FROM config ORDER BY id DESC LIMIT 1;";
        try (Statement stmt = getConnection().createStatement()) {

            try (ResultSet resultSet = stmt.executeQuery(queryLastConfig)) {
                if (resultSet.next()) {
                    //incrementa la nueva partida
                    idGame = resultSet.getInt("game") + 1;
                }
            }
        } catch (SQLException ex) {
            System.out.println("ERROR " + ex.getMessage());
        }
        return idGame;

    }


    /**
     * method to insert a card and round in table of logs
     *
     * @param card    Object Card to insert in table
     * @param roundId current round to insert in table
     */
    public static void insertTableRoundInfo(Card card, int roundId) {
        String[] cardParts = card.getDescription().split("of", 2);
        String cardNumber = cardParts[0].trim();
        String cardSuit = cardParts[1].trim();

        String insertQuery = " INSERT INTO logs_game" + idGame + " (id_round, value_card, card_suit) VALUES (?,?,?);";
        try (PreparedStatement prstmt = getConnection().prepareStatement(insertQuery)) {
            prstmt.setInt(1, roundId);
            prstmt.setString(2, cardNumber);
            prstmt.setString(3, cardSuit);

            prstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR  insertTableRoundInfo" + ex.getMessage());
        }
    }

    /**
     * Method to insert an arrayList in players table
     *
     * @param players arraylist who contain the game players
     */
    public static void insertTablePlayersInfo(List<Player> players) {

        for (Player p : players) {
            String name = p.getName();
            int bet = p.getBet();
            String suit = p.getHorseSuit();

            String insertQuery = "INSERT INTO players_game" + idGame + " (name, bet, suit) VALUES (?,?,?);";
            try (PreparedStatement prstmt = getConnection().prepareStatement(insertQuery)) {
                prstmt.setString(1, name);
                prstmt.setInt(2, bet);
                prstmt.setString(3, suit);

                prstmt.executeUpdate();

            } catch (SQLException ex) {
                System.out.println("ERROR insertTablePlayersInfo" + ex.getMessage());
            }

        }

    }

    /**
     * Method to change the number of horse position in the game and update in database
     *
     * @param horseSuit           suit of horse to update the database
     * @param newLayoutX_position new position of horse in game
     */
    public static void updateHorsePositionDatabase(String horseSuit, double newLayoutX_position) {

        String updateQuery = "UPDATE players_game" + idGame + " SET layoutX_position = ? WHERE suit = ?";

        try (PreparedStatement prstmt = getConnection().prepareStatement(updateQuery)) {
            prstmt.setDouble(1, newLayoutX_position);
            prstmt.setString(2, horseSuit);
            prstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR in updateHorsePositionDatabase" + e.getMessage());
        }


    }

    /**
     * Method to change the winner( 0 -> 1) in database  throw the suit of horse
     *
     * @param horseSuit of the winning horse
     */
    public static void updateWinnerDatabase(String horseSuit) {

        String stringQueryPlayer = "UPDATE players_game" + idGame + " SET isWinner = 1 WHERE suit = ?";
        String stringQueryGame = "UPDATE config SET winner = 1 WHERE id = ?";
        try (PreparedStatement prstmt = getConnection().prepareStatement(stringQueryPlayer)) {
            prstmt.setString(1, horseSuit);
            prstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR " + e.getMessage());
        }
        try (PreparedStatement prstmt = getConnection().prepareStatement(stringQueryGame)) {
            prstmt.setInt(1, idGame);
            prstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR updateWinnerDatabase" + ex.getMessage());
        }


    }

    /**
     * Method to get all games in database who haven't winner
     *
     * @return arraylist that contains all games with no winner
     */
    public static List<String> getUnfinishedGames() {
        List<String> games = new ArrayList<>();
        try (Statement stmt = getConnection().createStatement()) {
            stmt.execute(useDatabase);
            String query = """
                    SELECT table_name
                    FROM information_schema.tables
                    WHERE table_name LIKE 'players_game%'
                    AND table_schema = 'horse_race'
                    AND EXISTS(
                        SELECT 1
                        FROM config
                        WHERE config.game = CAST(SUBSTRING(table_name, 13) AS INT)
                        AND Config.winner = false
                    );""";
            try (PreparedStatement prstmt = getConnection().prepareStatement(query);

                 ResultSet resultSet = prstmt.executeQuery()) {


                while (resultSet.next()) {
                    String tableName = resultSet.getString("table_name");
                    games.add(tableName);
                }
            } catch (Exception e) {
                System.out.println("ERROR in getUnfinishedGames()2 " + e.getMessage());
            }

        } catch (SQLException ignored) {


        }
        return games;

    }

    /**
     * Method to select players and their information, in a specific table of database; and save in arraylist of map who is formed by one string(name of filed
     * and one Object that is the info in file
     *
     * @param tableName of the database to extract information
     * @return arrays list of Map Object formed by one String(name of field) and Object that is the info in file
     */
    public static List<Map<String, Object>> selectPlayersFromTable(String tableName) {
        List<Map<String, Object>> players = new ArrayList<>();

        String query = "SELECT name, bet, suit,layoutX_position FROM " + tableName + ";";
        try (PreparedStatement prstmt = getConnection().prepareStatement(query);
             ResultSet rs = prstmt.executeQuery()) {
            while (rs.next()) {
                Map<String, Object> playerData = new HashMap<>();
                playerData.put("name", rs.getString("name"));
                playerData.put("bet", rs.getInt("bet"));
                playerData.put("suit", rs.getString("suit"));
                playerData.put("layoutX_position", rs.getString("layoutX_position"));

                players.add(playerData);

            }
        } catch (SQLException e) {
            System.out.println("ERROR in selectPlayersFromTable() " + e.getMessage());
        }
        return players;

    }

    /**
     * Method to found the last round in table of logs , where are cards of each round
     * @param gameNumber number of the game
     * @return the last round save from table of logs
     */
    public static int lastRound( int gameNumber){
        String tableName = "logs_game" + gameNumber;
        String query = "SELECT id_round FROM "+ tableName + " ORDER BY id_round DESC LIMIT 1";
        int lastId = -1; // if table is empty
        try(PreparedStatement prstm = DatabaseManager.getConnection().prepareStatement(query);
            ResultSet rs = prstm.executeQuery()){
            if(rs.next()) lastId = rs.getInt("id_round");


        }catch (SQLException e ){
            System.out.println("ERROR lastRound() " + e.getMessage());
        }
        return lastId;

    }



}


