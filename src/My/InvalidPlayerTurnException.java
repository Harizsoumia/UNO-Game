package My;

public class InvalidPlayerTurnException extends Exception {
    private static final long serialVersionUID = 1L;
    private String playerId;

    public InvalidPlayerTurnException(String message, String playerId) {
        super(message);
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }
}
