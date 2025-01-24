package gamepackage;

import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import java.awt.Font;



// Classe Game
public class Game {
    private int currentPlayer; // Suit l'index du joueur actuel.
    private String[] playerIds; // Tableau pour stocker les identifiants des joueurs.

    private UnoDeck deck; // Représente le paquet de cartes du jeu.
    private ArrayList<ArrayList<UnoCard>> playerHand; // Liste des mains, une pour chaque joueur.
    private ArrayList<UnoCard> stockPile; // La pile de défausse.

    private UnoCard.Color validColor; // La couleur actuellement valide pour le jeu.
    private UnoCard.Value validValue; // La valeur actuellement valide pour le jeu.

    private boolean gameDirection; // Direction du jeu : false pour horaire, true pour anti-horaire.

    // Le constructeur initialise le jeu avec les identifiants des joueurs.
    public Game(String[] pids) {
        deck = new UnoDeck(); // Crée un nouvel objet UnoDeck.
        deck.shuffle(); // Mélange le paquet pour le randomiser.
        stockPile = new ArrayList<>(); // Initialise la pile de défausse.

        playerIds = pids; // Stocke les identifiants des joueurs.
        currentPlayer = 0; // Définit le premier joueur pour commencer.
        gameDirection = false; // La direction par défaut est horaire.

        playerHand = new ArrayList<>(); // Initialise les mains des joueurs.

        // Distribution de 7 cartes à chaque joueur.
        for (int i = 0; i < pids.length; i++) {
            ArrayList<UnoCard> hand = new ArrayList<>(Arrays.asList(deck.drawCard(7))); // Tire 7 cartes.
            playerHand.add(hand); // Ajoute la main à la liste des mains des joueurs.
        }
    }

