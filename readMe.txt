PokerTest.java

The main class PokerTest.java communicates with the Game.java class through lines 21 
and 25 of the PokerTest.java class. I started by building the Card, Deck, and Player 
classes to get the smaller building blocks out of the way before moving to the bulk 
of the program in Game.java. In Card.java I leveraged a series of constant variables 
for the Suits and Ranks, to avoid large chunks of code and potential errors in the 
compareTo and toString methods. I also changed the numerical identifiers for Suits 
and Rank to display the name of the card during gameplay, in order to improve usability 
for the player. In Deck.java I used a for loop to create and shuffle the deck. For 
Player.java I imported both ArrayList and Collections, and set the player bankroll to 100. 
Collection.sort was imported and reversed, to order the player’s cards from greatest to 
least as each hand is refreshed. In Game.java I imported ArrayList and Scanner, and once
again employed constant variables to limit chunks of code and potential errors. I also 
changed the instance variable ‘Player p’ to ‘Player player’ so it was easier to read the 
code, and added instance variables Scanner reader and currentHandPayout. Helper methods 
were added to limit chunks of code when creating statements or loops to identify the suit
and rank combinations of each hand. The instructions imply a poker game, but this project
seems more like a one player game where the only potential to 'lose' is when a hand does
not return one of the nine hand combinations. With that in mind, I built both games to end
once a player has selected or rejected replacement cards and enteres a bet. The game will
end, for example, if a player is satisfied with their hand and enters '0' followed by a
bet, or after a player has selected new cards and entered a bet. Once the game runs its
course, it will indicate the pair, payout, and updated bankroll. The initial bet is never
retained in the pot, and a winning hand returns the bankroll minus the bet amount plus
the bet amount times the payout on a winning hand. In Game.java, I have also included
validation for incorrect entries when prompted for bet amounts and replacement cards. 
