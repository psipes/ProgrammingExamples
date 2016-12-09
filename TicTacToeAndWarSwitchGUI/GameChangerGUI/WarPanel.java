package GameChangerGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Patricia on 11/20/2016.
 */
public class WarPanel extends JPanel {

    /**
     * Text to who has won the hand
     */
    private JLabel moveText;

    /**
     * button to flip the cards
     */
    private JButton move;
    /**
     * representation of p1's currently played card
     */
    private JLabel p1Card;
    /**
     * string representation of the number of cards in p1's deck
     */
    private JLabel p1ScoreText;
    /**
     * representation of p2's currently played card
     */
    private JLabel p2Card;
    /**
     * string representation of the number of cards in p2's deck
     */
    private JLabel p2ScoreText;
    /**
     * representation of p1's deck
     */
    private JLabel p1Deck;
    /**
     * representation of p2's deck
     */
    private JLabel p2Deck;
    /**
     * The uncut deck
     */
    private Deck deck;
    /**
     * counter for p1's cards
     */
    int p1Counter;
    /**
     * counter for p2's cards
     */
    int p2Counter;

    /**
     * Has war been declared? This allows for tracking of the tied cards stack
     */
    private boolean warDeclared;


    /**
     * The array to hold cards during war situations
     */
    Card [] tieCards = new Card[52];
    /**
     * counter for how many cards are in the tieCards array
     */
    int tieCounter = 2;


    /**
     * Default constructor sets up the playable area of the game
     */
    WarPanel()
    {
        Color myGreen = new Color(46, 139, 87);
        this.setBackground(myGreen);

        move = new JButton("FLIP CARDS");
        move.addActionListener(new FlipListener());
        this.setBackground(myGreen);

        deck = new Deck();
        deck.shuffleDeck(deck.starterDeck);
        deck.dealHands();


        p1Counter = 26;
        p2Counter = 26;

        p1Deck = new JLabel(new ImageIcon("card/b2fv.png"));
        p2Deck = new JLabel(new ImageIcon("card/b2fv.png"));
        p2Card = new JLabel(new ImageIcon("card/b2fv.png"));
        p1Card = new JLabel(new ImageIcon("card/b2fv.png"));

        JPanel p1 = new JPanel();
        p1.setBackground(myGreen);
        JPanel p2 = new JPanel();
        p2.setBackground(myGreen);
        JLabel p1Text = new JLabel("Player One: ");
        JLabel p2Text = new JLabel("Player Two: ");
        p1ScoreText = new JLabel("Cards remaining: " + p1Counter);
        p2ScoreText = new JLabel("Cards remaining: " + p2Counter);
        JPanel playArea = new JPanel();
        playArea.setBackground(myGreen);
        JPanel cards = new JPanel();
        cards.setBackground(myGreen);

        BorderLayout warLayout = new BorderLayout();
        this.setLayout(warLayout);

        BorderLayout playLayout = new BorderLayout();
        playArea.setLayout(playLayout);

        playArea.add(new JLabel("<html><br><br><br><br><br><br></html>"), playLayout.NORTH);
        playArea.add(cards, playLayout.CENTER);
        moveText = new JLabel("Press Button To Flip Cards");
        playArea.add(moveText, playLayout.SOUTH);

        cards.add(p1Card);
        cards.add(p2Card);

        p1.setPreferredSize(new Dimension(130, 400));
        p2.setPreferredSize(new Dimension(130, 400));
        p1.add(p1Text);
        p1.add(p1ScoreText);
        p1.add(p1Deck);

        p2.add(p2Text);
        p2.add(p2ScoreText);
        p2.add(p2Deck);

        this.add(p1, warLayout.WEST);
        this.add(p2, warLayout.EAST);
        this.add(playArea, warLayout.CENTER);
        this.add(move, warLayout.SOUTH);

    }


    /**
     * check if "war" has been ended because the one person is out of cards
     */
    private void checkTieWon()
    {
        if(p1Counter- tieCounter/2 < 1)
        {
            p1Counter = 0;
            moveText.setText("PLAYER 2 WINS THE GAME!");

            move.setText("Start Another Game");

        }

        if(p2Counter- tieCounter/2 < 1)
        {
            p2Counter = 0;
            moveText.setText("PLAYER 1 WINS THE GAME!");

            move.setText("Start Another Game");

        }
    }


    /**
     * returns the empty deck image (joker card)
     * @return joker card image
     */
    private ImageIcon displayEmpty()
    {
        //This displays the joker card
        return new ImageIcon("card/53.png");
    }


