package com.mollabs.gatecrasher.gameobject;

import android.content.Context;
import androidx.core.content.ContextCompat;
import com.mollabs.gatecrasher.R;
import com.mollabs.gatecrasher.main.GameLoop;

/*
* Enemy is a character in game, which always moves in the direction of the player.
* Enemy class is an extension of circle, which is an extension of GameObject
* */
public class Enemy extends Circle {
    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND*0.6;
    protected static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private static final double SPAWNS_PER_MINUTE = 40;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE / 60.0;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS / SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    private final Player player;

    public Enemy(Context context, Player player) {
        super(
                ContextCompat.getColor(context, R.color.colorEnemy),
                Math.random()*1000,
                Math.random()*1000,
                30
        );
        this.player = player;
    }

    public static boolean readyToSpawn() {
        // ===================================================================================
        // readyToSpawn checks if a new enemy should spawn,
        // according to the decided number of spawns per minute
        // ===================================================================================
        if (updatesUntilNextSpawn <= 0) {
            updatesUntilNextSpawn += UPDATES_PER_SPAWN;
            return true;
        } else {
            updatesUntilNextSpawn--;
            return false;
        }
    }

    @Override
    public void update() {
        // ===================================================================================
        //  Update velocity of enemy so that the velocity is in the direction of the player
        // ===================================================================================
        // Calculate vector from enemy to player (in x and y)
        double distanceToPlayerX = player.getPositionX() - positionX;
        double distanceToPlayerY = player.getPositionY() - positionY;

        // Calculate absolute distance from enemy(this) to player
        double distanceToPlayer = GameObject.getDistanceBetweenObjects(this, player);

        // Calculate direction between enemy and player
        double directionX = distanceToPlayerX/distanceToPlayer;
        double directionY = distanceToPlayerY/distanceToPlayer;

        // Set velocity in the direction of player
        if (distanceToPlayer > 0) {
            velocityX = directionX*MAX_SPEED;
            velocityY = directionY*MAX_SPEED;
        } else {
            velocityX = 0;
            velocityY = 0;
        }
        // Update the position of the enemy
        positionX += velocityX;
        positionY += velocityY;
    }
}
