package gamepacage;

package My;
public class Game {
    private int currentplayer;
    private string[] playerIds;
    
    
    private UnoDeck deck;
    private ArrayList<ArrayList<Unocard>> playerHand;
    private ArrayList<Unocard> stockpile;
    
    
    private Unocard.color validcolor;
    private Unocard.value validvalue;
    
    boolean gameDirection;
    
    public Game(string[] pids) {
        deck = new UnoDeck():
        deck.shuffle();
        stockPile = new ArrayList<Unocard>();
        
        playersIds = pids;
          = 0;
        game Direction = false ;
        
        playerHand = new ArrayList<ArrayList<Unocard>>();
        
        
        for (int i =0; i < pids.lenth; i++) {

        	ArrayList<Unocard> hand = new ArrayList<Unocard>(Array.asList(deck.drawCard(7)));
            playHand.add(hand);
            
          
        } 
        
        
    }
    
    
    public void start (Game game) {
        UnoCard card = deck.drawcard();
        validColor = card.getColor();
        validValue = card.getValue();
        
        if (card.getalue() == UnoCard.wild) {
             start(game);
        }     

        if (card.getValue() == UnoCard.Value.Wild_Four || card.getValue() == UnoCard.Value.DrawTow) {
           start(game);
        }
        if (card.getValue() == UnoCard.Value.skip) {
        	Jlable message = new Jlable(playerIds[currentPlayer] + " was skipped!");
        	message.setFont(new Font("Arial", Font.BOLD, 48)); 
        	JOptionPane.showMessageDialog(null, message);
        	
        	if(gameDirection == false) {
        		currentPlayer = (currentPlayer + 1) % playerIds.length;
        	}
        	
        	else if(gameDirection == true) {
        		currentPlayer = (currentPlayer - 1) % playerIds.length;
            	if (currentPlayer == -1) {
            		currentPlayer = playerIds.length - 1;
            	}
        		 
        	}
        	
        }
        if (card.getValue() == UnoCard.Value.Reverse) {
        	Jlable message = new Jlable(playerIds[currentPlayer] + "The game direction changed! ");
        	message.setFont(new Font("Arial", Font.BOLD, 48)); 
        	JOptionPane.showMessageDialog(null, message);
        	gameDirection = true;
        	currentPlayer = playerIds.length -1;
        }
        
        stockPile.add(card);
        
    }   

}



