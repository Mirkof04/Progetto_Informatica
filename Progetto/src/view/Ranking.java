package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ScrollPaneConstants;

public class Ranking extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JList list;
	
	public Ranking() {
		setBounds(100, 100, 494, 300);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//Aggiunta icona della finestra
		ImageIcon appIcon = new ImageIcon("img/appIcon.png");
		setIconImage(appIcon.getImage());
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		list = new JList();
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		list.setFont(new Font("Calibri", Font.BOLD, 25));
		list.setForeground(Color.WHITE);
		list.setBackground(new Color(0, 0, 255));
		scrollPane.setViewportView(list);
		
		setVisible(true);
	}

	public JList getList() {
		return list;
	}

}
