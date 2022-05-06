package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import model.Map;
/**
 * <h1>Finestra di gioco</h1>
 * 
 * @author Alessandro Salamone
 * @author Forcolin Mirko
 * @author Florea Gabriel
 * @see Gioco
 * @see JFrame
 */
public class Finestra extends JFrame {
	
	private	Gioco game;
	
	/**
	 * <p>Settaggio della finestra di gioco</p>
	 * 
	 * @param map mappa di gioco già configurata 
	 * 
	 * @see ImageIcon
	 */
	public Finestra(Map map) {
		
		game = new Gioco(map);
		setTitle("Brick Breaker");
		setSize(814, 700);
		setResizable(false);
		setFocusTraversalKeysEnabled(false);
		setFocusable(true);
		add(game);
		
		//Aggiunta icona della finestra
		ImageIcon appIcon = new ImageIcon("img/appIcon.png");
		setIconImage(appIcon.getImage());
		
		setVisible(true);
	}
}
