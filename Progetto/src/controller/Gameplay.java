package controller;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
		
		if(map.getnBricks()==0 || map.getBallY()>680) {
			updateRecord();
			timer.stop();
		}
		
		if (map.isPlay()) {
			// Controllo scontro palla con giocatore
			if (new Rectangle(map.getPaddleX(), 650, 100, 12).intersects(new Rectangle((int)map.getBallX(), (int)map.getBallY(), map.getBall(), map.getBall()))) {
				map.setBallYdir(-map.getBallYdir());
			}

			// Controllo scontro con cubetti
			A: for (int i = 0; i < map.getMap().length; i++) {
				for (int j = 0; j < map.getMap()[0].length; j++) {

					if (map.getMap()[i][j] == 1) {

						// Coordinate palla e cubetti
						int brickWidth = map.getBrickWidth();
						int brickHeight = map.getBrickHeight();
						int brickX = j * brickWidth + 140;
						int brickY = i * brickHeight + 50;

						// Creazione rettangoli palla e cubetti
						Rectangle brick = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ball = new Rectangle((int)map.getBallX(), (int)map.getBallY(), map.getBall(), map.getBall());
						
						if(brick.intersects(ball)) {
							
							map.setScore(map.getScore() + 5);
							map.setnBricks(map.getnBricks() - 1);
							map.setBrick(i, j, 0);
							
							//Scontro con lati cubetti
							if(map.getBallX()+(map.getBall()-Math.abs(map.getBallXdir())) <= brick.x || map.getBallX()+Math.abs(map.getBallXdir()) >= brick.x+brick.width) {
								map.setBallXdir(-map.getBallXdir());
							} else {
								map.setBallYdir(-map.getBallYdir());
							}
							break A;
						}
					}
				}
			}

			// Incremento posizione palla
			map.setBallX(map.getBallX() + map.getBallXdir());
			map.setBallY(map.getBallY() + map.getBallYdir());
			
			// Rimbalzi laterali palla
			if (map.getBallX() < 0) {
				map.setBallXdir(-map.getBallXdir());
			}
			if (map.getBallX() > 765) {
				map.setBallXdir(-map.getBallXdir());
			}
			if (map.getBallY() < 0) {
				map.setBallYdir(-map.getBallYdir());
			}
			
			//Spostamento giocatore
			if(rightPressed) {
				if (map.getPaddleX() > 670) {
					map.setPaddleX(685);
				}
				else {
					moveRight();
				}
			}
			if(leftPressed) {
				if (map.getPaddleX() < 10) {
					map.setPaddleX(1);
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
		
		if (!map.isPlay() && e.getKeyCode() == KeyEvent.VK_ENTER) {
			reset();
			map.setPlay(true);
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
		map.setPaddleX(map.getPaddleX() + 5);
	}

	// Movimento sinistra giocatore
	public void moveLeft() {
		map.setPaddleX(map.getPaddleX() - 5);
	}

	public void reset() {
		map.reset();
		map.setPaddleX(350);
		map.setBallX(390);
		map.setBallY(630);
		map.setGradi(Math.floor(Math.random()*40 + 30));
		map.setBallXdir(-(int) dirX(map.getGradi()));
		map.setBallYdir((int) dirY(map.getGradi()));
		map.setScore(0);
		map.setnBricks(30);
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
	
	public void updateRecord() {
		try {
			String path = "record.txt";
			File file = new File(path);
			
			if(file.exists()) {
				System.out.println("FILE "+path+" NON ESISTE");
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String best = br.readLine();
				br.close();
				if(bestScore(best) == 1) {
					System.out.println("new record");
					FileWriter fw = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(map.getScore()+"");
					bw.flush();
					bw.close();
				}
				else if(bestScore(best) == -1) {
					System.out.println("no record");
				}
				else {
					System.out.println("record eguaglieto");
				}
			}
			else if(file.createNewFile()){
				System.out.println("FILE "+path+" CREATO");
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(map.getScore()+"");
				bw.flush();
				bw.close();
			}
			else {
				System.out.println("FILE "+path+" NON PUO ESSERE CREATO");
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public int bestScore(String score) {
		int points = Integer.parseInt(score);
		System.out.println("punti migliori: "+points);
		System.out.println("punti attuali: "+map.getScore());
		if(map.getScore() < points) {
			return -1;
		}
		else if(map.getScore() > points) {
			return 1;
		}
		else {
			return 0;
		}
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
