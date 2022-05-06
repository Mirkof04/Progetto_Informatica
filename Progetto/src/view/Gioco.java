package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import model.Map;

/**
 * <h1>JPanel della finestra di gioco che controlla la parte grafica</h1>
 * 
 * @author Alessandro Salamone
 * @author Forcolin Mirko
 * @author Florea Gabriel
 *
 * @see JPanel
 * @see Map
 */
public class Gioco extends JPanel {
	
	private Map map;
	
	/**
	 * <p>Assegnazione mappa di gioco</p>
	 *
	 * @param map mappa di gioco
	 */
	public Gioco(Map map) {
		this.map = map;
	}
	 
	/**
	 * <p>Disegna graficamente su JPanel ogni elemento del gioco</p>
	 * 
	 * @see BufferedImage
	 * @see Graphics
	 * @see Color
	 * @see Font
	 * @see ImageIO
	 * @throws IOException
	 */
	public void paint(Graphics g) {
		setDoubleBuffered(true);
		
		//Colore di sfondo
		g.setColor(Color.blue);
		g.fillRect(25, 30, 750, 640);
		
		// Immagini di sfondo sui bordi
		try {
			BufferedImage background = ImageIO.read(new File("img/backgroundGame.png"));
			g.drawImage(background, 0, 0, null);
			g.drawImage(background, 774, 0, null);
			BufferedImage backgroundTop = ImageIO.read(new File("img/backgroundGameTop.png"));
			g.drawImage(backgroundTop, 25, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Disegno spazio punteggio
		g.setColor(new Color(204, 204, 204));
		g.fillRect(300, 2, 250, 28);

		//Disegno stringa punteggio
		g.setColor(Color.black);
		g.setFont(new Font("Calibri", Font.BOLD, 30));
		g.drawString("PUNTEGGIO: " + map.getScore(), 320, 25);

		//Linea di completamento
		g.setColor(Color.yellow);
		g.fillRect(0, 0, ((814/(map.getCol()*map.getRow()))*(map.getCol()*map.getRow()-map.getnBricks())), 5);
		
		//Disegno mappa
		map.draw((Graphics2D) g);
		
		// Giocatore
		g.setColor(Color.red);
		g.fillRect(map.getPaddleX(), 650, 100, 12);

		// Palla
		g.setColor(Color.yellow);
		g.fillOval((int)map.getBallX(), (int)map.getBallY(), map.getBall(), map.getBall());
				
		//Inizio
		if(!map.isPlay()) {
			g.setColor(new Color(255, 255, 255, 100));
			g.fillRect(0, 0, 814, 700);
			g.setColor(new Color(255, 0, 0, 200));
			g.setFont(new Font("Calibri", Font.BOLD, 35));
			g.drawString("Premi INVIO per giocare", 225, 350);
		}
		
		// Controllo vincita
		if (map.getnBricks() == 0) {
			map.setPlay(false);
			g.setColor(new Color(255, 255, 255, 100));
			g.fillRect(0, 0, 814, 700);
			g.setColor(new Color(255, 0, 0, 200));
			g.setFont(new Font("Calibri", Font.BOLD, 40));
			g.drawString("Hai vinto!", 310, 300);
			g.setFont(new Font("Calibri", Font.BOLD, 35));
			g.drawString("Premi INVIO per rigiocare", 220, 350);
		}

		// Controllo perdita
		if (map.getBallY() > 680) {
			map.setPlay(false);
			g.setColor(new Color(255, 255, 255, 100));
			g.fillRect(0, 0, 814, 700);
			g.setColor(new Color(255, 0, 0, 200));
			g.setFont(new Font("Calibri", Font.BOLD, 40));
			g.drawString("Hai perso!", 310, 300);
			g.setFont(new Font("Calibri", Font.BOLD, 35));
			g.drawString("Premi INVIO per rigiocare", 220, 350);
		}
		g.dispose();
	}
	
}
