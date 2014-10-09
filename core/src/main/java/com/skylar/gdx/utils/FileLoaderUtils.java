package com.skylar.gdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Skylar
 */
public class FileLoaderUtils {

    public static enum TextureID {

        BACKGROUND("image/background_cosmos.jpg"),
        PLAYER("image/starship.png"),
        ASTEROID("image/asteroid/asteroid.png"),
        ASTEROID_HP_1("image/asteroid/asteroid_1.png"),
        ASTEROID_HP_2("image/asteroid/asteroid_2.png"),
        ASTEROID_HP_3("image/asteroid/asteroid_3.png"),
        EXPLOSION("image/explosion.png"),
        GAME_OVER("image/game_over.png"),
        BULLET("image/bullet.png"),
        BUTTON_EXIT_GAME_RED("image/main_menu/button_exit_game_red.png"),
        BUTTON_EXIT_GAME_GREEN("image/main_menu/button_exit_game_green.png"),
        BUTTON_START_GAME_BLUE("image/main_menu/button_start_game_blue.png"),
        BUTTON_START_GAME_GREEN("image/main_menu/button_start_game_green.png"),
        BACKGROUND_MENU("image/main_menu/background_menu.jpg");

        String path;

        TextureID(String s) {
            this.path = s;
        }
    }

    private static final Map<TextureID, Texture> TEXTURE_MAP = new HashMap<TextureID, Texture>();

    private FileLoaderUtils() {}

    public static Texture loadTexture(TextureID textureID) {
        if(TEXTURE_MAP.containsKey(textureID))
            return TEXTURE_MAP.get(textureID);
        String path = textureID.path;
        FileHandle internal = Gdx.files.internal(path);
        Texture texture = new Texture(internal);
        TEXTURE_MAP.put(textureID, texture);
        return texture;
    }


}
