package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.skylar.gdx.screen.AsteroidBattle;
import com.skylar.gdx.screen.AsteroidBattleGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 600;
        config.title = "Asteroids Battle";
        config.resizable = false;
        new LwjglApplication(new AsteroidBattle(), config);
    }
}
