package com.mollabs.gatecrasher.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

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

        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.gameOver);
        paint.setColor(color);
        paint.setTextSize(textSize);
        paint.setFakeBoldText(true);

        canvas.drawText(gameOverText, x, y, paint);
    }
}
