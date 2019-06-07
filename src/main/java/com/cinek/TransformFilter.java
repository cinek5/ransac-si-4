package com.cinek;

import org.ejml.simple.SimpleMatrix;
import org.opencv.core.KeyPoint;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Cinek on 07.06.2019.
 */
public class TransformFilter {

    private Ransac ransac;

    public TransformFilter(Ransac ransac)
    {
        this.ransac = ransac;
    }

    public List<Pair> filter(List<Pair> pairs)
    {
        SimpleMatrix model = ransac.findBestModel(pairs);

       return pairs.stream().filter(
                pair -> ransac.modelError(pair, model)<=ransac.maxError)
                .collect(Collectors.toList());
    }
}
