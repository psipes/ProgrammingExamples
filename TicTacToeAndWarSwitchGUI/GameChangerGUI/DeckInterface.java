package GameChangerGUI;

/**
 * Classes: Driver, Frame, Panels, TicTacToePanel, WarPanel, Deck, Card
 * Interfaces: DeckInterface
 *
 * Deck Interface: basic interface for dealing with a deck of Cards.
 * MUST have a Card class, as well.
 *
 *
 * @author Patricia Sipes
 * @version 1.1
 */
public interface DeckInterface {
    /**
     * Sorts the provided deck by suit.
     * @param deckToSort Card[] deck to sort
     */
    public void sortDeckBySuit(Card[] deckToSort);

    /**
     * Sorts the provided deck by value
     * @param deckToSort Card [] deck to sort
     */
    public void sortDeckByValue(Card[] deckToSort);

    /**
     * Shuffles the provided deck
     * @param deckToShuffle Card [] deck to shuffle
     */
    public void shuffleDeck(Card[] deckToShuffle);

    /**
     * deals x number of decks of equal amounts of cards
     */
    public void dealHands();

    /**
     * adds the specified card to the specified deck
     * @param deck  Card [] deck to add to
     * @param newCard card to add
     */
    public void addCard(Card[] deck, Card newCard);

    /**
     * picks the top card from the deck and moves the deck to new positions
     * @param deck the deck to pick from
     * @return the card that is pulled
     */
    public Card playTopCard(Card[] deck);

    /**
     * checks if there are no cards in the deck
     * @param deck deck to be checked
     * @return boolean, true if empty, false if still populated
     */
    public boolean checkIsEmpty(Card[] deck);


}
