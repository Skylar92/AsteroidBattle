package com.skylar.gdx;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.skylar.gdx.controller.Controller;
import com.skylar.gdx.controller.ControllerGame;
import com.skylar.gdx.utils.HelpTable;
import com.skylar.gdx.utils.PlayerAchievements;
import com.skylar.gdx.utils.SoundUtils;

public class MyGdxGame extends Game {

    private static final MyGdxGame GDX_GAME = new MyGdxGame();

    private Controller controller;
    private SpriteBatch batch;
    private boolean needPrintResult = true;

    private MyGdxGame() {}

    public static MyGdxGame getInstance() {
        return GDX_GAME;
    }

    @Override
    public void create() {
        controller = new ControllerGame();
        controller.initialize();
        batch = new SpriteBatch();
        SoundUtils.setNeedMusic(false);
        SoundUtils.playBackendSound();
        PlayerAchievements.startGame();
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        controller.loopGameLogic();
        if (!controller.isGameOver()) {
            batch.begin();
            controller.drawBackground(batch);
            controller.drawBullets(batch);
            controller.drawStarShip(batch);
            controller.drawAsteroids(batch);
            HelpTable.getInstance().printHelpToShoot(batch);
            HelpTable.getInstance().printScore(batch);
            batch.end();
        } else {
            if(needPrintResult) printPlayerResult();
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

    private void printPlayerResult() {
        needPrintResult = false;
        PlayerAchievements.endGame();
    }

}
