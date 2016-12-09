package GameChangerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
        * Classes: Driver, Frame, Panels, TicTacToePanel, WarPanel, Deck, Card
        * Interfaces: DeckInterface
        *
        * TicTacToePanel: creates a tictactoe object to be placed in a playable area
        *
        * @author Patricia Sipes
        * @version 1.1
        */
public class TicTacToePanel extends JPanel {
    /**
     *     Updates the turn and win/lose statements
     */

    private JLabel status;
    /**
     * The grid for the x's and o'x
     */
    private JButton[][] clickables;
    /**
     * is it x's turn?
     */
    private boolean xTurn;
    /**
     * representation of what an empty cell should read
     */
    private String empty = " ";
    /**
     * counter for the number of moves (for checking stalemate)
     */
    private int moveCount = 0;

    /**
     * Default constructor, creates buttons and words and sets up playable area
     */
    TicTacToePanel()
    {
        status = new JLabel("X goes first, click the square you wish to play in");
        GridLayout ticLayout = new GridLayout(3, 3);
        JPanel playArea = new JPanel(ticLayout);
        clickables = new JButton[3][3];
        for(int col = 0; col < 3; col++)
        {
            for (int row = 0; row < 3; row++) {
                clickables[col][row] = new JButton(empty);
                clickables[col][row].setPreferredSize(new Dimension(50,50));
                clickables[col][row].addActionListener(new PlayListen());
                clickables[col][row].setFont(new Font("Arial", Font.PLAIN, 50));
                playArea.add(clickables[col][row]);

            }

        }
        BoxLayout boxxy = new BoxLayout(this, BoxLayout.Y_AXIS);

        this.setLayout(boxxy);
        this.add(playArea);
        this.add(status);
        xTurn = true;

    }

    /**
     * Changes the button to the X or O, gives error if already pressed
     * @param button the button to be changed
     */

    private void changeButton (JButton button)
    {
        if (button.getText().equalsIgnoreCase(empty)) {
            if (xTurn) {
                button.setText("x");
                status.setText("O's Turn.");
            } else {
                button.setText("o");
                status.setText("X's Turn.");
            }

            moveCount ++;
            if(!checkWin())
            {
                stalemate();
            }
        } else
        {
            status.setText("That space has already been played");
        }
    }

    /**
     * Checks if there is a stalemate
     * @return stalemeate = true, not = false
     */
    private boolean stalemate()
    {
        if (moveCount > 8)
        {
            disableButtons();
            status.setText("Stalemate, Please click Restart");
            return true;
        }
        return false;
    }

    /**
     * Checks if there is a winning combination
     * @return true if won, false if not
     */
    private boolean checkWin()
    {
        int horMove = 0;
        if(moveCount > 4)
        {
            //Horizontal Check
            for (int i = 0; i < 3; i++)
            {
                if(!(clickables[i][horMove].getText().equalsIgnoreCase(empty)))
                {
                    if ((clickables[i][horMove].getText().equalsIgnoreCase(clickables[i][1].getText()))
                            && (clickables[i][horMove].getText().equalsIgnoreCase(clickables[i][2].getText())))
                    {
                        status.setText("<html>The " + clickables[i][horMove].getText() + "'s have won! <br>" +
                                       "Please click RESTART to start a new game</html");
                        disableButtons();
                        return true;

                    }
                }
            }

            //Vertical Check
            for (int v = 0; v < 3; v++)
            {
                if(!(clickables[0][v].getText().equalsIgnoreCase(empty)))
                {
                    if ((clickables[0][v].getText().equalsIgnoreCase(clickables[1][v].getText()))
                            && (clickables[0][v].getText().equalsIgnoreCase(clickables[2][v].getText())))
                    {
                        status.setText("<html>The " + clickables[0][v].getText() + "'s have won! <br>" +
                                "Please click RESTART to start a new game</html");
                        disableButtons();
                        return true;
                    }
                }
            }


            //LeftHorizontal
            if(!(clickables[0][0].getText().equalsIgnoreCase(empty)))
            {
                if ((clickables[0][0].getText().equalsIgnoreCase(clickables[1][1].getText()))
                        && (clickables[0][0].getText().equalsIgnoreCase(clickables[2][2].getText())))
                {
                    status.setText("<html>The " + clickables[0][0].getText() + "'s have won! <br>" +
                            "Please click RESTART to start a new game</html");
                    disableButtons();
                    return true;
                }
            }

            if(!(clickables[0][2].getText().equalsIgnoreCase(empty)))
            {
                if ((clickables[0][2].getText().equalsIgnoreCase(clickables[1][1].getText()))
                        && (clickables[0][2].getText().equalsIgnoreCase(clickables[2][0].getText())))
                {
                    status.setText("<html>The " + clickables[0][2].getText() + "'s have won! <br>" +
                            "Please click RESTART to start a new game</html");
                    disableButtons();
                    return true;
                }
            }

        }

        return false;
    }

    /**
     * Disables buttons (for use after win or stalemate)
     */
    private void disableButtons()
    {
        for (int i = 0; i < 3; i++)
            for (int k = 0; k<3; k++)
            {
                clickables[i][k].setEnabled(false);
            }
    }

    /**
     * makes game playable again
     */
    public void resetGame()
    {
        status.setText("X Goes First");
        for(int i = 0; i < 3; i++)
            for (int k = 0; k < 3; k++)
            {
                clickables[i][k].setEnabled(true);
                clickables[i][k].setText(empty);
            }
        xTurn = true;
        moveCount = 0;

    }

    /**
     * Listener for clicks on the buttons
     */
    private class PlayListen implements ActionListener
    {

        /**
         * Listens for a click on the button and then changes the button text and whose turn it is
         * @param e the button click
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            changeButton((JButton)(e.getSource()));
            xTurn = !xTurn;
        }
    }
}
