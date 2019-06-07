package com.cinek;

import org.ejml.simple.SimpleMatrix;

import java.util.List;

/**
 * Created by Cinek on 07.06.2019.
 */
public class PerspectiveTransform implements Transform {
    @Override
    public Point transform(Point point, SimpleMatrix H) {
        double[][] h =  {{point.getX()}, {point.getY()},{1}};
        double[][] aHelp = {{H.get(0), H.get(1), H.get(2)}, {H.get(3), H.get(4), H.get(5)},{H.get(6),H.get(7),1}};
        SimpleMatrix matrixA = new SimpleMatrix(h);
        SimpleMatrix resMatrix = new SimpleMatrix(aHelp).mult(matrixA);
        double tu = resMatrix.get(0,0);
        double tv = resMatrix.get(1,0);
        double t = resMatrix.get(2,0);
        return new Point(tu/t,tv/t);
    }


    public SimpleMatrix computeTransform(List<Point> points)
    {
        Point a1 = points.get(0);
        Point a2 = points.get(1);
        Point a3 = points.get(2);
        Point a4 = points.get(3);

        Point b1 = points.get(4);
        Point b2 = points.get(5);
        Point b3 = points.get(6);
        Point b4 = points.get(7);


        double x1 = a1.getX();
        double y1 = a1.getY();

        double x2 = a2.getX();
        double y2 = a2.getY();

        double x3 = a3.getX();
        double y3 = a3.getY();

        double x4 = a4.getX();
        double y4 = a4.getY();

        double u1 = b1.getX();
        double v1 = b1.getY();

        double u2 = b2.getX();
        double v2 = b2.getY();

        double u3 = b3.getX();
        double v3 = b3.getY();

        double u4 = b4.getX();
        double v4 = b4.getY();





        double[][] a = {
                {x1, y1, 1,0,0,0, -1*u1*x1, -1*u1*y1},
                {x2,y2, 1, 0,0,0, -1*u2*x2, -1*u2*y2 },
                {x3,y3, 1, 0,0,0, -1*u3*x3, -1*u3*y3},
                {x4,y4, 1, 0,0,0, -1*u4*x4, -1*u4*y4},
                {0,0,0,x1,y1,1, -1*v1*x1, -1*v1*y1},
                {0,0,0,x2,y2,1, -1*v2*x2, -1*v2*y2},
                {0,0,0,x3,y3,1, -1*v3*x3, -1*v3*y3},
                {0,0,0,x4,y4,1, -1*v4*x4, -1*v4*y4}};
        double[][] b = {{u1}, {u2},{u3},{u4},{v1},{v2},{v3},{v4}};


        SimpleMatrix matrixA = new SimpleMatrix(a);
        SimpleMatrix matrixB = new SimpleMatrix(b);


        SimpleMatrix ret = matrixA.invert().mult(matrixB);

        return ret;

    }

    @Override
    public int getNumSamples() {
        return 4;
    }
}
