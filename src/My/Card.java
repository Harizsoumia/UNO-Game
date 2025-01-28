package My;

public abstract class Card {
    protected CardColor color;
    protected Value value; // Add this field to store the value of the card

    // Constructor that accepts both color and value
    public Card(CardColor color, Value value) {
        if (color == null || value == null) {
            throw new IllegalArgumentException("Card color and value cannot be null");
        }
        this.color = color;
        this.value = value;  // Set the value of the card
    }

    public CardColor getColor() {
        return this.color;
    }

    public Value getValue() {
        return this.value; // Now we have access to the card's value
    }

    @Override
    public abstract String toString(); // Abstract method for card representation

    // Enum for card colors
    public enum CardColor {
        Red, Yellow, Green, Blue, Wild; // Wild as a special color
    }

    // Enum for card values
    public enum Value {
        Zero, One, Two, Three, Four, Five, Six, Seven, Eight, Nine,
        DrawTwo, Skip, Reverse, Wild, WildFour;
    }
}
