package robotDrawerGUI;

import javax.swing.*;
import java.awt.*;

/**
 * Classes: RobotGUIDriver, RobotGUIFrame, RobotGUIPanels, PixelBlock
 *
 * Robot GUI Frame: Sets up the frame for show and adds the master panel.
 *
 * @author Patricia Sipes
 * @version 1.1
 */
public class RobotGUIFrame extends JFrame {

    /**
     * the frame
     */
    private JFrame frame;

    /**
     * Default constructor sets the frame up and adds panels
     */
    RobotGUIFrame()
    {
        frame = new JFrame("Robotic Drawing Simulator");
        frame.setPreferredSize(new Dimension(700, 532));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new RobotGUIPanels());



    }

    /**
     * sets the display items for the frame.
     */
    protected void display()
    {
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
    }
}
