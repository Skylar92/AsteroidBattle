package com.skylar.gdx.controller;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.skylar.gdx.actors.Asteroid;
import com.skylar.gdx.actors.Bullet;
import com.skylar.gdx.actors.Player;
import com.skylar.gdx.controller.actor.ControllerAsteroid;
import com.skylar.gdx.controller.actor.ControllerBullet;
import com.skylar.gdx.controller.actor.ControllerCollision;
import com.skylar.gdx.controller.actor.ControllerPlayer;
import com.skylar.gdx.utils.FileLoaderUtils;
import com.skylar.gdx.utils.SoundUtils;

import java.util.LinkedList;
import java.util.List;

import static com.badlogic.gdx.Gdx.graphics;

/**
 * @author Skylar
 */
public class ControllerGame implements Controller {

    private static final int ZERO_POSITION_X = 0;
    private static final int ZERO_POSITION_Y = 0;
    private static final int PLUG = 0;

    private List<Asteroid> asteroids = new LinkedList<Asteroid>();
    private List<Bullet> bullets = new LinkedList<Bullet>();
    private List<Asteroid> destoidAsteroids = new LinkedList<Asteroid>();

    private TextureRegion background;
    private TextureRegion gameover;
    private Player player;

    private ControllerCollision controllerCollision;
    private ControllerPlayer controllerPlayer;
    private ControllerAsteroid controlLogicAsteroids;
    private ControllerBullet controllerBullet;
    private ControllerGameComplexity controllerGameComplexity;
    private Animation animation;
    private float stateTime;
    private boolean playedExplosionPlayer = false;

    @Override
    public void initialize() {
        controllerCollision = new ControllerCollision();
        controllerPlayer = new ControllerPlayer();
        controlLogicAsteroids = new ControllerAsteroid();
        controllerBullet = new ControllerBullet();

        background = new TextureRegion(FileLoaderUtils.loadTexture(FileLoaderUtils.TextureID.BACKGROUND),
                graphics.getWidth(), graphics.getHeight());

        Texture gameOverImg = FileLoaderUtils.loadTexture(FileLoaderUtils.TextureID.GAME_OVER);
        gameover = new TextureRegion(gameOverImg, gameOverImg.getWidth(), gameOverImg.getHeight());
        player = new Player();
        controllerGameComplexity = new ControllerGameComplexity(controlLogicAsteroids, controllerPlayer);

        loadExplosionsAnimation();

    }

    private void loadExplosionsAnimation() {
        int FRAME_COLS = 5, FRAME_ROWS = 5;
        Texture animations = FileLoaderUtils.loadTexture(FileLoaderUtils.TextureID.EXPLOSION);
        TextureRegion[][] tmp = TextureRegion.split(animations, animations.getWidth() / FRAME_COLS, animations.getHeight() / FRAME_ROWS);
        TextureRegion[] framesExplosions = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                framesExplosions[index++] = tmp[i][j];
            }
        }
        animation = new Animation(0.05f, framesExplosions);
        stateTime = 0f;
    }

    @Override
    public void loopGameLogic() {
        controlLogicAsteroids.controlLogicAsteroids(asteroids);
        controllerPlayer.controlPlayer(player);
        controllerBullet.controlBullet(player, bullets);
        controllerCollision.detectedCollisionPlayerAndAsteroid(player, asteroids);
        List<Asteroid> asteroidsDestroyed = controllerCollision.detectedCollisionBulletAndAsteroid(bullets, asteroids);
        controlLogicAsteroids.setAliveAsteroidFalse(asteroidsDestroyed);
        destoidAsteroids.addAll(asteroidsDestroyed);
        controllerGameComplexity.controlComplexity();
    }

    @Override
    public boolean isGameOver() {
        return controllerCollision.isDetectedCollision();
    }

    @Override
    public void gameOver(Batch batch) {
        if(!playedExplosionPlayer) {
            SoundUtils.explosion();
            playedExplosionPlayer = true;
        }
        stateTime += graphics.getDeltaTime();
        player.setTexture(animation.getKeyFrame(stateTime));
        player.remove();
        player.clear();
    }


    public void drawBackground(Batch batch) {
        batch.draw(background, ZERO_POSITION_X, ZERO_POSITION_Y);
    }

    public void drawStarShip(Batch batch) {
        player.draw(batch, PLUG);
    }

    public void drawAsteroids(Batch batch) {
        for (Asteroid asteroid : asteroids) {
            asteroid.draw(batch, PLUG);
        }
        controlLogicAsteroids.controlDestroyerAsteroids(destoidAsteroids, animation);
    }

    @Override
    public void drawBullets(Batch batch) {
        for (Bullet bullet : bullets) {
            bullet.draw(batch, PLUG);
        }

    }

    @Override
    public void drawGameOver(Batch batch) {
        batch.draw(gameover, graphics.getWidth() / 4, graphics.getHeight() / 4);
    }
}
