package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map {
	
	public static final int ROW = 5;
	public static final int COL = 6;
	public static final int BALL = 20;
	
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	
	public int paddleX = 350;
	public int ballX = 390;
	public int ballY = 630;
	
	public int score = 0;
	public int nBricks = 30;
	public boolean play = false;
	
	//Generazione direzione iniziale
	public double gradi = Math.floor(Math.random()*40 + 30);
	public int ballXdir = -(int) dirX(gradi);
	public int ballYdir = (int) dirY(gradi);
	
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
	
	public void draw(Graphics2D g) {
		for(int i=0;i<map.length;i++) {
				
			for(int j=0;j<map[i].length;j++) {
			
				if(map[i][j] == 1) {
					//Griglia
					g.setColor(Color.blue);
					g.fillRect(j*brickWidth+140, i*brickHeight+50, brickWidth, brickHeight);
					
					//Bordo griglia
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth+140, i * brickHeight+50, brickWidth, brickHeight);
				}
			}
		}
	}
	
	public void setBrick(int ROW, int COL, int value) {
		map[ROW][COL] = value;
	}
	
	public void reset() {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				map[i][j] = 1;
			}
		}
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

	public int getPaddleX() {
		return paddleX;
	}

	public void setPaddleX(int paddleX) {
		this.paddleX = paddleX;
	}

	public int getBallX() {
		return ballX;
	}

	public void setBallX(int ballX) {
		this.ballX = ballX;
	}

	public int getBallY() {
		return ballY;
	}

	public void setBallY(int ballY) {
		this.ballY = ballY;
	}

	public int getBallXdir() {
		return ballXdir;
	}

	public void setBallXdir(int ballXdir) {
		this.ballXdir = ballXdir;
	}

	public int getBallYdir() {
		return ballYdir;
	}

	public void setBallYdir(int ballYdir) {
		this.ballYdir = ballYdir;
	}

	public int getBrickWidth() {
		return brickWidth;
	}

	public int getBrickHeight() {
		return brickHeight;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getnBricks() {
		return nBricks;
	}
}
