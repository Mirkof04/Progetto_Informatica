
import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		JFrame finestra = new JFrame();
		Finestra gioco = new Finestra();
		finestra.setTitle("Brick Break");
		finestra.setSize(800, 700);
		finestra.setResizable(false);
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		finestra.add(gioco);
		finestra.setVisible(true);
	}

}
