package com.cinek;

import com.cinek.Point;
import org.ejml.simple.SimpleMatrix;


/**
 * Created by Cinek on 29.05.2019.
 */
public interface Transform {
    Point transform(Point point, SimpleMatrix transform);
    SimpleMatrix computeTransform(Point a1, Point a2, Point a3, Point b1, Point b2, Point b3);
}
