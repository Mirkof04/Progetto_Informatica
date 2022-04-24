package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Menu;

public class Controller implements ActionListener {

	private Menu menu;
	private Gameplay gameplay;
	
	public Controller(Menu menu) {
		this.menu = menu;
		addActionListener();
	}
	
	public void addActionListener() {
		menu.getBtnNewGame().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(menu.getBtnNewGame() == e.getSource()) {
			gameplay = new Gameplay();
		}
	}

}
