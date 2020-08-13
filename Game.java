/********************************************
 * This class runs the poker game by calling
 * the play method and communicates with the
 * player, deck, and card classes to replace
 * cards, test hands, identify combinations,
 * and assign winnings to updated bankroll. 
 * 
 * Stephen Wright
 * svw2112
 *******************************************/

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	// types of possible hands
	private static final String ROYAL_FLUSH = "Royal Flush";
	private static final String STRAIGHT_FLUSH = "Straight Flush";
	private static final String FOUR_OF_A_KIND = "Four of a Kind";
	private static final String FULL_HOUSE = "Full House";
	private static final String FLUSH = "Flush";
	private static final String STRAIGHT = "Straight";
	private static final String THREE_OF_A_KIND = "Three of a Kind";
	private static final String TWO_PAIRS = "Two Pairs";
	private static final String ONE_PAIR = "One Pair";
	private static final String NO_PAIR = "No Pair";

	private Player player = new Player();
	private Deck deck = new Deck();
	private Scanner reader = new Scanner(System.in);
	private int currentHandPayout;

	// constructor to test code and build a hand for the player
    // c = clubs, d = diamonds, h = hearts, s = spades; 1-13 = Ace-King
    // example testhand = {"s1", "s13", "s12", "s11", "s10"} = royal flush
    public Game(String[] testHand){
		deck.shuffle();

		if (testHand.length != 5){
			System.out.println();
			System.out.println("A hand must have five cards.");
			System.exit(1);
		}

		for (String card : testHand){
			int suit = 0;
			char suitChar = card.charAt(0);
			if (suitChar == 'c'){
				suit = Card.CLUBS;
			}
			else if (suitChar == 'd'){
				suit = Card.DIAMONDS;
			}
			else if (suitChar == 'h'){
				suit = Card.HEARTS;
			}
			else if (suitChar == 's'){
				suit = Card.SPADES;
			}
			else
			{
				System.out.println();
				System.out.println("Invalid Card: " + card);
				System.exit(1);
			}

			int rank = Integer.parseInt(card.substring(1));
			if (rank < 1 || rank > 13){
				System.out.println();
				System.out.println("Invalid Card: " + card);
				System.exit(1);
			}

			Card newCard = new Card(suit, rank);
			player.addCard(newCard);
		}
		System.out.println();
        System.out.println("New Hand: ");
		printHand(player.getHand());
	}

	// no-argument constructor to play a normal game
	public Game(){
		deck.shuffle();
		for (int i = 0; i < 5; i++){
			Card card = deck.deal();
			player.addCard(card);
		}
		System.out.println();
		System.out.println("New Hand: ");
		printHand(player.getHand());
	}

	// this method should play the game
	public void play(){			
		int[] cardsSelected = askReplaceCards();
		ArrayList<Card> hand = new ArrayList<>(player.getHand());
		for (int selection : cardsSelected){
			Card cardToRemove = hand.get(selection - 1);
			player.removeCard(cardToRemove);
		}
		for (int i = 0; i < cardsSelected.length; i++){
			player.addCard(deck.deal());                  
		}
		if (cardsSelected.length > 0){
			System.out.println();
			System.out.println("Current Hand:");  
			printHand(player.getHand());
		}
		int bet = askBet();
		player.bets(bet);
		String handType = checkHand(player.getHand());
		double winnings = bet * currentHandPayout;
		player.winnings(winnings);
		System.out.println();
		System.out.println("Current Hand: " + handType + ", Payout: " + winnings);
		System.out.println("Bankroll: " + player.getBankroll());

		reader.close();
		System.out.println();
		System.out.println("GAME OVER");
		System.out.println();
	}

    // this method returns a combination of cards and their winning value
	public String checkHand(ArrayList<Card> hand){
		if (isRoyalFlush(hand)){
			currentHandPayout = 250;
			return ROYAL_FLUSH;
		}
		else if (isStraightFlush(hand)){
			currentHandPayout = 50;
			return STRAIGHT_FLUSH;
		}
		else if (isFourOfAKind(hand)){
			currentHandPayout = 25;
			return FOUR_OF_A_KIND;
		}
		else if (isFullHouse(hand)){
			currentHandPayout = 6;
			return FULL_HOUSE;
		}
		else if (isFlush(hand)){
			currentHandPayout = 5;
			return FLUSH;
		}
		else if (isStraight(hand)){
			currentHandPayout = 4;
			return STRAIGHT;
		}
		else if (isThreeOfAKind(hand)){
			currentHandPayout = 3;
			return THREE_OF_A_KIND;
		}
		else if (isTwoPairs(hand)){
			currentHandPayout = 2;
			return TWO_PAIRS;
		}
		else if (isOnePair(hand)){
			currentHandPayout = 1;
			return ONE_PAIR;
		}
		else
		{
			currentHandPayout = 0;
			return NO_PAIR;
		}	
	}

	private boolean isRoyalFlush(ArrayList<Card> hand){
		if (isSameSuit(hand)
				&& hand.get(0).getRank() == Card.ACE 
				&& hand.get(1).getRank() == Card.KING 
				&& hand.get(2).getRank() == Card.QUEEN
				&& hand.get(3).getRank() == Card.JACK
				&& hand.get(4).getRank() == 10){
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean isStraightFlush(ArrayList<Card> hand){
		if (isSameSuit(hand) && isConsecutive(hand)){
			return true;
		}
		else
		{
			return false;
		}	
	}

	private boolean isFourOfAKind(ArrayList<Card> hand){
		if (hand.get(0).getRank() == hand.get(3).getRank()){
			return true;
		}
		else if (hand.get(1).getRank() == hand.get(4).getRank()){
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean isFullHouse(ArrayList<Card> hand){
		// XXXYY
		if (hand.get(0).getRank() == hand.get(2).getRank()
				&& hand.get(3).getRank() == hand.get(4).getRank()){
			return true;
		}
		// XXYYY
		else if (hand.get(0).getRank() == hand.get(1).getRank()
				&& hand.get(2).getRank() == hand.get(4).getRank()){
			return true;
		}
		else
		{
			return false;
		}           
	}

	private boolean isFlush(ArrayList<Card> hand){
		if (isSameSuit(hand)){
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean isStraight(ArrayList<Card> hand){
		if (isConsecutive(hand)){
			return true;
		}
		else
		{
			return false;
		}
	}

	private boolean isThreeOfAKind(ArrayList<Card> hand){
		if (hand.get(0).getRank() == hand.get(2).getRank()){
			return true;
		}
		else if (hand.get(1).getRank() == hand.get(3).getRank()){
			return true;
		}
		else if (hand.get(2).getRank() == hand.get(4).getRank()){
			return true;
		}
		else
		{
			return false;
		}
	}   

	private boolean isTwoPairs(ArrayList<Card> hand){
		// XXYYZ
		if (hand.get(0).getRank() == hand.get(1).getRank()
				&& hand.get(2).getRank() == hand.get(3).getRank()){
			return true;
		}
		// XXYZZ
		else if (hand.get(0).getRank() == hand.get(1).getRank()
				&& hand.get(3).getRank() == hand.get(4).getRank()){
			return true;
		}
		// XYYZZ
		else if (hand.get(1).getRank() == hand.get(2).getRank()
				&& hand.get(3).getRank() == hand.get(4).getRank()){
			return true;
		}
		else
		{
			return false;
		}
	} 

	private boolean isOnePair(ArrayList<Card> hand){
		if (hand.get(0).getRank() == hand.get(1).getRank() 
				|| hand.get(1).getRank() == hand.get(2).getRank()
				|| hand.get(2).getRank() == hand.get(3).getRank() 
				|| hand.get(3).getRank() == hand.get(4).getRank()){             
			return true;
		}
		else
		{
			return false;
		}
	}

	// prompting the user to replace cards
	private int[] askReplaceCards(){
		int[] cards = new int[0];
		System.out.println();
		System.out.print("Which cards would you like to replace? (ex. '1 3 5', or '0' for none): ");
		boolean askAgain = true;
		while (askAgain == true){
			String input = reader.nextLine();
			String[] cardNumbers = input.split(" ");
			if (cardNumbers.length == 1 && cardNumbers[0].equals("0")){
				cards = new int[0];
				askAgain = false;
			}
			else
			{
				boolean valid = true;
				cards = new int[cardNumbers.length];
				for (int i = 0; i < cards.length; i++){
					int cardNumber = Integer.parseInt(cardNumbers[i]);
					if (cardNumber < 1 || cardNumber > 5){
						System.out.println();
						System.out.print("Invalid entry. Please select cards 1-5, or 0 for none: ");
						valid = false;
						break;
					}
					cards[i] = cardNumber;
				}
				if (valid == true){
					askAgain = false;   
				}  
			}         
		}
		return cards;
	}

	// prompting the user for a bet amount
	private int askBet(){
		int bet = 0;
		System.out.println();
		System.out.print("Please enter a bet amount from 1 to 5: ");
		boolean askAgain = true;
		while (askAgain == true){
			bet = reader.nextInt();
			reader.nextLine();
			if(bet > player.getBankroll()){
				System.out.println();
				System.out.print("Insufficient tokens - please enter again: ");
			}
			else if(bet < 1 || bet > 5){
				System.out.println();
				System.out.print("Please select a number from 1 to 5: ");
			}
			else
			{
				askAgain = false;
			}
		}
		return bet;
	}

	private void printHand(ArrayList<Card> hand){
		for (int i = 0; i < 5; i++){
			System.out.println((i + 1) + ". " + hand.get(i));
		}
	}

	private boolean isSameSuit(ArrayList<Card> hand){
		if (hand.get(0).getSuit() == hand.get(1).getSuit() 
				&& hand.get(1).getSuit() == hand.get(2).getSuit() 
				&& hand.get(2).getSuit() == hand.get(3).getSuit() 
				&& hand.get(3).getSuit() == hand.get(4).getSuit())
		{
			return true;
		}
		else
		{
			return false;
		}        
	}

	private boolean isConsecutive(ArrayList<Card> hand){
		if (hand.get(0).getRank() == Card.ACE 
				&& hand.get(1).getRank() == Card.KING 
				&& hand.get(2).getRank() == Card.QUEEN 
				&& hand.get(3).getRank() == Card.JACK
				&& hand.get(4).getRank() == 10){
			return true;
		}
		else if (hand.get(0).getRank() == hand.get(1).getRank() + 1 
				&& hand.get(1).getRank() == hand.get(2).getRank() + 1 
				&& hand.get(2).getRank() == hand.get(3).getRank() + 1 
				&& hand.get(3).getRank() == hand.get(4).getRank() + 1){
			return true;
		}
		else
		{
			return false;
		}
	}
}