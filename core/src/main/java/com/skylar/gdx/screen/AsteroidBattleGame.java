package com.skylar.gdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.skylar.gdx.utils.HelpTable;
import com.skylar.gdx.utils.PlayerAchievements;
import com.skylar.gdx.utils.SoundUtils;

public class AsteroidBattleGame implements Screen {

    private static final int TIME_TO_GO_MENU_SCREEN = 5000;

    private AsteroidBattle asteroidBattle;
    private long time;

    public AsteroidBattleGame(AsteroidBattle asteroidBattle) {
        this.asteroidBattle = asteroidBattle;
        SoundUtils.playBackendSound();
        PlayerAchievements.startGame();
        PlayerAchievements.resetPoint();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        asteroidBattle.controller.loopGameLogic();
        if (!asteroidBattle.controller.isGameOver()) {
            drawGame();
        } else {
            drawGameOver();
        }
    }

    private void drawGame() {
        asteroidBattle.batch.begin();
        asteroidBattle.controller.drawBackground(asteroidBattle.batch);
        asteroidBattle.controller.drawBullets(asteroidBattle.batch);
        asteroidBattle.controller.drawStarShip(asteroidBattle.batch);
        asteroidBattle.controller.drawAsteroids(asteroidBattle.batch);
        HelpTable.getInstance().printHelpToShoot(asteroidBattle.batch);
        HelpTable.getInstance().printScore(asteroidBattle.batch);
        asteroidBattle.batch.end();
        time = System.currentTimeMillis();
    }

    private void drawGameOver() {
        if(System.currentTimeMillis() - time > TIME_TO_GO_MENU_SCREEN) {
            asteroidBattle.dispose();
            asteroidBattle.initialize();
            asteroidBattle.setScreen(new AsteroidBattleMenu(asteroidBattle));
        } else {
            PlayerAchievements.endGame();
            SoundUtils.stopBackendSound();
            asteroidBattle.batch.begin();
            asteroidBattle.controller.drawBackground(asteroidBattle.batch);
            asteroidBattle.controller.drawStarShip(asteroidBattle.batch);
            asteroidBattle.controller.drawAsteroids(asteroidBattle.batch);
            asteroidBattle.controller.gameOver(asteroidBattle.batch);
            asteroidBattle.controller.drawGameOver(asteroidBattle.batch);
            asteroidBattle.batch.end();
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void dispose() {}
}
