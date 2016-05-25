package dev;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

class JSON {
	public static final String DIR = "json/";
	public JSON() {
		
	}
	
	public static void serialize(Clasa clasa) {
		try {
			Map<String, Cuvant> map = clasa.cuvinte;
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);

			mapper.writeValue(new File(DIR + Character.toLowerCase(clasa.identifier) + ".json"), map);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Map<String, Cuvant> deserialize(char identifier) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Include.NON_NULL);

			Map<String, Cuvant> map = mapper.readValue(
					new File(DIR + Character.toLowerCase(identifier) + ".json"), 
					new TypeReference<Map<String, Cuvant>>() {
			});
			return map;
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch(FileNotFoundException e) {
			Map<String, Cuvant> map = new HashMap<String,Cuvant>();
			return map;
		}catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
}