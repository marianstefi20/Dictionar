package dev;

import java.util.ArrayList;
import java.util.List;

public class Cuvant implements Primitiva {
	public String nume;
	public String definitie;
	public List<String> sinonime = new ArrayList<>();
	
	public Cuvant() {
	}
	
	public Cuvant(String nume, String definitie) {
		this.nume = nume;
		this.definitie = definitie;
	}
	
	public Cuvant(String nume, String definitie, List<String> sinonime) {
		this.nume = nume;
		this.definitie = definitie;
		this.sinonime = sinonime;
	}
	
	public void setNume(String nume) {
		this.nume = nume;
	}
	
	public void setDefinitie(String definitie) {
		this.definitie = definitie;
	}
}
