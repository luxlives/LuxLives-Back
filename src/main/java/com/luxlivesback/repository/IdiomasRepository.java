package com.luxlivesback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.luxlivesback.model.Idiomas;

@Transactional(readOnly = true)
public interface IdiomasRepository extends JpaRepository<Idiomas, Long> {
	
	List<Idiomas> findAllByOrderByLingua();
	
	Idiomas findByLinguaIgnoreCase(String lingua);
	
}
