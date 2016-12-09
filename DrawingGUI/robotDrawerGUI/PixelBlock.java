package robotDrawerGUI;

import javax.swing.*;
import java.awt.*;


/**
 * Classes: RobotGUIDriver, RobotGUIFrame, RobotGUIPanels, PixelBlock
 *
 * Pixel Block: defines a single block for drawing on
 *
 * @author Patricia Sipes
 * @version 1.1
 */
public class PixelBlock {
    /**
     * the button include in the block representing the drawable "pixel square"
     */
    private JButton button;
    /**
     * the color of the block
     */
    private Color mycolor;
    /**
     * the column location of the block
     */
    private int column;
    /**
     * the row location of the block
     */
    private int row;
    /**
     * has the block already been drawn on?
     */
    private boolean drawnOn;


    /**
     * Parameterised constructor, creates a Pixel block at 16x16 of a specific color.
     * @param color the color to assign to the background
     */
    PixelBlock(Color color)
    {
        this.button = new JButton();
        this.button.setPreferredSize(new Dimension(16, 16));
        this.button.setBackground(color);
        this.mycolor = color;
        this.drawnOn = false;
    }

    /**
     * returns the row the pixel block is in
     * @return int, row of pixel block
     */
    protected int getRow() {return this.row;}

    /**
     * returns the column the pixel block is in
     * @return int, column the pixel block is in
     */
    protected int getColumn() {return this.column;}

    /**
     * sets the drawn on status to false (USED WITH RESET FUNCTION ONLY).
     */
    protected void setBlank(){this.drawnOn = false;}

    /**
     * Sets the drawn on status to true
     */
    protected void setDrawnOn(){this.drawnOn = true;}

    /**
     * returns the button belonging to this pixel block
     * @return JButton, button belonging to this pixel block
     */
    protected JButton getButton() {return this.button;}

    /**
     * returns the drawn on status of the block
     * @return boolean, has been drawn on
     */
    protected boolean getDrawnOn() {return this.drawnOn;}

    /**
     * sets the backround color of the button
     * @param color color to set background
     */
    protected void setColor(Color color)
    {
        this.mycolor = color;
        this.button.setBackground(mycolor);

    }

    /**
     * sets column to parameter
     * @param x int, what column should equal
     */
    protected void setColumn(int x)
    {
        this.column = x;
    }

    /**
     * sets row to parameter
     * @param y int, what row should equal
     */
    protected void setRow(int y)
    {
        this.row = y;
    }
}
