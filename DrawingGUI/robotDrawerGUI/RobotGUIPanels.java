package robotDrawerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Classes: RobotGUIDriver, RobotGUIFrame, RobotGUIPanels, PixelBlock
 *
 * Robot GUI Panels: draws everything and logic for movement
 *
 * @author Patricia Sipes
 * @version 1.1
 */
public class RobotGUIPanels extends JPanel {

    /**
     * button for moving up
     */
    private JButton upButton;
    /**
     * button for moving down
     */
    private JButton downButton;
    /**
     * button for moving left
     */
    private JButton leftButton;
    /**
     * button for moving right
     */
    private JButton rightButton;


    /**
     * radio button for pen up
     */
    private JRadioButton penUp;
    /**
     * radio button for pen down
     */
    private JRadioButton penDown;


    /**
     * Color for when line is drawn
     */
    private Color lineColor;
    /**
     * Color for when square is blank
     */
    private Color blankColor;
    /**
     * Color to signify robot
     */
    private Color robotRed;


    /**
     * boolean is the pen down?
     */
    private boolean isPenDown;
    /**
     * block representing the robot
     */
    private PixelBlock robotBlock;

    /**
     * How many tall is the board
     */
    private final static int ROW_MAX = 30;
    /**
     * How many wide is the board
     */
    private final static int COL_MAX = 30;


    /**
     * the blocks to make up the board
     */
    private PixelBlock [][] blocks;


    /**
     * Default constructor sets up all panels and drawing area
     */
    RobotGUIPanels()
    {
        lineColor = Color.BLACK;
        blankColor = Color.lightGray;
        robotRed = Color.red;
        isPenDown = false;

        setBackground(Color.BLACK);
        add(setUpInstructionArea());
        add(setUpPlayArea());
    }


    /**
     * Sets up the 2D array of buttons
     * @param area The panel to put them on
     */
    private void setUpBlockButtons(JPanel area)
    {
        blocks = new PixelBlock[ROW_MAX][COL_MAX];
        for (int i = 0; i < ROW_MAX; i++)
        {
            for (int j = 0; j < COL_MAX; j++) {

                PixelBlock thisBlock = new PixelBlock(blankColor);
                area.add(thisBlock.getButton());
                thisBlock.getButton().setEnabled(false); //makes it so the buttons can't be interacted with.
                thisBlock.setColumn(j);
                thisBlock.setRow(i);
                blocks[i][j] = thisBlock;

            }
        }

    }

    /**
     * sets up the robot block. CAN ONLY BE CALLED AFTER THE ARRAY IS POPULATED.
     */
    private void setUpRobot()
    {

        robotBlock = blocks[ROW_MAX/2 -1][COL_MAX/2 -1];
        robotBlock.setColor(robotRed);
    }


    /**
     * sets up the play area by setting up the layout and adding the blocks to it.
     * @return the set up playArea panel to be added to the main panel
     */
    private JPanel setUpPlayArea()
    {
        JPanel playArea = new JPanel();

        GridLayout gridded = new GridLayout(30, 30);
        playArea.setLayout(gridded);
        setUpBlockButtons(playArea);
        setUpRobot();

        return playArea;

    }




    /**
     * Sets up the instruction area
     * @return the set up instructionArea panel for inclusion in main panel.
     */
    private JPanel setUpInstructionArea()
    {

        upButton = new JButton("^");
        downButton = new JButton("v");
        leftButton = new JButton("<");
        rightButton = new JButton(">");


        penUp = new JRadioButton("Pen UP");
        penDown = new JRadioButton("Pen DOWN");
        penUp.setBackground(Color.GRAY);
        penDown.setBackground(Color.GRAY);
        ButtonGroup group = new ButtonGroup();

        JButton reset = new JButton("Reset Canvas");




        upButton.addActionListener(new MovementListener());
        downButton.addActionListener(new MovementListener());
        leftButton.addActionListener(new MovementListener());
        rightButton.addActionListener(new MovementListener());
        JPanel instructionArea = new JPanel();
        instructionArea.setPreferredSize(new Dimension(200, 500));
        instructionArea.setBackground(Color.GRAY);

        JLabel instructions = new JLabel ("<html><br><br><br>" +
                                          "+Use the arrows to move your <br> robot around the board.<br> " +
                                          "+Robot is the red square.<br>" +
                                          "+You can choose to have: <br>" +
                                          "-----1. The pen up and not draw <br> " +
                                          "-----2. The pen down and draw <br>" +
                                          "+ Pen defaults to up." +
                                          "<br><br><br><br><br><br><br><br><br><br><br><br></html>");

        GridLayout grid = new GridLayout(3, 3);
        JPanel buttons = new JPanel();
        buttons.setLayout(grid);
        buttons.add(new JLabel());
        buttons.add(upButton);
        buttons.add(new JLabel());
        buttons.add(leftButton);
        buttons.add(new JLabel());
        buttons.add(rightButton);
        buttons.add(new JLabel());
        buttons.add(downButton);
        buttons.add(new JLabel());


        instructionArea.add(instructions);
        instructionArea.add(buttons);


        penDown.addActionListener(new RadioListener());
        penUp.addActionListener(new RadioListener());
        group.add(penDown);
        group.add(penUp);


        reset.addActionListener(new ResetListener());
        instructionArea.add(penDown);
        instructionArea.add(penUp);
        instructionArea.add(reset);

        return instructionArea;
    }


