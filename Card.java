/********************************************
 * This class creates and defines attributes
 * for cards included in the complete deck.
 * 
 * 
 * 
 * 
 * Stephen Wright
 * svw2112
 *******************************************/

	public class Card implements Comparable<Card>{

	private static final String[] SUITS = {"Clubs", "Diamonds", "Hearts", "Spades"};
	private static final String[] RANKS = {"Ace", "2", "3", "4", "5", "6", "7", "8",
			"9", "10", "Jack", "Queen", "King"};
	
    	public static final int CLUBS = 1;
    	public static final int DIAMONDS = 2;
    	public static final int HEARTS = 3;
    	public static final int SPADES = 4;
    
    	public static final int ACE = 1;
    	public static final int KING = 13;
    	public static final int QUEEN = 12;
    	public static final int JACK = 11;
    
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank

	// initialize instance variables for suit and rank
	public Card(int suit, int rank){
		this.suit = suit;
		this.rank = rank;
	}

	// method to compare cards so they can be easily sorted
	public int compareTo(Card card){
		if (rank == card.rank)
		{
			return 0;
		}
		else if (rank == ACE)
		{
			return 1;
		}
		else if (card.rank == ACE)
		{
			return -1;
		}
		else if (rank > card.rank)          
		{
			return 1;          
		}
		else
		{
			return -1;
		}
	}

	// returns a string representation of this card
	public String toString(){
		return RANKS[rank - 1] + " of " + SUITS[suit - 1];
	}

	//returns this card's suit
	public int getSuit(){
		return suit;
	}   

	//returns this card's rank
	public int getRank(){
		return rank;
	}  
}
