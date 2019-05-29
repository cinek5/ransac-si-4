package com.cinek.visualization;

import com.cinek.Pair;
import com.cinek.Picture;
import com.cinek.Point;
import com.cinek.Transform;
import org.ejml.simple.SimpleMatrix;
import org.opencv.core.KeyPoint;
import sun.java2d.pipe.SpanShapeRenderer;

import java.awt.*;
import java.util.List;

public class DrawImage {



    public  static void vizulization(String path1, String path2, List<Pair> pairs) {

// The image URL - change to where your image file is located!

        String imageURL = path1;
        String imageURL2 = path2;

// This call returns immediately and pixels are loaded in the background

        Image image = Toolkit.getDefaultToolkit().getImage(imageURL);
        Image image2 = Toolkit.getDefaultToolkit().getImage(imageURL2);

// Create a frame

        Frame frame = new Frame();

// Add a component with a custom paint method

        frame.add(new CustomPaintComponent(image, image2, pairs));

// Display the frame

        int frameWidth = 268;

        int frameHeight = 720;

        frame.setSize(frameWidth, frameHeight);

        frame.setVisible(true);

    }

    public  static void vizulizationRansac(String path1, String path2, List<Pair> pairs, Transform transform, SimpleMatrix model) {

// The image URL - change to where your image file is located!

        String imageURL = path1;
        String imageURL2 = path2;

// This call returns immediately and pixels are loaded in the background

        Image image = Toolkit.getDefaultToolkit().getImage(imageURL);
        Image image2 = Toolkit.getDefaultToolkit().getImage(imageURL2);

// Create a frame

        Frame frame = new Frame();

// Add a component with a custom paint method

        frame.add(new CustomPaintComponent(image, image2, pairs, transform, model));

// Display the frame

        int frameWidth = 268;

        int frameHeight = 720;

        frame.setSize(frameWidth, frameHeight);

        frame.setVisible(true);

    }

    /**
     * To draw on the screen, it is first necessary to subclass a Component
     * and override its paint() method. The paint() method is automatically called
     * by the windowing system whenever component's area needs to be repainted.
     */
    static class CustomPaintComponent extends Component {

        Image image1;
        Image image2;

        List<Pair> pairList;
        Transform transform;
        SimpleMatrix model;
        public CustomPaintComponent(Image image1, Image image2, List<Pair> pairs, Transform transform, SimpleMatrix model)
        {
            this(image1, image2, pairs);
            this.transform = transform;
            this.model = model;
        }

        CustomPaintComponent(Image image1, Image image2, List<Pair> pairs)
        {
            this.image1 = image1;
            this.image2 = image2;
            pairList = pairs;
        }

        public void paint(Graphics g) {

            // Retrieve the graphics context; this object is used to paint shapes

            Graphics2D g2d = (Graphics2D) g;

            /**
             * Draw an Image object
             * The coordinate system of a graphics context is such that the origin is at the
             * northwest corner and x-axis increases toward the right while the y-axis increases
             * toward the bottom.
             */

            int x = 0;

            int y = 0;

            int heightOffset = image1.getHeight(this);

            g2d.drawImage(image1, x, y, this);
            g2d.drawImage(image2, x, y+heightOffset, this);

            for (Pair pair : pairList)
            {
                Point a = pair.getPointA();
                Point b = pair.getPointB();

                g2d.setColor(Color.cyan);
                g2d.drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY()+heightOffset);
            }

            if (transform!=null)
            {
                for (Pair pair : pairList)
                {
                    Point a = pair.getPointA();
                    Point b = transform.transform(a, model);

                    g2d.setColor(Color.yellow);
                    g2d.drawLine((int) a.getX(), (int) a.getY(), (int) b.getX(), (int) b.getY()+heightOffset);
                }
            }

        }

    }

}
