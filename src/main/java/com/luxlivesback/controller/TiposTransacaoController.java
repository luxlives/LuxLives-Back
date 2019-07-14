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

import com.luxlivesback.dto.TiposTransacaoDto;
import com.luxlivesback.function.TiposTransacaoFunction;
import com.luxlivesback.model.TiposTransacao;
import com.luxlivesback.response.Response;
import com.luxlivesback.response.ResponseList;
import com.luxlivesback.service.TiposTransacaoService;

@RestController
@RequestMapping("/tiposTransacao")
@CrossOrigin("${origem-permitida}")
public class TiposTransacaoController {
	
	private static final Logger log = LoggerFactory.getLogger(TiposTransacaoController.class);
	
	@Autowired
	private TiposTransacaoService tiposTransacaoService;
	
	@Autowired
	private TiposTransacaoFunction tiposTransacaoFunction;
	
	@GetMapping
	public ResponseEntity<ResponseList<TiposTransacaoDto>> findAll() {
		
		log.info("Buscando lista de TiposTransacao");
		ResponseList<TiposTransacaoDto> response = new ResponseList<TiposTransacaoDto>();
		Optional<List<TiposTransacao>> tiposTransacao = tiposTransacaoService.findAllByOrderByNome();
		List<TiposTransacaoDto> dto = new ArrayList<TiposTransacaoDto>();
		tiposTransacao.get().forEach(x -> dto.add(tiposTransacaoFunction.convertEntityToDto(x)));
		response.setData(dto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<TiposTransacaoDto>> findById(@PathVariable Long id) {
		
		log.info("Buscando TipoTransacao pelo id: {}", id);
		Response<TiposTransacaoDto> response = new Response<TiposTransacaoDto>();
		Optional<TiposTransacao> tiposTransacao = tiposTransacaoService.findById(id);
		
		if (!tiposTransacao.isPresent()) {
			response.getErrors().add("TipoTransacao não encontrado");
			return ResponseEntity.badRequest().body(response);	
		}
		
		response.setData(tiposTransacaoFunction.convertEntityToDto(tiposTransacao.get()));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<TiposTransacaoDto>> save(@Valid @RequestBody TiposTransacaoDto dto,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Persistir TipoTransacao: {}", dto.toString());
		Response<TiposTransacaoDto> response = new Response<TiposTransacaoDto>();
		
		tiposTransacaoFunction.validaCad(dto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de TiposTransacao: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		TiposTransacao tiposTransacao = tiposTransacaoFunction.convertDtoToEntity(dto);
		tiposTransacao = tiposTransacaoService.save(tiposTransacao);
		response.setData(tiposTransacaoFunction.convertEntityToDto(tiposTransacao));
		return ResponseEntity.ok(response);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<TiposTransacaoDto>> update(
			@PathVariable Long id, @Valid @RequestBody TiposTransacaoDto dto,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Update TipoTransacao, id: {}, dto: {}", id, dto.toString());
		Response<TiposTransacaoDto> response = new Response<TiposTransacaoDto>();
		
		tiposTransacaoFunction.validaUpdate(id, dto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de TiposTransacao: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		TiposTransacao tiposTransacao = tiposTransacaoFunction.convertDtoToEntity(dto);
		tiposTransacao = tiposTransacaoService.save(tiposTransacao);
		response.setData(tiposTransacaoFunction.convertEntityToDto(tiposTransacao));
		return ResponseEntity.ok(response);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Boolean>> deleteById(@PathVariable Long id) {
		
		log.info("Deletando TiposTransacao pelo id: {}", id);		
		Response<Boolean> response = new Response<Boolean>();
		Optional<TiposTransacao> tiposTransacao = tiposTransacaoService.findById(id);
		
		if (!tiposTransacao.isPresent()) {
			response.getErrors().add("TipoTransacao não encontrado");
			return ResponseEntity.badRequest().body(response);	
		}
		
		Boolean resp = tiposTransacaoService.deleteById(id);
		response.setData(resp);
		return ResponseEntity.ok(response);			
	}
}
