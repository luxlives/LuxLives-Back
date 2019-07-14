package com.luxlivesback.service;

import java.util.List;
import java.util.Optional;

import com.luxlivesback.model.Idiomas;

public interface IdiomasService {
	
	Optional<List<Idiomas>> findAll();

	Optional<List<Idiomas>> findAllByOrderByLingua();
	
	Optional<Idiomas> findById(Long id);
	
	Optional<Idiomas> findByLinguaIgnoreCase(String lingua);
	
	Idiomas save(Idiomas idiomas);
	
	Boolean deleteById(Long id);
}
