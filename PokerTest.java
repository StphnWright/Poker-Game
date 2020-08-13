/********************************************
 * This is a test class for the Player, Game,
 * Deck, and Card classes. The PokerTest class
 * must work with the other classes and should
 * not be altered. 
 * 
 * 
 * Stephen Wright
 * svw2112
 *******************************************/

public class PokerTest{
    
    //this class must remain unchanged
    //your code must work with this test class
 
    public static void main(String[] args){
        if (args.length<1){
            Game g = new Game();
            g.play();
        }
        else{
            Game g = new Game(args);
            g.play();
        }
    }

}