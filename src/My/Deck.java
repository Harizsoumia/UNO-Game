import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        createDeck();
        shuffleDeck();
    }

    private void createDeck() {
        // Create number cards
        for (Card.Color color : Card.Color.values()) {
            if (color != Card.Color.Wild) {
                for (int i = 0; i <= 9; i++) {
                    cards.add(new NumberCard(color, i));
                    if (i != 0) {
                        cards.add(new NumberCard(color, i)); // Add second instance for 1-9
                    }
                }
            }
        }

        // Create action cards
        for (Card.Color color : Card.Color.values()) {
            if (color != Card.Color.Wild) {
                cards.add(new SkipCard(color)); // Assuming SkipCard constructor takes only color
                cards.add(new SkipCard(color)); // Second instance
                cards.add(new TakeTwoCard(color)); // Assuming TakeTwoCard constructor takes only color
                cards.add(new TakeTwoCard(color)); // Second instance
                cards.add(new ReverseCard(color)); // Assuming ReverseCard constructor takes only color
                cards.add(new ReverseCard(color)); // Second instance
            }
        }

        // Create wild cards
        for (int i = 0; i < 4; i++) {
            cards.add(new WildCard()); // Assuming WildCard has a default constructor
            cards.add(new WildrawFourCard()); // Assuming WildDrawFourCard has a default constructor
            cards.add(new WildReverseCard()); // Assuming WildReverseCard has a default constructor
        }
    }

    private void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("Cannot draw from an empty deck.");
        }
        return cards.remove(cards.size() - 1); // Draw from the top of the deck
    }

    public List<Card> getCards() {
        return cards;
    }
}
