package com.cinek;

import java.util.Comparator;

/**
 * Created by Cinek on 29.05.2019.
 */
public class NeighbourhoodComparator implements Comparator<Point> {

    private Point other;


    public NeighbourhoodComparator(Point other)
    {
        this.other = other;
    }
    @Override
    public int compare(Point o1, Point o2) {
        return Integer.compare(o1.getDifference(other), o2.getDifference(other));
    }
}
