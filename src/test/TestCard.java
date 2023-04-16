package test;

import entity.Card;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCard {
    @Test
    public void testConstructor() {
        Card card = new Card("H", "A");
        assertEquals("H", card.getSuit());
        assertEquals("A", card.getRank());
    }

    @Test
    public void testToString() {
        Card card = new Card("S", "J");
        assertEquals("JS", card.toString());
    }
}
