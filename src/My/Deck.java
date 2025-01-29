package My;

import java.util.*;

public class Game {
    private Deck deck;
    private List<Player> players;
    private int currentPlayerIndex;
    private Card topCard;
    private boolean reverseDirection = false;

    public Game() {
        this.deck = new Deck();
        this.players = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.topCard = deck.drawCard();
        setupGame();  // Set up the game
        dealInitialCards();
    }



    // Set up the game by asking for player preferences
    private void setupGame() {
        Scanner scanner = new Scanner(System.in);

        // Ask for number of players
        System.out.print("Enter number of players (2-4): ");
        int numPlayers = scanner.nextInt();
        while (numPlayers < 2 || numPlayers > 4) {
            System.out.print("Invalid number! Enter number of players (2-4): ");
            numPlayers = scanner.nextInt();
        }

        // Set up players
        for (int i = 0; i < numPlayers; i++) {
            System.out.print("Enter name for Player " + (i + 1) + ": ");
            String playerName = scanner.next();

            System.out.print("Is " + playerName + " a human (yes/no)? ");
            String isHuman = scanner.next();

            if (isHuman.equalsIgnoreCase("yes")) {
                players.add(new Player(playerName)); // Human player
            } else {
                players.add(new Bot(playerName)); // Bot player
            }
        }
    }

    // Deal initial cards to all players
    private void dealInitialCards() {
        for (Player player : players) {
            for (int i = 0; i < 7; i++) {
                player.addCard(deck.drawCard());
            }
        }
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            Player currentPlayer = players.get(currentPlayerIndex);
            System.out.println("It's " + currentPlayer.getName() + "'s turn.");
            System.out.println("Top card: " + topCard);
            currentPlayer.showCards();

            if (currentPlayer instanceof Bot) {
                Card playedCard = ((Bot) currentPlayer).playCard(topCard);
                if (playedCard != null) {
                    topCard = playedCard;
                    System.out.println(currentPlayer.getName() + " played: " + playedCard);
                    handleSpecialCardEffects(playedCard, currentPlayer);
                }
            } else {
                System.out.print("Enter card index to play (or -1 to draw): ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume the leftover newline character

                if (choice == -1) {
                    Card drawnCard = deck.drawCard();
                    currentPlayer.addCard(drawnCard);
                    System.out.println(currentPlayer.getName() + " drew a card: " + drawnCard);
                } else {
                    try {
                        Card playedCard = currentPlayer.playCard(choice);
                        if (isValidMove(playedCard)) {
                            topCard = playedCard;
                            System.out.println(currentPlayer.getName() + " played: " + playedCard);
                            handleSpecialCardEffects(playedCard, currentPlayer);
                        } else {
                            System.out.println("Invalid move! You can only play a card that matches the top card.");
                            currentPlayer.addCard(playedCard);
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }

            if (currentPlayer.hasEmptyHand()) {
                System.out.println(currentPlayer.getName() + " wins!");
                break;
            }

            currentPlayerIndex = (currentPlayerIndex + (reverseDirection ? -1 : 1) + players.size()) % players.size();
        }

        // Do not close the scanner here, as it will close System.in.
        // scanner.close();
    }

    private boolean isValidMove(Card card) {
        return card.getColor() == topCard.getColor() || card.getValue() == topCard.getValue() || card instanceof WildCard || card instanceof WildFourCard;
    }

    private void handleSpecialCardEffects(Card playedCard, Player currentPlayer) {
        if (playedCard instanceof ActionCard) {
            ActionCard actionCard = (ActionCard) playedCard;
            switch (actionCard.getValue()) {
                case DrawTwo:
                    System.out.println(currentPlayer.getName() + " played Draw Two! Next player must draw 2 cards.");
                    nextPlayerDrawsTwo();
                    break;
                case Skip:
                    System.out.println(currentPlayer.getName() + " played Skip! Next player is skipped.");
                    skipNextPlayer();
                    break;
                case Reverse:
                    reverseDirection = !reverseDirection;
                    System.out.println(currentPlayer.getName() + " played Reverse! The turn order is now " + (reverseDirection ? "reversed." : "normal."));
                    break;
                default:
                    // Number cards (0-9) do nothing special, so ignore them.
                    break;
            }
        } else if (playedCard instanceof WildCard || playedCard instanceof WildFourCard) {
            Scanner scanner = new Scanner(System.in);
            System.out.println(currentPlayer.getName() + " played a Wild card! Choose a color: (Red, Yellow, Green, Blue)");
            String color = scanner.nextLine().toUpperCase();

            try {
                Card.CardColor chosenColor = Card.CardColor.valueOf(color);
                topCard = new WildCard(chosenColor, playedCard.getValue());
                System.out.println(currentPlayer.getName() + " changed the color to " + chosenColor + "!");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid color! Defaulting to Red.");
                topCard = new WildCard(Card.CardColor.Red, playedCard.getValue());
            }

            if (playedCard instanceof WildFourCard) {
                System.out.println(currentPlayer.getName() + " played Wild Draw Four! Next player must draw 4 cards.");
                nextPlayerDrawsFour();
            }
        }
    }



    private void nextPlayerDrawsTwo() {
        int nextPlayerIndex = (currentPlayerIndex + (reverseDirection ? -1 : 1) + players.size()) % players.size();
        Player nextPlayer = players.get(nextPlayerIndex);
        for (int i = 0; i < 2; i++) {
            Card drawnCard = deck.drawCard();
            nextPlayer.addCard(drawnCard);
            System.out.println(nextPlayer.getName() + " drew a card: " + drawnCard);
        }
    }

    private void skipNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + (reverseDirection ? -1 : 1) + players.size()) % players.size();
        System.out.println("Skipping next player.");
    }
    private void nextPlayerDrawsFour() {
        int nextPlayerIndex = (currentPlayerIndex + (reverseDirection ? -1 : 1) + players.size()) % players.size();
        Player nextPlayer = players.get(nextPlayerIndex);
        System.out.println(nextPlayer.getName() + " must draw 4 cards!");
        for (int i = 0; i < 4; i++) {
            Card drawnCard = deck.drawCard();
            nextPlayer.addCard(drawnCard);
            System.out.println(nextPlayer.getName() + " drew a card: " + drawnCard);
        }
    }

}
