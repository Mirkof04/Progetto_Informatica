import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Finestra extends JPanel {
	
	private Map map;
	
	public Finestra() {
		map = new Map(5, 5);
	}
	
	public void paint(Graphics2D g) {
		map.draw(g);
		g.dispose();
	}
}
