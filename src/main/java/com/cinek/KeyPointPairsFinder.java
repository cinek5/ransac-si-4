package com.cinek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Cinek on 28.05.2019.
 */
public class KeyPointPairsFinder {
    Picture pictureA;
    Picture pictureB;

    private HashMap<Point, Point> pointsPictureA;
    private HashMap<Point, Point> pointsPictureB;

    public KeyPointPairsFinder(Picture pictureA, Picture pictureB)
    {
        this.pictureA = pictureA;
        this.pictureB = pictureB;
        pointsPictureA = new HashMap<>();
        pointsPictureB = new HashMap<>();
    }

    public List<Pair> findKeyPointsPairs()
    {
        computeClosestNeighbours();

        List<Pair> keyPointsPair = new ArrayList<>();

        for (Map.Entry<Point, Point> entryPointA : pointsPictureA.entrySet() )
        {
            Point pointA = entryPointA.getKey();
            Point pointFromB = entryPointA.getValue();
            if (pointsPictureB.get(pointFromB)==pointA)
            {
                keyPointsPair.add(new Pair(pointA, pointFromB));
            }
        }


        return keyPointsPair;

    }

    private void computeClosestNeighbours()
    {
        for (Point point : pictureA.keyPoints )
        {
            Point closestNeighbour = pictureB.getClosestNeighbour(point);
            pointsPictureA.put(point, closestNeighbour);

        }

        for (Point point : pictureB.keyPoints )
        {
            Point closestNeighbour = pictureA.getClosestNeighbour(point);
            pointsPictureB.put(point, closestNeighbour);

        }

    }

}
