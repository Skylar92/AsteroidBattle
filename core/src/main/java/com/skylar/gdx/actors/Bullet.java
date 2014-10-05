package com.skylar.gdx.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.skylar.gdx.utils.FileLoaderUtils;

/**
 * @author Skylar
 */
public class Bullet extends Actor {

    private Texture textureBullet;
    private static final float X_POSITION_FACTOR = 0.4f;
    private static final float Y_POSITION_FACTOR = 0.8f;
    private Rectangle rectangle;
    private boolean alive = true;

    public Bullet(Player player) {
        textureBullet = FileLoaderUtils.loadTexture(FileLoaderUtils.TextureID.BULLET);
        setX(player.getX() + (player.getWidth() * X_POSITION_FACTOR));
        setY(player.getY() + (player.getHeight() * Y_POSITION_FACTOR));

        rectangle = new Rectangle();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureBullet, getX(), getY());
    }

    public Rectangle getRectangleBullet() {
        rectangle.set(getX(), getY(), getWidth(), getHeight());
        return rectangle;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setTexture(Texture texture) {
        this.textureBullet = texture;
    }
}
