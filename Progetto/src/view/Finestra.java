package view;

import javax.swing.JFrame;

import model.Map;

public class Finestra extends JFrame {
	
	private	Gioco game;
	
	public Finestra(Map map) {
		game = new Gioco(map);
		setTitle("Brick Breaker");
		setSize(814, 700);
		setResizable(false);
		setFocusTraversalKeysEnabled(false);
		setFocusable(true);
		add(game);
		setVisible(true);
	}
}
