package dev;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	// Variabila globala in care se salveaza directorul
	public static final String DIRNAME = "tmp/";
	public JPanel mainPanel;
	private static String title;
	private Controller controller;
	
	public GUI() {
		this.prepareGUI();
		this.controller = new Controller(this);
	}
	
	public GUI(String title) {
		GUI.title = title;
		this.controller = new Controller(this);
		this.prepareGUI();
	}
	
	public void setTitle(String title) {
		GUI.title = title;
	}
	
	public void prepareGUI() {
		this.setSize(1000, 600);
		this.setLayout(new GridLayout(1,1));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		mainPanel = new JPanel();
		this.modelPanel(mainPanel);
		this.add(mainPanel);
	}
	
	public void modelPanel(JPanel panel) {
		Border empty = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);        
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        JLabel label1 = new JLabel();
        label1.setBorder(empty);
        label1.setText("<html><p style='font-size:42px;text-align:center;font-family:CMU Serif;font-weight:500'>" + GUI.title + "</p></html>");        
        panel.add(label1, gbc);
        
        gbc.fill = GridBagConstraints.CENTER;
        gbc.ipady = 10;
        gbc.ipadx = 20;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.1;
        JPanel oneRow = new JPanel();
    	Font font1 = new Font("Serif", Font.PLAIN, 20);
        JTextField textarea = new JTextField();
        textarea.setPreferredSize(new Dimension(600, 40));
        textarea.setFont(font1);
        oneRow.add(textarea);
        JButton submit = new JButton("Submit");
        submit.setPreferredSize(new Dimension(100, 38));
        oneRow.add(submit);
        panel.add(oneRow, gbc);
        
        gbc.fill = GridBagConstraints.CENTER;
        gbc.ipady = 10;
        gbc.ipadx = 20;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0.1;
        JPanel table = new JPanel(new GridLayout(3, 10));
        for(char i = 'A'; i<='Z'; i++) {
    		JButton alfabet = new JButton(Character.toString(i));
    		table.add(alfabet);
        }
        panel.add(table, gbc);
        
        gbc.fill = GridBagConstraints.CENTER;
        gbc.ipady = 10;
        gbc.ipadx = 20;
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        JButton adaugaCuvant = new JButton("Cuvant nou");
        adaugaCuvant.setActionCommand("adaugareCuvant");
        adaugaCuvant.addActionListener(this.controller);
        panel.add(adaugaCuvant, gbc);
        
        GridBagConstraints c = new GridBagConstraints();
 		c.gridx = 0;
 		c.gridy = 7;
 		c.fill = GridBagConstraints.BOTH;
 		c.weightx=1;
 		c.weighty=1;
 		c.gridwidth = 2;
 		panel.add(new JLabel(" "),c);
	}
	
	public void showPanel(){ 
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		GUI gui = new GUI("Dictionar");
		gui.showPanel();
	}

}
