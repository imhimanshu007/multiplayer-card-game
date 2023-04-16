package entity;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private  ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
        String[] suits = {"Clubs", "Diamonds", "Hearts", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        for (String suit : suits) {
            for (int j = 0; j < ranks.length; j++) {
                int value = j + 2;
                if (j >= 9) {
                    value = 10;
                }
                if (j == 12) {
                    value = 11; // Ace
                }
                cards.add(new Card(suit, ranks[j], value));
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(0);
    }
}