    /**
     * Sets up robot movement for vertical movement
     * @param goUp boolean, if goUp is true, up button pressed, if false, down button
     */
    private void goVert(boolean goUp)
    {
        if (isPenDown)
        {
            robotBlock.setDrawnOn();
            robotBlock.setColor(lineColor);
        }
        else
        {
            if(!robotBlock.getDrawnOn())
                robotBlock.setColor(blankColor);
            else
                robotBlock.setColor(lineColor);

        }

        if (goUp)
        {
            if(robotBlock.getRow()-1 < 0)
                robotBlock = blocks[ROW_MAX - 1][robotBlock.getColumn()];
            else
                robotBlock = blocks[robotBlock.getRow()-1][robotBlock.getColumn()];

        } else {
            if (robotBlock.getRow() + 1 > (ROW_MAX - 1))
                robotBlock = blocks[0][robotBlock.getColumn()];
            else
                robotBlock = blocks[robotBlock.getRow() + 1][robotBlock.getColumn()];
        }

        robotBlock.setColor(robotRed);


    }


    /**
     * Sets up Movement on the Horizontal axis
     * @param goLeft if goLeft is true, movement is left bound, false, movement is right bound
     */
    private void goHor (boolean goLeft)
    {
        if (isPenDown)
        {
            robotBlock.setDrawnOn();
            robotBlock.setColor(lineColor);
        }
        else
        {
            if(!robotBlock.getDrawnOn())
                robotBlock.setColor(blankColor);
            else
                robotBlock.setColor(lineColor);

        }

        if (goLeft)
        {
            if(robotBlock.getColumn()-1 < 0)
                robotBlock = blocks[robotBlock.getRow()][COL_MAX - 1];
            else
                robotBlock = blocks[robotBlock.getRow()][robotBlock.getColumn()-1];

        } else {
            if (robotBlock.getColumn() + 1 > (COL_MAX -1))
                robotBlock = blocks[robotBlock.getRow()][0];
            else
                robotBlock = blocks[robotBlock.getRow()][robotBlock.getColumn() + 1];
        }

        robotBlock.setColor(robotRed);


    }

    /**
     * Resets all blocks to blanks and replaces the robot
     */
    private void resetBlocks()
    {

        for (int i = 0; i < ROW_MAX; i++)
        {
            for (int j = 0; j < COL_MAX; j++) {
                blocks[i][j].setColor(blankColor);
                blocks[i][j].setBlank();
            }
        }


        setUpRobot();

    }


    /**
     * Internal class for ActionListener to be applied for movement buttons
     */
    private class MovementListener implements ActionListener
    {

        /**
         * Checks if a directional button has been pressed and calls function for movement
         * @param e the event (button click)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton)e.getSource();
            if(button.getText().equals(upButton.getText()))
            {
                goVert(true);
            }
            else if(button.getText().equals(downButton.getText())) {
                goVert(false);
            }
            else if(button.getText().equals(leftButton.getText()))
            {
                goHor(true);
            }
            else if(button.getText().equals(rightButton.getText()))
            {
                goHor(false);
            }
        }
    }


    /**
     * Internal class for ActionListener to be applied to pen radiobuttons
     */
    private class RadioListener implements ActionListener
    {
        /**
         * Checks if Radiobutton has been clicked and changes status of isPenDown
         * @param e event(Button Click)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton button = (JRadioButton)e.getSource();

            if (button.getText().equals(penDown.getText()))
            {
                isPenDown = true;
            }
            if (button.getText().equals(penUp.getText()))
            {
                isPenDown = false;
            }

        }
    }

    /**
     * Internal class for ActionListener to be applied to resetButton
     */
    private class ResetListener implements ActionListener
    {

        /**
         * Checks if Reset Button has been pressed and calls reset method
         * @param e event (button click)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            resetBlocks();
        }
    }
}
