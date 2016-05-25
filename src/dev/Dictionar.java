package dev;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * 
 * @author Stefanescu Marian
 * Clasa Dictionar este agregarea claselor Clasa care formeaza dictionarul. Un dictionar
 * generic este compus dintr-o colectie sortata de clase de echivalenta ordonate dupa
 * ordinea literelor in alfabet
 *
 */

public class Dictionar extends Clasa {
	private List<Clasa> dictionar = new ArrayList<>();
	
	public List<Clasa> getDictionar() {
		return dictionar;
	}
	
	public Cuvant search(String cuvant) {
		List<Clasa> temp = this.getDictionar();
		for(Clasa c: temp) {
			if(c.elExists(cuvant)) {
				return c.get(cuvant);
			}
		}
		return null;
	}
	
	public Cuvant advancedSearch(String cuvant) {
		
		String pattern = "(.*)?(.*)";

	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);

	      // Now create matcher object.
	      Matcher m = r.matcher(cuvant);
	      if (m.find( )) {
	    	  return null;
	      }
	      
	      return null;
	}
}
