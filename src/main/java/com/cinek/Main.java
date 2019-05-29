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

        NeighbourhoodCoherenceFilter neighbourhoodCoherenceFilter = new NeighbourhoodCoherenceFilter(keyPointPairs, 50, 45);

        List<Pair> filteredPairs = neighbourhoodCoherenceFilter.getFilteredPairs();

        DrawImage.vizulization(image1, image2, filteredPairs);

        Transform transform = new AffineTransform();
        Ransac ransac = new Ransac(1050000, 3, transform,0.5);

        SimpleMatrix model = ransac.findBestModel(filteredPairs);

        DrawImage.vizulizationRansac(image1, image2, filteredPairs, transform, model );
    }
}
