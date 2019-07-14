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

import com.luxlivesback.dto.PaisesDto;
import com.luxlivesback.function.PaisesFunction;
import com.luxlivesback.model.Paises;
import com.luxlivesback.response.Response;
import com.luxlivesback.response.ResponseList;
import com.luxlivesback.service.PaisesService;

@RestController
@RequestMapping("/paises")
@CrossOrigin("${origem-permitida}")
public class PaisesController {
	
	private static final Logger log = LoggerFactory.getLogger(PaisesController.class);
	
	@Autowired
	private PaisesService paisesService;
	
	@Autowired
	private PaisesFunction paisesFunction;
	
	@GetMapping
	public ResponseEntity<ResponseList<PaisesDto>> findAll() {
		
		log.info("Buscando lista de Paises");		
		ResponseList<PaisesDto> response = new ResponseList<PaisesDto>();
		Optional<List<Paises>> paises = paisesService.findAllByOrderByNome();
		List<PaisesDto> dto = new ArrayList<PaisesDto>();
		paises.get().forEach(x -> dto.add(paisesFunction.converteEntityToDto(x)));
		response.setData(dto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<PaisesDto>> findById(@PathVariable Long id) {
		
		log.info("Buscando Pais pelo id: {}", id);		
		Response<PaisesDto> response = new Response<PaisesDto>();
		Optional<Paises> paises = paisesService.findById(id);
		
		if (!paises.isPresent()) {
			response.getErrors().add("Pais não encontrado");
			return ResponseEntity.badRequest().body(response);	
		}
		
		response.setData(paisesFunction.converteEntityToDto(paises.get()));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<PaisesDto>> save(@Valid @RequestBody PaisesDto dto,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Persistindo Pais: {}", dto.toString());
		Response<PaisesDto> response = new Response<PaisesDto>();
		
		paisesFunction.validaCad(dto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de Paises: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		Paises paises = paisesFunction.convertDtoToPaises(dto);
		paises = paisesService.save(paises);
		response.setData(paisesFunction.converteEntityToDto(paises));
		return ResponseEntity.ok(response);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<PaisesDto>> update(
			@PathVariable Long id, @Valid @RequestBody PaisesDto dto,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Update Pais, id: {}, dto: {}", id, dto.toString());
		Response<PaisesDto> response = new Response<PaisesDto>();
		
		paisesFunction.validaUpdate(id, dto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de Paises: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		Paises paises = paisesFunction.convertDtoToPaises(dto);
		paises = paisesService.save(paises);
		response.setData(paisesFunction.converteEntityToDto(paises));
		return ResponseEntity.ok(response);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Boolean>> deleteById(@PathVariable Long id) {
		
		log.info("Deletando Pais pelo id: {}", id);		
		Response<Boolean> response = new Response<Boolean>();		
		Optional<Paises> paises = paisesService.findById(id);
		
		if (!paises.isPresent()) {
			response.getErrors().add("Pais não encontrado");
			return ResponseEntity.badRequest().body(response);	
		}
		
		Boolean resp = paisesService.deleteById(id);
		response.setData(resp);
		return ResponseEntity.ok(response);	
	}	
}
