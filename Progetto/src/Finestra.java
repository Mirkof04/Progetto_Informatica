import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Finestra extends JPanel implements ActionListener, KeyListener {
	
	private Timer timer;
	private Map map;
	
	private boolean play = false;
	private int paddleX = 350;
	private int ballX = 390;
	private int ballY = 630;
	private int ballXdir = 1;
	private int ballYdir = -2;
	
	public Finestra() {
		map = new Map(10, 10);
		addKeyListener(this);
		timer = new Timer(0, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 700);
		
		map.draw((Graphics2D) g);
		
		//Paddle
		g.setColor(Color.red);
		g.fillRect(paddleX, 650, 100, 10);
		
		//Ball
		g.setColor(Color.yellow);
		g.fillOval(ballX, ballY, 20, 20);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}
}
