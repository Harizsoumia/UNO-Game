package My;

public class Bot extends Player {

    public Bot(String name) {
        super(name);
    }

    // Bot plays a card based on a simple strategy
    public Card playCard(Card topCard) {
        // Try to play a valid card that matches the top card color or value
        for (int i = 0; i < getHand().size(); i++) {
            Card card = getHand().get(i);
            if (isValidMove(card, topCard)) {
                System.out.println(getName() + " plays: " + card);  // Log the played card
                return playCard(i);  // Play the card at index i
            }
        }

        // If no valid card, draw a new card
        System.out.println(getName() + " draws a card.");
        drawCard(new Deck());
        return null;  // Returning null since no card was played
    }

    // Check if the card is a valid move
    private boolean isValidMove(Card card, Card topCard) {
        return card.getColor() == topCard.getColor() || card.getValue().equals(topCard.getValue());
    }

    @Override
    public void drawCard(Deck deck) {
        super.drawCard(deck);
        System.out.println(getName() + " drew a card.");
    }
}
