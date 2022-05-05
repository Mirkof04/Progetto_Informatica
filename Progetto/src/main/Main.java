package main;


import controller.Controller;
import view.Menu;

public class Main {

	public static void main(String[] args) {
		
		Menu menu = new Menu();
		Controller controller = new Controller(menu);
		menu.setVisible(true);
		
	}

}