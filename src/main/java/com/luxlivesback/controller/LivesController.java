package com.luxlivesback.controller;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
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

import com.luxlivesback.dto.LivesDto;
import com.luxlivesback.function.LivesFunction;
import com.luxlivesback.model.Lives;
import com.luxlivesback.model.Usuarios;
import com.luxlivesback.repository.LivesRepository;
import com.luxlivesback.response.Response;
import com.luxlivesback.response.ResponseList;
import com.luxlivesback.service.LivesService;
import com.luxlivesback.service.UsuariosService;

@RestController
@RequestMapping("/lives")
@CrossOrigin("${origem-permitida}")
public class LivesController {
	
	private static final Logger log = LoggerFactory.getLogger(LivesController.class);
	
	@Autowired
	private LivesService livesService;
	
	@Autowired
	private UsuariosService usuariosService;
	
	@Autowired
	private LivesFunction livesFunction;
	
	@Autowired
	private LivesRepository livesRepository;
	
	@Value("${paginacao.qtd_por_pagina}")
	private int qtdPorPagina;
	
	@GetMapping("/pag/{pag}")
	public ResponseEntity<ResponseList<LivesDto>> findAllLazyToEagerByPage(@PathVariable int pag) {
		
		log.info("Buscar lista de lives, pag: {}", pag);		
		ResponseList<LivesDto> response = new ResponseList<LivesDto>();
		List<Lives> lives = livesService.findAllLazyToEagerByPage(PageRequest.of(pag, qtdPorPagina)).get();
		List<LivesDto> dto = new ArrayList<LivesDto>();
		lives.forEach(x -> dto.add(livesFunction.convertEntityToDto(x)));
		response.setData(dto);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Esse método busca um lista, ao qual a ordem de sequencia é passado por parâmetro pelo nome da coluna.
	 * 
	 * localhost:8080/LuxLives/lives/ordem/{coluna}/pag/{pag}
	 * localhost:8080/LuxLives/lives/ordem/idasc/pag/{pag}
	 * localhost:8080/LuxLives/lives/ordem/iddesc/pag/{pag}
	 * localhost:8080/LuxLives/lives/ordem/data/pag/{pag}
	 * localhost:8080/LuxLives/lives/ordem/titulo/pag/{pag}
	 * localhost:8080/LuxLives/lives/ordem/genero/pag/{pag}
	 * localhost:8080/LuxLives/lives/ordem/usuario/pag/{pag}
	 * 
	 * */
	
	@GetMapping("/ordem/{coluna}/pag/{pag}")
	public ResponseEntity<ResponseList<LivesDto>> findAllOrderByPage(@PathVariable String coluna, @PathVariable int pag) {
		
		log.info("Buscar lista de lives em ordem: {}, pag: {}", coluna, pag);		
		ResponseList<LivesDto> response = new ResponseList<LivesDto>();
		
		List<Lives> lives = null;		
		switch(coluna) {		
			case "idasc": lives = livesService.findAllLazyToEagerByPage(PageRequest.of(pag, qtdPorPagina)).get(); break;
			case "iddesc": lives = livesService.findAllLazyToEagerOrderByIdDescByPage(PageRequest.of(pag, qtdPorPagina)).get(); break;
			case "data": lives = livesService.findAllLazyToEagerOrderByDataIniDescByPage(PageRequest.of(pag, qtdPorPagina)).get();	 break;	
			case "titulo": lives = livesService.findAllLazyToEagerOrderByTituloByPage(PageRequest.of(pag, qtdPorPagina)).get(); break;
			case "genero": lives = livesService.findAllLazyToEagerOrderByGeneroByPage(PageRequest.of(pag, qtdPorPagina)).get(); break;
			case "usuario": lives = livesService.findAllLazyToEagerOrderByUsuariosByPage(PageRequest.of(pag, qtdPorPagina)).get();	 break;	
			default:
				response.getErrors().add("Tipo de ordem não existe");
				return ResponseEntity.badRequest().body(response);	
		}
		
		List<LivesDto> dto = new ArrayList<LivesDto>();
		lives.forEach(x -> dto.add(livesFunction.convertEntityToDto(x)));
		response.setData(dto);
		return ResponseEntity.ok(response);
	}
	
	/**
	 * Esse método pode realizar três tipos diferentes de requisição, dependendo o nome da coluna inserida.
	 * 
	 * localhost:8080/LuxLives/lives/busca/{coluna}/{valor}/pag/{pag}
	 * localhost:8080/LuxLives/lives/busca/titulo/{valor}/pag/{pag}
	 * localhost:8080/LuxLives/lives/busca/genero/{valor}/pag/{pag}
	 * localhost:8080/LuxLives/lives/busca/login/{valor}/pag/{pag}
	 * 
	 * */
	
	@GetMapping("/busca/{coluna}/{valor}/pag/{pag}")
	public ResponseEntity<ResponseList<LivesDto>> teste2(@PathVariable String coluna, @PathVariable String valor, @PathVariable int pag) {
		
		log.info("Buscar lista de lives pela coluna: {}, valor: {}, pag: {}", coluna, valor, pag);		
		ResponseList<LivesDto> response = new ResponseList<LivesDto>();
		
		List<Lives> lives = null;
		switch(coluna) {	
			case "titulo": lives = livesRepository.findAllByTituloLazyToEagerOrderByIdDescByPage(valor, PageRequest.of(pag, qtdPorPagina));break;
			case "genero": lives = livesRepository.findAllByGeneroLazyToEagerOrderByIdDescByPage(valor, PageRequest.of(pag, qtdPorPagina));break;
			case "login" :
				
				Optional<Usuarios> usuario = usuariosService.findByLogin(valor);
				if (!usuario.isPresent()) {
					response.getErrors().add("Usuario não encontrado");
					return ResponseEntity.badRequest().body(response);	
				}
				
				lives = livesRepository.findAllByUsuariosIdLazyToEagerOrderByIdDescByPage(usuario.get().getId(), PageRequest.of(pag, qtdPorPagina));
				break;
				
			default:
				response.getErrors().add("Tipo de busca não existe");
				return ResponseEntity.badRequest().body(response);	
		}
		
		List<LivesDto> dto = new ArrayList<LivesDto>();
		lives.forEach(x -> dto.add(livesFunction.convertEntityToDto(x)));
		response.setData(dto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<LivesDto>> findById(@PathVariable Long id) {
		
		log.info("Buscar usuário pelo id: {}", id);		
		Response<LivesDto> response = new Response<LivesDto>();
		Optional<Lives> lives = livesService.findById(id);
		
		if (!lives.isPresent()) {
			response.getErrors().add("Lives não encontrado");
			return ResponseEntity.badRequest().body(response);	
		}
		
		response.setData(livesFunction.convertEntityToDto(lives.get()));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<LivesDto>> save(@Valid @RequestBody LivesDto dto,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Persistindo lives: {}", dto.toString());
		Response<LivesDto> response = new Response<LivesDto>();
		
		livesFunction.validaCad(dto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de Lives: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		Lives lives = livesFunction.convertDtoToEntity(dto);
		lives = livesService.save(lives);
		response.setData(livesFunction.convertEntityToDto(lives));
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<LivesDto>> update(
			@PathVariable Long id, @Valid @RequestBody LivesDto dto,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Update lives: {}", dto.toString());
		Response<LivesDto> response = new Response<LivesDto>();
		
		livesFunction.validaUpdate(id, dto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de Lives: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		Lives lives = livesFunction.convertDtoToEntity(dto);
		lives = livesService.save(lives);
		response.setData(livesFunction.convertEntityToDto(lives));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Boolean>> deleteById(@PathVariable Long id) {
		
		log.info("Deletando Live pelo id: {}", id);		
		Response<Boolean> response = new Response<Boolean>();	
		Optional<Lives> lives = livesService.findById(id);
		
		if (!lives.isPresent()) {
			response.getErrors().add("Lives não encontrado");
			return ResponseEntity.badRequest().body(response);	
		}
		
		Boolean resp = livesService.deleteById(id);
		response.setData(resp);
		return ResponseEntity.ok(response);
	}
	
	//-------------------------------------------------------------------------------------------------------
	
	@GetMapping("/teste")
	public ResponseEntity<ResponseList<LivesDto>> teste(@PathVariable String titulo, @PathVariable int pag) {
		
		log.info("Buscar lista de lives, pag: {}", pag);		
		ResponseList<LivesDto> response = new ResponseList<LivesDto>();
		List<Lives> lives = livesRepository.findAllByTituloLazyToEagerOrderByIdDescByPage(titulo, PageRequest.of(pag, qtdPorPagina));
		List<LivesDto> dto = new ArrayList<LivesDto>();
		lives.forEach(x -> dto.add(livesFunction.convertEntityToDto(x)));
		response.setData(dto);
		return ResponseEntity.ok(response);
	}

}
