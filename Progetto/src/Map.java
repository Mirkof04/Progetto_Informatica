import java.awt.BasicStroke;
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
				map[i][j] = 1;
			}
		}
		
		this.brickWidth = 500/col;
		this.brickHeight = 300/row;
	}
	
	public void draw(Graphics2D g) {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[i].length;j++) {
				
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
