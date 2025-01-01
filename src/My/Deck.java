package My;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Deck {
    private Card[] cards;
    private int cardsInDeck;

    public Deck() {
        cards = new Card[108];
        reset();
    }

    public void reset() {
        CardColor[] colors = CardColor.values();
        cardsInDeck = 0;

        for (CardColor color : colors) {
            if (color != CardColor.WILD) {
                for (int i = 0; i <= 9; i++) {
                    cards[cardsInDeck++] = new NumberCard(color, i);
                    if (i != 0) {
                        cards[cardsInDeck++] = new NumberCard(color, i);
                    }
                }

                Value[] actions = {Value.DRAW_TWO, Value.SKIP, Value.REVERSE};
                for (Value action : actions) {
                    cards[cardsInDeck++] = new ActionCard(color, action);
                    cards[cardsInDeck++] = new ActionCard(color, action);
                }
            }
        }

        Value[] wilds = {Value.WILD, Value.WILD_FOUR};
        for (Value wild : wilds) {
            for (int i = 0; i < 4; i++) {
                cards[cardsInDeck++] = new WildCard(wild);
            }
        }
    }

    public void replaceDeckWith(List<Card> cardsList) {
        cards = cardsList.toArray(new Card[cardsList.size()]);
        cardsInDeck = cards.length;
        shuffleDeck();
    }

    private void shuffleDeck() {
        Random random = new Random();
        for (int i = cards.length - 1; i > 0; i--) {
            int randomIndex = random.nextInt(i + 1);
            Card temp = cards[randomIndex];
            cards[randomIndex] = cards[i];
            cards[i] = temp;
        }
    }

    public Card drawCard() {
        if (cardsInDeck == 0) {
            throw new IllegalArgumentException("Cannot draw from an empty deck");
        }
        return cards[--cardsInDeck];
    }

    public Card[] drawCards(int num) {
        if (num < 0 || num > cardsInDeck) {
            throw new IllegalArgumentException("Invalid number of cards to draw");
        }
        Card[] drawnCards = new Card[num];
        for (int i = 0; i < num; i++) {
            drawnCards[i] = drawCard();
        }
        return drawnCards;
    }

    public boolean isEmpty() {
        return cardsInDeck == 0;
    }
}
