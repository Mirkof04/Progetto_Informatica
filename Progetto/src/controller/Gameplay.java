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

/**
 * <h1>Controller per la gestione del gameplay </h1>
 * 
 * @author Alessandro Salamone
 * @author Forcolin Mirko
 * @author Florea Gabriel
 * 
 * @see ActionListener
 * @see KeyListener
 * @see Timer
 * @see Map
 * @see Finestra
 */
public class Gameplay implements ActionListener, KeyListener {
	
	public Timer timer;
	private Map map;
	private Finestra gioco;
	private boolean rightPressed = false;
	private boolean leftPressed = false;
	private String nickName;
	
	/**
	 * <p>Istanzia mappa.
	 * Aggiunge KeyListener.
	 * Setta timer che chiamerà in automatico l'actionPerformed ogni tot secondi</p>
	 * 
	 * @param nickname nome giocatore inserito nel menù
	 * 
	 * @see Timer
	 */
	public Gameplay(String nickname) {
		map = new Map();
		gioco = new Finestra(map);
		gioco.addKeyListener(this);
		this.nickName = nickname;
		timer = new Timer(6, this);
		timer.start();
	}
 
	/**
	 * <p>Gestione completa del gioco.
	 * Controlla vittoria e sconfitta.
	 * Controllo collisioni con mattoncini, lati mappa e giocatore.
	 * Incremento movimenti pallina e giocatore</p>
	 * 
	 * @see ActionEvent
	 * @see Rectangle
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(map.getnBricks()==0 || map.getBallY()>680) {
			updateRecord();
			updateRanking();
			timer.stop();
		}
		
		if (map.isPlay()) {
			
			// Incremento posizione palla
			map.setBallX(map.getBallX() + map.getBallXdir());
			map.setBallY(map.getBallY() + map.getBallYdir());
			
			if(map.getBallY() > 400) {
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
			}
			else {
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
							Rectangle ball = new Rectangle(map.getBallX(), map.getBallY(), map.getBall(), map.getBall());
							
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
			}
			
			// Rimbalzi laterali palla
			if (map.getBallX()<26 || map.getBallX() > 750) {
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
	/**
	 * <p>Verifica quando un tasto viene premuto</p>
	 * 
	 * @see KeyEvent
	 */
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

	/**
	 * <p>Verifica quando un tasto viene rilasciato</p>
	 * 
	 * @see KeyEvent
	 */
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
	/**
	 * <p>Muove il giocatore verso destra</p>
	 */
	public void moveRight() {
		map.setPaddleX(map.getPaddleX() + 5);
	}

	// Movimento sinistra giocatore
	/**
	 * <p>Muove il giocatore verso sinistra</p>
	 */
	public void moveLeft() {
		map.setPaddleX(map.getPaddleX() - 5);
	}

	/**
	 * <p>Resetta tutte le impostazioni di gioco</p>
	 * 
	 * @see Math
	 */
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
	
	/**
	 * <p>Calcolo direzione X di inizio gioco</p>
	 * 
	 * @param Degrees angolo random tra 30° e 70°
	 * @return coseno dell'angolo che sarà la direzione X di inizio gioco
	 */
	public double dirX(double Degrees) {
		double radianti = Math.toRadians(Degrees);
		double coseno = - Math.cos(radianti)*5;
		if((Math.random()*1) < 0.5) {
			coseno = -coseno;
		}
		return coseno;
	}
	
	/**
	 * <p>Calcolo direzione Y di inizio gioco</p>
	 * 
	 * @param Degrees angolo random tra 30° e 70°
	 * @return seno dell'angolo che sarà la direzione Y di inizio gioco
	 */
	public double dirY(double Degrees) {
		double radianti = Math.toRadians(Degrees);
		return Math.sin(radianti)*5;
	}
	
	/**
	 * <p>Aggiornamento del file con il record di sempre degli utenti su file</p>
	 * 
	 * @see File
	 * @see FileReader
	 * @see BufferedReader
	 * @see FileWriter
	 * @see BufferedWriter
	 * @throws IOException
	 */
	public void updateRecord() {
		try {
			String path = "record.txt";
			File file = new File(path);
			
			if(file.exists()) {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				br.readLine();
				String best = br.readLine();
				br.close();
				if(bestScore(best) == 1) {
					FileWriter fw = new FileWriter(file);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(nickName+"\n"); 
					bw.write(map.getScore()+"");
					bw.flush();
					bw.close();
				}
			}
			else {
				file.createNewFile();
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(nickName+"\n");
				bw.write(map.getScore()+"");
				bw.flush();
				bw.close(); 
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * <p>Aggiornamento della classifica virtuale su file</p>
	 * 
	 * @see ArrayList
	 * @see File
	 * @see FileReader
	 * @see BufferedReader
	 * @see FileWriter
	 * @see BufferedWriter
	 * @throws IOException
	 */
	public void updateRanking() {
		
		int pos = rankingPosition(map.getScore());
	
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
	
	/**
	 * <p>Calcolo della posizione del giocatore nella classifica virtuale</p>
	 * 
	 * @param score punteggio raggiunto dal giocatore
	 * @return posizione nella classifica virtuale del giocatore
	 */
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
	
	/**
	 * <p>Controlla se il punteggio raggiunto è un nuovo record</p>
	 *
	 * @param score punteggio raggiunto dal giocatore
	 * @return se il record è stato superato ritorna 1. Se è stato eguagliato ritorna 0. Sennò ritorna -1.
	 * 
	 * @see Integer
	 */
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
