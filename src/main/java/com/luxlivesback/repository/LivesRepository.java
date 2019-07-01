package com.luxlivesback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.luxlivesback.model.Lives;

@Transactional(readOnly = true)
public interface LivesRepository extends JpaRepository<Lives, Long> {
	
}
