package com.mollabs.gatecrasher.object;

import android.content.Context;

import androidx.core.content.ContextCompat;
import com.mollabs.gatecrasher.R;
import com.mollabs.gatecrasher.main.GameLoop;
import com.mollabs.gatecrasher.main.Joystick;
import com.mollabs.gatecrasher.object.Circle;

/*
* Player is the main character of the game, which the user controls using a touch joystick
* Player class is an extension of circle, which is an extension of GameObject
* */
public class Player extends Circle {
    protected static final double SPEED_PIXELS_PER_SECOND = 500.0;
    protected static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;
    private final Joystick joystick;

    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius) {
        super(ContextCompat.getColor(context, R.color.colorPlayer), positionX, positionY, radius);
        this.joystick = joystick;
    }

    public void update() {
        // Update velocity based on joystick movements
        velocityX = joystick.getActuatorX()*MAX_SPEED;
        velocityY = joystick.getActuatorY()*MAX_SPEED;

        // Update position
        positionX += velocityX;
        positionY += velocityY;
    }
}
