package com.skylar.gdx.controller.actor;

import com.badlogic.gdx.Input;
import com.skylar.gdx.actors.Bullet;
import com.skylar.gdx.actors.Player;

import java.util.List;

import static com.badlogic.gdx.Gdx.*;

/**
 * @author Skylar
 */
public class ControllerPlayer {

    private static final int STEP = 5;

    public void controlPlayer(Player player) {
        controlMove(player);
    }

    private void controlMove(Player player) {
        if(!player.isAlive()) return;
        if(input.isKeyPressed(Input.Keys.LEFT)) {
            if(canMoveLeft(player)) {
                player.setX(player.getX() - STEP);
            }
        }

        if(input.isKeyPressed(Input.Keys.RIGHT)) {
            if(canMoveRight(player)) {
                player.setX(player.getX() + STEP);
            }
        }
    }

    private boolean canMoveLeft(Player player) {
        return player.getX() > 0;
    }

    private boolean canMoveRight(Player player) {
        return player.getX() + player.getWidthSize() < graphics.getWidth();
    }


}
