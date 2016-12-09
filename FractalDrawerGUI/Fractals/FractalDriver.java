package Fractals;

/**
 * Basic driver, Created by Patricia on 7/24/2016.
 * Classes: FractalDriver, FractalFrame, FractalPanels, KochFlake, CCurve, Sierpinski. Interface: FractalDraw.
 */
public class FractalDriver {
    public static void main (String [] args)
    {
        FractalFrame drawIt = new FractalFrame();
        drawIt.display();
    }
}
