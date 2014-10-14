package com.skylar.gdx.controller;

import com.skylar.gdx.actors.Asteroid;
import com.skylar.gdx.actors.Player;
import com.skylar.gdx.controller.actor.ControllerAsteroid;
import com.skylar.gdx.controller.actor.ControllerPlayer;

import java.util.List;

/**
 * @author Skylar
 */
public class ControllerGameComplexity {

    private Player player;
    private List<Asteroid> asteroids;

    private ControllerAsteroid controllerAsteroid;
    private ControllerPlayer controllerPlayer;

    private static final long FREQENCY_COMPLEXITY_GAME = 20000;
    private static long timeGame;

    public ControllerGameComplexity(ControllerAsteroid controllerAsteroid, ControllerPlayer controllerPlayer) {
        this.controllerAsteroid = controllerAsteroid;
        this.controllerPlayer = controllerPlayer;
        timeGame = System.currentTimeMillis();
    }

    public void controlComplexity() {
        if(System.currentTimeMillis() - timeGame >= FREQENCY_COMPLEXITY_GAME) {
            timeGame = System.currentTimeMillis();
            int newStep = controllerAsteroid.getStep() + 1;
            controllerAsteroid.setStep(newStep);
        }
    }




}
