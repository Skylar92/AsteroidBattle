package com.skylar.gdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

/**
 * @author Skylar
 */
public class FileLoaderUtils {

    public static enum TextureID {

        BACKGROUND("image/background_cosmos.jpg"),
        PLAYER("image/starship.png"),
        ASTEROID("image/asteroid.png"),
        EXPLOSION("image/explosion.png"),
        GAME_OVER("image/game_over.png"),
        BULLET("image/bullet.png");

        String path;

        TextureID(String s) {
            this.path = s;
        }
    }

    private FileLoaderUtils() {}

    public static Texture loadTexture(TextureID textureID) {
        String path = FileDeveloperUtils.PATH_HOME + textureID.path;
        FileHandle internal = Gdx.files.internal(path);
        return new Texture(internal);
    }


}
