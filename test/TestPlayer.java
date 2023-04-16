package test;

import entity.Card;
import entity.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPlayer {
    @Test
    public void testConstructor() {
        Player player = new Player("Alice");
        assertEquals("Alice", player.getName());
        assertEquals(0, player.getHandSize());
    }

    @Test
    public void testAddCardToHand() {
        Player player = new Player("Bob");
        Card card = new Card("C", "8");
        player.addCardToHand(card);
        assertEquals(1, player.getHandSize());
        assertEquals(card, player.getHand().get(0));
    }

    @Test
    public void testPlayCard() {
        Player player = new Player("Charlie");
        player.addCardToHand(new Card("H", "7"));
        player.addCardToHand(new Card("D", "K"));
        Card card = player.playCard(1);
        assertEquals(new Card("D", "K"), card);
        assertEquals(1, player.getHandSize());
        assertEquals(new Card("H", "7"), player.getHand().get(0));
    }
}
