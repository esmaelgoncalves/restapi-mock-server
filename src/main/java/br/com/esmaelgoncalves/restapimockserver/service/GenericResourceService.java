package br.com.esmaelgoncalves.restapimockserver.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GenericResourceService {
	@Value("${app.resource.path}")
	private String resourcePathLocation;

	@SuppressWarnings("unchecked")
	public List<LinkedHashMap<String, Object>> listarTodos()
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		InputStream inputStream = new FileInputStream(resourcePathLocation);
		return mapper.readValue(inputStream, List.class);
	}

	public LinkedHashMap<String, Object> buscarResourcePorId(String id)
			throws JsonParseException, JsonMappingException, IOException {
		List<LinkedHashMap<String, Object>> dataAsMap = this.listarTodos();

		for (LinkedHashMap<String, Object> hashMap : dataAsMap) {
			if (id.equals(hashMap.get("id").toString())) {
				return hashMap;
			}
		}

		return null;
	}

	public LinkedHashMap<String, Object> novoResource(LinkedHashMap<String, Object> resource)
			throws JsonGenerationException, JsonMappingException, FileNotFoundException, IOException {
		List<LinkedHashMap<String, Object>> dataAsMap = this.listarTodos();
		
		int ultimoId = 0;
		
		for (LinkedHashMap<String, Object> linkedHashMap : dataAsMap) {
			int value = (int) linkedHashMap.get("id");
			if(value > ultimoId) 
				ultimoId = value;
		}	
			
		resource.put("id", ++ultimoId);

		dataAsMap.add(resource);

		escreverNoArquivo(dataAsMap);

		return resource;

	}

	public void removeResource(String id) throws JsonParseException, JsonMappingException, IOException {
		List<LinkedHashMap<String, Object>> dataAsMap = this.listarTodos();

		LinkedHashMap<String, Object> hashMapToRemove = null;
		for (LinkedHashMap<String, Object> hashMap : dataAsMap) {
			if (id.equals(hashMap.get("id").toString())) {
				hashMapToRemove = hashMap;
				break;
			}
		}
		dataAsMap.remove(hashMapToRemove);

		escreverNoArquivo(dataAsMap);
	}

	private void escreverNoArquivo(List<LinkedHashMap<String, Object>> dataAsMap)
			throws IOException, JsonGenerationException, JsonMappingException, FileNotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new FileOutputStream(resourcePathLocation), dataAsMap);
	}

}