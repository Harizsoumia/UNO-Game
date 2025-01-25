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
        Red, Yellow, Green, Blue, Wild;
    }

    // Enum for card values
    public enum Value {
        0, 1, 2, 3,4, 5, 6, 7, 8,9,
        Draw, Skip, Reverce, WILD, WILD_FOUR;
    }
}
