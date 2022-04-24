package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Menu extends JFrame{

	private JPanel contentPane;
	private JButton btnNewGame;
	private JLabel lblTitolo;

	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(159, 92, 139, 41);
		contentPane.add(btnNewGame);
		
		lblTitolo = new JLabel("Brick Breaker");
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setBounds(143, 22, 155, 35);
		contentPane.add(lblTitolo);
	}

	public JButton getBtnNewGame() {
		return btnNewGame;
	}

	public JLabel getLblTitolo() {
		return lblTitolo;
	}
}
