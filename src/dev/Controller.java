package dev;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Controller implements ActionListener {
	public GUI gui;
	
	public Controller(GUI gui) {
		this.gui = gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch(command) {
		case "adaugareCuvant":
			mkCuvant();
			break;
		}
	}
	
	private void mkCuvant() {
		JTextField field1 = new JTextField("");
        JTextArea field2 = new JTextArea("");
        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.setPreferredSize(new Dimension(300, 140));
        panel.add(new JLabel("Cuvant: "));
        panel.add(field1);
        panel.add(new JLabel("Definitie: "));
        panel.add(field2);
        int result = JOptionPane.showConfirmDialog(null, panel, "Adauga un cuvant",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
        	ArrayList<String> data = new ArrayList<String>();
        	data.add(field1.getText());
        	data.add(field2.getText());
        	// Aici se incearca inserarea unui cuvant nou
        	
        } else {
            System.out.println("Cancelled");
        }
	}

}
