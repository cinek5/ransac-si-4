package com.cinek;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Cinek on 28.05.2019.
 */
public class Loader {

    public Picture loadPictureData(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner reader = new Scanner(file);

        reader.nextLine();


        int numberOfKeypoints = Integer.parseInt(reader.nextLine());
        Point[] points = new Point[numberOfKeypoints];

        for (int i=0 ; i<numberOfKeypoints; i++)
        {
            double x = Double.parseDouble(reader.next());
            double y = Double.parseDouble(reader.next());

            reader.next();
            reader.next();
            reader.next();
            int[] features = new int[128];

            for (int k=0; k<128; k++)
            {
                features[k] = Integer.parseInt(reader.next());
            }
            Point point = new Point(x, y, features);
            points[i] = point;
        }

        return  new Picture(points);
    }
}
