package com.mollabs.gatecrasher.object;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Line extends GameObject {
    protected Paint paint;

    public Line(int color, double positionX, double positionY, double stopX, double stopY) {
        super(positionX, positionY);

        paint = new Paint();
        paint.setColor(color);

    }

    @Override
    public void draw(Canvas canvas) {
        double stopX = 0;
        double stopY = 0;
        canvas.drawLine((float) positionX, (float) positionY,
                (float) stopX, (float) stopY, paint);
    }
}
