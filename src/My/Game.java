package My;
class Game {
public Game(String[] pids) {
	
	deck = new Deck();
    deck.shuffleDeck();
    stockPile = new ArrayList<>();
    players = new ArrayList<>();

    // Initialize players
    for (String pid : pids) {
        if (pid.startsWith("Bot")) {
            players.add(new Bot(pid));
        } else {
            players.add(new Player(pid));
        }
    }

    if (players.size() < 2) {
        throw new IllegalArgumentException("A minimum of 2 players is required to start the game.");
    }

    currentPlayer = 0;
    gameDirection = false;

    // Distribute cards
    for (Player player : players) {
        ArrayList<Card> hand = new ArrayList<>(deck.drawCards(7));
        player.getHand().addAll(hand);
    }
}

// Ensure nextPlayer handles active rotation
private void nextPlayer() {
    do {
        currentPlayer = (!gameDirection) ? 
                        (currentPlayer + 1) % players.size() : 
                        (currentPlayer - 1 + players.size()) % players.size();
    } while (players.get(currentPlayer).getHand().isEmpty()); // Skip players with no cards
}

// Ensure proper handling in playTurn
public void playTurn(String playerId, Card card) {
    try {
        checkPlayerTurn(playerId);

        if (validCardPlay(card)) {
            stockPile.add(card);
            players.get(currentPlayer).getHand().remove(card);

            // Update validColor and validValue
            validValue = card.getValue();
            if (card.getColor() == Card.CardColor.Wild) {
                validColor = chooseColor(playerId);
            } else {
                validColor = card.getColor();
            }

            applyCardEffects(card);
        } else {
            System.out.println("Invalid card play! Please choose a valid card.");
        }
    } catch (InvalidPlayerTurnException e) {
        System.out.println(e.getMessage());
    } finally {
        nextPlayer(); // Always progress the turn
    }
}

// Add player validation in botPlayTurn
public void botPlayTurn() {
    Bot bot = (Bot) players.get(currentPlayer);
    Card topCard = getTopCard();
    Card cardToPlay = bot.playCard(topCard);

    if (cardToPlay != null) {
        playTurn(bot.getName(), cardToPlay);
    } else {
        if (bot.getHand().size() < 7) {
            bot.drawCard(deck);
        } else {
            System.out.println(bot.getName() + " cannot draw more cards.");
        }
        nextPlayer(); // Ensure turn progresses
    }
}
}
