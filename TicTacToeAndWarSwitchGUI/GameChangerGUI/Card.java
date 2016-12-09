package GameChangerGUI;

import javax.swing.*;

/**
 * Classes: Driver, Frame, Panels, TicTacToePanel, WarPanel, Deck, Card
 * Interfaces: DeckInterface
 *
 * Card: Defines a card object
 *
 * @author Patricia Sipes
 * @version 1.1
 */
public class Card {
    /**
     * numerical value for suit
     */
    protected int suit;
    /**
     * numerical value for card
     */
    protected int val;

    public ImageIcon image;


    /**
     * Parameterized Constructor, takes values for suit and value
     * @param value numeric for card
     * @param suitNum numeric representation of suit
     */
    Card(int value, int suitNum, ImageIcon cardImage)
    {
        this.val = value;
        this.suit = suitNum;
        this.image = cardImage;
    }

    /**
     * returns the value of the card
     * @return Int value of card
     */
    protected int getVal()
    {
        return this.val;
    }

    public ImageIcon getImage() {return this.image;}

    /**
     * returns the name of the Suit of the card
     * @return String, card suit name
     */
    protected String getSuitName()
    {
        switch (suit)
        {
            case 1:
                return "Spades";
            case 2:
                return "Hearts";
            case 3:
                return "Diamonds";
            case 4:
                return "Clubs";

        }

        //should never reach here
        return "Error in retrieving suit";
    }


    /**
     * returns the value of the card as a string
     * @return String, name of card value
     */
    private String getValName ()
    {
     switch(this.val)
     {
         case 1:
             return "Ace";
         case 2:
             return "Two";
         case 3:
             return "Three";
         case 4:
             return "Four";
         case 5:
             return "Five";
         case 6:
             return "Six";
         case 7:
             return "Seven";
         case 8:
             return "Eight";
         case 9:
             return "Nine";
         case 10:
             return "Ten";
         case 11:
             return "Jack";
         case 12:
             return "Queen";
         case 13:
             return "King";
     }

        return "Problem identifying number on card";
    }


    /**
     * Returns the full name of the card
     * @return String, full name of card
     */
    protected String getCard()
    {
        return getValName() + " of " + getSuitName();
    }


}
