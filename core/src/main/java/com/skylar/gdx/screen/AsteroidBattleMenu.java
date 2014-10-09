package com.skylar.gdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.skylar.gdx.actors.menu.ExitGameButton;
import com.skylar.gdx.actors.menu.StartGameButton;
import com.skylar.gdx.utils.FileLoaderUtils;

/**
 * Created by Skylar
 */
public class AsteroidBattleMenu implements Screen {

    private AsteroidBattle game;
    private Texture background;
    private ExitGameButton exitGameButton;
    private StartGameButton startGameButton;

    public AsteroidBattleMenu(AsteroidBattle game) {
        exitGameButton = new ExitGameButton();
        startGameButton = new StartGameButton();
        background = FileLoaderUtils.loadTexture(FileLoaderUtils.TextureID.BACKGROUND_MENU);
        this.game = game;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        exitGameButton.checkCursorSelected();
        startGameButton.checkCursorSelected();
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        startGameButton.draw(game.batch, Gdx.graphics.getDeltaTime());
        exitGameButton.draw(game.batch, Gdx.graphics.getDeltaTime());
        game.batch.end();
        if(exitGameButton.isClicked()) exit();
        if(startGameButton.isClicked()) game();
    }

    private void exit() {
        System.exit(-1);
    }

    private void game() {
        game.setScreen(new AsteroidBattleGame(game));
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
