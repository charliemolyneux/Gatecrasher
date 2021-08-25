package com.mollabs.gatecrasher.main;

import com.mollabs.gatecrasher.gameobject.GameObject;

public class GameDisplay {
    private double gameToDisplayCoordinateOffsetX;
    private double gameToDisplayCoordinateOffsetY;
    private double displayCenterX;
    private double displayCenterY;
    private GameObject centerObject;

    public GameDisplay(GameObject centerObject, int widthPixels, int heightPixels) {
        this.centerObject = centerObject;

        displayCenterX = widthPixels/2.0;
        displayCenterY = heightPixels/2.0;
    }

    public void update() {
        double gameCenterX = centerObject.getPositionX();
        double gameCenterY = centerObject.getPositionY();
        
        gameToDisplayCoordinateOffsetX = displayCenterX - gameCenterX;
        gameToDisplayCoordinateOffsetY = displayCenterY - gameCenterY;
    }

    public double gameToDisplayCoordinatesX(double positionX) {
        return positionX + gameToDisplayCoordinateOffsetX;
    }

    public double gameToDisplayCoordinatesY(double positionY) {
        return positionY + gameToDisplayCoordinateOffsetY;
    }
}
