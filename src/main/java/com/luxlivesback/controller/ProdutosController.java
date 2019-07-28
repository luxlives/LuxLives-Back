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

import com.luxlivesback.dto.ProdutosDto;
import com.luxlivesback.function.ProdutosFunction;
import com.luxlivesback.model.Produtos;
import com.luxlivesback.response.Response;
import com.luxlivesback.response.ResponseList;
import com.luxlivesback.service.ProdutosService;

@RestController
@RequestMapping("/produtos")
@CrossOrigin("${origem-permitida}")
public class ProdutosController {
	
	private static final Logger log = LoggerFactory.getLogger(ProdutosController.class);
	
	@Autowired
	private ProdutosService produtosService;
	
	@Autowired
	private ProdutosFunction produtosFunction;
	
	@Value("${paginacao.qtd_por_pagina_produtos}")
	private int qtdPorPagina;
	
	@GetMapping
	public ResponseEntity<ResponseList<ProdutosDto>> findAll() {
		
		log.info("Buscando lista de Produtos");
		ResponseList<ProdutosDto> response = new ResponseList<ProdutosDto>();
		Optional<List<Produtos>> produtos = produtosService.findAll();
		List<ProdutosDto> dto = new ArrayList<ProdutosDto>();
		produtos.get().forEach(x -> dto.add(produtosFunction.convertEntityToDto(x)));
		response.setData(dto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/ordem/{coluna}/{status}/pag/{pag}")
	public ResponseEntity<ResponseList<ProdutosDto>> findAllOrderByPage(@PathVariable String coluna, @PathVariable Boolean status, @PathVariable int pag) {
		
		log.info("Buscando lista de Produtos em ordem: {}, status: {}, pag: {}", coluna, status, pag);
		ResponseList<ProdutosDto> response = new ResponseList<ProdutosDto>();
		
		List<Produtos> produtos = null;
		switch(coluna) {
			case "id": produtos = produtosService.findAllByStatusOrderByIdAscByPage(status, PageRequest.of(pag, qtdPorPagina)).get(); break;	
			case "nome": produtos = produtosService.findAllByStatusOrderByNomeAscByPage(status, PageRequest.of(pag, qtdPorPagina)).get(); break;
			case "nomedesc": produtos = produtosService.findAllByStatusOrderByNomeDescByPage(status, PageRequest.of(pag, qtdPorPagina)).get(); break;
			case "token": produtos = produtosService.findAllByStatusOrderByQtdTokensAscByPage(status, PageRequest.of(pag, qtdPorPagina)).get(); break;	
			case "tokendesc": produtos = produtosService.findAllByStatusOrderByQtdTokensDescByPage(status, PageRequest.of(pag, qtdPorPagina)).get(); break;	
			case "preco": produtos = produtosService.findAllByStatusOrderByPrecoReaisAscByPage(status, PageRequest.of(pag, qtdPorPagina)).get(); break;	
			case "precodesc": produtos = produtosService.findAllByStatusOrderByPrecoReaisDescByPage(status, PageRequest.of(pag, qtdPorPagina)).get(); break;	
			default:
				response.getErrors().add("Tipo de ordem não existe");
				return ResponseEntity.badRequest().body(response);	
		}
		
		List<ProdutosDto> dto = new ArrayList<ProdutosDto>();
		produtos.forEach(x -> dto.add(produtosFunction.convertEntityToDto(x)));
		response.setData(dto);
		return ResponseEntity.ok(response);		
	}
	
	@GetMapping("/nome/{nome}/ordem/{coluna}/{status}/pag/{pag}")
	public ResponseEntity<ResponseList<ProdutosDto>> findAllByNomeOrderByPage(
			@PathVariable String nome, @PathVariable String coluna, 
			@PathVariable Boolean status, @PathVariable int pag) {
		
		log.info("Buscando lista de Produtos pelo nome: {} em ordem: {}, status: {}, pag: {}", nome, coluna, status, pag);
		ResponseList<ProdutosDto> response = new ResponseList<ProdutosDto>();
		
		List<Produtos> produtos = null;
		switch(coluna) {
			case "id": produtos = produtosService.findAllByNomeAndStatusOrderByIdAscByPage(nome ,status, PageRequest.of(pag, qtdPorPagina)).get(); break;	
			case "nome": produtos = produtosService.findAllByNomeAndStatusOrderByNomeAscByPage(nome ,status, PageRequest.of(pag, qtdPorPagina)).get(); break;
			case "nomedesc": produtos = produtosService.findAllByNomeAndStatusOrderByNomeDescByPage(nome ,status, PageRequest.of(pag, qtdPorPagina)).get(); break;
			case "token": produtos = produtosService.findAllByNomeAndStatusOrderByQtdTokensAscByPage(nome ,status, PageRequest.of(pag, qtdPorPagina)).get(); break;	
			case "tokendesc": produtos = produtosService.findAllByNomeAndStatusOrderByQtdTokensDescByPage(nome ,status, PageRequest.of(pag, qtdPorPagina)).get(); break;	
			case "preco": produtos = produtosService.findAllByNomeAndStatusOrderByPrecoReaisAscByPage(nome ,status, PageRequest.of(pag, qtdPorPagina)).get(); break;	
			case "precodesc": produtos = produtosService.findAllByNomeAndStatusOrderByPrecoReaisDescByPage(nome ,status, PageRequest.of(pag, qtdPorPagina)).get(); break;	
			default:
				response.getErrors().add("Tipo de ordem não existe");
				return ResponseEntity.badRequest().body(response);	
		}
		
		List<ProdutosDto> dto = new ArrayList<ProdutosDto>();
		produtos.forEach(x -> dto.add(produtosFunction.convertEntityToDto(x)));
		response.setData(dto);
		return ResponseEntity.ok(response);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<ProdutosDto>> findById(@PathVariable Long id) {
		
		log.info("Buscando lista de Produtos");
		Response<ProdutosDto> response = new Response<ProdutosDto>();
		Optional<Produtos> produtos = produtosService.findById(id);
		
		if (!produtos.isPresent()) {
			response.getErrors().add("Produtos não encontrado");
			return ResponseEntity.badRequest().body(response);	
		}		
		
		response.setData(produtosFunction.convertEntityToDto(produtos.get()));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<ProdutosDto>> save(@Valid @RequestBody ProdutosDto dto,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Persistindo o Produto: {}", dto.toString());
		Response<ProdutosDto> response = new Response<ProdutosDto>();
		
		produtosFunction.validaCad(dto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de Produtos: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		Produtos produtos = produtosFunction.convertDtoToEntity(dto);
		produtos = produtosService.save(produtos);
		response.setData(produtosFunction.convertEntityToDto(produtos));
		return ResponseEntity.ok(response);
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<ProdutosDto>> save(
			@PathVariable Long id, @Valid @RequestBody ProdutosDto dto,
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Update Produto, id: {}, dto: {}", id, dto.toString());
		Response<ProdutosDto> response = new Response<ProdutosDto>();
		
		produtosFunction.validaUpdate(id, dto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de Produtos: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		Produtos produtos = produtosFunction.convertDtoToEntity(dto);
		produtos = produtosService.save(produtos);
		response.setData(produtosFunction.convertEntityToDto(produtos));
		return ResponseEntity.ok(response);		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Boolean>> deleteById(@PathVariable Long id) {
		
		log.info("Deletando Produto id: {}", id);
		Response<Boolean> response = new Response<Boolean>();
		Optional<Produtos> produtos = produtosService.findById(id);
		
		if (!produtos.isPresent()) {
			response.getErrors().add("Produtos não encontrado");
			return ResponseEntity.badRequest().body(response);	
		}		
		
		Boolean resp = produtosService.deleteById(id);
		response.setData(resp);
		return ResponseEntity.ok(response);
	}

}
