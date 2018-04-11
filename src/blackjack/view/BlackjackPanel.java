package blackjack.view;

import blackjack.controller.*;
import blackjack.model.*;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

import javax.swing.border.*;

public class BlackjackPanel extends JPanel {
	private BlackjackController app;
	private BlackjackFrame frame;
	private Blackjack blackjack;
	private SpringLayout appLayout;
	private JLabel name;
	private JTextPane rules;
	private JButton startButton;
	private JButton newGame;
	private JLabel playerScore;
	private int player;
	private JLabel houseScore;
	private int house;
	private JLabel moneyIcon;
	private JLabel moneyAmount;
	private JButton hitButton;
	private JButton standButton;
	private List<JLabel> houseDeck;
	private JLabel houseCard1;
	private JLabel houseCard2;
	private List<JLabel> playerDeck;
	private JLabel playerCard1;
	private JLabel playerCard2;
	private JLabel pot;
	private JSpinner moneyBet;
	private JButton betButton;
	private JButton nextHand;
	private JLabel background;
	private JLabel resultLabel;
	private JLabel loseIcon;
	private String resource;
	private int bet;
	private boolean stand;
	private boolean lose;
	
	public BlackjackPanel(BlackjackController app) {
		super();
		setFont(new Font("Times New Roman", Font.PLAIN, 12));
		this.app = app;
		appLayout = new SpringLayout();
		blackjack = new Blackjack();
		name = new JLabel("Blackjack");
		bet = 0;
		stand = false;
		lose = false;
		setupLayout();
		houseDeck = new ArrayList<JLabel>();
		playerDeck = new ArrayList<JLabel>();
		houseDeck.add(houseCard1);
		houseDeck.add(houseCard2);
		playerDeck.add(playerCard1);
		playerDeck.add(playerCard2);	
		setupPanel();
		setupListeners();
	}
	
