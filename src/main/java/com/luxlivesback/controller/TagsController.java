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

import com.luxlivesback.dto.TagsDto;
import com.luxlivesback.function.TagsFunction;
import com.luxlivesback.model.Tags;
import com.luxlivesback.response.Response;
import com.luxlivesback.response.ResponseList;
import com.luxlivesback.service.TagsService;

@RestController
@RequestMapping("/tags")
@CrossOrigin("${origem-permitida}")
public class TagsController {
	
	private static final Logger log = LoggerFactory.getLogger(TagsController.class);
	
	@Autowired
	private TagsService tagsService;
	
	@Autowired
	private TagsFunction tagsFunction;
	
	@GetMapping
	public ResponseEntity<ResponseList<TagsDto>> findAll() {
		
		log.info("Buscando lista de Tags");		
		ResponseList<TagsDto> response = new ResponseList<TagsDto>();		
		Optional<List<Tags>> tags = tagsService.findAllByOrderByNome();
		List<TagsDto> dto = new ArrayList<TagsDto>();
		tags.get().forEach(x -> dto.add(tagsFunction.convertEntityToDto(x)));
		response.setData(dto);
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Response<TagsDto>> findById(@PathVariable Long id) {
		
		log.info("Buscando Tag pelo id: {}", id);
		
		Response<TagsDto> response = new Response<TagsDto>();
		Optional<Tags> tags = tagsService.findById(id);
		
		if (!tags.isPresent()) {
			response.getErrors().add("Tags não encontrado");
			return ResponseEntity.badRequest().body(response);	
		}
		
		response.setData(tagsFunction.convertEntityToDto(tags.get()));
		return ResponseEntity.ok(response);
	}
	
	@PostMapping
	public ResponseEntity<Response<TagsDto>> save(@Valid @RequestBody TagsDto dto, 
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Persistindo a Tag: {}", dto.toString());
		Response<TagsDto> response = new Response<TagsDto>();
		
		tagsFunction.validaCad(dto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de Tags: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		Tags tags = tagsFunction.convertDtoToEntity(dto);
		tags = tagsService.save(tags);
		response.setData(tagsFunction.convertEntityToDto(tags));
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Response<TagsDto>> update(
			@PathVariable Long id, @Valid @RequestBody TagsDto dto, 
			BindingResult result) throws NoSuchAlgorithmException {
		
		log.info("Update Tag, id: {}, dto: {}", id,  dto.toString());
		Response<TagsDto> response = new Response<TagsDto>();
		
		tagsFunction.validUpdate(id, dto, result);
		
		if (result.hasErrors()) {
			log.error("Erro validando dados de Tags: {}", result.getAllErrors());
			result.getAllErrors().forEach( error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);				
		}
		
		Tags tags = tagsFunction.convertDtoToEntity(dto);
		tags = tagsService.save(tags);
		response.setData(tagsFunction.convertEntityToDto(tags));
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Response<Boolean>> deleteById(@PathVariable Long id) {
		
		log.info("Deletando Tag pelo id: {}", id);
		
		Response<Boolean> response = new Response<Boolean>();
		Optional<Tags> tags = tagsService.findById(id);
		
		if (!tags.isPresent()) {
			response.getErrors().add("Tags não encontrado");
			return ResponseEntity.badRequest().body(response);	
		}
		
		Boolean resp = tagsService.deleteById(id);
		response.setData(resp);
		return ResponseEntity.ok(response);		
	}

}
