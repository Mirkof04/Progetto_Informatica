package main;


import controller.Controller;
import view.Menu;

/**
 * <h1>Men�</h1>
 * <p>Serve per istanziare men� di inizio gioco e il controller</p> 
 * 
 * @author Alessandro Salamone
 * @author Forcolin Mirko
 * @author Florea Gabriel
 * @see Menu
 * @see Controller
 * 
 */

public class Main {

	public static void main(String[] args) {
		
		Menu menu = new Menu();
		Controller controller = new Controller(menu);
		
		menu.setVisible(true);
		
	}

}