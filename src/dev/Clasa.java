package dev;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Clasa implements Primitiva {
	public Character identifier;
	public Map<String, Cuvant> cuvinte = new HashMap<String, Cuvant>();
	
	public Clasa() {
		
	}
	
	public Clasa(char identifier) {
		this.identifier = identifier;
	}
	
	public void setReprezentant(String cheie) {
		this.identifier = cheie.charAt(0);
	}
	
	public boolean contains(Character caracter) {
		return (Character.toLowerCase(identifier) == Character.toLowerCase(caracter)) ? true : false;
	}
	
	/**
	 * Metoda cu care verificam daca exista aceasta cheie in map
	 * */
	public boolean elExists(String element) {
		for(String c: cuvinte.keySet()) {
			if(c.matches(element)) {
				
				System.out.println("Avem un match!\n");
			}
		}
		try {
			return (cuvinte.containsKey(element)) ? true : false;
		} catch(NullPointerException e) {
			return false;
		}
	}
	
	public void add(Cuvant cuvant) {
		if(!elExists(cuvant.nume)) {
			cuvinte.put(cuvant.nume, cuvant);
		} else {
			System.out.println("Nu am putut sa adaug cuvantul fiindca el deja exista in aceasta clasa\n");
		}
	}
	
	public void remove(String cheie) {
		if(elExists(cheie)) {
			cuvinte.remove(cheie);
		} else {
			System.out.println("Nu am putut sa elimin cuvantul fiindca el nu exista\n");
		}
	}
	
	public Cuvant get(String cheie) {
		if(elExists(cheie)) {
			return cuvinte.get(cheie);
		} else {
			System.out.println("Nu pot returna acest element fiindca el nu exista(cheia e inexistenta)");
			return null;
		}
	}
}
