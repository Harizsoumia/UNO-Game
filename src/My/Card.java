package My;

public abstract class Card {
    protected CardColor color;

    public Card(CardColor color) {
        this.color = color;
    }

    public CardColor getColor() {
        return this.color;
    }

    public abstract String getValue();

    @Override
    public abstract String toString();

    // Enum for card colors
    public enum CardColor {
        RED, YELLOW, GREEN, BLUE, WILD;
    }

    // Enum for card values
    public enum Value {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE,
        DRAW_TWO, SKIP, REVERSE, WILD, WILD_FOUR;
    }
}
