package My;

import java.util.*;

public class Player {
    protected String name;
    protected List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public Card playCard(int index) {
        if (index < 0 || index >= hand.size()) {
            throw new IllegalArgumentException("Invalid card index.");
        }
        return hand.remove(index);
    }

    public void showCards() {
        if (hand.isEmpty()) {
            System.out.println("No cards to show.");
            return;
        }
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i));
        }
    }

    public boolean hasEmptyHand() {
        return hand.isEmpty();
    }
}
