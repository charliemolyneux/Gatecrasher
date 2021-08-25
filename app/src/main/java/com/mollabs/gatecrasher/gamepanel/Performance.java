package com.mollabs.gatecrasher.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.mollabs.gatecrasher.R;
import com.mollabs.gatecrasher.main.GameLoop;

import java.util.Locale;

public class Performance {
    private GameLoop gameLoop;
    private Context context;

    public Performance(Context context, GameLoop gameLoop) {
        this.context = context;
        this.gameLoop = gameLoop;
    }

    public void draw(Canvas canvas) {
        drawUPS(canvas);
        drawFPS(canvas);
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.colorData);
        paint.setColor(color);
        paint.setTextSize(50);
        String formatUPS = String.format(Locale.ENGLISH, "%.5s", averageUPS);
        canvas.drawText("UPS: " + formatUPS, 100, 900, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.colorData);
        paint.setColor(color);
        paint.setTextSize(50);
        String formatFPS = String.format(Locale.ENGLISH, "%.5s", averageFPS);
        canvas.drawText("FPS: " + formatFPS, 100, 1000, paint);
    }

}
