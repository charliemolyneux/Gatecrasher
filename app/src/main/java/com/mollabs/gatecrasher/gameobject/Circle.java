package com.mollabs.gatecrasher.gameobject;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Circle extends GameObject {
    protected double radius;
    protected Paint paint;

    public Circle(int color, double positionX, double positionY, double radius) {
        super(positionX, positionY);

        this.radius = radius;
        paint = new Paint();
        paint.setColor(color);
    }

    /**
     * isColliding checks if two circle objects are colliding, based on their positions and radii
     * @param obj1
     * @param obj2
     * @return
     */
    public static boolean isColliding(Circle obj1, Circle obj2) {
        double distance = getDistanceBetweenObjects(obj1, obj2);
        double distanceToCollision = obj1.getRadius() + obj2.getRadius();
        return distance < distanceToCollision;
    }

    private double getRadius() {
        return radius;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle((float) positionX, (float) positionY, (float) radius, paint);
    }
}
