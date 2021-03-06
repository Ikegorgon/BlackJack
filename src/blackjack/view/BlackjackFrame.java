package blackjack.view;

import blackjack.controller.*;
import java.awt.*;
import javax.swing.*;

public class BlackjackFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private BlackjackController app;
	private BlackjackPanel appPanel;
	public BlackjackFrame(BlackjackController app) {
		super();
		this.app = app;
		appPanel = new BlackjackPanel(app);
		setupFrame();
	}
	public BlackjackFrame() {
		 
	}
	private void setupFrame() {
		this.setContentPane(appPanel);
		this.setTitle("Blackjack");
		this.setSize(750, 750);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setName("Blackjack");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass()
				.getResource("/blackjack/view/images/blackjackIcon.png")));
	}
	public BlackjackController getController() {
		return app;
	}
}
