package GameChangerGUI;

import javax.swing.*;
import java.awt.*;

/**
 * Classes: Driver, Frame, Panels, TicTacToePanel, WarPanel, Deck, Card
 * Interfaces: DeckInterface
 *
 * Frame: Sets up the frame and all its parameters
 *
 * @author Patricia Sipes
 * @version 1.1
 */
public class Frame extends JFrame {
    /**
     * the one frame to rule them all.
     */
    JFrame theframe;

    /**
     * Default constructor sets up the basis for the frame
     */
    Frame()
    {
        theframe = new JFrame("SIPES: GameChanger");
        theframe.setPreferredSize(new Dimension(600, 500));
        theframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theframe.add(new Panels());
    }

    /**
     * sets up the display aspects of the frame
     */
    void displayit()
    {
        theframe.pack();
        theframe.setVisible(true);
        theframe.setResizable(false);
    }
}
