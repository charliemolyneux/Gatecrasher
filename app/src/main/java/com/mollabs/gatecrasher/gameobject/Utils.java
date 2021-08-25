package com.mollabs.gatecrasher.gameobject;

public class Utils {

    /**
     * getDistanceBetweenPoints returns the distance between 2d points p1 and p2
     * @param point1x
     * @param point1y
     * @param point2x
     * @param point2y
     * @return
     */
    public static double getDistanceBetweenPoints(int point1x, int point1y, double point2x, double point2y) {
        if (point1x == 0 && point1y == 0) {
            return Math.sqrt(
                    Math.pow(point2x, 2) +
                            Math.pow(point2y, 2)
            );
        } else {
            return Math.sqrt(
                    Math.pow(point1x - point2x, 2) +
                            Math.pow(point1y - point2y, 2)
            );
        }
    }
}
