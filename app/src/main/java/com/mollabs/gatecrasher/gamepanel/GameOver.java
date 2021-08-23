package com.mollabs.gatecrasher.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import androidx.core.content.ContextCompat;

import com.mollabs.gatecrasher.R;

/**
 *  Game Over draws the text "Game Over" when the player runs out of health
 */
public class GameOver {

    Context context;

    public GameOver(Context context) {
        this.context = context;
    }

    public void draw(Canvas canvas) {
        String gameOverText = "Game Over";
        float x = 850;
        float y = 300;
        float textSize = 200;

        Paint gameOverPaint = new Paint();
        int color = ContextCompat.getColor(context, R.color.gameOver);
        gameOverPaint.setColor(color);
        gameOverPaint.setTextSize(textSize);
        gameOverPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));


        canvas.drawText(gameOverText, x, y, gameOverPaint);
    }
}
