package com.cinek;
import org.ejml.simple.SimpleMatrix;



/**
 * Created by Cinek on 29.05.2019.
 */
public class AffineTransform {

    private SimpleMatrix A;

    public AffineTransform(Point a1, Point a2, Point a3, Point b1, Point b2, Point b3)
    {
        A = computeTransform(a1,a2,a3,b1,b2,b3);
    }

    public static void main(String[]  args )
    {
        AffineTransform affineTransform = new AffineTransform(new Point(1,2), new Point(2.4,3), new Point(3,4),
                new Point(5,2), new Point(7,4), new Point(4,2));
    }

    public Point transform(Point a)
    {
        double[][] h =  {{a.getX()}, {a.getY()},{1}};
        SimpleMatrix matrixA = new SimpleMatrix(h);
        SimpleMatrix resMatrix = A.mult(matrixA);
        double u = resMatrix.get(0,0);
        double v = resMatrix.get(1,0);
        return new Point(u,v);
    }

    private SimpleMatrix computeTransform(Point a1, Point a2, Point a3,  Point b1, Point b2, Point b3)
    {
        double x1 = a1.getX();
        double y1 = a1.getY();

        double x2 = a2.getX();
        double y2 = a2.getY();

        double x3 = a3.getX();
        double y3 = a3.getY();

        double u1 = b1.getX();
        double v1 = b1.getY();

        double u2 = b2.getX();
        double v2 = b2.getY();

        double u3 = b3.getX();
        double v3 = b3.getY();


        double[][] a = {{x1, y1, 1,0,0,0}, {x2,y2, 1, 0,0,0 }, {x3,y3, 1, 0,0,0}, {0,0,0,x1,y1,1}, {0,0,0,x2,y2,1}, {0,0,0,x3,y3,1}};
        double[][] b = {{u1}, {u2},{u3},{v1},{v2},{v3}};


        SimpleMatrix matrixA = new SimpleMatrix(a);
        SimpleMatrix matrixB = new SimpleMatrix(b);


        SimpleMatrix ret = matrixA.invert().mult(matrixB);

        return ret;

    }
}
