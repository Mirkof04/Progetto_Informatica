package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.Color;

public class Ranking extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JList list;
	
	public Ranking() {
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		list = new JList();
		list.setBorder(null);
		list.setForeground(Color.WHITE);
		list.setBackground(Color.BLACK);
		scrollPane.setViewportView(list);
		
		setVisible(true);
	}

	public JList getList() {
		return list;
	}

}
