package sorting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Classes: SortDriver, SortFrame, SortPanels, SelectionSort, InsertionSort, BubbleSort, QuickSort, MergeSort
 * Abstract Class: MasterSort
 * SortPanels: Sets up the panels/buttons/drawing surfaces and their logic
 * @author Patricia Sipes
 * @version 3.0
 */
public class SortPanels extends JPanel {
    /**
     * Combo box for use with the sort options
     */
    private JComboBox<String> sortOptions;
    /**
     * An array of strings for the sort options
     */
    private String[] sortTypes;
    /**
     * A String representation of the chosen sort type
     */
    private String chosenType;
    /**
     * Is the input valid
     */
    private boolean validInput;
    /**
     * Is the sort valid/been picked
     */
    private boolean validSort;
    /**
     * has sorting begun
     */
    private boolean beginSorting;
    /**
     * MasterSorter object. MasterSorter is an abstract class that parents the sort types
     */
    private MasterSort sorter;
    /**
     * is the animation finished?
     */
    private boolean finishedDrawing;
    /**
     * a stepper for animation
     */
    private int ndx;
    /**
     * The array of numbers.
     */
    private int[] sortableNumbers = new int[10];


    /**
     * Default constructor creates the panels and sets up the timer.
     */
    SortPanels()
    {
        //initializes major values.

        finishedDrawing = false;
        validInput = false;
        validSort = false;
        beginSorting = false;
        chosenType = "Select Sort Type";


        //Information for layout of Master panel (frame)
        BorderLayout bLayout = new BorderLayout();
        this.setLayout(bLayout);


        //Panel for top things
        JPanel interactable = new JPanel();
        interactable.setPreferredSize(new Dimension(800, 150));
        interactable.setBackground(Color.magenta);
        BorderLayout interactableLayout = new BorderLayout();
        interactable.setLayout(interactableLayout);

        //Adds the interactable area to the master panel at the top
        this.add(interactable, bLayout.NORTH);


        //theirNumbers, instructions, and sortOptions are all a part of interactable.
        //text box for user information
        JTextField theirNumbers = new JTextField("Enter Numbers Here");
        theirNumbers.addKeyListener(new KeyListen());


        //Instructions label
        //Labels can use html to edit how it looks. This is the only way to get multiple lines on a label.
        JLabel instructions = new JLabel("<html>Welcome to Sorting Headquarters. <br> " +
                "1. Please enter 10 positive integers separated by spaces and press enter/return.<br>" +
                "2. Select the sorting type you wish to view in the dropdown menu.<br>" +
                "3. Press the \"SORT\" button to start the animation.<br>" +
                "Enjoy.");
        //Size specifications for the instructions
        instructions.setPreferredSize(new Dimension(600, 100));


        //Setting up the dropdown menu
        sortTypes = new String[]{"Select Sort Type", "Insertion Sort", "Selection Sort", "Bubble Sort", "Merge Sort", "Quick Sort"};

        sortOptions = new JComboBox<>(sortTypes);
        sortOptions.addItemListener(new TypeListener());

        //Add the pieces to interactable.
        interactable.add(instructions, interactableLayout.NORTH);
        interactable.add(theirNumbers, interactableLayout.CENTER);
        interactable.add(sortOptions, interactableLayout.SOUTH);



        //sort button sits at the bottom
        JButton sortButton = new JButton("SORT");
        sortButton.addActionListener(new ButtonListener());
        this.add(sortButton, bLayout.SOUTH);



        //This creates a timer that blips every half a second.
        //The animation listener is called after the 500 seconds.

        Timer timer = new Timer(500, new AnimationListener());
        timer.start();
    }


    /**
     * This is how everything is drawn
     * @param pen the Graphics component to be used
     */
    public void paintComponent(Graphics pen)
    {
        //IMPORTANT. DO NOT REMOVE
        super.paintComponent(pen);

        //If sorting hasn't begun, draw the waiting circles
        if (!beginSorting) {
            paintStartingCircles(pen);
            repaint();
        }
        //If the animation is still going
        else if (!finishedDrawing)
        {
            drawIt(pen);
        }

        //If the animation is finished
        else if (finishedDrawing)
        {
            drawFinished(sorter.booleanChange.size()-1, pen);
        }

    }


