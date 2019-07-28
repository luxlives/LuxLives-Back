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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxlivesback.dto.TagsLivesDto;
import com.luxlivesback.function.TagsLivesFunction;
import com.luxlivesback.model.TagsLives;
import com.luxlivesback.response.Response;
import com.luxlivesback.response.ResponseList;
import com.luxlivesback.service.TagsLivesService;

@RestController
@RequestMapping("/tagsLives")
@CrossOrigin("${origem-permitida}")
public class TagsLivesController {
	
	private static final Logger log = LoggerFactory.getLogger(TagsLivesController.class);
	
	@Autowired
	private TagsLivesService tagsLivesService;
	
	@Autowired
	private TagsLivesFunction tagsLivesFunction;
	
	@Value("${paginacao.qtd_por_pagina_tags_lives}")
	private int qtdPorPagina;
	
	
	private static final String ERRO_TIPO_DE_BUSCA = "Tipo de busca não existe";
	private static final String ERRO_TIPO_DE_PAGE = "Tipo de paginação não existe";
	
	/**
	 * Busca com paginação.
	 * localhost:8080/LuxLives/tagsLives/{coluna}/{id}/{paginacao}/{pag}
	 * localhost:8080/LuxLives/tagsLives/none-order-lives/0/pag/0 -> busca uma lista geral por order de lives, pag 0, 1, 2...
	 * localhost:8080/LuxLives/tagsLives/none-order-tags/0/pag/0 -> busca uma lista geral por order de tags, pag 0, 1, 2...
	 * localhost:8080/LuxLives/tagsLives/lives/1/pag/0 -> busca uma lista passando como parâmetro live_id 1 por order de tags, pag 0, 1, 2...
	 * localhost:8080/LuxLives/tagsLives/tags/1/pag/0 -> busca uma lista passando como parâmetro tags_id 1 por order de lives, pag 0, 1, 2...
	 * 
	 * Busca sem paginação.
	 * localhost:8080/LuxLives/tagsLives/none-order-lives/0/no-pag/0 -> busca uma lista geral por order de lives
	 * localhost:8080/LuxLives/tagsLives/none-order-tags/0/no-pag/0 -> busca uma lista geral por order de tags
	 * localhost:8080/LuxLives/tagsLives/lives/1/no-pag/0 -> busca uma lista passando como parâmetro live_id 1 por order de tags
	 * localhost:8080/LuxLives/tagsLives/tags/1/no-pag/0 -> busca uma lista passando como parâmetro tags_id 1 por order de lives
	 * 
	 * OBS1: Em buscas sem paginação, é obrigatório passar um inteiro qualquer como parâmetro em {pag} para não ocorrer erro;
	 * OBS2: Em buscas de lista geral, é obrigatorio passar um inteiro qualquer como parâmetro em {id} para não ocorrer erro.
	 * */
	
	@GetMapping("/{coluna}/{id}/{paginacao}/{pag}")
	public ResponseEntity<ResponseList<TagsLivesDto>> teste(
			@PathVariable String coluna, @PathVariable Long id, 
			@PathVariable String paginacao, @PathVariable int pag) {
		
		log.info("Buscar lista de ComprasExtrato, pag: {}", pag);
		ResponseList<TagsLivesDto> response = new ResponseList<TagsLivesDto>();
		
		List<TagsLives> tagsLives = null;
		switch (paginacao) {		
			case "pag":
				switch (coluna) {		
					case "none-order-lives": tagsLives = tagsLivesService.findAllLazyToEagerOrderByLivesByPage(PageRequest.of(pag, qtdPorPagina)).get(); break;
					case "none-order-tags": tagsLives = tagsLivesService.findAllLazyToEagerOrderByTagsByPage(PageRequest.of(pag, qtdPorPagina)).get(); break;
					case "lives": tagsLives = tagsLivesService.findAllByLivesIdLazyToEagerOrderByTagsByPage(id, PageRequest.of(pag, qtdPorPagina)).get(); break;		
					case "tags": tagsLives = tagsLivesService.findAllByTagsIdLazyToEagerOrderByLivesByPage(id, PageRequest.of(pag, qtdPorPagina)).get(); break;
					default:
						response.getErrors().add(ERRO_TIPO_DE_BUSCA);
						return ResponseEntity.badRequest().body(response);		
				}
			break;			
			case "no-pag":
				switch (coluna) {		
					case "none-order-lives": tagsLives = tagsLivesService.findAllLazyToEagerOrderByLives().get(); break;
					case "none-order-tags": tagsLives = tagsLivesService.findAllLazyToEagerOrderByTags().get(); break;
					case "lives": tagsLives = tagsLivesService.findAllByLivesIdLazyToEagerOrderByTags(id).get(); break;		
					case "tags": tagsLives = tagsLivesService.findAllByTagsIdLazyToEagerOrderByLives(id).get(); break;
					default:
						response.getErrors().add(ERRO_TIPO_DE_BUSCA);
						return ResponseEntity.badRequest().body(response);		
				}
			break;
			default:
				response.getErrors().add(ERRO_TIPO_DE_PAGE);
				return ResponseEntity.badRequest().body(response);				
		}
		
		List<TagsLivesDto> dto = new ArrayList<TagsLivesDto>();
		tagsLives.forEach(x -> dto.add(tagsLivesFunction.convertEntityToDto(x)));
		response.setData(dto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{liveId}/{tagId}")
	public ResponseEntity<Response<TagsLivesDto>> findByLiveIdAndTagId(@PathVariable Long liveId, @PathVariable Long tagId) {
		
		log.info("Buscando TagsLives, live_id: {}, tag_id", liveId, tagId);
		Response<TagsLivesDto> response = new Response<TagsLivesDto>();
		Optional<TagsLives> tagsLives = tagsLivesService.findByLivesIdAndTagsIdLazyToEager(liveId, tagId);
		
		if (!tagsLives.isPresent()) {
			response.getErrors().add("ComprasExtrato não encontrado");
			return ResponseEntity.badRequest().body(response);	
		}		
		
		response.setData(tagsLivesFunction.convertEntityToDto(tagsLives.get()));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<TagsLivesDto>> save(@Valid @RequestBody TagsLivesDto dto, 
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Persistindo TagsLives: {}", dto.toString());
		Response<TagsLivesDto> response = new Response<TagsLivesDto>();
		
		tagsLivesFunction.validaCad(dto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de TagsLives: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		TagsLives tagsLives = tagsLivesFunction.convertDtoToEntity(dto);
		tagsLives = tagsLivesService.save(tagsLives);
		response.setData(tagsLivesFunction.convertEntityToDto(tagsLives));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{liveId}/{tagId}")
	public ResponseEntity<Response<Boolean>> deleteByLiveIdAndTagId(@PathVariable Long liveId, @PathVariable Long tagId) {
		
		log.info("Deletando TagsLives, live_id: {}, tag_id", liveId, tagId);
		Response<Boolean> response = new Response<Boolean>();		
		
		if (!tagsLivesService.findByLivesIdAndTagsIdLazyToEager(liveId, tagId).isPresent()) {
			response.getErrors().add("ComprasExtrato não encontrado");
			return ResponseEntity.badRequest().body(response);	
		}		
		
		Boolean x = tagsLivesService.deleteByLivesIdAndTagsId(liveId, tagId);
		response.setData(x);
		return ResponseEntity.ok(response);
	}

}
