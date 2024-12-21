package com.example.demo.helper.Database;

import com.example.demo.model.Cards.Card;
import com.example.demo.model.players.Player;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class DatabaseManager {

    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String user = "root";
    private static final String password = "";
    private static int idPartida = 1;
    private static final String useDatabase = "USE horse_race;";
    private static final String databaseName = "horse_race";
    private static Connection connection;


    /**
     * Constructor with pattern singleton
     */
    private DatabaseManager() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, user, password);
        }
        return connection;
    }

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

    public static void createDatabase() {

        try (Statement stmt = getConnection().createStatement()) {

            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + databaseName;
            stmt.executeUpdate(createDatabaseQuery);
            System.out.println("Database " + databaseName + " Created!");

        } catch (SQLException e) {

            System.out.println("Error in connection or creation of database");
            e.printStackTrace();
        }


    }


    public static void createTables() {
        //creacion de tablas
        String configTable = """
                CREATE TABLE IF NOT EXISTS config(
                    id INT PRIMARY KEY,
                    game INT
                );
                """;
        try (Statement stmt = getConnection().createStatement()) {
            stmt.execute(useDatabase);
            stmt.execute(configTable);

            String checkEmpty = "SELECT COUNT(*) AS count FROM config";
            try (ResultSet rs = stmt.executeQuery(checkEmpty)) {
                if (rs.next() && rs.getInt("count") == 0) {
                    String firstINSERT = "INSERT INTO config VALUES (1,1);";
                    stmt.executeUpdate(firstINSERT);
                    idPartida = 1;
                    System.out.println("partida inicial en config");
                } else {
                    idPartida = selectLastGame();
                    String newGame = "INSERT INTO config VALUES (" + idPartida + "," + idPartida + ");";
                    stmt.execute(newGame);
                }
            }

            String playersTable = "players_game" + idPartida;
            String logsTable = "logs_game" + idPartida;
            String createPlayersTable = """
                    CREATE TABLE IF NOT EXISTS %s(
                        id_player INT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(30),
                        bet INT,
                        suit VARCHAR(8),
                        layaoutX_position DECIMAL DEFAULT 131,
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

    private static int selectLastGame() {
        String queryLastConfig = "SELECT game FROM config ORDER BY id DESC LIMIT 1;";
        try (Statement stmt = getConnection().createStatement()) {

            try (ResultSet resultSet = stmt.executeQuery(queryLastConfig)) {
                if (resultSet.next()) {
                    //incrementa la nueva partida
                    idPartida = resultSet.getInt("game") + 1;
                }
            } catch (SQLException e) {
                System.out.println("ERROR " + e.getMessage());
            }
        } catch (SQLException ex) {
            System.out.println("ERROR " + ex.getMessage());
        }
        return idPartida;
    }


    public static void insertTableRoundInfo(Card card, int roundId) {
        String[] cardParts = card.getDescription().split("of", 2);
        String cardNumber = cardParts[0].trim();
        String cardSuit = cardParts[1].trim();

        String insertQuery = " INSERT INTO logs_game" + idPartida + " (id_round, value_card, card_suit) VALUES (?,?,?);";
        try (PreparedStatement prstmt = getConnection().prepareStatement(insertQuery)) {
            prstmt.setInt(1, roundId);
            prstmt.setString(2, cardNumber);
            prstmt.setString(3, cardSuit);

            prstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("ERROR " + ex.getMessage());
        }
    }

    public static void insertTablePlayersInfo(List<Player> players) {

        for (Player p : players) {
            String name = p.getName();
            int bet = p.getBet();
            String suit = p.getHorseSuit();

            String insertQuery = "INSERT INTO players_game" + idPartida + " (name, bet, suit) VALUES (?,?,?);";
            try (PreparedStatement prstmt = getConnection().prepareStatement(insertQuery)) {
                prstmt.setString(1, name);
                prstmt.setInt(2, bet);
                prstmt.setString(3, suit);

                prstmt.executeUpdate();

            } catch (SQLException ex) {
                System.out.println("ERROR " + ex.getMessage());
            }

        }

    }

    public static void updateHorsePositionDatabase(String horseSuit, double newLayaoutX_position) {

        String updateQuery = "UPDATE players_game" + idPartida + " SET layaoutX_position = ? WHERE suit = ?";

        try (PreparedStatement prstmt = getConnection().prepareStatement(updateQuery)) {
            prstmt.setDouble(1, newLayaoutX_position);
            prstmt.setString(2, horseSuit);
            prstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("ERROR " + e.getMessage());
        }


    }

    public static void updateWinnerDatabase(String horseSuit){

        String stringQuery = "UPDATE players_game" + idPartida + " SET isWinner = 1 WHERE suit = ?";
        try(PreparedStatement prstmt = getConnection().prepareStatement(stringQuery)){
            prstmt.setString(1,horseSuit);
            prstmt.executeUpdate();

        }catch (SQLException e){
            System.out.println("ERROR " + e.getMessage() );
        }
    }




}


