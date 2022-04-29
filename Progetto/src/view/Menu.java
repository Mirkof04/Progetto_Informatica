package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Menu extends JFrame{

	private JPanel contentPane;
	private JButton btnNewGame;
	private JLabel lblTitolo;
	private JTextField textFieldNickname;
	private JLabel lblNickname;
	private JButton btnRanking;
	private JButton btnRecord;

	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		btnNewGame = new JButton("New Game");
		btnNewGame.setFont(new Font("Calibri", Font.BOLD, 13));
		btnNewGame.setBounds(268, 93, 97, 35);
		contentPane.add(btnNewGame);
		
		lblTitolo = new JLabel("Brick Breaker Game");
		lblTitolo.setFont(new Font("Calibri", Font.BOLD, 30));
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setBounds(91, 10, 274, 58);
		contentPane.add(lblTitolo);
		
		textFieldNickname = new JTextField();
		textFieldNickname.setBounds(117, 93, 132, 35);
		contentPane.add(textFieldNickname);
		textFieldNickname.setColumns(10);
		
		lblNickname = new JLabel("Nickname");
		lblNickname.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNickname.setForeground(Color.WHITE);
		lblNickname.setBounds(116, 71, 90, 24);
		contentPane.add(lblNickname);
		
		btnRanking = new JButton("Ranking");
		btnRanking.setFont(new Font("Calibri", Font.BOLD, 13));
		btnRanking.setBounds(175, 152, 105, 35);
		contentPane.add(btnRanking);
		
		btnRecord = new JButton("Record");
		btnRecord.setFont(new Font("Calibri", Font.BOLD, 13));
		btnRecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRecord.setBounds(175, 197, 105, 35);
		contentPane.add(btnRecord);
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

	public JButton getBtnRecord() {
		return btnRecord;
	}
}
