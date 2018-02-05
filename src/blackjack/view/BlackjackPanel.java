package blackjack.view;

import blackjack.controller.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class BlackjackPanel extends JPanel {
	private BlackjackController app;
	private BlackjackFrame frame;
	private SpringLayout appLayout;
	private JLabel a;
	public BlackjackPanel(BlackjackController app) {
		super();
		this.app = app;
		frame = new BlackjackFrame();
		appLayout = new SpringLayout();
		
		setupPanel();
		setupLayout();
		setupListeners();
	}
	private void setupPanel() {
		this.setBackground(Color.GREEN.darker());
		this.setLayout(appLayout);
//		this.add();
	}
	private void setupLayout() {
		
	}
	private void setupListeners() {
		
	}
}
