package com.skylar.gdx.utils;

import com.skylar.gdx.actors.Asteroid;

/**
 * @author Skylar
 */
public class PlayerAchievements {

    private static int scorePlayer;
    private static long timeGame;
    private static boolean isGameNow = false;

    private PlayerAchievements() {}

    public static void resetPoint() {
        scorePlayer = 0;
    }

    public static void destroyAsteroid(Asteroid asteroid) {
        int max_heal_point = asteroid.getMax_heal_point();
        scorePlayer += max_heal_point * 100;
    }

    public static void startGame() {
        isGameNow = true;
        timeGame = System.currentTimeMillis();
    }

    public static void endGame() {
        if(isGameNow) {
            timeGame = System.currentTimeMillis() - timeGame;
            isGameNow = false;
        }
    }

    public static int getScorePlayer() {
        return scorePlayer;
    }

    public static long getTimeGame() {
        return timeGame / 1000;
    }
}
