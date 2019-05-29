package com.cinek;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Cinek on 28.05.2019.
 */
public class NeighbourhoodCoherenceFilter {

    private HashMap<Point, List<Point> > neighbourhoodA;
    private HashMap<Point, List<Point> > neighbourhoodB;

    private HashMap<Point, Pair> pairsByPoint;

    private List<Point> pointsA;
    private List<Point> pointsB;

    private List<Pair> pairs;
    private int k;

    private int coherenceCondition;

    public NeighbourhoodCoherenceFilter(List<Pair> keyPointsPairs, int k, int coherenceCondition)
    {
        neighbourhoodA = new HashMap<>();
        neighbourhoodB = new HashMap<>();
        this.pairs = keyPointsPairs;
        this.k = k;
        initPoints();
        initPairsByPoint();
        this.coherenceCondition = coherenceCondition;

    }

    public List<Pair>  getFilteredPairs()
    {
        List<Pair> filteredPairs = new ArrayList<>();
        computerNeighbourhood();

        for (Pair pair: pairs)
        {

            int howManyPairs = 0;

            Point pointA = pair.getPointA();
            Point pointB = pair.getPointB();

            List<Point> neighboursA = neighbourhoodA.get(pointA);
            List<Point> neighboursB = neighbourhoodB.get(pointB);

            for (Point point : neighboursA )
            {
                Point pointFromPair = pairsByPoint.get(point).getPointB();

                if (neighboursB.contains(pointFromPair))
                {
                    howManyPairs++;
                }

            }
            if (howManyPairs>=coherenceCondition)
            {
                filteredPairs.add(pair);
            }

        }


        return filteredPairs;
    }

    private void initPairsByPoint()
    {
        pairsByPoint = new HashMap<>();

        for (Pair pair : pairs)
        {
            pairsByPoint.put(pair.getPointA(), pair);
            pairsByPoint.put(pair.getPointB(), pair);
        }
    }

    private void initPoints()
    {
        pointsA = new ArrayList<>();
        pointsB = new ArrayList<>();
        for (Pair pair : pairs)
        {
            pointsA.add(pair.pointA);
            pointsB.add(pair.pointB);
        }
    }

    private void computerNeighbourhood()
    {
        Iterator<Point> itA = pointsA.iterator();
        while (itA.hasNext())
        {
            Point pointA = itA.next();
            List<Point> sorted = pointsA.stream().sorted(new NeighbourhoodComparator(pointA)).collect(Collectors.toList());
            neighbourhoodA.put(pointA, sorted.subList(1, k+1));
        }

        Iterator<Point> itB = pointsB.iterator();
        while ( itB.hasNext() )
        {
            Point pointB = itB.next();
            List<Point> sorted = pointsB.stream().sorted(new NeighbourhoodComparator(pointB)).collect(Collectors.toList());
            neighbourhoodB.put(pointB, sorted.subList(1, k+1));
        }


    }
}
