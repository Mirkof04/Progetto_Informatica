package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * <h1>Mappa di gioco</h1>
 * 
 * @author Alessandro Salamone
 * @author Forcolin Mirko
 * @author Florea Gabriel
 * 
 * @see Math
 */
public class Map {
	
	private static final int ROW = 5;
	private static final int COL = 6;
	private static final int BALL = 20;
	
	private int map[][];
	private int brickWidth;
	private int brickHeight;
	
	private int paddleX = 350;
	private int ballX = 390;
	private int ballY = 630;
	
	private int score = 0;
	private int nBricks = 30;
	private boolean play = false;
	
	//Generazione direzione iniziale
	private double degrees = Math.floor(Math.random()*40 + 30);
	private int ballXdir = -(int) dirX(degrees);
	private int ballYdir = (int) dirY(degrees);
	
	/**
	 * <p>Setta la matrice della mappa</p>
	 */
	public Map() {
		this.map = new int[ROW][COL];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				map[i][j] = 1;
			}
		}
		
		this.brickWidth = 500/COL;
		this.brickHeight = 300/ROW;
	}
	
	/**
	 * <p>Disegna la griglia di mattoncini</p>
	 * 
	 * @param g Graphics 2D
	 * @see Graphics2D
	 * @see Color
	 * @see BasicStroke
	 */
	public void draw(Graphics2D g) {
		
		for(int i=0;i<map.length;i++) {
				
			for(int j=0;j<map[i].length;j++) {
			
				if(map[i][j] == 1) {
					//Griglia
					g.setColor(new Color(204, 204, 204));
					g.fillRect(j*brickWidth+150, i*brickHeight+70, brickWidth, brickHeight);
					
					//Bordo griglia
					g.setStroke(new BasicStroke(3));
					g.setColor(new Color(121, 121, 121));
					g.drawRect(j * brickWidth+150, i * brickHeight+70, brickWidth, brickHeight);
				}
			}
		}
	}
	
	/**
	 * <p>Set del valore di una cella della matrice</p>
	 * 
	 * @param ROW righe della matrice
	 * @param COL colonne della matrice
	 * @param value valore della cella della matrice
	 */
	public void setBrick(int ROW, int COL, int value) {
		map[ROW][COL] = value;
	}
	
	/**
	 * <p>Resetta la matrice</p>
	 */
	public void reset() {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				map[i][j] = 1;
			}
		}
	}
	
	/**
	 * <p>Calcolo direzione X di inizio gioco</p>
	 * 
	 * @param Degrees angolo random tra 30? e 70?
	 * @return coseno dell'angolo che sar? la direzione X di inizio gioco
	 */
	public double dirX(double gradi) {
		double radianti = Math.toRadians(gradi);
		double coseno = - Math.cos(radianti)*5;
		if((Math.random()*1) < 0.5) {
			coseno = -coseno;
		}
		return coseno;
	}
	
	/**
	 * <p>Calcolo direzione Y di inizio gioco</p>
	 * 
	 * @param Degrees angolo random tra 30? e 70?
	 * @return seno dell'angolo che sar? la direzione Y di inizio gioco
	 */
	public double dirY(double gradi) {
		double radianti = Math.toRadians(gradi);
		return Math.sin(radianti)*5;
	}

	public int getBrickWidth() {
		return brickWidth;
	}

	public int getBrickHeight() {
		return brickHeight;
	}

	public int getPaddleX() {
		return paddleX;
	}

	public int getBallX() {
		return ballX;
	}

	public int getBallY() {
		return ballY;
	}

	public int getScore() {
		return score;
	}

	public int getnBricks() {
		return nBricks;
	}

	public double getDegrees() {
		return degrees;
	}

	public int getBallXdir() {
		return ballXdir;
	}

	public int getBallYdir() {
		return ballYdir;
	}

	public static int getRow() {
		return ROW;
	}

	public static int getCol() {
		return COL;
	}

	public static int getBall() {
		return BALL;
	}

	public boolean isPlay() {
		return play;
	}

	public void setPlay(boolean play) {
		this.play = play;
	}

	public void setBallXdir(int ballXdir) {
		this.ballXdir = ballXdir;
	}

	public void setBallYdir(int ballYdir) {
		this.ballYdir = ballYdir;
	}

	public int[][] getMap() {
		return map;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setnBricks(int nBricks) {
		this.nBricks = nBricks;
	}

	public void setBallX(int ballX) {
		this.ballX = ballX;
	}

	public void setBallY(int ballY) {
		this.ballY = ballY;
	}

	public void setPaddleX(int paddleX) {
		this.paddleX = paddleX;
	}

	public void setDegrees(double degrees) {
		this.degrees = degrees;
	}
}
