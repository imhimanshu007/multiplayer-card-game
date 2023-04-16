package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Game {
    private Deck deck;
    private ArrayList<Player> players;
    private ArrayList<Card> drawPile;
    private ArrayList<Card> discardPile;
    private int currentPlayerIndex;

    public Game() {
        deck = new Deck();
        deck.shuffle();
        players = new ArrayList<Player>();
        drawPile = new ArrayList<Card>();
        discardPile = new ArrayList<Card>();
        currentPlayerIndex = 0;

        // Initialize players with 5 cards each
        for (int i = 0; i < 4; i++) {
            Player player = new Player("entity.Player " + (i+1));
            for (int j = 0; j < 5; j++) {
                Card card = deck.dealCard();
                player.addCardToHand(card);
            }
            players.add(player);
        }

        // Put the top card of the deck onto the discard pile
        discardPile.add(deck.dealCard());
    }

    public void play() {
        boolean gameIsOver = false;

        while (!gameIsOver) {
            // Print current player's name and hand
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("\n" + currentPlayer.getName() + "'s turn. Current card on the discard pile: "
                    + discardPile.get(discardPile.size() - 1));
            System.out.println("Your hand: ");
            for (int i = 0; i < currentPlayer.getHandSize(); i++) {
                System.out.println((i + 1) + ": " + currentPlayer.getHand().get(i));
            }

            // Check if the current player can play a card
            boolean canPlayCard = false;
            for (int i = 0; i < currentPlayer.getHandSize(); i++) {
                Card card = currentPlayer.getHand().get(i);
                if (card.getSuit().equals(discardPile.get(discardPile.size() - 1).getSuit()) ||
                        card.getRank().equals(discardPile.get(discardPile.size() - 1).getRank())) {
                    canPlayCard = true;
                    break;
                }
            }

            if (canPlayCard) {
                // Get the index of the card to play from the player
                int cardIndex = -1;
                do {
                    System.out.print("Enter the index of the card you want to play (or 0 to draw a card): ");
                    Scanner scanner = new Scanner(System.in);
                    cardIndex = scanner.nextInt();
                    if (cardIndex < 0 || cardIndex > currentPlayer.getHandSize()) {
                        System.out.println("Invalid input. Please enter a number between 0 and " + currentPlayer.getHandSize() + ".");
                    }
                } while (cardIndex < 0 || cardIndex > currentPlayer.getHandSize());

                if (cardIndex == 0) {
                    // Draw a card from the draw pile
                    Card card = deck.dealCard();
                    if (card == null) {
                        // The draw pile is empty, so the game ends in a draw
                        System.out.println("\nNo cards left to draw. The game ends in a draw!");
                        gameIsOver = true;
                        break;
                    }
                    currentPlayer.addCardToHand(card);
                    drawPile.add(card);
                    System.out.println("You drew a " + card + ".");
                } else {
                    // Play the selected card
                    Card card = currentPlayer.playCard(cardIndex - 1);
                    discardPile.add(card);
                    System.out.println("You played a " + card + ".");

                    if (card.getRank().equals("A")) {
                        // Skip the next player in turn
                        System.out.println("The next player will be skipped!");
                        currentPlayerIndex = (currentPlayerIndex + 2) % players.size();
                    } else if (card.getRank().equals("K")) {
                        // Reverse the sequence of who plays next
                        System.out.println("The direction of play has been reversed!");
                        Collections.reverse(players);
                        currentPlayerIndex = (players.size() - currentPlayerIndex) % players.size();
                    } else if (card.getRank().equals("Q")) {
                        // Draw 2 cards from the draw pile
                        System.out.println("The next player will draw 2 cards!");
                        Player nextPlayer = players.get((currentPlayerIndex + 1) % players.size());
                        Card drawnCard1 = deck.dealCard();
                        if (drawnCard1 == null) {
                            // The draw pile is empty, so the game ends in a draw
                            System.out.println("\nNo cards left to draw. The game ends in a draw!");
                            gameIsOver = true;
                            break;
                        }
                        nextPlayer.addCardToHand(drawnCard1);
                        drawPile.add(drawnCard1);
                        Card drawnCard2 = deck.dealCard();
                        if (drawnCard2 == null) {
                            // The draw pile is empty, so the game ends in a draw
                            System.out.println("\nNo cards left to draw. The game ends in a draw!");
                            gameIsOver = true;
                            break;
                        }
                        nextPlayer.addCardToHand(drawnCard2);
                        drawPile.add(drawnCard2);
                    } else if (card.getRank().equals("J")) {
                        // Draw 4 cards from the draw pile
                        System.out.println("The next player will draw 4 cards!");
                        Player nextPlayer = players.get((currentPlayerIndex + 1) % players.size());
                        for (int i = 0; i < 4; i++) {
                            Card drawnCard = deck.dealCard();
                            if (drawnCard == null) {
                                // The draw pile is empty, so the game ends in a draw
                                System.out.println("\nNo cards left to draw. The game ends in a draw!");
                                gameIsOver = true;
                                break;
                            }
                            nextPlayer.addCardToHand(drawnCard);
                            drawPile.add(drawnCard);
                        }
                    }

                    // Check if the current player has won
                    if (currentPlayer.getHandSize() == 0) {
                        System.out.println(currentPlayer.getName() + " wins the game!");
                        gameIsOver = true;
                    }
                }
            } else{
                // The current player cannot play a card, so they must draw a card from the draw pile
                System.out.println("You cannot play a card. You must draw a card from the draw pile.");
                Card card = deck.dealCard();
                if (card == null) {
                    // The draw pile is empty, so the game ends in a draw
                    System.out.println("\nNo cards left to draw. The game ends in a draw!");
                    gameIsOver = true;
                    break;
                }
                currentPlayer.addCardToHand(card);
                drawPile.add(card);
            }

            // Move on to the next player
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
