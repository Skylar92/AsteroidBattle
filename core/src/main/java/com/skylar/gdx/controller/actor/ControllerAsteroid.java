package com.skylar.gdx.controller.actor;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.skylar.gdx.actors.Asteroid;

import java.util.Iterator;
import java.util.List;

import static com.badlogic.gdx.Gdx.graphics;

/**
 * @author Skylar
 */
public class ControllerAsteroid {

    private int step;

    private static final int ROTATION = 2;
    private static final int MAX_ASTEROIDS_IN_SCREEN = 20;
    private static final long FREQUENCY_ASTEROID = 200;
    private static final int MAGIC_VALUE = 1;

    private long timePassed;

    public ControllerAsteroid() {
        step = 1;
        timePassed = System.currentTimeMillis();
    }

    public void controlLogicAsteroids(List<Asteroid> asteroids) {
        if(canCreateAsteroid(asteroids))
            createAsteroid(asteroids);

        Iterator<Asteroid> asteroidIterator = asteroids.iterator();

        while (asteroidIterator.hasNext()) {
            Asteroid asteroid = asteroidIterator.next();
            if((!asteroid.isAlive() && asteroid.isExplosionFinish()) || asteroid.getY() + (asteroid.getMaxRandomSize() * MAGIC_VALUE) < 0) {
                asteroid.clear();
                asteroid.remove();
                asteroidIterator.remove();
            }

            asteroid.setY(asteroid.getY() - step);
            asteroid.setRotation(asteroid.getRotation() + ROTATION);
        }
    }

    public void controlDestroyerAsteroids(List<Asteroid> asteroidsDestroyed, Animation animation) {
        Iterator<Asteroid> asteroidIterator = asteroidsDestroyed.iterator();

        while(asteroidIterator.hasNext()) {
            Asteroid asteroid = asteroidIterator.next();
            if(asteroid.isExplosionFinish()) {
                asteroidIterator.remove();
            }

            asteroid.setDeltaTime(asteroid.getDeltaTime() + graphics.getDeltaTime());
            asteroid.setTexture(animation.getKeyFrame(asteroid.getDeltaTime()));
        }
    }

    public void setAliveAsteroidFalse(List<Asteroid> asteroidsDestroyed) {
        for (Asteroid asteroid : asteroidsDestroyed) {
            asteroid.hit();
        }
    }

    private boolean canCreateAsteroid(List<Asteroid> asteroids) {
        return asteroids.size() < MAX_ASTEROIDS_IN_SCREEN
                && (System.currentTimeMillis() - timePassed) >= FREQUENCY_ASTEROID;
    }

    private void createAsteroid(List<Asteroid> asteroids) {
        Asteroid asteroid = new Asteroid();
        asteroids.add(asteroid);
        timePassed = System.currentTimeMillis();
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getStep() {
        return step;
    }
}
