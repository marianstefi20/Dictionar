package dev;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.Border;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

import com.sun.xml.internal.ws.util.StringUtils;

class GUI extends JFrame {
	private static final long serialVersionUID = 1L;
	// Variabila globala in care se salveaza directorul
	public static final String DIRNAME = "tmp/";
	public JPanel mainPanel;
	public JTextField textarea;
	private static String title;
	private Controller controller;
	public Dictionar dictionar;
	public JTextPane bufferRezultate = new JTextPane();
	public JScrollPane rezultate = new JScrollPane();
	
	public GUI() {
		this.prepareGUI();
		this.controller = new Controller(this);
		ClasaFactory fabricator = new ClasaFactory();
		this.dictionar = fabricator.getDictionar("engleza");
	}
	
	public GUI(String title) {
		GUI.title = title;
		this.controller = new Controller(this);
		ClasaFactory fabricator = new ClasaFactory();
		this.dictionar = fabricator.getDictionar("engleza");
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
        textarea = new JTextField();
        textarea.setPreferredSize(new Dimension(600, 40));
        textarea.setFont(font1);
        oneRow.add(textarea);
        JButton submit = new JButton("Submit");
        submit.setActionCommand("vizualizareCuvant");
        submit.addActionListener(this.controller);
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
    		alfabet.setActionCommand("alfabet");
    		alfabet.addActionListener(new Controller(this, i));
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
        
        gbc.fill = GridBagConstraints.CENTER;
        gbc.ipady = 10;
        gbc.ipadx = 20;
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weighty = 0.1;
        rezultate.setPreferredSize(new Dimension(680, 150));
        rezultate.add(bufferRezultate);
        rezultate.setVerticalScrollBarPolicy(rezultate.VERTICAL_SCROLLBAR_AS_NEEDED);
        rezultate.setHorizontalScrollBarPolicy(rezultate.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(rezultate, gbc);
        
        GridBagConstraints c = new GridBagConstraints();
 		c.gridx = 0;
 		c.gridy = 10;
 		c.fill = GridBagConstraints.BOTH;
 		c.weightx=1;
 		c.weighty=0;
 		c.gridwidth = 2;
 		panel.add(new JLabel(" "),c);
	}
	
	public void vizualizareCuvant(Cuvant cuv) {
		bufferRezultate.setEditable(false);
		bufferRezultate.setContentType("text/html");
		if(cuv == null) {
			bufferRezultate.setText("<html><p style='font-family:14px;font-weight:800;color:red'>Nu exista acest cuvant in dictionar!</p></html>");
		} else {
			String tempSyn = new String();
			for(int i=0;i<cuv.sinonime.size();i++) {
				tempSyn = tempSyn + ", " + cuv.sinonime.get(0);
			}
			bufferRezultate.setText("<html><p style='font-family:14px;font-weight:800'>" + cuv.nume + tempSyn + " -  <span style='font-family:14px;font-weight: 100'>" + cuv.definitie +  "</span></p></html>");
			bufferRezultate.setCaretPosition(0);
		}
		rezultate.setViewportView(bufferRezultate);
		rezultate.validate();
		rezultate.repaint();
	}
	
	public void append(String nume, String definitie, String sinonime) {
		bufferRezultate.setEditable(false);
		bufferRezultate.setContentType("text/html");
	    try {
	       HTMLDocument doc = (HTMLDocument) bufferRezultate.getDocument();
	       //HTMLDocument doc = new HTMLDocument();
	       HTMLEditorKit kit = new HTMLEditorKit();
	       bufferRezultate.setEditorKit(kit);
	       bufferRezultate.setDocument(doc);
	       //doc.remove(0, doc.getLength());
	       try {
				kit.insertHTML(doc, doc.getLength(), "<p style='font-family:14px;font-weight:800'>" + nume + " - <span style='font-family:14px;font-weight: 100'>" + definitie + sinonime + "</span></p>", 0, 0, null);
	       } catch (IOException e) {
				e.printStackTrace();
			}
	    } catch(BadLocationException exc) {
	       exc.printStackTrace();
	    }
	    rezultate.setViewportView(bufferRezultate);
		rezultate.validate();
		rezultate.repaint();
		
	}
	
	public void adaugaCuvinte(Map<String, Cuvant> map) {
		Set<String> cheieDinamica = map.keySet();
		Cuvant c ;
		for(String cheie: cheieDinamica) {
			c = map.get(cheie);
			append(c.nume, c.definitie, c.sinonime.toString());
		}
	}
	
	public void showPanel(){ 
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		GUI gui = new GUI("Dictionar");
		gui.showPanel();
	}

}
