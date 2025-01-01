package My;

public class ActionCard extends Card {
    private final Value action;

    public ActionCard(CardColor color, Value action) {
        super(color);
        this.action = action;
    }

    @Override
    public String getValue() {
        return action.toString();
    }

    @Override
    public String toString() {
        return color + "-" + action;
    }
}

