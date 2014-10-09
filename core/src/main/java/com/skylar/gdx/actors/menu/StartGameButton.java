package com.skylar.gdx.actors.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.skylar.gdx.utils.FileLoaderUtils;

/**
 * Created by Skylar
 */
public class StartGameButton extends Actor {

    private static final float x = 200;
    private static final float y = 400;

    private static final float MAGIC_FACTOR = 4.3f;

    private Texture greenButton;
    private Texture blueButton;
    private Texture currentButton;
    private Rectangle rectangle;
    private boolean cursorOnButton = false;

    public StartGameButton() {
        //load texture
        greenButton = FileLoaderUtils.loadTexture(FileLoaderUtils.TextureID.BUTTON_START_GAME_GREEN);
        blueButton = FileLoaderUtils.loadTexture(FileLoaderUtils.TextureID.BUTTON_START_GAME_BLUE);
        currentButton = blueButton;
        //create rectangle
        rectangle = new Rectangle();
        rectangle.x = x;
        rectangle.y = y - (MAGIC_FACTOR * currentButton.getHeight());
        rectangle.height = currentButton.getHeight();
        rectangle.width = currentButton.getWidth();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(currentButton, x, y);
    }

    public void checkCursorSelected() {
        if(rectangle.contains(Gdx.input.getX(), Gdx.input.getY())) {
            currentButton = greenButton;
            cursorOnButton = true;
        } else {
            cursorOnButton = false;
            currentButton = blueButton;
        }
    }

    public boolean isClicked() {
        return Gdx.input.isTouched() && cursorOnButton;
    }
}
