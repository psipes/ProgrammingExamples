package GameChangerGUI;


import javax.swing.*;
import java.util.Random;

/**
 * Classes: Driver, Frame, Panels, TicTacToePanel, WarPanel, Deck, Card
 * Interfaces: DeckInterface
 *
 * Deck: Deck Logic. MUST HAVE CARD CLASS
 *
 * @author Patricia Sipes
 * @version 1.1
 */
public class Deck implements DeckInterface {

    /**
     * Full 52 card deck
     */
    protected Card[] starterDeck =  new Card[52];
    /**
     * half Deck for player 1
     */
    protected Card[] p1Deck = new Card[52];
    /**
     * half Deck for player 2
     */
    protected Card[] p2Deck = new Card[52];

    /**
     * Default Constructor
     * **THIS IS TOO SPECIFIC.
     * **THIS CONSTRUCTOR ONLY WORKS IF THERE ARE 52 CARDS IN THE DECK
     */
    Deck()
    {
        int counter = 0;
        for (int i = 1; i < 5; i++) {
            for (int k = 1; k < 14; k++) {
                this.starterDeck[counter] = new Card(k, i, new ImageIcon("card/" + (counter + 1) +".png"));
                counter++;
                }
            }


    }





    /**
     * Sorts the provided deck by suit.
     * @param deckToSort Card[] deck to sort
     */
    @Override
    public void sortDeckBySuit (Card[] deckToSort )
    {
        Card[] sortHolder = new Card[deckToSort.length];
        int temp = 0;
        int suit = 1;

        while (suit < 5)
        {
            for (int i = 0; i < deckToSort.length; i++)
            {
                if (deckToSort[i].suit == suit)
                {
                    sortHolder[temp] = deckToSort[i];
                    temp++;
                }
            }
            suit++;
        }

        for (int k = 0; k < deckToSort.length; k ++)
        {
            deckToSort[k] = sortHolder[k];
            sortHolder[k] = null;
        }

    }



    /**
     * Sorts the provided deck by value
     * @param deckToSort Card [] deck to sort
     */
    @Override
    public void sortDeckByValue (Card[] deckToSort )
    {
        Card[] sortHolder = new Card[deckToSort.length];
        int temp = 0;
        int value = 1;

        while (value < 14)
        {
            for (int i = 0; i < deckToSort.length; i++)
            {
                if (deckToSort[i].val == value)
                {
                    sortHolder[temp] = deckToSort[i];
                    temp++;
                }
            }
            value++;
        }

        for (int k = 0; k < deckToSort.length; k ++)
        {
            deckToSort[k] = sortHolder[k];
            sortHolder[k] = null;
        }

    }





    /**
     * Shuffles the provided deck
     * @param deckToShuffle Card [] deck to shuffle
     */
    @Override
    public void shuffleDeck (Card[] deckToShuffle)
    {
        Random rand = new Random();
        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < deckToShuffle.length; i++) {
                boolean included = true;
                int temp = rand.nextInt(deckToShuffle.length);
                Card cardHolder = deckToShuffle[i];


                deckToShuffle[i] = deckToShuffle[temp];
                deckToShuffle[temp] = cardHolder;

            }
            //BECAUSE I HAVE TO
            if (k == 0)
                sortDeckByValue(deckToShuffle);
            else if (k == 1)
                sortDeckBySuit(deckToShuffle);


        }


    }




    /**
     * deals x number of decks
     */
    @Override
    public void dealHands ()
    {
        int count = 0;
        for (int i = 0; i < starterDeck.length/2; i++)
        {
            p1Deck[i] = starterDeck[count];
            count++;

            p2Deck[i] = starterDeck[count];
            count++;
        }
    }




    /**
     * adds the specified card to the specified deck
     * @param deck  Card [] deck to add to
     * @param newCard card to add
     */
    @Override
    public void addCard(Card[] deck, Card newCard)
    {
        int counter = 0;
        while(deck[counter] != null)
        {
            counter++;
        }

        deck[counter] = newCard;
    }



    /**
     * picks the top card from the deck and moves the deck to new positions
     * @param deck the deck to pick from
     * @return the card that is pulled
     */
    @Override
    public Card playTopCard (Card[] deck)
    {
        Card topHold = deck[0];
        int counter = 0;
        while(deck[counter + 1] != null)
        {
            deck[counter] = deck[counter + 1];
            counter ++;
        }
        deck[counter] = null;

        return topHold;
    }

    /**
     * checks if there are no cards in the deck
     * @param deck deck to be checked
     * @return boolean, true if empty, false if still populated
     */
    @Override
    public boolean checkIsEmpty (Card[] deck)
    {
        return deck[0] == null;
    }


}
