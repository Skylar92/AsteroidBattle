package com.skylar.gdx.db;

import java.util.Date;

/**
 * Created by Skylar
 */
public class PlayerResult implements PlayerResultI {

    private final String name;
    private final int scoreCount;
    private final Date date;

    public PlayerResult(String name, int scoreCount, Date date) {
        this.name = name;
        this.scoreCount = scoreCount;
        this.date = date;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getScoreCount() {
        return scoreCount;
    }

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "PlayerResult{" +
                "name='" + name + '\'' +
                ", scoreCount=" + scoreCount +
                ", date=" + date +
                '}';
    }
}
