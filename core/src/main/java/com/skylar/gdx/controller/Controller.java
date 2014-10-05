package com.skylar.gdx.controller;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * @author Skylar
 */
public interface Controller {

    void initialize();

    void loopGameLogic();

    boolean isGameOver();

    void gameOver(Batch batch);

    void drawBackground(Batch batch);

    void drawStarShip(Batch batch);

    void drawAsteroids(Batch batch);

    void drawBullets(Batch batch);

    void drawGameOver(Batch batch);

}
