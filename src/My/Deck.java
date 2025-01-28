package My;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        reset();
    }

    public void reset() {
        cards.clear();
        addNumberCards();
        addActionCards();
        addWildCards();
        shuffleDeck();
    }

    private void addNumberCards() {
        for (Card.CardColor color : Card.CardColor.values()) {
            if (color != Card.CardColor.Wild) { // Skip Wild color
                for (int i = 0; i <= 9; i++) {
                    Card.Value value = Card.Value.values()[i]; // Convert the number to corresponding Value enum

                    cards.add(new NumberCard(color, value));  // Add a number card

                    if (i != 0) {  // Add a second card for numbers 1-9
                        cards.add(new NumberCard(color, value));
                    }
                }
            }
        }
    }



    private void addActionCards() {
        for (Card.CardColor color : Card.CardColor.values()) {
            if (color != Card.CardColor.Wild) { // Skip Wild color
                for (Card.Value action : new Card.Value[]{Card.Value.DrawTwo, Card.Value.Skip, Card.Value.Reverse}) {
                    cards.add(new ActionCard(color, action));  // Pass color and value (action)
                    cards.add(new ActionCard(color, action));  // Add a second card for the action
                }
            }
        }
    }

    private void addWildCards() {
        for (Card.Value wild : new Card.Value[]{Card.Value.Wild, Card.Value.WildFour}) {
            for (int i = 0; i < 4; i++) {
                cards.add(new WildCard(wild));
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("Cannot draw from an empty deck. Consider resetting the deck.");
        }
        return cards.remove(cards.size() - 1);
    }

    public List<Card> drawCards(int num) {
        if (num < 0 || num > cards.size()) {
            throw new IllegalArgumentException("Invalid number of cards to draw: " + num);
        }
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            drawnCards.add(drawCard());
        }
        return drawnCards;
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int cardsRemaining() {
        return cards.size();
    }
}