	private void setupPanel() {
		this.setBackground(Color.GREEN.darker());
		this.setLayout(appLayout);
		this.add(name);
		this.add(newGame);
		this.add(loseIcon);
		this.add(resultLabel);
		this.add(playerScore);
		this.add(houseScore);
		this.add(houseCard1);
		this.add(houseCard2);
		this.add(playerCard1);
		this.add(playerCard2);
		this.add(moneyAmount);
		this.add(moneyIcon);
		this.add(moneyBet);
		this.add(betButton);
		this.add(hitButton);
		this.add(standButton);
		this.add(nextHand);
		this.add(pot);
		this.add(background);

		
		setupStart();
	}
	private void setupStart() {
		this.add(startButton);
		this.add(rules);

		deactivate();
	}
	private void setupLayout() {
		appLayout.putConstraint(SpringLayout.NORTH, name, 10, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.WEST, name, 260, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.SOUTH, name, 70, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.EAST, name, 490, SpringLayout.WEST, this);
		name.setPreferredSize(new Dimension(60, 20));
		name.setToolTipText("Blackjack");
		name.setFont(new Font("Times New Roman", Font.BOLD, 50));
		name.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
		newGame = new JButton("New Game");
		newGame.setToolTipText("Start a new game");
		newGame.setFont(new Font("Times New Roman", Font.BOLD, 15));
		appLayout.putConstraint(SpringLayout.NORTH, newGame, 0, SpringLayout.NORTH, name);
		appLayout.putConstraint(SpringLayout.WEST, newGame, 10, SpringLayout.WEST, this);
		playerScore = new JLabel("Your score is: ");
		playerScore.setToolTipText("Player score");
		appLayout.putConstraint(SpringLayout.EAST, playerScore, -30, SpringLayout.EAST, name);
		playerScore.setFont(new Font("Times New Roman", Font.BOLD, 20));
		houseScore = new JLabel("House score is: ");
		houseScore.setToolTipText("House score");
		appLayout.putConstraint(SpringLayout.WEST, houseScore, 300, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.WEST, playerScore, 0, SpringLayout.WEST, houseScore);
		houseScore.setFont(new Font("Times New Roman", Font.BOLD, 20));
		houseCard2 = new JLabel("", new ImageIcon(getClass()
				.getResource("/blackjack/view/images/back.png")), SwingConstants.CENTER);
		houseCard2.setToolTipText("House card 2");
		appLayout.putConstraint(SpringLayout.EAST, houseCard2, -12, SpringLayout.EAST, name);
		houseCard1 = new JLabel("", new ImageIcon(getClass()
				.getResource("/blackjack/view/images/back.png")), SwingConstants.CENTER);
		houseCard1.setToolTipText("House card 1");
		appLayout.putConstraint(SpringLayout.WEST, houseCard1, 12, SpringLayout.WEST, name);
		appLayout.putConstraint(SpringLayout.NORTH, houseScore, 6, SpringLayout.SOUTH, houseCard1);
		appLayout.putConstraint(SpringLayout.NORTH, houseCard1, 31, SpringLayout.SOUTH, name);
		appLayout.putConstraint(SpringLayout.NORTH, houseCard2, 0, SpringLayout.NORTH, houseCard1);
		appLayout.putConstraint(SpringLayout.WEST, houseCard2, 6, SpringLayout.EAST, houseCard1);
		playerCard1 = new JLabel("", new ImageIcon(getClass()
				.getResource("/blackjack/view/images/back.png")), SwingConstants.CENTER);
		playerCard1.setToolTipText("Player card 1");
		appLayout.putConstraint(SpringLayout.NORTH, playerScore, 10, SpringLayout.SOUTH, playerCard1);
		appLayout.putConstraint(SpringLayout.EAST, playerCard1, 0, SpringLayout.EAST, houseCard1);
		playerCard2 = new JLabel("", new ImageIcon(getClass()
				.getResource("/blackjack/view/images/back.png")), SwingConstants.CENTER);
		playerCard2.setToolTipText("Player card 2");
		appLayout.putConstraint(SpringLayout.NORTH, playerCard2, 0, SpringLayout.NORTH, playerCard1);
		appLayout.putConstraint(SpringLayout.WEST, playerCard2, 0, SpringLayout.WEST, houseCard2);
		moneyIcon = new JLabel("", new ImageIcon(getClass()
				.getResource("/blackjack/view/images/MoneyF1.png")), SwingConstants.CENTER);
		moneyIcon.setToolTipText("Wealth Icon");
		moneyAmount = new JLabel("300");
		moneyAmount.setToolTipText("Player’s money");
		moneyAmount.setBorder(new LineBorder(new Color(0, 0, 0)));
		appLayout.putConstraint(SpringLayout.WEST, moneyAmount, 0, SpringLayout.WEST, moneyIcon);
		appLayout.putConstraint(SpringLayout.SOUTH, moneyAmount, -5, SpringLayout.NORTH, moneyIcon);
		appLayout.putConstraint(SpringLayout.EAST, moneyAmount, 0, SpringLayout.EAST, moneyIcon);
		moneyAmount.setOpaque(true);
		moneyAmount.setHorizontalAlignment(SwingConstants.CENTER);
		moneyAmount.setFont(new Font("Times New Roman", Font.BOLD, 25));
		moneyBet = new JSpinner();
		appLayout.putConstraint(SpringLayout.WEST, moneyBet, -1, SpringLayout.WEST, moneyIcon);
		appLayout.putConstraint(SpringLayout.EAST, moneyBet, 1, SpringLayout.EAST, moneyIcon);
		moneyBet.setFont(new Font("Times New Roman", Font.BOLD, 15));
		moneyBet.setToolTipText("Amount of money you’re betting");
		moneyBet.setModel(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 10));
		appLayout.putConstraint(SpringLayout.SOUTH, moneyBet, -5, SpringLayout.NORTH, moneyAmount);
		betButton = new JButton("Bet");
		betButton.setToolTipText("Bet money for this hand");
		appLayout.putConstraint(SpringLayout.WEST, betButton, -5, SpringLayout.WEST, moneyIcon);
		appLayout.putConstraint(SpringLayout.SOUTH, betButton, 0, SpringLayout.NORTH, moneyBet);
		appLayout.putConstraint(SpringLayout.EAST, betButton, 5, SpringLayout.EAST, moneyIcon);
		betButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		background = new JLabel();
		appLayout.putConstraint(SpringLayout.NORTH, playerCard1, 0, SpringLayout.NORTH, background);
		appLayout.putConstraint(SpringLayout.SOUTH, moneyIcon, -13, SpringLayout.SOUTH, background);
		appLayout.putConstraint(SpringLayout.SOUTH, background, -10, SpringLayout.SOUTH, this);
		appLayout.putConstraint(SpringLayout.WEST, moneyIcon, 12, SpringLayout.WEST, background);
		appLayout.putConstraint(SpringLayout.EAST, moneyIcon, -12, SpringLayout.EAST, background);
		background.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		appLayout.putConstraint(SpringLayout.NORTH, background, -230, SpringLayout.SOUTH, this);
		background.setBackground(Color.GRAY);
		background.setOpaque(true);
		appLayout.putConstraint(SpringLayout.WEST, background, 0, SpringLayout.WEST, newGame);
		appLayout.putConstraint(SpringLayout.EAST, background, 10, SpringLayout.EAST, newGame);
		hitButton = new JButton("Hit");
		hitButton.setToolTipText("Get another card");
		appLayout.putConstraint(SpringLayout.NORTH, hitButton, 5, SpringLayout.SOUTH, playerScore);
		hitButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		appLayout.putConstraint(SpringLayout.WEST, hitButton, 0, SpringLayout.WEST, houseCard1);
		appLayout.putConstraint(SpringLayout.EAST, hitButton, 0, SpringLayout.EAST, houseCard1);
		standButton = new JButton("Stand");
		standButton.setToolTipText("Stay and let house play");
		appLayout.putConstraint(SpringLayout.NORTH, standButton, 0, SpringLayout.NORTH, hitButton);
		standButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
		appLayout.putConstraint(SpringLayout.WEST, standButton, 0, SpringLayout.WEST, houseCard2);
		appLayout.putConstraint(SpringLayout.EAST, standButton, 0, SpringLayout.EAST, houseCard2);
		startButton = new JButton("Start");
		startButton.setToolTipText("Start Game");
		startButton.setFont(new Font("Times New Roman", Font.BOLD, 30));
		rules = new JTextPane();
		appLayout.putConstraint(SpringLayout.NORTH, rules, 150, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.WEST, rules, 100, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.SOUTH, rules, 450, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.EAST, rules, -100, SpringLayout.EAST, this);
		appLayout.putConstraint(SpringLayout.WEST, startButton, 200, SpringLayout.WEST, rules);
		appLayout.putConstraint(SpringLayout.EAST, startButton, -200, SpringLayout.EAST, rules);
		rules.setContentType("text/html");
		appLayout.putConstraint(SpringLayout.NORTH, startButton, 25, SpringLayout.SOUTH, rules);
		appLayout.putConstraint(SpringLayout.SOUTH, startButton, 75, SpringLayout.SOUTH, rules);
		rules.setAutoscrolls(false);
		rules.setEditable(false);
		rules.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		rules.setMargin(new Insets(10, 10, 10, 10));
		rules.setBackground(Color.WHITE);
		rules.setToolTipText("Rules for Blackjack");
		rules.setFont(new Font("Times New Roman", Font.BOLD, 15));
		rules.setText("<html><style>#test {text-align:center; font-family: \"Times New Roman\"; font-size:15pt; font-weight: bold;}</style>"
				+ " <div id=\"test\">The name of the game is Blackjack, also referred to as 21, as the goal is to get "
				+ "as close to 21 as possible without exceeding it. You also want to beat the house. All "
				+ "standard cards (2-10) are worth face value, while face cards (Jack, Queen, King) are "
				+ "worth 10, and an ace is worth 11 until your score exceeds 21 of which then the value "
				+ "of the ace is dropped to 1. You’ll first make a bet of how much money you are willing "
				+ "to wager. Both you and the house will receive two cards, though you can only see one "
				+ "of the house cards while the other remains hidden until their turn. You may “Hit” to "
				+ "receive another card that’s added to your score, but beware! If your score goes over "
				+ "21 you “Bust” and lose all your money on the spot. If you don’t bust you can “Stand” "
				+ "when you’re satisfied and then the house plays. The house must hit until they have 17 "
				+ "or more and must stand when they reach that number. If they bust, you get double your "
				+ "money. If you have more than them and didn’t bust, you get double your money. If you "
				+ "have the same as them, you draw and get you money back. If you have less than the "
				+ "house, you lose and they get your money. But! If you get a blackjack, which is being "
				+ "dealt a 21 then you get double your money. Do your best and make some money!</div?</html>");
		pot = new JLabel("0");
		pot.setToolTipText("Pot");
		appLayout.putConstraint(SpringLayout.NORTH, pot, 80, SpringLayout.SOUTH, houseScore);
		appLayout.putConstraint(SpringLayout.WEST, pot, 340, SpringLayout.WEST, this);
		appLayout.putConstraint(SpringLayout.SOUTH, pot, -80, SpringLayout.NORTH, playerCard1);
		appLayout.putConstraint(SpringLayout.EAST, pot, -340, SpringLayout.EAST, this);
		pot.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		pot.setOpaque(true);
		pot.setBackground(Color.GRAY);
		pot.setFont(new Font("Times New Roman", Font.BOLD, 20));
		pot.setHorizontalAlignment(SwingConstants.CENTER);
		nextHand = new JButton("Next Hand");
		nextHand.setToolTipText("Play next hand");
		resultLabel = new JLabel("");
		resultLabel.setToolTipText("Result of the current hand");
		appLayout.putConstraint(SpringLayout.NORTH, nextHand, 10, SpringLayout.SOUTH, resultLabel);
		resultLabel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		resultLabel.setOpaque(true);
		appLayout.putConstraint(SpringLayout.WEST, nextHand, 60, SpringLayout.WEST, resultLabel);
		appLayout.putConstraint(SpringLayout.EAST, nextHand, -60, SpringLayout.EAST, resultLabel);
		appLayout.putConstraint(SpringLayout.NORTH, resultLabel, 30, SpringLayout.NORTH, playerCard1);
		appLayout.putConstraint(SpringLayout.WEST, resultLabel, 20, SpringLayout.EAST, standButton);
		appLayout.putConstraint(SpringLayout.SOUTH, resultLabel, -30, SpringLayout.SOUTH, playerCard1);
		appLayout.putConstraint(SpringLayout.EAST, resultLabel, -20, SpringLayout.EAST, this);
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		loseIcon = new JLabel(new ImageIcon(getClass()
				.getResource("/blackjack/view/images/lose.png")));
		loseIcon.setToolTipText("You’ve lost");
		loseIcon.setEnabled(false);
		appLayout.putConstraint(SpringLayout.NORTH, loseIcon, 150, SpringLayout.NORTH, this);
		appLayout.putConstraint(SpringLayout.WEST, loseIcon, 125, SpringLayout.WEST, this);
	}
	private void setupListeners() {
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				deactivate();
				blackjack.reset();
				blackjack.setupDeck();
				blackjack.shuffleDeck();
				resetBoard();
				resetMoney();
			}
		});
		hitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				if (bet > 0 && !stand) {
					blackjack.getPlayerDeck().add(blackjack.getDeck().get(0));
					blackjack.getDeck().remove(0);
					updateBoard();
				}
			}
		});
		standButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				if (bet > 0 && !stand) {
					stand = true;
					runHouse();
					updateResult();
				}
			}
		});
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				activate();
			}
		});
		nextHand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				if (!lose) {
					resetBoard();
					blackjack.reset();
					blackjack.setupDeck();
					blackjack.shuffleDeck();
					stand = false;
					nextHand.setVisible(false);
					nextHand.setEnabled(false);
				}
			}
		});
		
