package com.luxlivesback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.luxlivesback.model.Idiomas;

@Transactional(readOnly = true)
public interface IdiomasRepository extends JpaRepository<Idiomas, Long> {
	
}
