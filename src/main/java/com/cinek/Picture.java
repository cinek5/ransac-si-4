package com.cinek;

/**
 * Created by Cinek on 28.05.2019.
 */
public class Picture {
    Point[] keyPoints;
    public Picture(Point[] keyPoints)
    {
        this.keyPoints = keyPoints;
    }


    public Point getClosestNeighbour(Point other)
    {
       Point closestPoint = keyPoints[0];
       int minDiff = other.getDifference(closestPoint);

       for (Point point: keyPoints)
       {
           int diff = other.getDifference(point);
           if (diff<minDiff)
           {
               minDiff = diff;
               closestPoint = point;
           }
       }

       return closestPoint;

    }
}
