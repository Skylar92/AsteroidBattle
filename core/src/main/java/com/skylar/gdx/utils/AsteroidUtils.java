package com.skylar.gdx.utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.skylar.gdx.utils.FileLoaderUtils.*;

/**
 * Created by Skylar
 */
public class AsteroidUtils {

    private AsteroidUtils() {}

    private static TextureRegion asteroidOneHP;
    private static TextureRegion asteroidTwoHP;
    private static TextureRegion asteroidThreeHP;

    static {
        asteroidOneHP = new TextureRegion(loadTexture(TextureID.ASTEROID_HP_1));
        asteroidTwoHP = new TextureRegion(loadTexture(TextureID.ASTEROID_HP_2));
        asteroidThreeHP = new TextureRegion(loadTexture(TextureID.ASTEROID_HP_3));
    }

    public static TextureRegion loadTextureAfterShoot(int currentHealPoint) {
        switch (currentHealPoint) {
            case 1:
                return asteroidOneHP;
            case 2:
                return asteroidTwoHP;
            case 3:
                return asteroidThreeHP;
            default:
                throw new IllegalArgumentException("Not supported image asteroid heal point [" + currentHealPoint + "]");
        }
    }





}
