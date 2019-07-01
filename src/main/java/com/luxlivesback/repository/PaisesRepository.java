package com.luxlivesback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.luxlivesback.model.Paises;

@Transactional(readOnly = true)
public interface PaisesRepository extends JpaRepository<Paises, Long> {
	
	List<Paises> findAllByOrderByNome();
	
	Paises findByNomeIgnoreCase(String nome);
	
}
