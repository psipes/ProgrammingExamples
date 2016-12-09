package sorting;

import javax.swing.*;
import java.awt.*;

/**
 * Classes: SortDriver, SortFrame, SortPanels, SelectionSort, InsertionSort, BubbleSort, QuickSort, MergeSort
 * Abstract Class: MasterSort
 * SortFrame: The JFrame implementation
 * @author Patricia Sipes
 * @version 1.0
 */
public class SortFrame extends JFrame {

    /**
     * A JFrame object to represent our frame
     */
    JFrame frame = new JFrame();

    /**
     * basic constructor sets the mostly non interactible parts of the frame
     */
    SortFrame()
    {
        frame = new JFrame("Group 1 Sorting");
        frame.setPreferredSize(new Dimension(800, 400));
        frame.add(new SortPanels());
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
