package com.skylar.gdx.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.math.Rectangle;
import com.skylar.gdx.utils.FileLoaderUtils;

public class Player extends Actor {

    private static final int start_location_x = 400;
    private static final int start_location_y = 50;

    private TextureRegion texture;
    private Rectangle rectangle;
    private boolean alive = true;

    public Player() {
        texture = new TextureRegion(FileLoaderUtils.loadTexture(FileLoaderUtils.TextureID.PLAYER));
        setSize(texture.getRegionWidth(), texture.getRegionHeight());
        setX(start_location_x);
        setY(start_location_y);
        setWidth(texture.getRegionWidth());
        setHeight(texture.getRegionHeight());
        rectangle = new Rectangle();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture,getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public int getWidthSize() {
        return texture.getRegionWidth();
    }

    public Rectangle getRectanglePlayer() {
        rectangle.set(getX(), getY(), getWidth(), getHeight());
        return rectangle;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }
}
