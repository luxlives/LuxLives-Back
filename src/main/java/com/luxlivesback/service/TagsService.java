package com.luxlivesback.service;

import java.util.List;
import java.util.Optional;

import com.luxlivesback.model.Tags;

public interface TagsService {
	
	Optional<List<Tags>> findAll();
	
	Optional<List<Tags>> findAllByOrderByNome();
	
	Optional<List<Tags>> findAllByNomeStartingWithIgnoreCaseOrderByNome(String nome);	
	
	Optional<Tags> findById(Long id);
	
	Optional<Tags> findByNomeIgnoreCase(String nome);
	
	Tags save(Tags tags);
	
	Boolean deleteById(Long id);

}
