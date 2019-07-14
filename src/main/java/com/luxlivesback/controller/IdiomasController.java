package com.luxlivesback.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxlivesback.dto.IdiomasDto;
import com.luxlivesback.function.IdiomasFunction;
import com.luxlivesback.model.Idiomas;
import com.luxlivesback.response.Response;
import com.luxlivesback.response.ResponseList;
import com.luxlivesback.service.IdiomasService;

@RestController
@RequestMapping("/idiomas")
@CrossOrigin("${origem-permitida}")
public class IdiomasController {

	private static final Logger log = LoggerFactory.getLogger(IdiomasController.class);
	
	@Autowired
	private IdiomasService idiomasService;
	
	@Autowired
	private IdiomasFunction idiomasFunction;
	
	@GetMapping
	public ResponseEntity<ResponseList<IdiomasDto>> findAll() {
		
		log.info("Buscando lista de idiomas");
		ResponseList<IdiomasDto> response = new ResponseList<IdiomasDto>();
		Optional<List<Idiomas>> idiomas = idiomasService.findAllByOrderByLingua();
		List<IdiomasDto> dto = new ArrayList<IdiomasDto>();
		idiomas.get().forEach(x -> dto.add(idiomasFunction.converteEntityToDto(x)));
		response.setData(dto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<IdiomasDto>> findById(@PathVariable Long id) {
		
		log.info("Buscando Idioma pelo id: {}", id);
		Response<IdiomasDto> response = new Response<IdiomasDto>();
		Optional<Idiomas> idiomas = idiomasService.findById(id);
		
		if (!idiomas.isPresent()) {
			response.getErrors().add("Idioma não encontrado");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(idiomasFunction.converteEntityToDto(idiomas.get()));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<IdiomasDto>> save(@Valid @RequestBody IdiomasDto dto,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Persistindo Idiomas: {}", dto.toString());
		Response<IdiomasDto> response = new Response<IdiomasDto>();
		
		idiomasFunction.validaCad(dto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de Idiomas: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		Idiomas idiomas = idiomasFunction.convertDtoToIdiomasDto(dto);
		idiomas = idiomasService.save(idiomas);
		response.setData(idiomasFunction.converteEntityToDto(idiomas));
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<IdiomasDto>> update(
			@PathVariable Long id, @Valid @RequestBody IdiomasDto dto,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Persistindo Idiomas, id: {}, dto: {}", id, dto.toString());
		Response<IdiomasDto> response = new Response<IdiomasDto>();
		
		idiomasFunction.validaUpdate(id, dto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de Idiomas: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		Idiomas idiomas = idiomasFunction.convertDtoToIdiomasDto(dto);
		idiomas = idiomasService.save(idiomas);
		response.setData(idiomasFunction.converteEntityToDto(idiomas));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Boolean>> deleteById(@PathVariable Long id) {
		
		log.info("Deletando Idioma pelo id: {}", id);		
		Response<Boolean> response = new Response<Boolean>();	
		Optional<Idiomas> idiomas = idiomasService.findById(id);
		
		if (!idiomas.isPresent()) {
			response.getErrors().add("Idioma não encontrado");
			return ResponseEntity.badRequest().body(response);
		}
		
		Boolean resp = idiomasService.deleteById(id);
		response.setData(resp);
		return ResponseEntity.ok(response);	
	}
}
