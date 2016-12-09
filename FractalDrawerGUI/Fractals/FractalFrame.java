package Fractals;

import javax.swing.*;
import java.awt.*;

/**
 * Sets up the frame and calls the panel object, Created by Patricia on 7/24/2016.
 * Classes: FractalDriver, FractalFrame, FractalPanels, KochFlake, CCurve, Sierpinski. Interface: FractalDraw.
 */
public class FractalFrame extends JFrame {
    JFrame frame = new JFrame();

    /**
     * basic constructor sets the mostly non interactible parts of the frame
     */
    FractalFrame()
    {
        frame = new JFrame("PSipes Fractal Draw");
        frame.setPreferredSize(new Dimension(800, 600));
        frame.add(new FractalPanels());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * sets the displayable aspects and fixed aspect
     */
    public void display()
    {
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
