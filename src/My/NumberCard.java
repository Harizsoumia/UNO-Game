package My;

public class WildCard extends Card {
    // Constructor for WildCard that only takes the action (Wild or WildFour)
    public WildCard(Value value) {
        super(CardColor.Wild, value); // WildCard is always of Wild color, value is Wild or WildFour
    }

    @Override
    public String toString() {
        return color + " - " + value; // String representation of the WildCard
    }
}
