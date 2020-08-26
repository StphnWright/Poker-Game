/********************************************
 * This class creates a poker player with a
 * starting bankroll amount of 100 tokens.
 * 
 * 
 * 
 * 
 * Stephen Wright
 * svw2112
 *******************************************/

import java.util.ArrayList;
import java.util.Collections;

public class Player {

	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
	private double bet;
    
    	// create a player with an initial bankroll of 100
    	public Player(){		
		hand = new ArrayList<>();
		bankroll = 100.0;   
	}

	// add the given card to the player's hand
	public void addCard(Card card){
		hand.add(card);
        Collections.sort(hand, Collections.reverseOrder());
	}

	// remove the given card from the player's hand
	public void removeCard(Card card){
		hand.remove(card);
	}

	// player makes a bet
    	public void bets(double betAmount){        
        bankroll -= betAmount;	
	}
	
    	// adjust bankroll if player wins
    	public void winnings(double winAmount){      
        bankroll += winAmount;        		
	}

	// return current balance of bankroll
    	public double getBankroll(){       
        return bankroll;		
	}

    	// return current hand
 	public ArrayList<Card> getHand(){
        return hand;       
    }
}
