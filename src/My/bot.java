package My;

public class Bot extends Player {

    public Bot(String name) {
        super(name);
    }

    public Card playCard(Card topCard) {
        // Bot logic to play the best card possible
        for (int i = 0; i < hand.size(); i++) {
            Card card = hand.get(i);
            if (card.getColor() == topCard.getColor() || card.getValue() == topCard.getValue() || card instanceof WildCard || card instanceof WildFourCard) {
                return playCard(i);
            }
        }
        return null; // Bot will draw a card if no valid move
    }
}
