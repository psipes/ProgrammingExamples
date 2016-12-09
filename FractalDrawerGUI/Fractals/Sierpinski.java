package Fractals;

import java.awt.*;

/**
 * Draws the Sierpinski Sieve using recursion, Created by Patricia on 7/24/2016.
 * Classes: FractalDriver, FractalFrame, FractalPanels, KochFlake, CCurve, Sierpinski. Interface: FractalDraw.
 */
public class Sierpinski {

    /**
     * Constructor that builds fractal at desired level on desired surface using window height.
     * @param height height of drawable area
     * @param order level
     * @param pen draw here
     */
    Sierpinski(int height, int order, Graphics pen)
    {
        int triangleHeight = (int) Math.round(height * Math.sqrt(3.0) /2);
        int xOffset = 25;
        Point p1 = new Point(xOffset, triangleHeight);
        Point p2 = new Point(height/2 + xOffset, 0);
        Point p3 = new Point(height + xOffset, triangleHeight);

        drawSFractal(order, p1, p2, p3, pen);

    }

    /**
     * Unlike other fractals, does not implement due to being set up slightly different (it could implement...but it would be messier)
     * @param order level
     * @param p1 point (x, y) corresponding to points of an equilateral triangle
     * @param p2 point (x, y) corresponding to points of an equilateral triangle
     * @param p3 point (x, y) corresponding to points of an equilateral triangle
     * @param pen draw here
     */
    void drawSFractal( int order, Point p1, Point p2, Point p3, Graphics pen)
    {
        Point p4, p5, p6;
        if (order == 0)
        {
            Polygon mainTri = new Polygon();
            mainTri.addPoint(p1.x, p1.y);
            mainTri.addPoint(p2.x, p2.y);
            mainTri.addPoint(p3.x, p3.y);
            pen.fillPolygon(mainTri);
        } else
        {
            p4 = mid(p1, p2);
            p5 = mid(p2, p3);
            p6 = mid(p1, p3);

            drawSFractal(order - 1, p1, p4, p6, pen);
            drawSFractal(order - 1, p4, p2, p5, pen);
            drawSFractal(order - 1, p6, p5, p3, pen);
        }

    }

    /**
     * creates mid point point
     * @param p1 start point
     * @param p2 end point
     * @return return point
     */
    public static Point mid (Point p1, Point p2)
    {return new Point ((p1.x + p2.x)/2, (p1.y + p2.y)/2);}


}
