/* 

* Grey Kumar 

* CPSC 5002, Seattle University 

* This is free and unencumbered software released into the public domain. 

*/
package gkumar_p3;
import java.util.Scanner;

/**
 * This program simulates the card game WAR. each player is dealt 7 cards from 
 * a 52 card deck. they then compare the first card in their hand with the card 
 * that is on the discard pile.I if higher, then do nothing, if lower, then they 
 * take 2 cards. if equal, then they take one card. At the end of each turn the 
 * player sets their card face up on the discard pile
 * 
 * @author Grey Kumar
 * @version 1.0
 *
 */
public class SillyCardGame {

	/**
	 * This function makes the game model object, switches players, initializes
	 * the deck and prints the output for each players turn
	 * 
	 * @param args A string array containing the command line arguments.
	 */
	public static void main(String[] args) {

		//create variables
		final String YES= "y";
		String repeat;
		Scanner keyboard = new Scanner(System.in);
		boolean win = true;
		
		//welcome
		System.out.println("Welcome to the Card Game!\nEach player will be dealt "
				+ "7 cards from 52 card deck.\nThe players will take placing cards on "
				+ "top of the discard stack.\nIf the players card is higher than the"
				+ " one on the discard stack, their turn is over.\nHowever, if the "
				+ "players card is lower than the one on the stack,\nthey must draw "
				+ "two cards from deal stack. The first one to run out of cards wins.");
		System.out.println();
		
		do {
			//setup game
			GameModel game = new GameModel();
			game.genDeck();
			game.setHand();
			game.switchPlayer();
			do {
				game.switchPlayer();
				System.out.println(game.getPlayer() + ", it's your turn.\nCards:");
				//show cards
				game.displayHand();
				System.out.println("Discard pile card: " + game.discardPile());
				System.out.println("Your current card: " + game.current());
				System.out.print("Press RETURN to take a turn ");
				String input = keyboard.nextLine();
				
				//determine if higher or lower or equal
				if(input.equals("")) {
					if (game.current() < game.discardPile()) {
						win = game.switchCards(game.discardPile(), game.current());
						if (!win) {
							System.out.println("Your card is LOWER, pick up two cards.");
						} 
					} else if (game.current() > game.discardPile()){
						//check for win
						win = game.switchCards(game.discardPile(), game.current());
						if (!win) {
							System.out.println("Your card is HIGHER, turn is over.");
						} else 
							System.out.println("You won the game!");		
					} else if (game.current() == game.discardPile()) {
						win = game.switchCards(game.discardPile(), game.current());
						if (!win)
							System.out.println("Your card is EQUAL, pick up one card.");
					}	
					
					System.out.println();
				}	
			}while(!win);
			
			//prompt to play again
			System.out.println("The game has finished");
			System.out.println();
			System.out.println("Play again? (y/n):");
			repeat = keyboard.nextLine();
			System.out.println();
		}while(repeat.equalsIgnoreCase(YES));
		
		//goodbye
		System.out.println("Thanks for playing!");
		
	}

}