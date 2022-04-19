import java.awt.Color;
import java.awt.Font;
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

	private static final int ROW = 5;
	private static final int COL = 6;
	private static final int BALL = 20;
	
	private Timer timer;
	private Map map;
	
	private boolean play = false;
	private int score = 0;
	private int nBricks = 30;
	
	private int paddleX = 350;
	private int ballX = 390;
	private int ballY = 630;
	
	
	//Generazione direzione iniziale
	private double gradi = Math.floor(Math.random()*40 + 30);
	private int ballXdir = -(int) dirX(gradi);
	private int ballYdir = (int) dirY(gradi);
	
	public Finestra() {
		map = new Map(COL, ROW);
		addKeyListener(this);
		setFocusTraversalKeysEnabled(false);
		setFocusable(true);
		timer = new Timer(10, this);
		timer.start();
	}

	public void paint(Graphics g) {
		// Sfondo
		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 700);

		// Punteggio
		g.setColor(Color.white);
		g.setFont(new Font("Calibri", Font.BOLD, 30));
		g.drawString("PUNTEGGIO: " + score, 300, 30);

		map.draw((Graphics2D) g);

		// Giocatore
		g.setColor(Color.red);
		g.fillRect(paddleX, 650, 100, 12);

		// Palla
		g.setColor(Color.yellow);
		g.fillOval((int)ballX, (int)ballY, BALL, BALL);

		// Controllo vincita
		if (nBricks == 0) {
			play = false;
			g.setColor(Color.red);
			g.setFont(new Font("Calibri", Font.BOLD, 35));
			g.drawString("Hai vinto!", 230, 300);
			g.setFont(new Font("Calibri", Font.BOLD, 30));
			g.drawString("Premi INVIO per giocare", 230, 350);
			timer.stop();
		}

		// Controllo perdita
		if (ballY > 680) {
			play = false;
			g.setColor(Color.red);
			g.setFont(new Font("Calibri", Font.BOLD, 35));
			g.drawString("Hai perso!", 230, 300);
			g.setFont(new Font("Calibri", Font.BOLD, 30));
			g.drawString("Premi INVIO per giocare", 230, 350);
			timer.stop();
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (play) {

			// Controllo scontro palla con giocatore
			if (new Rectangle(paddleX, 650, 100, 12).intersects(new Rectangle((int)ballX, (int)ballY, BALL, BALL))) {
				ballYdir = -ballYdir;
			}

			// Controllo scontro con cubetti
			A: for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {

					if (map.map[i][j] == 1) {

						// Coordinate palla e cubetti
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						int brickX = j * brickWidth + 140;
						int brickY = i * brickHeight + 50;

						// Creazione rettangoli palla e cubetti
						Rectangle brick = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ball = new Rectangle((int)ballX, (int)ballY, BALL, BALL);
						
						if(brick.intersects(ball)) {
							score += 5;
							nBricks--;
							map.setBrick(i, j, 0);
							
							//Scontro con lati cubetti
							if(ballX+(BALL-Math.abs(ballXdir)) <= brick.x || ballX+Math.abs(ballXdir) >= brick.x+brick.width) {
								ballXdir = -ballXdir;
							} else {
								ballYdir = -ballYdir;
							}

							break A;
						}
					}
				}
			}

			// Incremento posizione palla
			ballX += ballXdir;
			ballY += ballYdir;

			// Rimbalzi laterali palla
			if (ballX < 0) {
				ballXdir = -ballXdir;
			}
			if (ballX > 765) {
				ballXdir = -ballXdir;
			}
			if (ballY < 0) {
				ballYdir = -ballYdir;
			}
			repaint();
		}
	}

	// Pressione tasti
	@Override
	public void keyPressed(KeyEvent e) {
		if (!play && e.getKeyCode() == KeyEvent.VK_ENTER) {
			reset();
			play = true;
		}
		// Gestione pressione freccia destra
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (paddleX > 670) {
				paddleX = 685;
			} else {
				moveRight();
			}
		}

		// Gestione pressione freccia sinistra
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (paddleX < 10) {
				paddleX = 1;
			} else {
				moveLeft();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	// Movimento destra giocatore
	public void moveRight() {
		play = true;
		paddleX += BALL;
	}

	// Movimento sinistra giocatore
	public void moveLeft() {
		play = true;
		paddleX -= BALL;
	}

	public void reset() {
		map = new Map(COL, ROW);
		paddleX = 350;
		ballX = 390;
		ballY = 630;
		gradi = Math.floor(Math.random()*40 + 30);
		ballXdir = -(int) dirX(gradi);
		ballYdir = (int) dirY(gradi);
		score = 0;
		timer.start();
	}
	
	public double dirX(double gradi) {
		double radianti = Math.toRadians(gradi);
		double coseno = - Math.cos(radianti)*5;
		if((Math.random()*1) < 0.5) {
			coseno = -coseno;
		}
		return coseno;
	}
	
	public double dirY(double gradi) {
		double radianti = Math.toRadians(gradi);
		return Math.sin(radianti)*5;
	}
}
