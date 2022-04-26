package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;

public class Menu extends JFrame{

	private JPanel contentPane;
	private JButton btnNewGame;
	private JLabel lblTitolo;
	private JTextField textFieldNickname;
	private JLabel lblNickname;
	private JButton btnRanking;

	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		add(contentPane);
		contentPane.setLayout(null);
		
		btnNewGame = new JButton("New Game");
		btnNewGame.setBounds(285, 82, 97, 35);
		contentPane.add(btnNewGame);
		
		lblTitolo = new JLabel("Brick Breaker");
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setBounds(143, 22, 155, 35);
		contentPane.add(lblTitolo);
		
		textFieldNickname = new JTextField();
		textFieldNickname.setBounds(143, 82, 132, 35);
		contentPane.add(textFieldNickname);
		textFieldNickname.setColumns(10);
		
		lblNickname = new JLabel("Nickname");
		lblNickname.setForeground(Color.WHITE);
		lblNickname.setBounds(143, 57, 90, 24);
		contentPane.add(lblNickname);
		
		btnRanking = new JButton("Ranking");
		btnRanking.setBounds(176, 138, 105, 35);
		contentPane.add(btnRanking);
	}

	public JButton getBtnNewGame() {
		return btnNewGame;
	}

	public JLabel getLblTitolo() {
		return lblTitolo;
	}

	public JTextField getTextFieldNickname() {
		return textFieldNickname;
	}

	public JButton getBtnRanking() {
		return btnRanking;
	}
}
