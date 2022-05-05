package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import view.Menu;
import view.Ranking;

public class Controller implements ActionListener {

	private Menu menu;
	private Gameplay gameplay;
	
	public Controller(Menu menu) {
		this.menu = menu;
		createRanking();
		addActionListener();
	}
	
	//Aggiunta actionListener a tutti i bottoni
	public void addActionListener() {
		menu.getBtnNewGame().addActionListener(this);
		menu.getBtnRecord().addActionListener(this);
		menu.getBtnRanking().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Gestione del bottone per creazione nuova partita
		if(menu.getBtnNewGame() == e.getSource()) {
			if(menu.getTextFieldNickname().getText().trim().equals("") || menu.getTextFieldNickname().getText()==null) {
				JOptionPane.showMessageDialog(menu, "Inserisci il Nickname", "Errore", JOptionPane.ERROR_MESSAGE);
			}
			else {
				gameplay = new Gameplay(menu.getTextFieldNickname().getText());
			}
		}
		
		//Gestione del bottone per mostrare miglior punteggio giocatore
		if(menu.getBtnRecord() == e.getSource()) {
			try {
				
				File file = new File("record.txt");
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				String record = "Il record è stato di "+br.readLine()+" con "+br.readLine()+" punti";
				JOptionPane.showMessageDialog(menu, record, "Record", JOptionPane.INFORMATION_MESSAGE);
				
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(menu, "Non è presente nessuna partita precedente", "Errore", JOptionPane.ERROR_MESSAGE);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(menu, "Errore in I/O dati", "Errore", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		//Gestione bottone per mostrare la classifica
		if(menu.getBtnRanking() == e.getSource()) {
			//Creo un nuovo JFrame e aggiorno la JList 
			Ranking rank = new Ranking();
			rank.getList().setModel(printRanking());
		}
	}
	
	//Creazione file iniziale con la classifica virtuale
	public void createRanking() {
		try {
			String path = "ranking.txt";
			File file = new File(path);
			
			if(!file.exists()) {	
				file.createNewFile();
				FileWriter fw = new FileWriter(file);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("Paolo\n");
				bw.write(270+"\n");
				bw.write("Justin\n");
				bw.write(230+"\n");
				bw.write("Andrea\n");
				bw.write(200+"\n");
				bw.write("Silvia\n");
				bw.write(170+"\n");
				bw.write("Vittoria\n");
				bw.write(150+"\n");
				bw.write("Chiara\n");
				bw.write(130+"\n");
				bw.write("Jason\n");
				bw.write(120+"\n");
				bw.write("Mattia\n");
				bw.write(100+"\n");
				bw.write("Jennifer\n");
				bw.write(70+"\n");
				bw.write("Mario\n");
				bw.write(40+"\n");
				bw.flush();
				bw.close();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	//Creazione DefaultListModel da inserire nella JList
	public DefaultListModel<String> printRanking() {
		DefaultListModel<String> model = new DefaultListModel<>();
		
		try {
			//Leggo dato da file
			File file = new File("ranking.txt");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			//Aggiunta elementi al DefaultListModel
			model.addElement("CLASSIFICA");
			for(int i=0;i<10;i++) {
				model.addElement(i+1+"° "+br.readLine()+" ("+br.readLine()+" punti)");
			}
			
		//Gestisco possibili errori che potrebbero presentarsi in fase di lettura dati da file
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return model;
	}
}