//startComplexity
		betButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent click) {
				while (Integer.parseInt(moneyBet.getValue().toString()) > Integer.parseInt(moneyAmount.getText())) {
					moneyBet.setValue(Integer.parseInt(moneyBet.getValue().toString()) - 1);
				}
				if (bet == 0) {
					betMoney();
				}
			}
		});
	}
	private void betMoney() {
		if (Integer.parseInt(moneyBet.getValue().toString()) > 0 
				&& Integer.parseInt(moneyBet.getValue().toString()) <= 
				Integer.parseInt(moneyAmount.getText())) {
			pot.setText(moneyBet.getValue().toString());
			int money = Integer.parseInt(moneyAmount.getText());
			money = money - Integer.parseInt(moneyBet.getValue().toString());
			moneyAmount.setText(Integer.toString(money));
			bet++;
			
			blackjack.setupDeck();
			blackjack.shuffleDeck();
			blackjack.dealDeck();
			updateBoard();
		}
	}
//endComplexity
	
	private void activate() {
		startButton.setVisible(false);
		startButton.setEnabled(false);
		rules.setVisible(false);
		rules.setEnabled(false);
		newGame.setVisible(true);
		newGame.setEnabled(true);
		playerScore.setVisible(true);
		playerScore.setEnabled(true);
		houseScore.setVisible(true);
		houseScore.setEnabled(true);
		houseCard1.setVisible(true);
		houseCard1.setEnabled(true);
		houseCard2.setVisible(true);
		houseCard2.setEnabled(true);
		playerCard1.setVisible(true);
		playerCard1.setEnabled(true);
		playerCard2.setVisible(true);
		playerCard2.setEnabled(true);
		moneyAmount.setVisible(true);
		moneyAmount.setEnabled(true);
		moneyIcon.setVisible(true);
		moneyIcon.setEnabled(true);
		hitButton.setVisible(true);
		hitButton.setEnabled(true);
		standButton.setVisible(true);
		standButton.setEnabled(true);
		pot.setVisible(true);
		pot.setEnabled(true);
		betButton.setVisible(true);
		betButton.setEnabled(true);
		moneyBet.setVisible(true);
		moneyBet.setEnabled(true);
		background.setVisible(true);
		background.setEnabled(true);
	}
	private void deactivate() {
		startButton.setVisible(true);
		startButton.setEnabled(true);
		rules.setVisible(true);
		rules.setEnabled(true);
		
		newGame.setVisible(false);
		newGame.setEnabled(false);
		playerScore.setVisible(false);
		playerScore.setEnabled(false);
		houseScore.setVisible(false);
		houseScore.setEnabled(false);
		houseCard1.setVisible(false);
		houseCard1.setEnabled(false);
		houseCard2.setVisible(false);
		houseCard2.setEnabled(false);
		playerCard1.setVisible(false);
		playerCard1.setEnabled(false);
		playerCard2.setVisible(false);
		playerCard2.setEnabled(false);
		moneyAmount.setVisible(false);
		moneyAmount.setEnabled(false);
		moneyIcon.setVisible(false);
		moneyIcon.setEnabled(false);
		hitButton.setVisible(false);
		hitButton.setEnabled(false);
		standButton.setVisible(false);
		standButton.setEnabled(false);
		pot.setVisible(false);
		pot.setEnabled(false);
		resultLabel.setVisible(false);
		resultLabel.setEnabled(false);
		betButton.setVisible(false);
		betButton.setEnabled(false);
		nextHand.setVisible(false);
		nextHand.setEnabled(false);
		moneyBet.setVisible(false);
		moneyBet.setEnabled(false);
		background.setVisible(false);
		background.setEnabled(false);
		loseIcon.setVisible(false);
	}
	
