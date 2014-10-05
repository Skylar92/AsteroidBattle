package com.skylar.gdx.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;

/**
 * @author Skylar
 */
public class SoundUtils {

    private SoundUtils() {}

    private static boolean needMusic = true;
    private static Music music;
    private static Sound blasterShoot;
    private static Sound explosion;

    private static final float VOLUME = 0.05f;

    private static enum Sounds {
        BACKGROUND_MUSIC("music/backend.mp3"),
        SHOOT_MUSIC("music/blaster_shoot.mp3"),
        EXPLOSION_MUSIC("music/explosion.mp3");

        private String path;

        Sounds(String s) {
            this.path = s;
        }
    }

    public static void playBackendSound() {
        if(!needMusic) return;
        music = Gdx.audio.newMusic(load(Sounds.BACKGROUND_MUSIC));
        music.play();
        music.setVolume(VOLUME);
        music.setLooping(true);
    }

    public static void stopBackendSound() {
        if(music != null && music.isPlaying()) {
            music.stop();
        }
    }

    public static void shootBluster() {
        if(!needMusic) return;
        if(blasterShoot == null) {
            blasterShoot = Gdx.audio.newSound(load(Sounds.SHOOT_MUSIC));
        }
        blasterShoot.play(VOLUME);
    }

    public static void explosion() {
        if(!needMusic) return;
        if(explosion == null) {
            explosion = Gdx.audio.newSound(load(Sounds.EXPLOSION_MUSIC));
        }

        explosion.play(VOLUME);
    }

    private static FileHandle load(Sounds sound) {
        String pathToSound = FileDeveloperUtils.PATH_HOME + sound.path;
        return Gdx.files.internal(pathToSound);
    }

    public static void setNeedMusic(boolean needMusic) {
        SoundUtils.needMusic = needMusic;
    }
}
