package blackjack.model;

public class Card {
	private String suite;
	private int number;
	private int score;
	
	public Card(String suite, int number) {
		this.suite = suite;
		this.number = number;
		if (number > 10) {
			this.score = 10;
		} else {
			this.score = number;
		}
	}
	public String toFile() {
		String description = "";
		description += suite + number + ".png";;
		return description;
	}
	
	public String getSuite() {
		return suite;
	}
	public int getNumber() {
		return number;
	}
	public int getScore() {
		return score;
	}
	public void setSuite(String suite) {
		this.suite = suite;
	}
	public void setNumber(int number) {
		this.number = number;
	}
}
