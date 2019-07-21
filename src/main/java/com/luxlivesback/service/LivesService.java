package com.luxlivesback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.luxlivesback.model.Lives;

public interface LivesService {
	
	Optional<List<Lives>> findAll();
	
	Optional<List<Lives>> findAllLazyToEagerByPage(Pageable pageable);
	
	Optional<List<Lives>> findAllLazyToEagerOrderByIdDescByPage(Pageable pageable);
	
	Optional<List<Lives>> findAllLazyToEagerOrderByTituloByPage(Pageable pageable);
	
	Optional<List<Lives>> findAllLazyToEagerOrderByGeneroByPage(Pageable pageable);
	
	Optional<List<Lives>> findAllLazyToEagerOrderByUsuariosByPage(Pageable pageable);
	
	Optional<List<Lives>> findAllLazyToEagerOrderByDataIniDescByPage(Pageable pageable);
	
	Optional<List<Lives>> findAllByTituloLazyToEagerOrderByIdDescByPage(String titulo, Pageable pageable);
	
	Optional<List<Lives>> findAllByGeneroLazyToEagerOrderByIdDescByPage(String genero, Pageable pageable);
	
	Optional<List<Lives>> findAllByUsuariosIdLazyToEagerOrderByIdDescByPage(Long id, Pageable pageable);	
	
	Optional<Lives> findById(Long id);
	
	Optional<Lives> findByIdLazyToEager(Long id);
	
	Lives save(Lives lives);
	
	Boolean deleteById(Long id);

}
