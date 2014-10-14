package com.skylar.gdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.skylar.gdx.utils.FileLoaderUtils.*;

/**
 * @author Skylar
 */
public class HelpTable {

    private static BitmapFont font;
    private static HelpTable instance;

    private static final long FREQUENCY_SHOW_HELP = 10000;
    private static final String PRESS_HELP = "Press 'SPACE' to shoot";

    private static long lastShow;

    private HelpTable() {
        lastShow = System.currentTimeMillis();
        HelpTable.font = new BitmapFont(loadFile(FileID.FONT), new TextureRegion(loadTexture(TextureID.FONT)), false);
    }

    public static HelpTable getInstance() {
        if(instance == null)
            instance = new HelpTable();
        return instance;
    }

    public void printHelpToShoot(Batch batch) {
        if(!needShow()) return;
        font.draw(batch, PRESS_HELP, Gdx.graphics.getWidth() - (PRESS_HELP.length() * 18), 40);
        font.setColor(Color.GREEN);
    }

    private boolean needShow() {
        if(System.currentTimeMillis() - lastShow < FREQUENCY_SHOW_HELP)
            return true;
        return false;
    }

    public void printScore(Batch batch) {
        font.draw(batch, "Score: " + PlayerAchievements.getScorePlayer(), 20, 40);
        font.setColor(Color.GREEN);
    }

    public void dispose() {
        font.dispose();
    }

}
