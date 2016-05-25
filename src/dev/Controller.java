package dev;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class Controller implements ActionListener {
	public GUI gui;
	private char litera;
	
	public Controller(GUI gui) {
		this.gui = gui;
	}
	
	public Controller(GUI gui, char c) {
		this.gui = gui;
		this.litera = c;
	}
	
	public void setLitera(char c) {
		this.litera = c;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
		case "adaugareCuvant":
			mkCuvant();
			break;
		case "vizualizareCuvant":
			getCuvant();
			break;
		case "alfabet":
			getCuvinte();
			break;
		}
	}
	
	
	private void getCuvinte() {
		Document doc = gui.bufferRezultate.getDocument();
	       try {
			doc.remove(0,doc.getLength());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(this.litera + "\n");
		List<Clasa> clase = this.gui.dictionar.getDictionar()
						  .stream()
						  .filter(p -> p.contains(this.litera))
						  .collect(Collectors.toList());
		if(clase.isEmpty()) {
			System.out.println("Nu exista elemente cu aceasta litera");
		} else {
			Clasa c = clase.get(0);
			System.out.println(c.cuvinte.toString());
			Map<String, Cuvant> map = c.cuvinte;
			this.gui.adaugaCuvinte(map);
		}
	}
	
	private void getCuvant(){
		String cuvant = this.gui.textarea.getText();
		Cuvant cuv = (Cuvant)this.gui.dictionar.search(cuvant);
		if(cuv != null) {
			System.out.println("Cuvantul exista in dictionar: " + cuv + " \n");
			GridBagConstraints gbc = new GridBagConstraints();
			
			this.gui.vizualizareCuvant(cuv);
		}
		else {
			this.gui.vizualizareCuvant(null);
			System.out.println("Cuvantul nu exista in dictionar! \n");
		}
	}
	
	private void mkCuvant() {
		JTextField field1 = new JTextField("");
        JTextArea field2 = new JTextArea("");
        JTextArea field3 = new JTextArea("");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setPreferredSize(new Dimension(300, 220));
        panel.add(new JLabel("Cuvant: "));
        panel.add(field1);
        panel.add(new JLabel("Definitie: "));
        panel.add(field2);
        panel.add(new JLabel("Lista sinonime: (sep. cu virgual)"));
        panel.add(field3);
        int result = JOptionPane.showConfirmDialog(null, panel, "Adauga un cuvant",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
        	// Aici se incearca inserarea unui cuvant nou
        	this.tryClasa(field1.getText(), field2.getText(), field3.getText());
        } else {
            System.out.println("Cancelled");
        }
	}
	
	/**
	 * Metoda care primeste cuvantul ce se vrea a fi adaugat. 
	 * 1. Verificam daca cuvantul exista
	 * 2. Daca exista afisam mesaj si iesim
	 * 3. Daca nu il adaugam si serializam noua clasas
	 * */
	private void tryClasa(String entry, String definitie, String sinonime) {
	   // obtinem clasa in care se afla cuvantul
		Character caracter = entry.charAt(0);
		List<Clasa> dictionar = this.gui.dictionar.getDictionar();
		if(dictionar.isEmpty()) {
			Clasa d = new Clasa();
			d.setReprezentant(entry);
			String [] items = sinonime.split(",");
			List<String> container = Arrays.asList(items);
			d.add(new Cuvant(entry, definitie, container));
			JSON.serialize(d);
			dictionar.add(d);
			return;
		}
		for(Clasa c: dictionar) {
			if(c.contains(caracter)) {
				// insemna ca trebuie sa adaugam la acest map
				if(!c.elExists(entry)) {
					String [] items = sinonime.split(",");
					List<String> container = Arrays.asList(items);
					c.add(new Cuvant(entry, definitie, container));
					System.out.println(c.get(entry).sinonime.toString());
					JSON.serialize(c);
					System.out.println("Am ajuns sa cream un nou caracter!");
					return;
				}
			} else {
				Clasa e = new Clasa();
				e.setReprezentant(entry);
				String [] items = sinonime.split(",");
				List<String> container = Arrays.asList(items);
				e.add(new Cuvant(entry, definitie, container));
				dictionar.add(e);
				JSON.serialize(e);
				return;
			}
		}
	}

}
