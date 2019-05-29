package com.cinek;

/**
 * Created by Cinek on 28.05.2019.
 */
public class Pair {
    Point pointA;
    Point pointB;

    public Pair(Point pointA, Point pointB)
    {
        this.pointA = pointA;
        this.pointB = pointB;
    }

    public Point getPointA() {
        return pointA;
    }

    public Point getPointB() {
        return pointB;
    }
}
