package com.example.demo.helper.Database;

import com.example.demo.model.Cards.Card;

import java.io.IOException;
import java.sql.*;

public class DatabaseManager {

    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String user = "root";
    private static final String password = "";
    private static int idPartida = 1;
    private static final String useDatabase = "USE horse_race;";
    private static final String databaseName = "horse_race";
    private static Connection connection;

    private static String logsTableId;


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
                        id_player INT PRIMARY KEY,
                        name VARCHAR(30),
                        bet INT,
                        suit VARCHAR(8),
                        layaoutY_position INT,
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


    public static void insertTableRoundInfo(Card card ,int roundId){
        String[] cardParts = card.getDescription().split("of",2);
        String cardNumber = cardParts[0].trim();
        String cardSuit = cardParts[1].trim();
        System.out.println(cardNumber);
        System.out.println(cardSuit);
        System.out.println(roundId);

        String insertQuery = " INSERT INTO logs_game" + idPartida + " (id_round, value_card, card_suit) VALUES (?,?,?);";
        try(PreparedStatement prstmt = getConnection().prepareStatement(insertQuery)){
            prstmt.setInt(1,roundId);
            prstmt.setString(2, cardNumber);
            prstmt.setString(3,cardSuit);

            prstmt.executeUpdate();


        }catch (SQLException ex){
            System.out.println("ERROR "+ ex.getMessage());
        }
    }


}
