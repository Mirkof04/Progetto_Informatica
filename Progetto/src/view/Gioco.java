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

import model.Map;

public class Gioco extends JPanel {
	
	private Map map;
	
	public Gioco(Map map) {
		this.map = map;
	}
	
	public void paint(Graphics g) {
		
		// Sfondo
		try {
			BufferedImage image = ImageIO.read(new File("img.png"));
			g.drawImage(image, 0, 0, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Punteggio
		
		g.setColor(Color.white);
		g.fillRect(280, 0, 150, 30);
		
		g.setColor(Color.blue);
		g.setFont(new Font("Calibri", Font.BOLD, 30));
		g.drawString("PUNTEGGIO: " + map.getScore(), 300, 25);

		map.draw((Graphics2D) g);
		
		// Giocatore
		g.setColor(Color.red);
		g.fillRect(map.getPaddleX(), 650, 100, 12);

		// Palla
		g.setColor(Color.yellow);
		g.fillOval((int)map.getBallX(), (int)map.getBallY(), map.getBall(), map.getBall());
		
		//Inizio
		if(!map.isPlay()) {
			g.setColor(new Color(255, 255, 255, 200));
			g.fillRect(200, 250 , 400, 180);
			g.setColor(new Color(255, 0, 0, 200));
			g.setFont(new Font("Calibri", Font.BOLD, 35));
			g.drawString("Premi INVIO per giocare", 225, 350);
		}
		
		// Controllo vincita
		if (map.getnBricks() == 0) {
			map.setPlay(false);
			g.setColor(Color.red);
			g.setFont(new Font("Calibri", Font.BOLD, 40));
			g.drawString("Hai vinto!", 310, 300);
			g.setFont(new Font("Calibri", Font.BOLD, 35));
			g.drawString("Premi INVIO per rigiocare", 220, 350);
		}

		// Controllo perdita
		if (map.getBallY() > 680) {
			map.setPlay(false);;
			g.setColor(Color.red);
			g.setFont(new Font("Calibri", Font.BOLD, 40));
			g.drawString("Hai perso!", 310, 300);
			g.setFont(new Font("Calibri", Font.BOLD, 35));
			g.drawString("Premi INVIO per rigiocare", 220, 350);
		}
		g.dispose();
	}
}
