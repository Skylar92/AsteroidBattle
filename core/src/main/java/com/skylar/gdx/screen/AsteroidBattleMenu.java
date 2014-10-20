package com.skylar.gdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.skylar.gdx.actors.menu.ExitGameButton;
import com.skylar.gdx.actors.menu.StartGameButton;
import com.skylar.gdx.db.DatabaseException;
import com.skylar.gdx.utils.DatabaseUtils;
import com.skylar.gdx.utils.FileLoaderUtils;
import com.skylar.gdx.utils.HelpTable;
import com.skylar.gdx.utils.SoundUtils;

import static com.badlogic.gdx.Gdx.*;

/**
 * Created by Skylar
 */
public class AsteroidBattleMenu implements Screen {

    private static final String TAG_ERROR = "ERROR";

    private AsteroidBattle game;
    private Texture background;
    private ExitGameButton exitGameButton;
    private StartGameButton startGameButton;

    public AsteroidBattleMenu(AsteroidBattle game) {
        try {
            this.game = game;
            exitGameButton = new ExitGameButton();
            startGameButton = new StartGameButton();
            background = FileLoaderUtils.loadTexture(FileLoaderUtils.TextureID.BACKGROUND_MENU);
            DatabaseUtils.createConnection();
        } catch (DatabaseException e) {
            Gdx.app.log(TAG_ERROR, e.getMessage());
            dispose();
        }
    }

    @Override
    public void render(float delta) {
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        exitGameButton.checkCursorSelected();
        startGameButton.checkCursorSelected();
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        startGameButton.draw(game.batch, graphics.getDeltaTime());
        exitGameButton.draw(game.batch, graphics.getDeltaTime());
        game.batch.end();
        if (exitGameButton.isClicked()) dispose();
        if (startGameButton.isClicked()) game();
    }

    private void game() {
        game.setScreen(new AsteroidBattleGame(game));
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        try {
            game.dispose();
            SoundUtils.dispose();
            HelpTable.getInstance().dispose();
            FileLoaderUtils.dispose();
            DatabaseUtils.closeConnection();
        } catch (DatabaseException e) {
            Gdx.app.log(TAG_ERROR, e.getMessage());
        }
        //and OFF :)
        System.exit(0);
    }
}
