/********************************************
 * This class creates, shuffles, and deals
 * cards as part of a complete card deck.
 * 
 * 
 * 
 * 
 * Stephen Wright
 * svw2112
 *******************************************/

	public class Deck {

	private Card[] cards;
	private int top; // the index of the top of the deck

	// make a 52 card deck
	public Deck(){        
		cards = new Card[52];
		for (int suit = 1; suit <= 4; suit++){
			for (int rank = 1; rank <= 13; rank++){
				Card card = new Card(suit, rank);
                cards[top] = card;
                top++;  
			}
		}
		top = cards.length - 1;        
	}

	// shuffle the deck here
	public void shuffle(){        
		for (int i = 0; i < 100; i++){
			int i1 = (int) (Math.random() * (top + 1));
			int i2 = (int) (Math.random() * (top + 1));

			// swap the cards
			Card temp = cards[i1];
			cards[i1] = cards[i2];
			cards[i2] = temp;
		}		
	}

	// deal the top card in the deck
	public Card deal(){        
		Card topCard = cards[top];
		cards[top] = null;
		top--;
		return topCard;     
	}

	// get number of cards left in the deck
	public int getSize(){
		return top + 1;
	}
}
