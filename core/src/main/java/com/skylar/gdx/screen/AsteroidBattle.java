package com.skylar.gdx.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.skylar.gdx.controller.Controller;
import com.skylar.gdx.controller.ControllerGame;

/**
 * Created by Skylar
 */
public class AsteroidBattle extends Game {

    public Controller controller;
    public SpriteBatch batch;

    public AsteroidBattle() {
    }

    @Override
    public void create() {
        initialize();
        setScreen(new AsteroidBattleMenu(this));
    }

    public void initialize() {
        controller = new ControllerGame();
        controller.initialize();
        batch = new SpriteBatch();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void render() {
        super.render();
    }
}