package com.mollabs.gatecrasher.gameobject;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;
import com.mollabs.gatecrasher.R;
import com.mollabs.gatecrasher.graphics.Sprite;
import com.mollabs.gatecrasher.main.Game;
import com.mollabs.gatecrasher.main.GameDisplay;
import com.mollabs.gatecrasher.main.GameLoop;

/*
* Enemy is a character in game, which always moves in the direction of the player.
* Enemy class is an extension of circle, which is an extension of GameObject
* */
public class Enemy extends Circle {
    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND*0.6;
    protected static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    protected static final double MAX_COLLISION_AVOID_SPEED = MAX_SPEED*0.12;
    private static final double SPAWNS_PER_MINUTE = 40;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE / 60.0;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS / SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    private final Player player;
    private Sprite sprite;

    public Enemy(Context context, Player player, Sprite sprite) {
        super(
                ContextCompat.getColor(context, R.color.colorEnemy),
                Math.random()*1800,
                Math.random()*1000,
                30
        );
        this.player = player;
        this.sprite = sprite;
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

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        sprite.draw(
                canvas,
                (int) gameDisplay.gameToDisplayCoordinatesX(getPositionX()) - sprite.getWidth()/2,
                (int) gameDisplay.gameToDisplayCoordinatesY(getPositionY()) - sprite.getHeight()/2
        );
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

    public static boolean spawnTooClose(Enemy enemy, Player player) {
        // ===================================================================================
        // Check if an enemy has spawned too close to the player, and boolean true or false
        // ===================================================================================
        // Get distance between enemy and player
        double distanceToPlayer = GameObject.getDistanceBetweenObjects(enemy, player);

        return distanceToPlayer < 400;
    }

    public static void collisionDetection(Circle currentEnemy, Circle enemy) {
        // ===================================================================================
        //  Update velocity of enemy so that they avoid a collision with another enemy
        // ===================================================================================
        // Calculate vector from currentEnemy to enemy
        double distanceToEnemyX = enemy.getPositionX() - currentEnemy.getPositionX();
        double distanceToEnemyY = enemy.getPositionY() - currentEnemy.getPositionY();

        // Calculate absolute distance from currentEnemy to enemy
        double distanceToEnemy = GameObject.getDistanceBetweenObjects(currentEnemy, enemy);
        double distanceToCollision = currentEnemy.getRadius() + enemy.getRadius();

        // Calculate direction between currentEnemy and enemy
        double directionX = distanceToEnemyX / distanceToEnemy;
        double directionY = distanceToEnemyY / distanceToEnemy;

        // Set velocity in the direction away from enemy
        if (distanceToEnemy <= distanceToCollision) {
            currentEnemy.velocityX = directionX * MAX_COLLISION_AVOID_SPEED * -1;
            currentEnemy.velocityY = directionY * MAX_COLLISION_AVOID_SPEED * -1;
            enemy.velocityX = directionX * MAX_COLLISION_AVOID_SPEED;
            enemy.velocityY = directionY * MAX_COLLISION_AVOID_SPEED;

            // Update the position of the currentEnemy + enemy
            currentEnemy.positionX += currentEnemy.velocityX;
            currentEnemy.positionY += currentEnemy.velocityY;
            enemy.positionX += enemy.velocityX;
            enemy.positionY += enemy.velocityY;
        } else {
            currentEnemy.velocityX = 0;
            currentEnemy.velocityX = 0;
            enemy.velocityX = 0;
            enemy.velocityX = 0;
        }
    }

}
