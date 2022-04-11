import java.awt.Color;
import java.awt.Graphics2D;

public class Map {
	
	private int map[][];
	private int brickWidth;
	private int brickHeight;
	
	public Map(int col, int row) {
		this.map = new int[row][col];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				if(i%2 == 0) {
					map[i][j] = 1;
				}
			}
		}
		
		this.brickWidth = 500/col;
		this.brickHeight = 350/row;
	}
	
	public void draw(Graphics2D g) {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				g.setColor(Color.red);
				g.fillRect(j*brickWidth, i*brickHeight, brickWidth, brickHeight);
				/*if(map[i][j]%2 == 0) {
					g.setColor(Color.red);
					g.fillRect(j*brickWidth, i*brickHeight, brickWidth, brickHeight);
				}
				else {
					g.setColor(Color.black);
					g.fillRect(j*brickWidth, i*brickHeight, brickWidth, brickHeight);
				}*/
			}
		}
	}
}
