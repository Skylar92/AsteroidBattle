package com.skylar.gdx.controller.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.skylar.gdx.actors.Bullet;
import com.skylar.gdx.actors.Player;
import com.skylar.gdx.utils.SoundUtils;

import java.util.Iterator;
import java.util.List;

import static com.badlogic.gdx.Gdx.input;

/**
 * @author Skylar
 */
public class ControllerBullet {

    private static final long FREQUENCY_BULLET = 200;
    private static final float STEP = 13.0f;
    private long timePassed;

    public ControllerBullet() {
        timePassed = System.currentTimeMillis();
    }

    public void controlBullet(Player player, List<Bullet> bullets) {
        checkOnCreateBullet(player, bullets);
        controlMoveBullet(bullets);
    }

    private void controlMoveBullet(List<Bullet> bullets) {
        Iterator<Bullet> bulletIterator = bullets.iterator();

        while (bulletIterator.hasNext()) {
            Bullet bullet = bulletIterator.next();

            if(!bullet.isAlive() || bullet.getY() > Gdx.graphics.getHeight()) {
                bullet.clear();
                bullet.remove();
                bulletIterator.remove();
            }

            bullet.setY(bullet.getY() + STEP);
        }
    }

    private void checkOnCreateBullet(Player player, List<Bullet> bullets) {
        if(input.isKeyPressed(Input.Keys.SPACE) && player.isAlive()) {
            if(canCreateBullet()) {
                SoundUtils.shootBluster();
                bullets.add(new Bullet(player));
                timePassed = System.currentTimeMillis();
            }
        }
    }

    private boolean canCreateBullet() {
        return (System.currentTimeMillis() - timePassed) >= FREQUENCY_BULLET;
    }

}
