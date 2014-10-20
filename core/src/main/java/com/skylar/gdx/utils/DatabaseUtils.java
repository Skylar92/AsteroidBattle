package com.skylar.gdx.utils;

import com.skylar.gdx.db.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Skylar
 */
public class DatabaseUtils {

    private static final DatabaseManagerI DATABASE_MANAGER = new DatabaseManager();
    private static final Comparator<PlayerResultI> PLAYER_RESULT_COMPARATOR = new ComparatorPlayerResult();

    public static void createConnection() throws DatabaseException {
        if (!DATABASE_MANAGER.isConnected())
            DATABASE_MANAGER.createConnection();
    }

    public static void closeConnection() throws DatabaseException {
        if (DATABASE_MANAGER.isConnected())
            DATABASE_MANAGER.closeConnection();
    }

    public static void insertPlayerResult(PlayerResultI playerResult) throws DatabaseException {
        if(playerResult == null)
            throw new IllegalArgumentException("PlayerResult is null!");
        checkConnection();
        DATABASE_MANAGER.insertPlayerResult(playerResult);
    }

    public static List<PlayerResultI> getPlayerResults() throws DatabaseException {
        checkConnection();
        return DATABASE_MANAGER.getPlayerResults();
    }

    public static List<PlayerResultI> getSortedPlayerResults() throws DatabaseException {
        List<PlayerResultI> playerResults = getPlayerResults();
        Collections.sort(playerResults, PLAYER_RESULT_COMPARATOR);
        return playerResults;
    }

    public static List<PlayerResultI> getTopTenPlayerResults() throws DatabaseException {
        List<PlayerResultI> sortedPlayerResults = getSortedPlayerResults();
        List<PlayerResultI> topTenPlayerResults = new LinkedList<PlayerResultI>();
        for(int i = 0; i < 10; i++) {
            if(sortedPlayerResults.size() == 0 || sortedPlayerResults.size() - 1 < i) break;
            PlayerResultI playerResult = sortedPlayerResults.get(i);
            topTenPlayerResults.add(playerResult);
        }
        return topTenPlayerResults;
    }

    private static void checkConnection() {
        if(!DATABASE_MANAGER.isConnected())
            throw new IllegalStateException("Database is not connection!");
    }

    private static final class ComparatorPlayerResult implements Comparator<PlayerResultI> {

        @Override
        public int compare(PlayerResultI o1, PlayerResultI o2) {
            return o1.getScoreCount() - o2.getScoreCount();
        }
    }


}
