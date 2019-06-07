package com.cinek;

import org.ejml.simple.SimpleMatrix;

import java.awt.image.SampleModel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Cinek on 29.05.2019.
 */
public class Ransac {

    private int numIters;

    private int numSamples;

    private Transform transform;

    double maxError;

    public Ransac(int numIters,  Transform
            transform, double maxError ) {
        this.numIters = numIters;
        this.numSamples = transform.getNumSamples();
        this.transform = transform;
        this.maxError = maxError;
    }

    public SimpleMatrix findBestModel(List<Pair> pairs) {
        SimpleMatrix bestModel = null;
        int bestScore = 0;

        for (int i = 0; i < numIters; i++) {
            SimpleMatrix model = null;
            Set<Pair> samples = new HashSet<>(3);
            while (model == null) {


                samples.add(getRandomPair(pairs));

                if (samples.size() == numSamples) {
                    List<Point> pointsA = new ArrayList<>();
                    List<Point> pointsB = new ArrayList<>();
                    for (Pair pair : samples)
                    {
                        pointsA.add(pair.getPointA());
                        pointsB.add(pair.getPointB());
                    }
                    List<Point> points = new ArrayList<>();
                    points.addAll(pointsA);
                    points.addAll(pointsB);
                    try {
                        model = transform.computeTransform(points);
                    } catch( Exception e)
                    {
                        model = null;
                        samples = new HashSet<>(numSamples);
                    }
                }
            }

            int score = 0;

            for (Pair pair: pairs)
            {
                double error = modelError(pair, model);
                if (error < maxError )
                {
                    score++;
                }
            }
            if (score>bestScore)
            {
                bestScore = score;
                bestModel = model;
            }

        }

        return bestModel;
    }

    public Pair getRandomPair(List<Pair> pairs) {
        int rand = (int)( Math.random() * pairs.size());
        return pairs.get(rand);
    }

    public double modelError(Pair pair, SimpleMatrix model)
    {
        Point pointA = pair.getPointA();
        Point pointB = pair.getPointB();
        Point transformedPoint = transform.transform(pointA, model);


        return Math.sqrt((transformedPoint.getY() - pointB.getY()) * (transformedPoint.getY() - pointB.getY())
                + (transformedPoint.getX() - pointB.getX()) * (transformedPoint.getX() - pointB.getX()));

    }
}
