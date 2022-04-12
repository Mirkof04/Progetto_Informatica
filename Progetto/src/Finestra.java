import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
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
		map = new Map(5, 5);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(10, this);
		timer.start();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 700);
		
		map.draw((Graphics2D) g);
		
		//Paddle
		g.setColor(Color.red);
		g.fillRect(paddleX, 650, 100, 12);
		
		//Ball
		g.setColor(Color.yellow);
		g.fillOval(ballX, ballY, 20, 20);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(play) {
			ballX += ballXdir;
			ballY += ballYdir;
			
			if(new Rectangle(paddleX, 650, 100, 12).intersects(new Rectangle(ballX, ballY, 20, 20))) {
				ballYdir = -ballYdir;
			}
			
			if(ballX < 0) {
				ballXdir = -ballXdir;
			}
			if(ballX > 780) {
				ballXdir = -ballXdir;
			}
			if(ballY < 0) {
				ballYdir = -ballYdir;
			}
			repaint();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(paddleX > 670) {
				paddleX = 685;
			}
			else {
				moveRight();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT){
			if(paddleX < 10) {
				paddleX = 1;
			}
			else {
				moveLeft();
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	public void moveRight() {
		play = true;
		paddleX += 20;
	}
	
	public void moveLeft() {
		play = true;
		paddleX -= 20;
	}
}
