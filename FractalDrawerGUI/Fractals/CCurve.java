package Fractals;

import java.awt.*;

/**
 * Draws the  C-Curve fractal using recursion, Created by Patricia on 7/24/2016.
 * Classes: FractalDriver, FractalFrame, FractalPanels, KochFlake, CCurve, Sierpinski. Interface: FractalDraw.
 */
public class CCurve implements Fractaldraw {
    /**
     *  draws a C-Curve at specified order using recursion
     * @param order level
     * @param x1 start point of initial line
     * @param y1 start point of initial line
     * @param x2 end point of initial line
     * @param y2 end point of initial line
     * @param pen draw here
     */
    CCurve(int order, int x1, int y1, int x2, int y2, Graphics pen)
    {
        drawFractal(order, x1, y1, x2, y2, pen);

    }


    /**
     * Implemented method to define how to draw the fractal
     * @param order level
     * @param startx start x point
     * @param starty start y point
     * @param endx   end x point
     * @param endy   end y point
     * @param pen    draw here
     */
    @Override
    public void drawFractal(int order, int startx, int starty, int endx, int endy, Graphics pen) {
        int midx, midy;

        if (order == 0)
            pen.drawLine(startx, starty, endx, endy);
        else
        {
            midx = (startx + endx) /2 + (starty - endy)/2;
            midy = (endx - startx)/2 + (starty + endy)/2;
            drawFractal(order - 1, startx, starty, midx, midy, pen);
            drawFractal(order - 1, midx, midy, endx, endy, pen);

        }
    }
}
