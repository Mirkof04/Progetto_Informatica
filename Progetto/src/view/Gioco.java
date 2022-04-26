package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.Map;

public class Gioco extends JPanel {
	
	private Map map;
	
	public Gioco(Map map) {
		this.map = map;
	}
	
	public void paint(Graphics g) {
		// Sfondo
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 700);

		// Punteggio
		g.setColor(Color.white);
		g.setFont(new Font("Calibri", Font.BOLD, 30));
		g.drawString("PUNTEGGIO: " + map.getScore(), 300, 30);

		map.draw((Graphics2D) g);
		
		// Giocatore
		g.setColor(Color.red);
		g.fillRect(map.getPaddleX(), 650, 100, 12);

		// Palla
		g.setColor(Color.yellow);
		g.fillOval((int)map.getBallX(), (int)map.getBallY(), map.getBall(), map.getBall());
		
		// Controllo vincita
		if (map.getnBricks() == 0) {
			map.setPlay(false);
			g.setColor(Color.red);
			g.setFont(new Font("Calibri", Font.BOLD, 35));
			g.drawString("Hai vinto!", 310, 300);
			g.setFont(new Font("Calibri", Font.BOLD, 30));
			g.drawString("Premi INVIO per giocare", 240, 350);
		}

		// Controllo perdita
		if (map.getBallY() > 680) {
			map.setPlay(false);;
			g.setColor(Color.red);
			g.setFont(new Font("Calibri", Font.BOLD, 35));
			g.drawString("Hai perso!", 310, 300);
			g.setFont(new Font("Calibri", Font.BOLD, 30));
			g.drawString("Premi INVIO per giocare", 240, 350);
		}
		g.dispose();
	}
}