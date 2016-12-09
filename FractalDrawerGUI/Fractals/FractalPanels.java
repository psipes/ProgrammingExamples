package Fractals;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Draws the panels and components and sets up the listeners, Created by Patricia on 7/24/2016.
 * Classes: FractalDriver, FractalFrame, FractalPanels, KochFlake, CCurve, Sierpinski. Interface: FractalDraw.
 */
public class FractalPanels extends JPanel {



    JPanel userOptions = new JPanel(); //panel to hold all the interactible bits

    //Combo box to choose the type of fractal used
    JPanel fractalOptionPanel = new JPanel();
    JLabel fractalOptionLabel = new JLabel("Fractal Type");
    String [] fractalWords = {"Choose Fractal", "Koch's Snowflake", "L\u00E9vy (C-Curve)", "Sierpinski's Triange"};
    JComboBox<String> fractalOption = new JComboBox <>( fractalWords );





    //Slider for the background color
    //Slider was chosen to simplify things, there are 10 color option to slide through (most in a rainbow fashion)
    JPanel bgColorPanel = new JPanel();
    JLabel bgColorLabel = new JLabel("Background Color");
    JSlider bgColor = new JSlider(0, 10);




    //Slider for the fractal color
    //Slider was chosen to simplify things, there are 10 color option to slide through (most in a rainbow fashion)
    JPanel fractalColorPanel = new JPanel();
    JLabel fractalColorLabel = new JLabel("Fractal Color");
    JSlider fractalColor = new JSlider(0, 10);


    //the increase and decrease buttons to step through the levels of the fractal available
    JPanel buttonHolder = new JPanel();
    JLabel stepButtonsLabel = new JLabel("Step: 1");
    JButton decStep = new JButton("-");
    JButton incStep = new JButton("+");


    //starting points for snowflake
    final int cx1 = 325;
    final int cy1 = 25;
    final int cx2 = 50;
    final int cy2 = 400;
    final int cx3 = 575;
    final int cy3 = 400;

    //starting points for c-curve
    final int curvex1 = 165;
    final int curvex2 =  490;
    final int curvey1 = 150;
    final int curvey2 = curvey1;


    //max sets for individual types
    final int snowflakeMax = 8;
    final int cMax = 15;
    final int sMax = 7;
    //default max for the sake of having something
    int max = 5;



    //level/order counter
    int current = 0;
    //pen Color to be changed with slider
    Color penCol = Color.black;


    /**
     * Basic constructor that lays out everything
     */
    FractalPanels()
    {
        setBackground(Color.pink);
        //Layouts to be used, Overall GridBag
        GridBagLayout gridBag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        GridLayout userOptLayout = new GridLayout(8, 1);
        GridLayout doubles = new GridLayout(2,1);


        setLayout(gridBag);

        c.fill = GridBagConstraints.HORIZONTAL;


        userOptions.setLayout(userOptLayout);


        //Adding the drop down menu
        fractalOption.addItemListener(new ChangeList());
        fractalOptionPanel.setLayout(doubles);
        fractalOptionPanel.add(fractalOptionLabel);
        fractalOptionPanel.add(fractalOption);
        userOptions.add(fractalOptionPanel);


        //adding the bg color slider
        bgColor.addChangeListener(new BgChanger());
        bgColorPanel.setLayout(doubles);
        bgColorPanel.add(bgColorLabel);
        bgColorPanel.add(bgColor);
        userOptions.add(bgColorPanel);


        //adding the fractal color slider
        fractalColor.addChangeListener(new FractChanger());
        fractalColorPanel.setLayout(doubles);
        fractalColorPanel.add(fractalColorLabel);
        fractalColorPanel.add(fractalColor);
        userOptions.add(fractalColorPanel);



        //adding the inc/dec buttons
        incStep.addActionListener(new IncListen());
        decStep.addActionListener(new DecListen());
        userOptions.add(stepButtonsLabel);
        buttonHolder.add(decStep);
        buttonHolder.add(incStep);
        userOptions.add(buttonHolder);




        //setting up userOption panels Grid Bag settings
        c.ipady = 540;
        c.ipadx = 0;
        c.insets = new Insets(0, 650, 0, 0);
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 2;
        gridBag.setConstraints(userOptions, c);
        add(userOptions);

    }


