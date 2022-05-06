package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * <h1>Finestra menù</h1>
 * <p>Viene creata interamente la finestra iniziale con il menù di gioco tra cui varie opzioni</p>
 * 
 * @author Alessandro Salamone
 * @author Forcolin Mirko
 * @author Florea Gabriel
 * 
 * @see JFrame
 */

public class Menu extends JFrame{

	private JPanel contentPane;
	private JButton btnNewGame;
	private JLabel lblTitolo;
	private JTextField textFieldNickname;
	private JLabel lblNickname;
	private JButton btnRanking;
	private JButton btnRecord;
	private JLabel lblRecord;
	private JLabel lblRanking;
	
	/**
	 * <p>Istanzia tutti gli oggetti JavaSwing ed imposta altri parametri della finestra</p>
	 * 
	 * @see Image
	 * @see ImageIcon
	 * @see Graphics
	 * @see Color
	 * @see BorderFactory
	 * @see Font
	 * @throws IOException
	 */
	public Menu() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 494, 300);
		setTitle("Brick Breaker");
		setResizable(false);
		
		// Aggunta icona della finestra
		ImageIcon appIcon = new ImageIcon("img/appIcon.png");
		setIconImage(appIcon.getImage());
		
		// Aggiunta immagine di sfondo
		try {
			Image backgroundImage = ImageIO.read(new File("img/backgroundMenu.png"));
			contentPane = new JPanel(){
		        public void paintComponent(Graphics g) {
		            g.drawImage(backgroundImage, 0, 0, null);
		        }
		    };
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPane);
		contentPane.setLayout(null);
		
		// Bottone per una nuova partita
		ImageIcon playIcon = new ImageIcon("img/play.png");
		btnNewGame = new JButton(playIcon);
		btnNewGame.setContentAreaFilled(false);
		btnNewGame.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnNewGame.setFont(new Font("Calibri", Font.BOLD, 13));
		btnNewGame.setBounds(280, 85, 50, 50);
		contentPane.add(btnNewGame);
		
		// Label per il titolo
		lblTitolo = new JLabel("Brick Breaker Game");
		lblTitolo.setFont(new Font("Calibri", Font.BOLD, 30));
		lblTitolo.setForeground(Color.WHITE);
		lblTitolo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitolo.setBounds(91, 10, 274, 58);
		contentPane.add(lblTitolo);
		
		// TextField per inserimento nickName
		textFieldNickname = new JTextField();
		textFieldNickname.setBounds(138, 93, 132, 35);
		contentPane.add(textFieldNickname);
		textFieldNickname.setColumns(10);
		
		// Label nickName
		lblNickname = new JLabel("Nickname");
		lblNickname.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNickname.setForeground(Color.WHITE);
		lblNickname.setBounds(138, 71, 90, 24);
		contentPane.add(lblNickname);
		
		// Bottone per la classifica
		ImageIcon rankingIcon = new ImageIcon("img/podium.png");
		btnRanking = new JButton(rankingIcon);
		btnRanking.setContentAreaFilled(false);
		btnRanking.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnRanking.setFont(new Font("Calibri", Font.BOLD, 13));
		btnRanking.setBounds(148, 168, 80, 40);
		contentPane.add(btnRanking);
		
		// Bottone per il record tra utenti 
		ImageIcon recordIcon = new ImageIcon("img/record.png");
		btnRecord = new JButton(recordIcon);
		btnRecord.setContentAreaFilled(false);
		btnRecord.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		btnRecord.setFont(new Font("Calibri", Font.BOLD, 13));
		btnRecord.setBounds(243, 166, 80, 40);
		contentPane.add(btnRecord);
		
		// Label record utenti registrati
		lblRecord = new JLabel("RECORD");
		lblRecord.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecord.setForeground(Color.WHITE);
		lblRecord.setFont(new Font("Calibri", Font.BOLD, 13));
		lblRecord.setBounds(242, 206, 81, 24);
		contentPane.add(lblRecord);
		
		// Label classifica
		lblRanking = new JLabel("RANKING");
		lblRanking.setHorizontalAlignment(SwingConstants.CENTER);
		lblRanking.setForeground(Color.WHITE);
		lblRanking.setFont(new Font("Calibri", Font.BOLD, 13));
		lblRanking.setBounds(147, 206, 81, 24);
		contentPane.add(lblRanking);
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
