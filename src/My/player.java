import java.util.Arraylist;
package My;
	public class player {
	private string nom ;
	private Arraylist<Card> PlayerCards;
	public int nbrCards;
	public player(string nom  ) {
	 this.nom=nom;
	 this.PlayerCards=new Arraylist<>();
	 }
	 public string getnom() {
	  return nom;
	 }
	public Arraylist<Card> getPlayerCards(){
	 return playerCards;
	 {
	public void ajouterCarte(Card card) {
	 PlayerCards.add(card);
	 nbrCards++;
	}
	public Card lancerCarte(int i) {
	 while(i>0) {
	  if(i<nbrCards) {
	   return PlayerCards.remove(i);
	   nbrCards++;
	  }
	  
	 }
	}


	public void afficherCartes() {
	 System.out.println("les cartes de"+nom+"sont:");
	 for(int j = 0;j<nbrCartes;j++) {
	  System.out.println(PlayerCards.get(j));
	}
	}
	public void NombreDeCarte() {
	 System.out.println(nbrCards);
	}public void winner() {
	 if (nbrcards = 0) {
	 system.out.println(nom+"a gange.");
	 
	 }else {system.out.println(nom+",tas pas encore gange");
	}
	 

	}
}
