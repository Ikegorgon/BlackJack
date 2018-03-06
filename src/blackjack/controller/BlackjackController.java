package blackjack.controller;

import blackjack.view.*;
import blackjack.model.*;

public class BlackjackController {
	private BlackjackFrame appFrame;
	private Blackjack blackjack;
	void start() {
		appFrame = new BlackjackFrame(this);
	}
	public BlackjackFrame getFrame() {
		return appFrame;
	}
	public Blackjack getBlackjack() {
		return blackjack;
	}
}
