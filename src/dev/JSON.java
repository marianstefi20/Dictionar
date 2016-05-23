package dev;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

class JSON {
	public JSON() {
		try {
	        Cuvant cuv = new Cuvant();
	        ObjectMapper mapper = new ObjectMapper();
	        mapper.writeValue(new File("json/data.json"), cuv);
	    } catch (JsonGenerationException e1) {
	       e1.printStackTrace();
	    } catch (JsonMappingException e2) {
	       e2.printStackTrace();
	    } catch (IOException e3) {
	       e3.printStackTrace();
	    }
	}
}