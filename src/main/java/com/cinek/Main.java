package com.cinek;

import com.cinek.visualization.DrawImage;
import org.ejml.simple.SimpleMatrix;

import java.io.FileNotFoundException;
import java.util.List;


public class Main {



    public static void main(String[] args) throws FileNotFoundException {
	// write your code her

        Loader loader = new Loader();
        String path = "C:\\Users\\Cinek\\Desktop\\studia\\si\\ai4\\extract_features2\\extract_features\\";
        String image1 = path+"myszka1.png";
        String image2 = path+"myszka2.png";
        Picture pictureA = loader.loadPictureData(path+"myszka1.png.haraff.sift");
        Picture pictureB = loader.loadPictureData(path+"myszka2.png.haraff.sift");
        System.out.println("wczytano");

        KeyPointPairsFinder keyPointPairsFinder = new KeyPointPairsFinder(pictureA, pictureB);

        List<Pair> keyPointPairs = keyPointPairsFinder.findKeyPointsPairs();

        System.out.println("wyznczono pary pkt kluczowych");

        DrawImage.vizulization(image1, image2, keyPointPairs);

        System.out.println(keyPointPairs.size());

        NeighbourhoodCoherenceFilter neighbourhoodCoherenceFilter = new NeighbourhoodCoherenceFilter(keyPointPairs, 6, 5);



        List<Pair> filteredPairs = neighbourhoodCoherenceFilter.getFilteredPairs();

        System.out.println(filteredPairs.size());

        DrawImage.vizulization(image1, image2, filteredPairs);

        //Transform transform = new AffineTransform();
        Transform transform = new PerspectiveTransform();
        Ransac ransac = new Ransac(105000, transform,25);
        TransformFilter transformFilter = new TransformFilter(ransac);

        List<Pair> ransacPairs = transformFilter.filter(filteredPairs);



        DrawImage.vizulizationRansac(image1, image2, filteredPairs,ransacPairs );
    }
}
