package com.skylar.gdx.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.badlogic.gdx.math.Rectangle;
import com.skylar.gdx.utils.FileLoaderUtils;
import com.skylar.gdx.utils.SoundUtils;

import java.util.Random;

/**
 * @author Skylar
 */
public class Asteroid extends Actor {

    private static final int TINY_SIZE = 16;
    private static final int SMALL_SIZE = 32;
    private static final int NORMAL_SIZE = 64;
    private static final int BIG_SIZE = 128;

    private static final int MAX_RANDOM_SIZE = 50;

    private static final int START_HEIGHT = 700;
    private static final int START_WIDTH = 800;

    private static final long EXPLOSIONS_TIME = 300;

    private TextureRegion asteroid;
    private Texture texture;
    private Rectangle rectangle;
    private int size;
    private boolean alive= true;
    private long timeExplosion;
    private float deltaTime = 0f;
    private int heal_point;
    private int max_heal_point;

    public Asteroid() {
        //-------------------------//
        texture = FileLoaderUtils.loadTexture(FileLoaderUtils.TextureID.ASTEROID);
        asteroid = new TextureRegion(texture);
        //-------------------------//
        Random random = new Random();
        size = getRandomSize(random);
        setSize(size, size);
        setOrigin(size / 2, size / 2);
        //-------------------------//
        int randomWidth = random.nextInt(START_WIDTH);
        setXAndY(randomWidth, START_HEIGHT);
        rectangle = new Rectangle();
    }

    private int getRandomSize(Random random) {
        int i = random.nextInt();
        if(i % 3 == 0) {
            heal_point = max_heal_point = 1;
            return TINY_SIZE;
        } else if (i % 5 == 0) {
            heal_point = max_heal_point  = 2;
            return SMALL_SIZE;
        } else if (i % 7 ==0) {
            heal_point = max_heal_point = 4;
            return BIG_SIZE;
        } else {
            heal_point = max_heal_point = 3;
            return NORMAL_SIZE;
        }
    }

    private void setXAndY(int x, int y) {
        if(x <= MAX_RANDOM_SIZE || x >= START_WIDTH - MAX_RANDOM_SIZE)
            x = START_WIDTH / (x == 0 ? MAX_RANDOM_SIZE : x);
        setX(x);
        setY(y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(asteroid, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public int getMaxRandomSize() {
        return MAX_RANDOM_SIZE;
    }

    public Rectangle getRectangleAsteroid() {
        rectangle.set(getX(), getY(), size, size);
        return rectangle;
    }

    public void setTexture(TextureRegion texture) {
        this.asteroid = texture;
    }

    public void hit() {
        heal_point--;
        if(heal_point <= 0) {
            SoundUtils.explosion();
            this.alive = false;
            this.timeExplosion = System.currentTimeMillis();
        }
    }

    public int getMax_heal_point() {
        return max_heal_point;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isExplosionFinish() {
        return (System.currentTimeMillis() - timeExplosion) >= EXPLOSIONS_TIME;
    }

    public float getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(float deltaTime) {
        this.deltaTime = deltaTime;
    }
}