    /**
     * Displays the empty card if the current card is the last card in the player's deck.
     */
    private void checkLastCard()
    {
        if (p1Counter < 2)
        {
            p1Deck.setIcon(displayEmpty());
        }

        if (p2Counter < 2)
        {
            p2Deck.setIcon(displayEmpty());
        }
    }

    /**
     * The logic to play a hand of cards, called on listener
     */
    private void playRound()
    {
        Card thisCard = deck.playTopCard(deck.p1Deck);
        Card thatCard = deck.playTopCard(deck.p2Deck);
        p1Card.setIcon(thisCard.getImage());
        p2Card.setIcon(thatCard.getImage());

        if (!warDeclared) {


            if (thisCard.getVal() > thatCard.getVal()) {
                moveText.setText("Player 1 wins the hand!");
                deck.addCard(deck.p1Deck, thisCard);
                deck.addCard(deck.p1Deck, thatCard);
                p1Counter++;
                p2Counter--;
                if(p2Counter < 1)
                {
                    moveText.setText("PLAYER 1 WINS THE GAME!");

                }
            } else if (thisCard.getVal() < thatCard.getVal()) {
                moveText.setText("Player 2 wins the hand!");
                deck.addCard(deck.p2Deck, thisCard);
                deck.addCard(deck.p2Deck, thatCard);
                p1Counter--;
                p2Counter++;
                if(p1Counter < 1)
                {
                    moveText.setText("PLAYER 2 WINS THE GAME!");

                }
            } else {
                warDeclared = true;
                moveText.setText("War has been declared!");


                tieCards[0] = thisCard;
                tieCards[1] = thatCard;

                checkTieWon();
            }
        }
        else
        {
            if(thisCard.getVal() > thatCard.getVal())
            {
                tieCards[tieCounter] = thisCard;
                tieCounter++;
                tieCards[tieCounter] = thatCard;
                tieCounter++;

                moveText.setText("Player 1 wins the hand!");
                for(Card card: tieCards)
                {
                    deck.addCard(deck.p1Deck, card);
                }
                p1Counter+= tieCounter;
                p2Counter-= tieCounter;
                tieCounter = 2;
                warDeclared = false;
                checkTieWon();



            } else if(thisCard.getVal() < thatCard.getVal())
            {
                tieCards[tieCounter] = thisCard;
                tieCounter++;
                tieCards[tieCounter] = thatCard;
                tieCounter++;

                moveText.setText("Player 2 wins the hand!");
                for(Card card: tieCards)
                {
                    deck.addCard(deck.p2Deck, card);
                }
                p1Counter-= tieCounter;
                p2Counter+= tieCounter;
                tieCounter = 2;
                warDeclared = false;
                checkTieWon();

            }
            else
            {
                tieCards[tieCounter] = thisCard;
                tieCounter++;
                tieCards[tieCounter] = thatCard;
                tieCounter++;

                moveText.setText("War Continues");
                checkTieWon();
            }
        }

        p1ScoreText.setText("CARDS REMAINING: " + p1Counter);
        p2ScoreText.setText("CARDS REMAINING: " + p2Counter);
        if (p1Counter > 0)
        {
            p1Deck.setIcon(new ImageIcon("card/b2fv.png"));
        }
        if (p2Counter > 0) {
            p2Deck.setIcon(new ImageIcon("card/b2fv.png"));
        }

    }

    /**
     * resets the game
     */
    protected void resetGame()
    {
        p1Counter = 26;
        p2Counter = 26;

        deck = new Deck();
        deck.shuffleDeck(deck.starterDeck);
        deck.dealHands();
        p1ScoreText.setText("CARDS REMAINING: " + p1Counter);
        p2ScoreText.setText("CARDS REMAINING: " + p2Counter);
        moveText.setText("Click Button to Flip Cards");
        move.setText("FLIP CARDS");

        p1Card.setIcon(new ImageIcon("card/b2fv.png"));
        p2Card.setIcon(new ImageIcon("card/b2fv.png"));
        p1Deck.setIcon(new ImageIcon("card/b2fv.png"));
        p2Deck.setIcon(new ImageIcon("card/b2fv.png"));


    }

    /**
     * Button listener for flipping cards
     */
    private class FlipListener implements ActionListener
    {

        /**
         * Listens for click and then plays the hand or resets the game
         * @param e the click
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            checkLastCard();

            if (p1Counter < 1 || p2Counter < 0)
            {
                resetGame();
            }

            playRound();

        }
    }
}
