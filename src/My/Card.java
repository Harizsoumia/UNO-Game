package My;

public abstract class Card {
    private CardColor color;
    private CardValue value;

    public Card(CardColor color, CardValue value) {
        this.color = color;
        this.value = value;
    }

    public CardColor getColor() {
        return color;
    }

    public CardValue getValue() {
        return value;
    }

    @Override
    public String toString() {
        return color + " - " + value;
    }
}
