package com.skylar.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.skylar.gdx.controller.Controller;
import com.skylar.gdx.controller.ControllerGame;
import com.skylar.gdx.utils.HelpTable;
import com.skylar.gdx.utils.PlayerAchievements;
import com.skylar.gdx.utils.SoundUtils;

public class AsteroidBattle extends Game {

    private static final AsteroidBattle GDX_GAME = new AsteroidBattle();

    private Controller controller;
    private SpriteBatch batch;

    private AsteroidBattle() {}

    public static AsteroidBattle getInstance() {
        return GDX_GAME;
    }

    @Override
    public void create() {
        controller = new ControllerGame();
        controller.initialize();
        batch = new SpriteBatch();
        SoundUtils.playBackendSound();
        PlayerAchievements.startGame();
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        controller.loopGameLogic();
        if (!controller.isGameOver()) {
            drawGame();
        } else {
            drawGameOver();
        }
    }

    private void drawGame() {
        batch.begin();
        controller.drawBackground(batch);
        controller.drawBullets(batch);
        controller.drawStarShip(batch);
        controller.drawAsteroids(batch);
        HelpTable.getInstance().printHelpToShoot(batch);
        HelpTable.getInstance().printScore(batch);
        batch.end();
    }

    private void drawGameOver() {
        SoundUtils.stopBackendSound();
        batch.begin();
        controller.drawBackground(batch);
        controller.drawStarShip(batch);
        controller.drawAsteroids(batch);
        controller.gameOver(batch);
        controller.drawGameOver(batch);
        batch.end();
    }

}
