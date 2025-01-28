package My;

public class ActionCard extends Card {
    // Constructor for ActionCard that takes color and action (value)
    public ActionCard(CardColor color, Value value) {
        super(color, value); // Call the parent constructor with color and value
    }

    @Override
    public String toString() {
        return color + " - " + value; // String representation of the ActionCard
    }
}
