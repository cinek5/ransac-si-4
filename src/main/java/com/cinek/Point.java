package com.cinek;

/**
 * Created by Cinek on 28.05.2019.
 */
public class Point {
    double x;
    double y;
    int[] features;

    public Point(double x, double y)
    {
        this.x=x;
        this.y=y;
    }
    public Point(double x, double y, int[] features)
    {
        this.x = x;
        this.y = y;
        this.features = features;
    }

    public int getDifference(Point other)
    {
        int difference = 0;
        for (int i=0; i<features.length; i++)
        {
            difference+=Math.abs(features[i]-other.features[i]);
        }

        return difference;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
