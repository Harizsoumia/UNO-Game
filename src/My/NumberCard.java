package My;

public class NumberCard extends Card {
    
    // Constructor that takes both color and value
    public NumberCard(CardColor color, Value value) {
        super(color, value); // Call the parent constructor to set the color and value
    }

    @Override
    public String toString() {
        return color + " - " + value; // String representation of the card
    }
}
