package entity;

import java.util.ArrayList;

public class Player {
    private String name;
    private  ArrayList<Card> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<Card>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public Card playCard(int index) {
        return hand.remove(index);
    }

    public int getHandSize() {
        return hand.size();
    }

    @Override
    public String toString() {
        return name;
    }
}
