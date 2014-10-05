package com.skylar.gdx.controller.actor;

import com.skylar.gdx.actors.Asteroid;
import com.skylar.gdx.actors.Bullet;
import com.skylar.gdx.actors.Player;
import com.skylar.gdx.utils.PlayerAchievements;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Skylar
 */
public class ControllerCollision {

    private boolean isDetectedCollision = false;

    public void detectedCollisionPlayerAndAsteroid(Player player, List<Asteroid> asteroids) {
        for (Asteroid asteroid : asteroids) {
            if (player.getRectanglePlayer().overlaps(asteroid.getRectangleAsteroid())) {
                player.setAlive(false);
                isDetectedCollision = true;
            }
        }
    }

    public boolean isDetectedCollision() {
        return isDetectedCollision;
    }

    public List<Asteroid> detectedCollisionBulletAndAsteroid(List<Bullet> bullets, List<Asteroid> asteroids) {
        List<Asteroid> asteroidsDestroy = new LinkedList<Asteroid>();
        for (Bullet bullet : bullets) {
            for (Asteroid asteroid : asteroids) {
                if(bullet.getRectangleBullet().overlaps(asteroid.getRectangleAsteroid())) {
                    hitBullet(bullet, asteroid, asteroidsDestroy);
                }
            }
        }
        return asteroidsDestroy;
    }

    private void hitBullet(Bullet bullet, Asteroid asteroid, List<Asteroid> asteroidsDestroy) {
        bullet.setAlive(false);
        asteroid.hit();
        if(!asteroid.isAlive()) {
            PlayerAchievements.destroyAsteroid(asteroid);
            asteroidsDestroy.add(asteroid);
        }
    }

}
