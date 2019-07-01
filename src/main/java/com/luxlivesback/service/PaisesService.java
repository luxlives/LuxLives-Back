package com.luxlivesback.service;

import java.util.List;
import java.util.Optional;

import com.luxlivesback.model.Paises;

public interface PaisesService {
	
	Optional<List<Paises>> findAll();
	
	Optional<List<Paises>> findAllByOrderByNome();	
	
	Optional<Paises> findById(Long id);
	
	Optional<Paises> findByNomeIgnoreCase(String nome);
	
	Paises save(Paises paises);
	
	Boolean deleteById(Long id);

}
