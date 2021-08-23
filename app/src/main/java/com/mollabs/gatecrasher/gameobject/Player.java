package com.mollabs.gatecrasher.gameobject;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;
import com.mollabs.gatecrasher.R;
import com.mollabs.gatecrasher.gamepanel.HealthBar;
import com.mollabs.gatecrasher.graphics.Sprite;
import com.mollabs.gatecrasher.main.GameLoop;
import com.mollabs.gatecrasher.gamepanel.Joystick;

/*
* Player is the main character of the game, which the user controls using a touch joystick
* Player class is an extension of circle, which is an extension of GameObject
* */
public class Player extends Circle {
    public static final int MAX_HEALTH_POINTS = 10;
    protected static final double SPEED_PIXELS_PER_SECOND = 500.0;
    protected static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final Joystick joystick;
    private HealthBar healthBar;
    private int healthPoints;
    private Sprite sprite;

    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius, Sprite sprite) {
        super(ContextCompat.getColor(context, R.color.colorPlayer), positionX, positionY, radius);
        this.joystick = joystick;
        this.healthBar = new HealthBar(context,this);
        this.healthPoints = MAX_HEALTH_POINTS;
        this.sprite = sprite;
    }

    public void update() {
        // Update velocity based on joystick movements
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;

        // Update position
        positionX += velocityX;
        positionY += velocityY;

        // Update direction
        if (velocityX != 0 || velocityY != 0) {
            // Normalize velocity to get direction (unit vector of velocity)
            double distance = Utils.getDistanceBetweenPoints(0, 0, velocityX, velocityY);
            directionX = velocityX/distance;
            directionY = velocityY/distance;
        }
    }

    public void draw(Canvas canvas) {
        //super.draw(canvas);
        sprite.draw(
                canvas,
                (int) getPositionX() - sprite.getWidth()/2,
                (int) getPositionY() - sprite.getHeight()/2
        );
        healthBar.draw(canvas);
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        if (healthPoints >= 0) {
            this.healthPoints = healthPoints;
        }
    }
}
