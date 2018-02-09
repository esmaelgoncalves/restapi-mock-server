package br.com.esmaelgoncalves.restapimockserver.resource;

import java.io.IOException;
import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import br.com.esmaelgoncalves.restapimockserver.service.GenericResourceService;

@RestController
@RequestMapping("/${app.resourceName}")
public class GenericResource {

	@Autowired
	private GenericResourceService service;

	@GetMapping
	public ResponseEntity<Object> listarTodos() throws JsonParseException, JsonMappingException, IOException {
		List<LinkedHashMap<String, Object>> dataAsMap = service.listarTodos();
		
		return dataAsMap == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(dataAsMap);

	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarResourcePorId(@PathVariable("id") String id)
			throws JsonParseException, JsonMappingException, IOException {
		LinkedHashMap<String, Object> result = service.buscarResourcePorId(id);

		return result == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(result);
	}

	@PostMapping
	public ResponseEntity<Object> novoResource(@RequestBody LinkedHashMap<String, Object> resource, HttpServletResponse response)
			throws JsonParseException, JsonMappingException, IOException {
		LinkedHashMap<String, Object> result = service.novoResource(resource);
		setHeaderLocation(response, result);
		return new ResponseEntity<Object>(result, HttpStatus.CREATED);
	}


	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerResource(@PathVariable("id") String id) throws JsonParseException, JsonMappingException, IOException{
		service.removeResource(id);
	}
	
	private void setHeaderLocation(HttpServletResponse response, LinkedHashMap<String, Object> result) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(result.get("id")).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}

}
