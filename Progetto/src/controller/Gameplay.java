package controller;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;

import model.Map;
import view.Finestra;

public class Gameplay implements ActionListener, KeyListener {
	
	public Timer timer;
	private Map map;
	private Finestra gioco;
	private boolean rightPressed = false;
	private boolean leftPressed = false;
	private String nickName;
	
	public Gameplay(String nickname) {
		map = new Map();
		gioco = new Finestra(map);
		gioco.addKeyListener(this);
		this.nickName = nickname;
		timer = new Timer(10, this);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(map.getnBricks()==0 || map.getBallY()>680) {
			updateRecord();
			updateRanking();
			timer.stop();
		}
		
		if (map.isPlay()) {
			// Controllo scontro palla con giocatore
			if (new Rectangle(map.getPaddleX(), 650, 30, 12).intersects(new Rectangle(map.getBallX(), map.getBallY(), map.getBall(), map.getBall()))) {
				map.setBallYdir(-map.getBallYdir());
				map.setBallXdir(map.getBallXdir() - 2);
			}
			else if(new Rectangle(map.getBallX(), map.getBallY(), 20, 20).intersects(new Rectangle(map.getPaddleX() + 70, 650, 30, 12))){
				map.setBallYdir(-map.getBallYdir());
				map.setBallXdir(map.getBallXdir() + 1);
			}
			else if(new Rectangle(map.getBallX(), map.getBallY(), 20, 20).intersects(new Rectangle(map.getPaddleX() + 30, 650, 40, 12))){
				map.setBallYdir(-map.getBallYdir());
			}

			// Controllo scontro con cubetti
			A: for (int i = 0; i < map.getMap().length; i++) {
				for (int j = 0; j < map.getMap()[0].length; j++) {

					if (map.getMap()[i][j] == 1) {

						// Coordinate palla e cubetti
						int brickWidth = map.getBrickWidth();
						int brickHeight = map.getBrickHeight();
						int brickX = j * brickWidth + 150;
						int brickY = i * brickHeight + 70;

						// Creazione rettangoli palla e cubetti
						Rectangle brick = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ball = new Rectangle((int)map.getBallX(), (int)map.getBallY(), map.getBall(), map.getBall());
						
						if(brick.intersects(ball)) {
							
							map.setScore(map.getScore() + 10);
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
			if (map.getBallX() < 26) {
				map.setBallXdir(-map.getBallXdir());
			}
			if (map.getBallX() > 750) {
				map.setBallXdir(-map.getBallXdir());
			}
			if (map.getBallY() < 31) {
				map.setBallYdir(-map.getBallYdir());
			}
			
			//Spostamento giocatore
			if(rightPressed) {
				if (map.getPaddleX() > 670) {
					map.setPaddleX(675);
				}
				else {
					moveRight();
				}
			}
			if(leftPressed) {
				if (map.getPaddleX() < 30) {
					map.setPaddleX(25);
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
		map.setDegrees(Math.floor(Math.random()*40 + 30));
		map.setBallXdir(-(int) dirX(map.getDegrees()));
		map.setBallYdir((int) dirY(map.getDegrees()));
		map.setScore(0);
		map.setnBricks(30);
		timer.start();
	}
	
	public double dirX(double Degrees) {
		double radianti = Math.toRadians(Degrees);
		double coseno = - Math.cos(radianti)*5;
		if((Math.random()*1) < 0.5) {
			coseno = -coseno;
		}
		return coseno;
	}
	
	public double dirY(double Degrees) {
		double radianti = Math.toRadians(Degrees);
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
				br.readLine();
				String best = br.readLine();
				br.close();
				if(bestScore(best) == 1) {
					System.out.println("new record");
					FileWriter fw = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(nickName+"\n"); 
					bw.write(map.getScore()+"");
					bw.flush();
					bw.close();
				}
				else if(bestScore(best) == -1) {
					System.out.println("no record");
				}
				else {
					System.out.println("record eguagliato");
				}
			}
			else if(file.createNewFile()){
				System.out.println("FILE "+path+" CREATO");
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(nickName+"\n");
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
	
	public void updateRanking() {
		
		int pos = rankingPosition(map.getScore());
		System.out.println("Posizione: "+pos);
		if(pos != -1) {
			
			try {
				
				File file = new File("ranking.txt");
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				
				ArrayList<String> names = new ArrayList<>();
				ArrayList<String> points = new ArrayList<>();
				
				int i = 0;
				
				String line = br.readLine();
				while(line != null) {
					if(i%2==0 || i==0) {
						names.add(line);
					}
					else {
						points.add(line);
					}
					i++;
					line = br.readLine();
				}
				
				names.add(pos-1, nickName);
				names.remove(10);
				points.add(pos-1, map.getScore()+"");
				points.remove(10);
				
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				
				for(i=0;i<names.size();i++) {
					bw.write(names.get(i)+"\n");
					bw.write(points.get(i)+"\n");
				}
				bw.flush();
				bw.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else {
			return;
		}
	}
	
	public int rankingPosition(int score) {
		
		int cont = 0;
		int i = 0;
		
		try {
			
			File file = new File("ranking.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			
			String line = br.readLine();
			while(line != null) {
				i++;
				line = br.readLine();
				if(i%2!=0) {
					if(Integer.parseInt(line) < score) {
						cont++;
						break;
					}
					else {
						cont++;
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(cont < 10) {
			return cont;
		}
		else {
			return -1;
		}
	}
	
	public int bestScore(String score) {
		int points = Integer.parseInt(score);
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