    /**
     * Sets up and draws the circles and numbers waiting to be animated.
     * If the numbers aren't valid, it prints out a message to the user to do it right.
     * @param g the graphics component of the parent
     */
    private void paintStartingCircles(Graphics g)
    {

        Color chosenColor;
        if (validInput) {
            switch (chosenType) {
                case "Select Sort Type":

                    g.setColor(Color.black);
                    chosenColor = Color.BLACK;
                    g.drawString("Please Select Sort Option Above.", 300, 170);
                    break;
                default:
                    g.setColor(Color.RED);
                    chosenColor = Color.RED;
                    break;

            }

            for (int i = 0; i < 10; i++) {
                g.fillOval(80 * i, 200, 75, 75);
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(sortableNumbers[i]), (80 * i) + 20, 230);
                g.setColor(chosenColor);
            }
        }
        else
            g.drawString("Please Enter 10 Positive Integers separated by spaces above and then press ENTER.", 0, 200);
        repaint();

    }


    /**
     * Checks that the input string is actually numbers and puts them into an array to sort
     * @param input String of user input (from textfield)
     * @return boolean is input correct?
     */
    private boolean checkInput(String input)
    {
        String [] splitter;
        //check if the first character is a letter and spit it out
        if(Character.isAlphabetic(input.charAt(0)))
            return false;
        //if not, split it up into pieces
        else
        {
            splitter = input.split(" ");
            for (String split: splitter)
            {
                for (int i = 0; i < split.length(); i++)
                {
                    //if the pieces are all digits, don't do anything
                    if(Character.isDigit(split.charAt(i)))
                    {
                        //do nothing
                    }
                    //if it's something other than a number, spit it out
                    else
                        return false;
                }
            }

            //if the split array has a length greater than 10, it's too long. spit it out.
            if (splitter.length !=10)
                return false;
        }


        //if it passes all the tests, parse the string bits to an integer array
        for (int i = 0; i < sortableNumbers.length; i++)
        {
            sortableNumbers[i] = Integer.parseInt(splitter[i]);
        }
        return true;
    }


    /**
     * Draws the FINISHED version of the circles/array
     * @param indx the last index of the array
     * @param pen the graphics component of the parent
     */
    private void drawFinished(int indx, Graphics pen)
    {
        for (int i = 0; i < 10; i++) {

            pen.setColor(Color.GREEN);



            pen.fillOval(80 * i, 200, 75, 75);
            pen.setColor(Color.BLACK);
            pen.drawString(Integer.toString(sorter.steps.get(indx)[i]), (80 * i) + 20, 230);
        }
    }


    /**
     * draws the arrays that have been stored
     * @param pen the graphics components
     */
    private void drawIt(Graphics pen)
    {
        int indx = this.ndx;
        if (indx > sorter.steps.size() - 1)
        {
            finishedDrawing = true;
            this.ndx = 0;
        }
        else
        {
            for (int i = 0; i < 10; i++) {

                if(sorter.booleanChange.get(indx)[i])
                    pen.setColor(Color.ORANGE);
                else
                    pen.setColor(Color.RED);


                pen.fillOval(80 * i, 200, 75, 75);
                pen.setColor(Color.BLACK);
                pen.drawString(Integer.toString(sorter.steps.get(indx)[i]), (80 * i) + 20, 230);
            }
            ndx ++;
        }
    }

    /**
     * resets the drawing canvas
     */
    private void reset()
    {
        sorter = null;
        beginSorting = false;
        finishedDrawing = false;
    }

    /**
     * Private internal class, anonymously sets up Item Listener.
     */
    private class TypeListener implements ItemListener
    {

        /**
         * Checks if a state has been changed on the dropdown box
         * @param e the event
         */

        @Override
        public void itemStateChanged(ItemEvent e) {
            //Item State Changed fires twice, one for selected, one for deselected
            //This if statement ensures that we are just checking the selection statement.
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if(beginSorting)
                {
                    reset();
                }
                chosenType =(sortOptions.getSelectedItem().toString());
                validSort = !chosenType.equals(sortTypes[0]);

                switch (chosenType)
                {
                    case "Selection Sort":
                        sorter = new SelectionSort(sortableNumbers);
                        break;
                    case "Insertion Sort":
                        sorter = new InsertionSort(sortableNumbers);
                        break;
                    case "Bubble Sort":
                        sorter = new BubbleSort(sortableNumbers);
                        break;
                    case "Merge Sort":
                        sorter = new MergeSort(sortableNumbers);
                        break;
                    case "Quick Sort":
                        sorter = new QuickSort(sortableNumbers);
                        break;
                    default:
                        sorter = new SelectionSort(sortableNumbers);
                }
            }
        }
    }

    /**
     * private internal class. Attached to the enter key on the text field
     */
    private class KeyListen implements KeyListener
    {
        /**
         * not used
         * @param e the key event
         */
        @Override
        public void keyTyped(KeyEvent e) {
            //Nothing to see here
        }

        /**
         * checks  if the enter key has been pressed and asks for number validation. repaints as appropriate.
         * @param e the key event
         */
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                JTextField textField = (JTextField) e.getSource();
                String text = textField.getText();
                validInput = (checkInput(text));
                repaint();
            }
        }

        /**
         * not used
         * @param e the key event
         */
        @Override
        public void keyReleased(KeyEvent e) {
            //nothing to see here.
        }
    }


    /**
     * Private internal class attached to sort button
     */
    private class ButtonListener implements ActionListener
    {
        /**
         * Starts the sorting on click
         * @param e the click
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (validInput && validSort) {
                sorter.sortMethod();
                beginSorting = true;
            }
        }
    }


    /**
     * Private internal class for animation
     */
    private class AnimationListener implements ActionListener
    {
        /**
         * Attached to a timer, this repaints every half second.
         * @param e half second timer reset.
         */

        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }
}
