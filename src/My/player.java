package My;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card playCard(int index) {
        if (index < 0 || index >= hand.size()) {
            throw new IllegalArgumentException("Invalid card index: " + index + ". The hand size is: " + hand.size());
        }
        return hand.remove(index);
    }

    public int getCardsCount() {
        return hand.size();  // Directly return the size of the hand
    }

    public void drawCard(Deck deck) {
        if (hand.size() < 7) {  // Prevent the player from having more than 7 cards
            Card card = deck.drawCard();
            addCard(card);
            System.out.println(name + " drew a " + card);  // Added logging to show the drawn card
        } else {
            System.out.println(name + " already has 7 cards and cannot draw more.");
        }
    }

    public boolean hasEmptyHand() {
        return hand.isEmpty();  // Check if the hand is empty
    }

    public void showCards() {
        System.out.println(name + "'s cards:");
        if (hand.isEmpty()) {
            System.out.println("No cards left.");
        } else {
            for (int i = 0; i < hand.size(); i++) {
                System.out.println(i + ": " + hand.get(i));  // Display index and card
            }
        }
    }
}
