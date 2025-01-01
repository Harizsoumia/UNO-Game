package My;

public class NumberCard extends Card {
    private final int number;

    public NumberCard(CardColor color, int number) {
        super(color);
        this.number = number;
    }

    @Override
    public String getValue() {
        return String.valueOf(this.number);
    }

    @Override
    public String toString() {
        return color + "-" + number;
    }
}
