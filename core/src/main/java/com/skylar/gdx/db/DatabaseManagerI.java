package com.skylar.gdx.db;

import java.util.List;

/**
 * Created by Skylar
 */
public interface DatabaseManagerI {

    /**
     * Create connection to Database.
     * @throws DatabaseException
     */
    void createConnection() throws DatabaseException;

    /**
     * Insert into database player result after game
     * @param playerResult - object info about game player
     */
    void insertPlayerResult(PlayerResultI playerResult) throws DatabaseException;

    /**
     * @return list all results player
     */
    List<PlayerResultI> getPlayerResults() throws DatabaseException;

    /**
     * Close database connection
     * @throws DatabaseException
     */
    void closeConnection() throws DatabaseException;

    /**
     * @return is connected to database
     */
    boolean isConnected();
}
