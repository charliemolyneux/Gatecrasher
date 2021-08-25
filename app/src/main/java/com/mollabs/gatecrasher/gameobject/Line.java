/*
package com.mollabs.gatecrasher.gameobject;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Line extends GameObject {
    protected double length;
    protected Paint paint;

    public Line(int color, double startX, double startY, double stopX, double stopY) {
        super(startX, startY);

        this.length = GameObject.getLength(startX, startY, stopX, stopY);
        paint = new Paint();
        paint.setColor(color);

    }

    public void draw(Canvas canvas) {
        double stopX = 0;
        double stopY = 0;
        canvas.drawLine((float) positionX, (float) positionY,
                (float) stopX, (float) stopY, paint);
    }
}
*/
