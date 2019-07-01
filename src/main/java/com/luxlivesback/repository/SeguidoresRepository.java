package com.luxlivesback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.luxlivesback.model.Seguidores;

@Transactional(readOnly = true)
public interface SeguidoresRepository extends JpaRepository<Seguidores, Long> {
	
}
