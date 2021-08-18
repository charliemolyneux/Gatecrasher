package com.mollabs.gatecrasher.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.mollabs.gatecrasher.R;
import com.mollabs.gatecrasher.gameobject.Player;

/*
* HealthBar displays the players health to the screen
* */
public class HealthBar {
    private Player player;
    private int width, height, margin; // pixel values
    private Paint borderPaint, healthPaint, healthTextPaint;

    public HealthBar(Context context, Player player) {
        this.player = player;
        this.width = 300;
        this.height = 60;
        this.margin = 2;

        // Paint border for health bar
        this.borderPaint = new Paint();
        int borderColor = ContextCompat.getColor(context, R.color.healthBarBorder);
        borderPaint.setColor(borderColor);

        // Paint health bar
        this.healthPaint = new Paint();
        int healthColor = ContextCompat.getColor(context, R.color.healthBarHealth);
        healthPaint.setColor(healthColor);

        // Paint health bar text
        this.healthTextPaint = new Paint();
        int healthTextColor = ContextCompat.getColor(context, R.color.healthBarText);
        healthTextPaint.setColor(healthTextColor);
        healthTextPaint.setTextSize(40);
    }

    public void draw(Canvas canvas) {
        float healthPointsPercentage = (float) player.getHealthPoints() / Player.MAX_HEALTH_POINTS;

        // Draw border
        float borderLeft, borderTop, borderRight, borderBottom;
        borderLeft = 100;
        borderRight = borderLeft + width;
        borderTop = 1000;
        borderBottom = borderTop - height;
        canvas.drawRect(borderLeft, borderTop, borderRight, borderBottom, borderPaint);

        // Draw Health
        float healthLeft, healthTop, healthRight, healthBottom, healthWidth, healthHeight;
        healthWidth = width - 2*margin;
        healthHeight = height - 2*margin;
        healthLeft = borderLeft + margin;
        healthRight = healthLeft + healthWidth*healthPointsPercentage;
        healthBottom = borderBottom + margin;
        healthTop = healthBottom + healthHeight;
        canvas.drawRect(healthLeft, healthTop, healthRight, healthBottom, healthPaint);

        // Draw Text
        float healthTextPositionX , healthTextPositionY;
        String healthBarText = "Health";
        healthTextPositionX = width - 200;
        healthTextPositionY = borderTop - 72;
        canvas.drawText(healthBarText, healthTextPositionX, healthTextPositionY, healthTextPaint);
    }
}