//startAbstraction
	private void updateBoard() {
		updateCards();
		updateScore();
		updateMoney();
		updateResult();
	}
	private void updateCards() {
		houseDeck.get(0).setIcon(new ImageIcon(getClass()
				.getResource("/blackjack/view/images/Back.png")));
		for (int h = 1; h < 2; h++) {
			resource = blackjack.getHouseDeck().get(h).toFile();
			houseDeck.get(h).setIcon(new ImageIcon(getClass()
					.getResource("/blackjack/view/images/" + resource)));
		}
		int test = 1;
		for (int p = blackjack.getPlayerDeck().size() - 1; p > blackjack.getPlayerDeck().size() - 3; p--) {
			resource = blackjack.getPlayerDeck().get(p).toFile();
			playerDeck.get(test--).setIcon(new ImageIcon(getClass()
					.getResource("/blackjack/view/images/" + resource)));
		}
	}
	private void updateScore() {
		player = 0;
		int aceP = 0;
		
		for (int i = 0; i < blackjack.getPlayerDeck().size(); i++) {
			player += blackjack.getPlayerDeck().get(i).getScore();
		}
		for (int i = 0; i < blackjack.getPlayerDeck().size(); i++) {
			if (blackjack.getPlayerDeck().get(i).getNumber() == 1) {
				aceP++;
			}
		}
			if (player > 21) {
				player = player - (10 * aceP);
			}
		playerScore.setText("Your score is: " + player);
		
		house = 0;
		int aceH = 0;
		for (int i = 1; i < blackjack.getHouseDeck().size(); i++) {
			house += blackjack.getHouseDeck().get(i).getScore();
		}
		for (int i = 0; i < blackjack.getHouseDeck().size(); i++) {
			if (blackjack.getHouseDeck().get(i).getNumber() == 1) {
				aceH++;
			}
		}
		if (house > 21) {
			house = house - (10 * aceH);
		}
		houseScore.setText("House score is: " + house);
	}