    /**
     * Painting component checks index and draws items
     * @param pen Graphics/page
     */
    public void paintComponent(Graphics pen)
    {
        super.paintComponent(pen);
        pen.setColor(penCol);

        if (fractalOption.getSelectedIndex() == 1) {
            repaint();
            new KochFlake(current, cx1, cy1, cx2, cy2, cx3, cy3, pen);
            max = snowflakeMax;
        }
        if (fractalOption.getSelectedIndex() == 2) {
            repaint();
            new CCurve(current, curvex1, curvey1, curvex2, curvey2, pen);
            max = cMax;

        }

        if (fractalOption.getSelectedIndex() == 3) {
            repaint();
            new Sierpinski(getHeight(), current, pen);
            max = sMax;
        }




    }

//INTERFACES

    private class IncListen implements ActionListener
    {

        /**
         * Listens for button to be interacted with
         * @param e interaction
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            if (current < max)
                current++;

            stepButtonsLabel.setText("Step: " + (current + 1));
            repaint();

        }
    }

    private class DecListen implements ActionListener
    {

        /**
         * Listens for button to be interacted with
         * @param e interaction
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (current > 0)
            {
                current--;
            }

            stepButtonsLabel.setText("Step: " + (current + 1));
            repaint();
        }
    }

    private class BgChanger implements ChangeListener
    {
        /**
         * check if bg slider has changed
         * @param e item has changed
         */
        @Override
        public void stateChanged(ChangeEvent e) {

            int val = bgColor.getValue();
            switch(val) {
                case 0:
                    setBackground(Color.black);
                    break;
                case 1:
                    setBackground(new Color(164, 66, 220));
                    break;
                case 2:
                    setBackground(new Color(75, 0, 130));
                    break;
                case 3:
                    setBackground(Color.blue);
                    break;
                case 4:
                    setBackground(Color.green);
                    break;
                case 5:
                    setBackground(new Color(255, 255, 0));
                    break;
                case 6:
                    setBackground(new Color(255, 165, 0));
                    break;
                case 7:
                    setBackground(Color.red);
                    break;
                case 8:
                    setBackground(Color.pink);
                    break;
                case 9:
                    setBackground(Color.cyan);
                    break;
                case 10:
                    setBackground(Color.white);
                    break;
            }
            repaint();


        }
    }


    private class FractChanger implements ChangeListener
    {

        /**
         * if fractal slider has changed
         * @param e has changed
         */
        @Override
        public void stateChanged(ChangeEvent e) {


            int val = fractalColor.getValue();
            switch(val) {
                case 0:
                    penCol = (Color.black);
                    break;
                case 1:
                    penCol = (new Color(164, 66, 220));
                    break;
                case 2:
                    penCol = (new Color(75, 0, 130));
                    break;
                case 3:
                    penCol = (Color.blue);
                    break;
                case 4:
                    penCol = (Color.green);
                    break;
                case 5:
                    penCol = (new Color(255, 255, 0));
                    break;
                case 6:
                    penCol = (new Color(255, 165, 0));
                    break;
                case 7:
                    penCol = (Color.red);
                    break;
                case 8:
                    penCol = (Color.pink);
                    break;
                case 9:
                    penCol = (Color.cyan);
                    break;
                case 10:
                    penCol = (Color.white);
                    break;
            }
            repaint();


        }
    }

    private class ChangeList implements ItemListener
    {

        /**
         * checks if dropdown has changed
         * @param e change
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            current = 0;
            stepButtonsLabel.setText("Step: " + (current + 1));
            repaint();
        }
    }


}
