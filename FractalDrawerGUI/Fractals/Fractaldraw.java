package Fractals;

import java.awt.*;

/**
 * Interface created for equation holding, Created by Patricia on 7/24/2016.
 * Classes: FractalDriver, FractalFrame, FractalPanels, KochFlake, CCurve, Sierpinski. Interface: FractalDraw.
 */
public interface Fractaldraw {
    /**
     * draw a fractal at x level using graphics pen with a start and end point defined
     * @param order level
     * @param startx start point x
     * @param starty start point y
     * @param endx   end point x
     * @param endy   end point y
     * @param pen    draw here
     */
    void drawFractal (int order, int startx, int starty, int endx, int endy, Graphics pen);

}
