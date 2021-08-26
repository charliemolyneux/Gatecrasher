package com.mollabs.gatecrasher.gameobject;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.mollabs.gatecrasher.R;
import com.mollabs.gatecrasher.gameobject.Circle;
import com.mollabs.gatecrasher.gameobject.Player;
import com.mollabs.gatecrasher.main.GameLoop;

public class Spell extends Circle {
    protected static final double SPEED_PIXELS_PER_SECOND = 800.0;
    protected static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / GameLoop.MAX_UPS;

    public Spell(Context context, Player spellcaster) {

        super(
                ContextCompat.getColor(context, R.color.colorSpell),
                spellcaster.getPositionX(),
                spellcaster.getPositionY(),
                25
        );
        velocityX = spellcaster.getDirectionX()*MAX_SPEED;
        velocityY = spellcaster.getDirectionY()*MAX_SPEED;
    }

    @Override
    public void update() {
        positionX += velocityX;
        positionY += velocityY;
    }
}
