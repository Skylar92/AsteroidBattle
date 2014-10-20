package com.skylar.gdx.db;

import java.util.Date;

/**
 * Created by Skylar
 */
public interface PlayerResultI {

    /**
     * @return player name
     */
    String getName();

    /**
     * @return total score count
     */
    int getScoreCount();

    /**
     * @return date of the saved game
     */
    Date getDate();


}
