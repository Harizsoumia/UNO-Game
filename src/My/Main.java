package My;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Example setup: Create 2 players, one human and one bot
        Player player1 = new Player("Human Player");
        Player player2 = new Bot("Bot Player");

        // Add players to the game
        List<Player> players = Arrays.asList(player1, player2);

        // Start the game
        Game game = new Game();
        game.start();
    }
}
