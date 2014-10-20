package com.skylar.gdx.db;

import com.badlogic.gdx.Gdx;

import java.sql.*;
import java.util.*;
import java.util.Date;

/**
 * Created by Skylar
 */
public class DatabaseManager implements DatabaseManagerI {

    private static final String TAG_LOG = "Database Manager";

    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String LOCAL_SQL_DB = "jdbc:sqlite:";
    private static final String NAME_DB = "asteroidBattle.db";

    private static final String NAME = "NAME";
    private static final String SCORE = "SCORE";
    private static final String DATE = "DATE";

    private static final String CREATE_TABLE = "create table if not exists achievements \n" +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
            "NAME CHAR(50),\n" +
            "SCORE INT NOT NULL,\n" +
            "DATE DATETIME NOT NULL)";

    private static final String INSERT_INTO_ACHIEVEMENTS = "INSERT INTO achievements (NAME,SCORE,DATE)\n" +
            "VALUES ( \'%s\', %d, \'%s\');";

    private static final String SELECT_ACHIEVEMENTS = "SELECT * FROM achievements";

    private Connection connection;
    private Statement statement;

    private final String PATH_DB;

    public DatabaseManager() {
        this.PATH_DB = LOCAL_SQL_DB + Gdx.files.getLocalStoragePath() + NAME_DB;
    }

    @Override
    public void createConnection() throws DatabaseException {
        try {
            log("Try load driver: " + DRIVER);
            Class.forName(DRIVER);
            log("Try connect to: " + PATH_DB);
            connection = DriverManager.getConnection(PATH_DB);
            statement = connection.createStatement();
            log("Try create table if not exist: \n" + CREATE_TABLE);
            statement.execute(CREATE_TABLE);
        } catch (ClassNotFoundException e) {
            throw new DatabaseException(e);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void insertPlayerResult(PlayerResultI playerResult) throws DatabaseException {
        try {
            String sqlQuery = String.format(INSERT_INTO_ACHIEVEMENTS,
                    playerResult.getName(), playerResult.getScoreCount(), new Timestamp(playerResult.getDate().getTime()));
            log("Execute query: " + sqlQuery);
            statement.execute(sqlQuery);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<PlayerResultI> getPlayerResults() throws DatabaseException {
        log("Execute query: " + SELECT_ACHIEVEMENTS);
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(SELECT_ACHIEVEMENTS);
            return readResultSet(resultSet);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    //we ignore this exception in finally block. This is not important. Maybe i am wrong ;)
                }
            }
        }
    }

    private List<PlayerResultI> readResultSet(ResultSet resultSet) throws SQLException {
        List<PlayerResultI> playerResults = new LinkedList<PlayerResultI>();
        while (resultSet.next()) {
            String name = resultSet.getString(NAME);
            int totalScore = resultSet.getInt(SCORE);
            Timestamp timestamp = resultSet.getTimestamp(DATE);
            playerResults.add(new PlayerResult(name, totalScore, new Date(timestamp.getTime())));
        }
        return playerResults;
    }

    @Override
    public void closeConnection() throws DatabaseException {
        log("Try close database connection");
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public boolean isConnected() {
        return connection != null && statement != null;
    }

    private void log(String message) {
        if(Gdx.app != null) {
            Gdx.app.log(TAG_LOG, message);
        } else {
            System.out.println(message);
        }
    }
}
