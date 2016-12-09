package Fractals;

import java.awt.*;

/**
 * Draws the Koch Snowflake using recursion, Created by Patricia on 7/24/2016.
 * Classes: FractalDriver, FractalFrame, FractalPanels, KochFlake, CCurve, Sierpinski. Interface: FractalDraw.
 */
public class KochFlake implements Fractaldraw {
    double sq = Math.sqrt(3.0) /6;

    /**
     * constructor with parameters
     * @param order level
     * @param x1 point on the triangle (x, y)
     * @param y1 point on the triangle (x, y)
     * @param x2 point on the triangle (x, y)
     * @param y2 point on the triangle (x, y)
     * @param x3 point on the triangle (x, y)
     * @param y3 point on the triangle (x, y)
     * @param pen draw on this
     */
    KochFlake(int order, int x1, int y1, int x2, int y2, int x3, int y3, Graphics pen)
    {
        drawFractal(order, x1, y1, x2, y2, pen);
        drawFractal(order, x2, y2, x3, y3, pen );
        drawFractal(order, x3, y3, x1, y1, pen);
    }

    /**
     * Interface from FractalDraw, all math done here
     * @param order level
     * @param startx starting x point
     * @param starty starting y point
     * @param endx ending x point
     * @param endy ending y point
     * @param pen draw here
     */
    @Override
    public void drawFractal(int order, int startx, int starty, int endx, int endy, Graphics pen) {
        {

            int x1 = startx;
            int x5 = endx;
            int y1 = starty;
            int y5 = endy;
            int deltax;
            int deltay;
            int x2, x3, x4, y2, y3, y4;
            if(order == 0)
                pen.drawLine(x1, y1, x5, y5);
            else
            {
                deltax = x5-x1;
                deltay = y5-y1;
                x2 = x1 + deltax / 3;
                y2 = y1 + deltay /3;
                x3 = (int) ((x1 + x5)/2 + sq * (y1-y5));
                y3 = (int) ((y1 + y5)/2 + sq * (x5 - x1));
                x4 = x1 + deltax * 2/3;
                y4 = y1 + deltay * 2/3;

                drawFractal(order -1, x1, y1, x2, y2, pen);
                drawFractal(order - 1, x2, y2, x3, y3, pen);
                drawFractal(order - 1, x3, y3, x4, y4, pen);
                drawFractal(order - 1, x4, y4, x5, y5, pen);
            }
        }
    }


}
