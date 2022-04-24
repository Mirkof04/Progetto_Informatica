package controller;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;

import model.Map;
import view.Finestra;

public class Gameplay implements ActionListener, KeyListener {
	
	public Timer timer;
	private Map map;
	private Finestra gioco;
	private boolean rightPressed = false;
	private boolean leftPressed = false;
	
	public Gameplay() {
		map = new Map();
		gioco = new Finestra(map);
		gioco.addKeyListener(this);
		timer = new Timer(10, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(map.nBricks==0 || map.ballY>680) {
			timer.stop();
		}
		
		if (map.play) {
			// Controllo scontro palla con giocatore
			if (new Rectangle(map.paddleX, 650, 100, 12).intersects(new Rectangle((int)map.ballX, (int)map.ballY, map.BALL, map.BALL))) {
				map.ballYdir = -map.ballYdir;
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
						Rectangle ball = new Rectangle((int)map.ballX, (int)map.ballY, map.BALL, map.BALL);
						
						if(brick.intersects(ball)) {
							map.score += 5;
							map.nBricks--;
							map.setBrick(i, j, 0);
							
							//Scontro con lati cubetti
							if(map.ballX+(map.BALL-Math.abs(map.ballXdir)) <= brick.x || map.ballX+Math.abs(map.ballXdir) >= brick.x+brick.width) {
								map.ballXdir = -map.ballXdir;
							} else {
								map.ballYdir = -map.ballYdir;
							}
							
							break A;
						}
					}
				}
			}

			// Incremento posizione palla
			map.ballX += map.ballXdir;
			map.ballY += map.ballYdir;

			// Rimbalzi laterali palla
			if (map.ballX < 0) {
				map.ballXdir = -map.ballXdir;
			}
			if (map.ballX > 765) {
				map.ballXdir = -map.ballXdir;
			}
			if (map.ballY < 0) {
				map.ballYdir = -map.ballYdir;
			}
			
			//Spostamento giocatore
			if(rightPressed) {
				if (map.paddleX > 670) {
					map.paddleX = 685;
				}
				else {
					moveRight();
				}
			}
			if(leftPressed) {
				if (map.paddleX < 10) {
					map.paddleX = 1;
				}
				else {
					moveLeft();
				}
			}
			gioco.repaint();
		}
	}

	// Pressione tasti
	@Override
	public void keyPressed(KeyEvent e) {
		
		if (!map.play && e.getKeyCode() == KeyEvent.VK_ENTER) {
			reset();
			map.play = true;
		}
		
		// Gestione pressione freccia destra
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}

		// Gestione pressione freccia sinistra
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(rightPressed) {
				rightPressed = false;
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(leftPressed) {
				leftPressed = false;
			}
		}
	}

	// Movimento destra giocatore
	public void moveRight() {
		map.paddleX += 5;
	}

	// Movimento sinistra giocatore
	public void moveLeft() {
		map.paddleX -= 5;
	}

	public void reset() {
		map.reset();
		map.paddleX = 350;
		map.ballX = 390;
		map.ballY = 630;
		map.gradi = Math.floor(Math.random()*40 + 30);
		map.ballXdir = -(int) dirX(map.gradi);
		map.ballYdir = (int) dirY(map.gradi);
		map.score = 0;
		map.nBricks = 30;
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

	public Map getMap() {
		return map;
	}

	public Finestra getGioco() {
		return gioco;
	}

	public Timer getTimer() {
		return timer;
	}
}
