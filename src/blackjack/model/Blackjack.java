package blackjack.model;

import java.util.*;

public class Blackjack {
	private List<Card> deck;
	private List<Card> house;
	private List<Card> player;
	private int round;
	public Blackjack() {
		deck = new ArrayList<Card>(52);
		player = new ArrayList<Card>();
		house = new ArrayList<Card>();
	}
	public List<Card> setupDeck() {
		for (int i = 1; i < 14; i++) {
			deck.add(new Card("clubs", i));
			deck.add(new Card("spades", i));
			deck.add(new Card("diamonds", i));
			deck.add(new Card("hearts", i));
		}
//		for (int i = 0; i < 52; i++) {
//			System.out.print(deck.get(i).toFile() + " ");
//		}
//		System.out.println("");

		return deck;
	}
	public void shuffleDeck() {
		Collections.shuffle(deck);
		Collections.shuffle(deck);
		Collections.shuffle(deck);
//		for (int i = 0; i < 52; i++) {
//			System.out.print(deck.get(i).toFile() + " ");
//		}
//		System.out.println("");
	}
	public void dealDeck() {
		player.add(deck.get(0));
		deck.remove(0);
		house.add(deck.get(0));
		deck.remove(0);
		player.add(deck.get(0));
		deck.remove(0);
		house.add(deck.get(0));
		deck.remove(0);
		for (int i = 0; i < house.size(); i++) {
			System.out.print("House " + house.get(i).toFile() + " ");
		}
		System.out.println("");
		for (int i = 0; i < player.size(); i++) {
			System.out.print("Player " + player.get(i).toFile() + " ");
		}
		System.out.println("");
//		for (int i = 0; i < deck.size(); i++) {
//			System.out.print(deck.get(i).toFile() + " ");
//		}
//		System.out.println("");
	}
	public void reset() {
		deck.clear();
		house.clear();
		player.clear();
		round = 0;
	}
	public String toFile(int num) {
		String description = "";
		description += deck.get(num).getSuite() + deck.get(num).getNumber() + ".png";;
		return description;
	}
	public List<Card> getDeck() {
		return deck;
	}
	public List<Card> getHouseDeck() {
		return house;
	}
	public List<Card> getPlayerDeck() {
		return player;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int num) {
		round = num;
	}
}
