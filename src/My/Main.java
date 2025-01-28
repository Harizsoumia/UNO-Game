package My;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt for the number of human players
        System.out.print("Enter the number of human players: ");
        int numHumanPlayers = Integer.parseInt(scanner.nextLine().trim());

        // Prompt for the number of bot players
        System.out.print("Enter the number of bot players: ");
        int numBotPlayers = Integer.parseInt(scanner.nextLine().trim());

        int totalPlayers = numHumanPlayers + numBotPlayers;
        String[] playerIds = new String[totalPlayers];

        // Input human player names
        for (int i = 0; i < numHumanPlayers; i++) {
            System.out.print("Enter name for human player " + (i + 1) + ": ");
            playerIds[i] = scanner.nextLine().trim();
        }

        // Add bot names
        for (int i = 0; i < numBotPlayers; i++) {
            playerIds[numHumanPlayers + i] = "Bot" + (i + 1);
        }

        // Initialize the game
        Game game = new Game(playerIds);
        game.start();

        // Game loop
        while (!game.isGameOver()) {
            System.out.println("Current Player: " + game.getCurrentPlayer());
            System.out.println("Top card: " + game.getTopCard());

            Player currentPlayer = game.getPlayerByName(game.getCurrentPlayer());

            if (currentPlayer instanceof Bot) {
                Bot bot = (Bot) currentPlayer;
                Card cardToPlay = bot.playCard(game.getTopCard());
                if (cardToPlay != null) {
                    game.playTurn(bot.getName(), cardToPlay);
                } else {
                    System.out.println(bot.getName() + " has no valid card and draws one.");
                    bot.drawCard(game.getDeck());
                }
            } else {
                // Human player's turn
                System.out.println("Your hand: ");
                currentPlayer.showCards();  // Display hand with index

                boolean validMove = false;
                boolean drawnCard = false;

                while (!validMove) {
                    System.out.println("Choose a card to play by index, or type 'draw' to draw a card: ");
                    String input = scanner.nextLine().trim();

                    if (input.equalsIgnoreCase("draw")) {
                        currentPlayer.drawCard(game.getDeck());
                        drawnCard = true;

                        if (hasValidMove(currentPlayer, game)) {
                            System.out.println("You drew a valid card! You can play it.");
                        } else {
                            System.out.println("The card you drew is not valid. Your turn ends.");
                            validMove = true;
                        }
                    } else {
                        try {
                            int cardIndex = Integer.parseInt(input);
                            if (cardIndex >= 0 && cardIndex < currentPlayer.getHand().size()) {
                                Card cardToPlay = currentPlayer.getHand().get(cardIndex);

                                if (game.validCardPlay(cardToPlay)) {
                                    game.playTurn(currentPlayer.getName(), cardToPlay);
                                    validMove = true;
                                } else {
                                    System.out.println("Invalid card! Please choose a valid card.");
                                }
                            } else {
                                System.out.println("Invalid index! Please choose a valid index.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input! Please enter a valid index or 'draw'.");
                        }
                    }

                    if (drawnCard && !hasValidMove(currentPlayer, game)) {
                        System.out.println("You have drawn a card, but you still don't have a valid card to play.");
                        break;
                    }
                }
            }
        }

        // Game Over
        System.out.println("Game Over!");
        Player winner = null;
        for (Player player : game.getPlayers()) {
            if (player.getHand().isEmpty()) {
                winner = player;
                break;
            }
        }

        if (winner != null) {
            System.out.println(winner.getName() + " wins!");
        }

        scanner.close();
    }

    private static boolean hasValidMove(Player player, Game game) {
        for (Card card : player.getHand()) {
            if (game.validCardPlay(card)) {
                return true;
            }
        }
        return false;
    }
}
