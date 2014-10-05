package com.skylar.gdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * @author Skylar
 */
public class HelpTable {

    private static BitmapFont font;
    private static HelpTable instance;
    private static final long FREQUENCY_SHOW_HELP = 10000;
    private static long lastShow;

    private HelpTable() {
        lastShow = System.currentTimeMillis();
        font = new BitmapFont();
    }

    public static HelpTable getInstance() {
        if(instance == null)
            instance = new HelpTable();
        return instance;
    }

    public void printHelpToShoot(Batch batch) {
        if(!needShow()) return;
        String text = "Press 'SPACE' to shoot";
        font.draw(batch, text, Gdx.graphics.getWidth() - (text.length() * 7), text.length());
        font.setColor(Color.GREEN);
    }

    private boolean needShow() {
        if(System.currentTimeMillis() - lastShow < FREQUENCY_SHOW_HELP)
            return true;
        return false;
    }

    public void printScore(Batch batch) {
        font.draw(batch, "Score: " + PlayerAchievements.getScorePlayer(), 20, 20);
        font.setColor(Color.GREEN);
    }


}
