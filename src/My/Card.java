package My;

public abstract class Card {
    public enum CardColor { Red, Yellow, Green, Blue, Wild }
    public enum Value { Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine, Skip, Reverse, DrawTwo, Wild, WildFour }

    protected CardColor color;
    protected Value value;

    public Card(CardColor color, Value value) {
        this.color = color;
        this.value = value;
    }

    public CardColor getColor() {
        return color;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return color + " - " + value;
    }
}
