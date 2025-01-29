package My;

public class WildCard extends Card {
    public WildCard(CardColor color, Value value) {
        super(color, value);
    }

    @Override
    public String toString() {
        return "Wild - " + value;
    }
}
