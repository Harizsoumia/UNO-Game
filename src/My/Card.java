package My;

public enum CardColor {
    RED, YELLOW, GREEN, BLUE;
}

public class Card {
    private CardColor color;
    private String type; // e.g., Number, Skip, Reverse, Draw Two, Wild
    private int value;   // e.g., 1-9 for number cards

    public Card(CardColor color, String type, int value) {
        this.color = color;
        this.type = type;
        this.value = value;
    }

    // Getters
    public CardColor getColor() {
        return color;
    }

    public String getType() {
        return type;
    }

    public int getValue() {
        return value;
    }

    // Setters
    public void setColor(CardColor color) {
        this.color = color;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static void main(String[] args) {
        // Create a new card
        Card card = new Card(CardColor.RED, "Number", 5);

        // Access card attributes using getters
        System.out.println("Color: " + card.getColor());
        System.out.println("Type: " + card.getType());
        System.out.println("Value: " + card.getValue());

        // Modify card attributes using setters
        card.setColor(CardColor.BLUE);
        card.setType("Skip");
        card.setValue(0);

        // Access modified attributes
        System.out.println("New Color: " + card.getColor());
        System.out.println("New Type: " + card.getType());
        System.out.println("New Value: " + card.getValue());
    }
}
