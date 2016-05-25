package dev;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Stefanescu Marian
 * Clasa generica prin care instantiem de fapt clasele iterand prin caracterele ASCII
 * Este de fapt o implementare la FactoryPattern
 *
 */
public class ClasaFactory {
	public Dictionar getDictionar(String type) {
		if(type.equalsIgnoreCase("engleza")) {
			// este vorba de un dictionar de engleza
			System.out.println("Ajungem in creator\n");
			Dictionar dictionar = new Dictionar();
			for(char i = 'a'; i<= 'z'; i++) {
				Clasa c = new Clasa(i);
				Map<String, Cuvant> cuvinte = JSON.deserialize(i);
				cuvinte.forEach((a,b) -> {
					c.cuvinte.put(a, b);
				});
				dictionar.getDictionar().add(c);
			}
			return dictionar;
		}
		return null;
	}
}
