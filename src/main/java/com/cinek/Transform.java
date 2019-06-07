package com.cinek;

import com.cinek.Point;
import org.ejml.simple.SimpleMatrix;

import java.util.List;


/**
 * Created by Cinek on 29.05.2019.
 */
public interface Transform {
    Point transform(Point point, SimpleMatrix transform);
    SimpleMatrix computeTransform(List<Point> pointList);
    int getNumSamples();
}

