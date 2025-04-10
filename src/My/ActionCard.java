package My;

public abstract class ActionCard extends Card implements Actionable {
    public ActionCard(CardColor color, CardValue value) {
        super(color, value);
    }
}
