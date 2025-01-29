package My;

import java.util.*;

public class Deck {
    private Card[] cards;
    private int topIndex;

    public Deck() {
        cards = new Card[108];
        topIndex = 0;
        reset();
    }

    public void reset() {
        topIndex = 0;
        addNumberCards();
        addActionCards();
        addWildCards();
        shuffleDeck();
    }

    private void addNumberCards() {
        int index = 0;
        for (Card.CardColor color : Card.CardColor.values()) {
            if (color != Card.CardColor.Wild) {
                for (int i = 0; i <= 9; i++) {
                    cards[index++] = new NumberCard(color, Card.Value.values()[i]);
                    if (i != 0) {
                        cards[index++] = new NumberCard(color, Card.Value.values()[i]);
                    }
                }
            }
        }
    }

    private void addActionCards() {
        int index = 76;
        for (Card.CardColor color : Card.CardColor.values()) {
            if (color != Card.CardColor.Wild) {
                for (Card.Value action : new Card.Value[]{Card.Value.DrawTwo, Card.Value.Skip, Card.Value.Reverse}) {
                    cards[index++] = new ActionCard(color, action);
                    cards[index++] = new ActionCard(color, action);
                }
            }
        }
    }

    private void addWildCards() {
        int index = 100;
        for (Card.Value wild : new Card.Value[]{Card.Value.Wild, Card.Value.WildFour}) {
            for (int i = 0; i < 4; i++) {
                cards[index++] = new WildCard(Card.CardColor.Wild, wild);
            }
        }
    }

    public void shuffleDeck() {
        Random rand = new Random();
        for (int i = cards.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            Card temp = cards[i];
            cards[i] = cards[j];
            cards[j] = temp;
        }
    }

    public Card drawCard() {
        if (topIndex >= cards.length) {
            throw new IllegalArgumentException("Cannot draw from an empty deck. Consider resetting the deck.");
        }
        return cards[topIndex++];
    }
}
