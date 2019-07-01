package com.luxlivesback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.luxlivesback.model.HistoricoLives;

@Transactional(readOnly = true)
public interface HistoricoLivesRepository extends JpaRepository<HistoricoLives, Long> {
	

}
