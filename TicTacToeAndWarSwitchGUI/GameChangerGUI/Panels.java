package GameChangerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Classes: Driver, Frame, Panels, TicTacToePanel, WarPanel, Deck, Card
 * Interfaces: DeckInterface
 *
 * Panels: The master drawing surface on which all other panels are applied (what the game is played on)
 *
 * @author Patricia Sipes
 * @version 1.1
 */
public class Panels extends JPanel {
    /**
     * String representation of the dropdown choice
     */
    private String currentGame;
    /**
     * Panel where the playables are switched out
     */
    private JPanel games;
    /**
     * Area that doesn't have any new setup between changes (instructions change)
     */
    private JPanel staticArea;
    /**
     * instructions for individual games
     */
    private JLabel instructions;
    /**
     * drop down box for choices of games
     */
    private JComboBox<String> choices;
    /**
     * Tictactoe drop down choice
     */
    private final static String TICTACTOE_GAME = "Play TicTacToe";
    /**
     * War drop down choice
     */
    private final static String WAR_GAME = "Play War";
    /**
     * Default drop down choice
     */
    private final static String CHOOSE_GAME = "Choose a Game";
    /**
     * drop down choices as an array
     */
    private String [] boxChoices = {CHOOSE_GAME, TICTACTOE_GAME, WAR_GAME};
    /**
     * Panel representing tictactoe
     */
    private TicTacToePanel toepane;
    /**
     * panel representing war
     */
    private WarPanel warpane;


    /**
     * Default constructor, sets up the basics including the CARD LAYOUT (which handles the switching of games)
     */
    Panels()
    {
        JPanel startArea = new JPanel();
        JLabel intro = new JLabel("<html>Hello and Welcome to War and Tic Tac Toe.<br>" +
                "Please select the game you wish to play<br>" +
                "From the drop down menu.<br>" +
                "Games can be switched between mid game <br>" +
                "without losing progress.<br>"+
                "Use the restart button on the game you wish to <br>" +
                "reset to start again.");
        startArea.add(intro);

        BoxLayout blay = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(blay);



        choices = new JComboBox<>(boxChoices);
        choices.addItemListener(new DropDownListener());
        instructions = new JLabel("");
        JPanel restartPanel = new JPanel();
        JButton restart = new JButton("RESTART");
        restart.addActionListener(new ResetListener());

        restartPanel.add(restart);
        staticArea = new JPanel();
        BorderLayout staticLayout = new BorderLayout();
        staticArea.setLayout(staticLayout);

        staticArea.add(instructions, staticLayout.NORTH);
        staticArea.add(choices, staticLayout.CENTER);
        staticArea.add(restartPanel, staticLayout.SOUTH);

        add(staticArea);




        games = new JPanel();
        add(games);

        warpane = new WarPanel();
        toepane = new TicTacToePanel();



        CardLayout carded = new CardLayout();
        games.setLayout(carded);
        games.add(startArea, CHOOSE_GAME);
        games.add(toepane, TICTACTOE_GAME);
        games.add(warpane, WAR_GAME);

    }

    /**
     * Prints the instructions in the static area based on what game has been called
     * @param forCards are the instructions for cards?
     */
    private void printInstructions(boolean forCards)
    {
        if(currentGame.equalsIgnoreCase(boxChoices[0]))
            instructions.setText("");
        else {
            if (forCards) {
                instructions.setText("<html> <center>Welcome to War." +
                        "<br> Two players flip cards, the higher card wins." +
                        "<br>The game ends when one player has all the games. " +
                        "<br>In case of a tie, cardss are laid down until one is higher. " +
                        "<br> HOUSE RULES: ACES ARE LOW.</html>");
            } else {
                instructions.setText("<html><center>Welcome to Tic-Tac-Toe." +
                        "<br>Two players take turns clicking the buttons" +
                        "<br> and trying to get three in a row.</html>");
            }
        }
    }

    /**
     * Resets the current playing game (DOES NOT RESET BOTH GAMES)
     */
    private void resetGame()
    {
        if (currentGame.equalsIgnoreCase(boxChoices[2]))
        {
           warpane.resetGame();
        }
        else if (currentGame.equalsIgnoreCase(boxChoices[1] ))
        {
            toepane.resetGame();
        }
    }


    /**
     * Listener class for the dropdown menu
     */
    private class DropDownListener implements ItemListener
    {

        /**
         * Listens for an item state change and applies new logic for game desired (cardLayout)
         * @param e change of state
         */
        @Override
        public void itemStateChanged(ItemEvent e) {
            CardLayout c1 = (CardLayout)(games.getLayout());
            c1.show(games, (String)e.getItem());
            if (((String)e.getItem()).equals(boxChoices[2]))
            {
                currentGame = boxChoices[2];
                printInstructions(true);

            } else if (((String)e.getItem()).equals(boxChoices[1]))
            {
                currentGame = boxChoices[1];
                printInstructions(false);

            } else {
                currentGame = boxChoices[0];
                printInstructions(false);

            }

        }
    }

    /**
     * Listener for the reset button
     */
    private class ResetListener implements ActionListener
    {

        /**
         * Listens for a button click on the restart button
         * @param e the click
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            resetGame();
        }
    }

}
