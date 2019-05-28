package com.cinek;

import com.cinek.visualization.DrawImage;

import java.io.FileNotFoundException;
import java.util.List;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// write your code her
        Loader loader = new Loader();
        String path = "C:\\Users\\Cinek\\Desktop\\studia\\si\\ai4\\extract_features2\\extract_features\\";
        String image1 = path+"kubek1.png";
        String image2 = path+"kubek2.png";
        Picture pictureA = loader.loadPictureData(path+"kubek1.png.haraff.sift");
        Picture pictureB = loader.loadPictureData(path+"kubek2.png.haraff.sift");
        System.out.println("wczytano");

        KeyPointPairsFinder keyPointPairsFinder = new KeyPointPairsFinder(pictureA, pictureB);

        List<Pair> keyPointPairs = keyPointPairsFinder.findKeyPointsPairs();

        System.out.println("wyznczono pary pkt kluczowych");

        DrawImage.vizulization(image1, image2, keyPointPairs);
    }
}
