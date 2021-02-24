/* 

* Grey Kumar 

* CPSC 5002, Seattle University 

* This is free and unencumbered software released into the public domain. 

*/
package gkumar_p3;
import java.util.Random;
import java.util.ArrayList;

/**
 * This class holds the logic for the game and builds the stacks and queues
 * 
 * @author greyk
 *
 */
public class GameModel {
	private String player;
	private Stack<Integer> deck;
	private Queue<Integer> p1Hand = new Queue<>();
	private Queue<Integer> p2Hand = new Queue<>();
	private Stack<Integer> discard;
	
	/**
	 * This constructor sets the array size for the stacks 
	 * and sets the first player
	 */
	public GameModel() {
		deck = new Stack<>(52);
		player = "Andy";
		discard = new Stack<>(52);
	}
	
	/**
	 * This method returns the current player
	 * 
	 * @return the current player
	 */
	public String getPlayer() {
		return player;
	}
	
	/**
	 * This method switches between the two players
	 */
	public void switchPlayer() {
		if (player == ("Andy")) {
			player = "Dwight";
		} else if (player == ("Dwight")) {
			player = "Andy";
		}
	}

	/**
	 * This method generates the deck and calls the shuffle function
	 * 
	 * @return the shuffled stack of cards
	 */
	public Stack<Integer> genDeck() {
		ArrayList<Integer> cards = new ArrayList<>();
		
		for (int i = 0; i < 4; i ++) {
			for (int j = 1; j < 14; j++) {
				cards.add(j);
			}
		}
		//shuffle
		shuffleDeck(cards);
		
		//push onto stack from arraylist
		for (int z = 0; z < cards.size(); z++) {
			deck.push(cards.get(z));
		}
		
		return deck;
	}
	
	/**
	 * This function deals out 7 cards to each player and sets the first card in
	 * the discard pile
	 */
	public void setHand() {
		for(int i = 0; i < 7; i++) {
			p1Hand.enqueue(deck.pop());
			p2Hand.enqueue(deck.pop());
		}
		discard.push(deck.pop());
	}
	
	/**
	 * this method shows hand of each player
	 */
	public void displayHand() {
		if (player == ("Andy")) {
			System.out.println(p1Hand);
		} else if (player == ("Dwight")) {
			System.out.println(p2Hand);
		}
	}
	
	/**
	 * This function returns the top card in the discard pile
	 * 
	 * @return the top card in the discard pile
	 */
	public int discardPile() {
		return discard.peek();	
	}
	
	/**
	 * This function shows each players current card 
	 * 
	 * @return the first card in the queue for each player
	 */
	public int current() {
		if (player == ("Andy")) {
			return p1Hand.peek();
		} else if (player == ("Dwight")) {
			return p2Hand.peek();
		}
		
		return -1;
	}
	
	
	/**
	 * This function swaps out the cards from each hand and onto the discard deck
	 * of from the dealer deck into each hand and calls the flipdeck function
	 * 
	 * @param discard the top card in discard pile
	 * @param current the first card in the hand
	 * @return true if a hand is empty
	 */
	public boolean switchCards(int discard, int current) {
		boolean winner = false;
		
		if (player == ("Andy")) {
			
			if (current < discard) {
				flipDeck();
				p1Hand.enqueue(deck.pop());
				flipDeck();
				p1Hand.enqueue(deck.pop());
				this.discard.push(p1Hand.dequeue());
			} else if (current > discard) {
				this.discard.push(p1Hand.dequeue());
				winner = checkGame();
				if(winner) {
					return true;
				}
			} else if (current == discard) {
				flipDeck();
				p1Hand.enqueue(deck.pop());
				this.discard.push(p1Hand.dequeue());
			}
				
		} else if (player == ("Dwight")) {
			
			if (current < discard) {
				flipDeck();
				p2Hand.enqueue(deck.pop());
				flipDeck();
				p2Hand.enqueue(deck.pop());
				this.discard.push(p2Hand.dequeue());
			} else if (current > discard) {
				this.discard.push(p2Hand.dequeue());
				winner = checkGame();
				if(winner) {
					return true;
				}
			} else if (current == discard) {
				flipDeck();
				p2Hand.enqueue(deck.pop());
				this.discard.push(p2Hand.dequeue());
			}
			
		}
		return winner;
	}
	
	/**
	 * This function flips the discard deck but hold the top facing card
	 * 
	 */
	private void flipDeck() {
		if(deck.empty()) {
			System.out.println();
			int var = this.discard.pop();
			while(!this.discard.empty()) {
				deck.push(this.discard.pop());
			}
			this.discard.push(var);
		}
	}
	
	/**
	 * This function checks if a players hand is empty
	 * 
	 * @return true if a hand is empty
	 */
	private boolean checkGame() {
		if (p1Hand.empty() || p2Hand.empty())
			return true;
		
		return false;
	}
	
	/**
	 * Shuffles the cards using the
	 * <a href="https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle">
	 * Fisher-Yates algorithm</a>
	 * @param cards deck to shuffle
	 */
	private void shuffleDeck(ArrayList<Integer> cards) {
	    Random rand = new Random();
	    for (int i = cards.size(); i > 1; i--) {
	        int j = rand.nextInt(i);
	        int temp = cards.get(i - 1);
	        cards.set(i - 1, cards.get(j));
	        cards.set(j, temp);
	    }
	}
	

}