//endAbstraction
	
	private void updateMoney() {
		int money = Integer.parseInt(moneyAmount.getText());
		if (money == 0 && stand) {
			loseIcon.setVisible(true);
			loseIcon.setEnabled(true);
			lose = true;
		}
		if (money == 0) {
			moneyIcon.setIcon(new ImageIcon(getClass()
					.getResource("/blackjack/view/images/no-money.png")));
		}
		if (money != 0 && money < 500) {
			moneyIcon.setIcon(new ImageIcon(getClass()
					.getResource("/blackjack/view/images/MoneyF1.png")));
		}
		if (money >= 500) {
			moneyIcon.setIcon(new ImageIcon(getClass()
					.getResource("/blackjack/view/images/MoneyF2.png")));
		}
		if (money >= 1000) {
			moneyIcon.setIcon(new ImageIcon(getClass()
					.getResource("/blackjack/view/images/MoneyF3.png")));
		}
		if (money >= 5000) {
			moneyIcon.setIcon(new ImageIcon(getClass()
					.getResource("/blackjack/view/images/MoneyF4.png")));
		}
		if (money >= 10000) {
			moneyIcon.setIcon(new ImageIcon(getClass()
					.getResource("/blackjack/view/images/MoneyF5.png")));
		}
		if (money >= 50000) {
			moneyIcon.setIcon(new ImageIcon(getClass()
					.getResource("/blackjack/view/images/MoneyF6.png")));
		}
		if (money >= 100000) {
			moneyIcon.setIcon(new ImageIcon(getClass()
					.getResource("/blackjack/view/images/MoneyF7.png")));
		}
		if (money >= 500000) {
			moneyIcon.setIcon(new ImageIcon(getClass()
					.getResource("/blackjack/view/images/MoneyF8.png")));
		}
		if (money >= 1000000) {
			moneyIcon.setIcon(new ImageIcon(getClass()
					.getResource("/blackjack/view/images/MoneyF9.png")));
		}
	}
	private void updateResult() {
		String result = resultLabel.getText();
		int potNum = Integer.parseInt(pot.getText());
			int tempHouse = 0;
			int aceH = 0;
			for (int i = 0; i < blackjack.getHouseDeck().size(); i++) {
				tempHouse += blackjack.getHouseDeck().get(i).getScore();
			}
			for (int i = 0; i < blackjack.getHouseDeck().size(); i++) {
				if (blackjack.getHouseDeck().get(i).getNumber() == 1) {
					aceH++;
				}
			}
			if (tempHouse > 21) {
				tempHouse = tempHouse - (10 * aceH);
			}
		if (blackjack.getRound() == 0 && player == 21 && tempHouse == 21) {
			result = "Tie!";
			stand = true;
			runHouse();
		} else if (blackjack.getRound() == 0 && player != 21 && tempHouse == 21) {
			result = "Lose!";
			stand = true;
			runHouse();
		} else if (blackjack.getRound() == 0 && player == 21 && tempHouse != 21) {
			result = "Blackjack!";
			stand = true;
		} else if (blackjack.getRound() != 0 && player == 21 && tempHouse != 21) {
			result = "21!";
			stand = true;
		} else if (player <= 21 && player > house && stand) {
			result = "Win!";
		} else if (house <= 21 && player < house && stand) {
			result = "Lose!";
		} else if (player > 21) {
			result = "Bust!";
			stand = true;
		} else if (house > 21) {
			result = "House Bust!";
			stand = true;
		} else if (player == house && stand) {
			result = "Tie!";
		} else {
			result = Integer.toString(player);
		}
		blackjack.setRound(blackjack.getRound() + 1);
		if (stand) {
			if (result.equals("Bust!") || result.equals("Lose!")) {
				potNum = 0;
			} else if (result.equals("Win!") || result.equals("21!") || result.equals("House Bust!")) {
				potNum = potNum * 2;
			} else if (result.equals("Blackjack!")) {
				potNum = potNum * 3;
			} else if (result.equals("Tie!")) {
				
			}
			moneyAmount.setText(Integer.toString(Integer.parseInt(moneyAmount.getText()) + potNum));
			updateMoney();
			pot.setText("0");
			nextHand.setVisible(true);
			nextHand.setEnabled(true);
		}
		
		resultLabel.setText(result);
		resultLabel.setVisible(true);
		resultLabel.setEnabled(true);
	}
	
	private void resetBoard() {
		playerScore.setText("Your score is: ");;
		houseScore.setText("House score is: ");;
		houseCard1.setIcon(new ImageIcon(getClass()
				.getResource("/blackjack/view/images/Back.png")));
		houseCard2.setIcon(new ImageIcon(getClass()
				.getResource("/blackjack/view/images/Back.png")));
		playerCard1.setIcon(new ImageIcon(getClass()
				.getResource("/blackjack/view/images/Back.png")));
		playerCard2.setIcon(new ImageIcon(getClass()
				.getResource("/blackjack/view/images/Back.png")));
		pot.setText("0");;
		moneyBet.setValue(0);
		resultLabel.setText("");;
		bet = 0;
		stand = false;
		lose = false;
		resultLabel.setVisible(false);
		resultLabel.setEnabled(false);
	}
	private void resetMoney() {
		moneyIcon.setIcon(new ImageIcon(getClass()
				.getResource("/blackjack/view/images/MoneyF1.png")));
		moneyAmount.setText("300");;
	}
	
	private void runHouse() {
		house = 0;
		int aceH = 0;
		for (int i = 0; i < blackjack.getHouseDeck().size(); i++) {
			house += blackjack.getHouseDeck().get(i).getScore();
		}
		for (int i = 0; i < blackjack.getHouseDeck().size(); i++) {
			if (blackjack.getHouseDeck().get(i).getNumber() == 1) {
				aceH++;
			}
		}
		if (house > 21) {
			house = house - (10 * aceH);
		}
		houseScore.setText("House score is: " + house);
		while (house < 17) {
			blackjack.getHouseDeck().add(blackjack.getDeck().get(0));
			blackjack.getDeck().remove(0);
			
			house = 0;
			aceH = 0;
			for (int i = 0; i < blackjack.getHouseDeck().size(); i++) {
				house += blackjack.getHouseDeck().get(i).getScore();
			}
			for (int i = 0; i < blackjack.getHouseDeck().size(); i++) {
				if (blackjack.getHouseDeck().get(i).getNumber() == 1) {
					aceH++;
				}
			}
			if (house > 21) {
				house = house - (10 * aceH);
			}
			houseScore.setText("House score is: " + house);
		}
		int test = 1;
		for (int h = blackjack.getHouseDeck().size() - 1; h > blackjack.getHouseDeck().size() - 3; h--) {
			resource = blackjack.getHouseDeck().get(h).toFile();
			houseDeck.get(test--).setIcon(new ImageIcon(getClass()
					.getResource("/blackjack/view/images/" + resource)));
		}
	}
}