    // Démarre le jeu en tirant la carte initiale et en définissant les règles.
    public void start(Game game) {
        UnoCard card = deck.drawCard(); // Tire la première carte.
        validColor = card.getColor(); // Définit la couleur valide.
        validValue = card.getValue(); // Définit la valeur valide.

        // Redémarre si la carte est une carte Wild ou un autre type spécial.
        if (card.getValue() == UnoCard.Value.Wild || 
            card.getValue() == UnoCard.Value.Wild_Four || 
            card.getValue() == UnoCard.Value.DrawTwo) {
            start(game);
        }

        // Gère la logique de la carte Skip.
        if (card.getValue() == UnoCard.Value.Skip) {
            JLabel message = new JLabel(playerIds[currentPlayer] + " a été sauté !"); // Informe le joueur.
            message.setFont(new Font("Arial", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null, message);

            // Ajuste l'index du joueur actuel en fonction de la direction.
            if (!gameDirection) {
                currentPlayer = (currentPlayer + 1) % playerIds.length;
            } else {
                currentPlayer = (currentPlayer - 1 + playerIds.length) % playerIds.length;
            }
        }

        // Gère la logique de la carte Reverse.
        if (card.getValue() == UnoCard.Value.Reverse) {
            JLabel message = new JLabel(playerIds[currentPlayer] + " a changé la direction du jeu !"); // Informe les joueurs.
            message.setFont(new Font("Arial", Font.BOLD, 48));
            JOptionPane.showMessageDialog(null, message);
            gameDirection = !gameDirection; // Inverse la direction du jeu.
        }

        stockPile.add(card); // Ajoute la carte initiale à la pile de défausse.
    }

    // Retourne la carte actuellement en haut de la pile.
    public UnoCard getTopCard() {
        return new UnoCard(validColor, validValue); // Crée une nouvelle carte basée sur les règles valides.
    }

    // Retourne l'image de la carte actuellement en haut de la pile.
    public ImageIcon getTopCardImage() {
        return new ImageIcon(validColor + "-" + validValue + ".png"); // Crée un nom de fichier dynamique pour l'image.
    }

    // Vérifie si le jeu est terminé en vérifiant les mains des joueurs.
    public boolean isGameOver() {
        for (String player : this.playerIds) { // Parcourt tous les joueurs.
            if (hasEmptyHand(player)) { // Vérifie si un joueur n'a plus de cartes.
                return true; // Termine le jeu si un joueur a gagné.
            }
        }
        return false; // Continue si personne n'a gagné.
    }

    // Retourne l'identifiant du joueur actuel.
    public String getCurrentPlayer() {
        return this.playerIds[this.currentPlayer]; // Retourne l'identifiant du joueur actif.
    }

    // Récupère l'identifiant d'un joueur précédent en fonction d'un décalage vers l'arrière.
    public String getPreviousPlayer(int i) {
        int index = (this.currentPlayer - i + playerIds.length) % playerIds.length; // Gère les index circulaires.
        return this.playerIds[index]; // Retourne l'identifiant du joueur demandé.
    }

    // Retourne tous les identifiants des joueurs.
    public String[] getPlayers() {
        return playerIds; // Retourne le tableau des identifiants des joueurs.
    }

    // Récupère la main d'un joueur spécifié.
    public ArrayList<UnoCard> getPlayerHand(String pid) {
        int index = Arrays.asList(playerIds).indexOf(pid); // Trouve l'index de l'identifiant du joueur.
        return playerHand.get(index); // Retourne la main correspondante.
    }

    // Retourne la taille de la main d'un joueur spécifique.
    public int getPlayerHandSize(String pid) {
        return getPlayerHand(pid).size(); // Retourne le nombre de cartes dans la main.
    }

    // Retourne une carte spécifique de la main d'un joueur.
    public UnoCard getPlayerCard(String pid, int choice) {
        return getPlayerHand(pid).get(choice); // Retourne la carte à l'index donné.
    }

    // Vérifie si la main d'un joueur est vide.
    public boolean hasEmptyHand(String pid) {
        return getPlayerHand(pid).isEmpty(); // Retourne vrai si le joueur n'a plus de cartes.
    }

    // Valide si une carte peut être jouée légalement.
    public boolean validCardPlay(UnoCard card) {
        return card.getColor() == validColor || card.getValue() == validValue; // Vérifie si la couleur ou la valeur correspond.
    }

    // S'assure que c'est bien le tour du joueur spécifié.
    public void checkPlayerTurn(String pid) throws InvalidPlayerTurnException {
        if (!this.playerIds[this.currentPlayer].equals(pid)) { // Vérifie si l'identifiant du joueur correspond au joueur actuel.
            throw new InvalidPlayerTurnException("Ce n'est pas le tour de " + pid, pid); // Lève une exception si ce n'est pas son tour.
        }
    }

    // La logique du jeu et les méthodes supplémentaires peuvent suivre le même modèle de commentaires détaillés...
}

// Exception personnalisée pour les tours de joueur invalides.
class InvalidPlayerTurnException extends Exception {
    private String playerId; // Identifiant du joueur ayant causé l'exception.

    public InvalidPlayerTurnException(String message, String pid) {
        super(message); // Passe le message à la classe parente.
        this.playerId = pid; // Stocke l'identifiant du joueur.
    }

    public String getPid() {
        return playerId; // Retourne l'identifiant du joueur.
    }
}

// Exception personnalisée pour les soumissions de couleur invalides.
class InvalidColorSubmissionException extends Exception {
    private UnoCard.Color expected; // Couleur attendue.
    private UnoCard.Color actual; // Couleur réellement soumise.

    public InvalidColorSubmissionException(UnoCard.Color expected, UnoCard.Color actual) {
        super("Couleur attendue : " + expected + ", mais obtenue : " + actual); // Construit un message d'erreur.
        this.expected = expected; // Stocke la couleur attendue.
        this.actual = actual; // Stocke la couleur réellement soumise.
    }
}

// Exception personnalisée pour les soumissions de valeur invalides.
class InvalidValueSubmissionException extends Exception {
    private UnoCard.Value expected; // Valeur attendue.
    private UnoCard.Value actual; // Valeur réellement soumise.

    public InvalidValueSubmissionException(UnoCard.Value expected, UnoCard.Value actual) {
        super("Valeur attendue : " + expected + ", mais obtenue : " + actual); // Construit un message d'erreur.
        this.expected = expected; // Stocke la valeur attendue.
        this.actual = actual; // Stocke la valeur réellement soumise.
    }
}
