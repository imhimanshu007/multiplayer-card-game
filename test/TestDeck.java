import entity.Card;
import entity.Deck;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDeck {
    @Test
    public void testConstructor() {
        Deck deck = new Deck();
        assertEquals(52, deck.getCardCount());
    }

    @Test
    public void testDealCard() {
        Deck deck = new Deck();
        Card card = deck.dealCard();
        assertNotNull(card);
        assertEquals(51, deck.getCardCount());
    }

    @Test
    public void testShuffle() {
        Deck deck1 = new Deck();
        Deck deck2 = new Deck();
        deck2.shuffle();
        assertNotEquals(deck1, deck2);
    }
}
