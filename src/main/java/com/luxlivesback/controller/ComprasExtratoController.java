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

import com.luxlivesback.dto.ComprasExtratoDto;
import com.luxlivesback.function.ComprasExtratoFunction;
import com.luxlivesback.model.ComprasExtrato;
import com.luxlivesback.response.Response;
import com.luxlivesback.response.ResponseList;
import com.luxlivesback.service.ComprasExtratoService;

@RestController
@RequestMapping("/comprasExtrato")
@CrossOrigin("${origem-permitida}")
public class ComprasExtratoController {
	
	private static final Logger log = LoggerFactory.getLogger(ComprasExtratoController.class);
	
	@Autowired
	private ComprasExtratoService comprasExtratoService;
	
	@Autowired
	private ComprasExtratoFunction comprasExtratoFunction;
	
	@Value("${paginacao.qtd_por_pagina_compras_extrato}")
	private int qtdPorPagina;
	
	@GetMapping("/ordem/{coluna}/pag/{pag}")
	public ResponseEntity<ResponseList<ComprasExtratoDto>> findAllOrderByPage(@PathVariable String coluna, @PathVariable int pag) {
		
		log.info("Buscar lista de ComprasExtrato, pag: {}", pag);
		ResponseList<ComprasExtratoDto> response = new ResponseList<ComprasExtratoDto>();
		
		List<ComprasExtrato> comprasExtrato = null;
		switch(coluna) {
			case "id": comprasExtrato = comprasExtratoService.findAllLazyToEagerByPage(PageRequest.of(pag, qtdPorPagina)).get(); break;
			case "data": comprasExtrato = comprasExtratoService.findAllLazyToEagerOrderByDataTransacaoDescByPage(PageRequest.of(pag, qtdPorPagina)).get(); break;
			default:
				response.getErrors().add("Tipo de ordem não existe");
				return ResponseEntity.badRequest().body(response);			
		}
		
		List<ComprasExtratoDto> dto = new ArrayList<ComprasExtratoDto>();
		comprasExtrato.forEach(x -> dto.add(comprasExtratoFunction.convertEntityToDto(x)));
		response.setData(dto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/user/{user_id}/{coluna}/{coluna_id}/pag/{pag}")
	public ResponseEntity<ResponseList<ComprasExtratoDto>> findAllByNomeAndColumnIdOrderByDataTransacaoDescByPage(
			 @PathVariable Long user_id, @PathVariable String coluna, 
			 @PathVariable Long coluna_id, @PathVariable int pag) {
		
		log.info("Buscar lista de ComprasExtrato do usuario id: {}, {} id: {}, pag: {}", user_id, coluna, coluna_id, pag);
		ResponseList<ComprasExtratoDto> response = new ResponseList<ComprasExtratoDto>();
		
		String erro = comprasExtratoFunction.validaUsuarioAndProdutoOrTipoTransacao(user_id, coluna, coluna_id);
		if (erro.length() > 0) {
			response.getErrors().add(erro);
			return ResponseEntity.badRequest().body(response);	
		}
		
		List<ComprasExtrato> comprasExtrato = null;
		switch(coluna) {
			case "produto": comprasExtrato = comprasExtratoService.findAllByUsuariosIdAndProdutosIdLazyToEagerOrderByDataTransacaoDescByPage(user_id, coluna_id, PageRequest.of(pag, qtdPorPagina)).get(); break;
			case "tipoTransacao": comprasExtrato = comprasExtratoService.findAllByUsuariosIdAndTipoTransacaoIdLazyToEagerOrderByDataTransacaoDescByPage(user_id, coluna_id, PageRequest.of(pag, qtdPorPagina)).get(); break;
			default:
				response.getErrors().add("Tipo de ordem não existe");
				return ResponseEntity.badRequest().body(response);		
		}
		
		List<ComprasExtratoDto> dto = new ArrayList<ComprasExtratoDto>();
		comprasExtrato.forEach(x -> dto.add(comprasExtratoFunction.convertEntityToDto(x)));
		response.setData(dto);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<ComprasExtratoDto>> findById(@PathVariable Long id) {
		
		log.info("Buscando ComprasExtrato pelo id: {}", id);
		Response<ComprasExtratoDto> response = new Response<ComprasExtratoDto>();		
		Optional<ComprasExtrato> comprasExtrato = comprasExtratoService.findById(id);
		
		if (!comprasExtrato.isPresent()) {
			response.getErrors().add("ComprasExtrato não encontrado");
			return ResponseEntity.badRequest().body(response);	
		}
		
		response.setData(comprasExtratoFunction.convertEntityToDto(comprasExtrato.get()));
		return ResponseEntity.ok(response);		
	}
	
	@PostMapping
	public ResponseEntity<Response<ComprasExtratoDto>> save(@Valid @RequestBody ComprasExtratoDto dto, 
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Persistindo ComprasExtrato: {}", dto.toString());
		Response<ComprasExtratoDto> response = new Response<ComprasExtratoDto>();
		
		comprasExtratoFunction.validaUsuarioAndProdutoAndTipoTransacao(dto.getUsuariosId(), dto.getProdutosId(), dto.getTipoTransacaoId(), result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de ComprasExtrato: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		ComprasExtrato comprasExtrato = comprasExtratoFunction.convertDtoToEntity(dto);
		comprasExtrato = comprasExtratoService.save(comprasExtrato);
		response.setData(comprasExtratoFunction.convertEntityToDto(comprasExtrato));
		return ResponseEntity.ok(response);		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<ComprasExtratoDto>> update(
			@PathVariable Long id, @Valid @RequestBody ComprasExtratoDto dto, 
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Persistindo ComprasExtrato: {}", dto.toString());
		Response<ComprasExtratoDto> response = new Response<ComprasExtratoDto>();
		
		comprasExtratoFunction.validaUpdate(id, dto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de ComprasExtrato: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		ComprasExtrato comprasExtrato = comprasExtratoFunction.convertDtoToEntity(dto);
		comprasExtrato = comprasExtratoService.save(comprasExtrato);
		response.setData(comprasExtratoFunction.convertEntityToDto(comprasExtrato));
		return ResponseEntity.ok(response);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Boolean>> deleteById(@PathVariable Long id) {
		
		log.info("Deletando ComprasExtrato pelo id: {}", id);
		Response<Boolean> response = new Response<Boolean>();		
		Optional<ComprasExtrato> comprasExtrato = comprasExtratoService.findById(id);
		
		if (!comprasExtrato.isPresent()) {
			response.getErrors().add("ComprasExtrato não encontrado");
			return ResponseEntity.badRequest().body(response);	
		}
		
		Boolean resp = comprasExtratoService.deleteById(id);
		response.setData(resp);
		return ResponseEntity.ok(response);		
	}

}
