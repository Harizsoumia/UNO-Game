package My;

public class WildCard extends ActionCard {
    public WildCard() {
        super(CardColor.Wild, CardValue.Wild);
    }

    @Override
    public void applyAction(Game game, Player currentPlayer) {
        // Logic to change the color of play
        game.changeColor(currentPlayer);
    }
}
